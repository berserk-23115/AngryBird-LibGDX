package game.dev.catapult;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class catapult {
    private World world;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture slingshotTexture, projectileTexture, trajectoryPointTexture;
    private ProjectileEquation projectileEquation;
    private Vector2 slingshotPosition;
    private Vector2 projectilePosition;
    private Controller controller;
    private TrajectoryActor trajectoryActor;
    private boolean isDragging;
    private Vector2 dragStart;
    private float timeElapsed;
    private Stage stage;
    public static final float PPM = 100f;
    Body projectileBody;

    public static class Controller {
        public float power = 50f;
        public float angle = 0f;
    }

    public static class TrajectoryActor extends Actor {
        private Controller controller;
        private ProjectileEquation projectileEquation;
        private TextureRegion trajectoryTextureRegion;

        public int trajectoryPointCount = 30;
        public float timeSeparation = 0.2f;

        public TrajectoryActor(Controller controller, float gravity, TextureRegion trajectoryTextureRegion) {
            this.controller = controller;
            this.trajectoryTextureRegion = trajectoryTextureRegion;
            this.projectileEquation = new ProjectileEquation();
            this.projectileEquation.gravity = gravity;
        }

        @Override
        public void act(float delta) {
            super.act(delta);
            projectileEquation.startVelocity.set(controller.power, 0f);
            projectileEquation.startVelocity.setAngleDeg(controller.angle);
        }

        @Override
        public void draw(Batch batch, float parentAlpha) {
            float t = 0f;

            for (int i = 0; i < trajectoryPointCount; i++) {
                // Scale trajectory positions by PPM
                float x = (projectileEquation.getX(t) + getX()) / PPM;
                float y = (projectileEquation.getY(t) + getY()) / PPM + 40 / PPM;

                if (y < 0) break; // Stop rendering if trajectory goes below ground level

                // Draw trajectory points
                batch.setColor(getColor());
                batch.draw(trajectoryTextureRegion, x, y, 10 / PPM, 10 / PPM);

                t += timeSeparation;
            }
        }


        @Override
        public Actor hit(float x, float y, boolean touchable) {
            return null; // No hit detection for trajectory points
        }
    }

    public catapult(Viewport viewport) {
        world = new World(new Vector2(0, -9.8f), true);
        // Initialize batch and camera
        this.projectileEquation = new ProjectileEquation();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);

        // Load textures
        slingshotTexture = new Texture("TileMaps/catapult.png");
        projectileTexture = new Texture("TileMaps/blue.png");
        trajectoryPointTexture = new Texture("libgdx.png");
        slingshotPosition = new Vector2(60, 80);
        projectilePosition = new Vector2(slingshotPosition);
// Define the projectile body
        BodyDef projectileBodyDef = new BodyDef();
        projectileBodyDef.type = BodyDef.BodyType.KinematicBody; // Kinematic initially
        projectileBodyDef.position.set(slingshotPosition.x / PPM, slingshotPosition.y / PPM);

        projectileBody = world.createBody(projectileBodyDef);


        projectileBody = world.createBody(projectileBodyDef);

// Define the projectile shape
        CircleShape projectileShape = new CircleShape();
        projectileShape.setRadius(10 / PPM); // Set radius based on texture size

        FixtureDef projectileFixtureDef = new FixtureDef();
        projectileFixtureDef.shape = projectileShape;
        projectileFixtureDef.density = 1f; // Mass = density × volume
        projectileFixtureDef.friction = 0.5f;
        projectileFixtureDef.restitution = 0.3f; // Bounciness

        projectileBody.createFixture(projectileFixtureDef);
        projectileShape.dispose();
        // Initialize slingshot and projectile positions


        // Initialize controller
        controller = new Controller();
        controller.power = 0f;
        controller.angle = 0f;

        // Create trajectory actor
        TextureRegion trajectoryTextureRegion = new TextureRegion(trajectoryPointTexture);
        trajectoryActor = new TrajectoryActor(controller, -9.8f, trajectoryTextureRegion);
        trajectoryActor.setPosition(slingshotPosition.x, slingshotPosition.y);

        // Setup stage
        stage = new Stage(viewport);
        stage.addActor(trajectoryActor);

        // Input handling
        dragStart = new Vector2();
        isDragging = false;

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                dragStart.set(screenX, Gdx.graphics.getHeight() - screenY);
                isDragging = true;
                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                if (isDragging) {
                    Vector2 dragEnd = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                    float maxDragDistance = 50f; // Maximum dragging distance

                    // Calculate the distance and limit it
                    float distance = dragStart.dst(dragEnd);
                    if (distance > maxDragDistance) {
                        dragEnd = dragStart.cpy().lerp(dragEnd, maxDragDistance / distance);
                    }


                    // Calculate power and angle based on drag
                    controller.power = dragStart.dst(dragEnd) * 3; // Scale power
                    controller.angle = dragEnd.sub(dragStart).angleDeg() - 180;

                    // Update projectile position
                    projectilePosition.set(dragEnd.x + 200, dragEnd.y + 200);
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                isDragging = false;

                // Set projectile equation parameters
                projectileEquation.startVelocity.set(controller.power, 0f);
                projectileEquation.startVelocity.setAngleDeg(controller.angle);

                // Apply launch velocity to the projectileBody
                Vector2 launchVelocity = new Vector2(controller.power, 0f);
                launchVelocity.setAngleDeg(controller.angle);

                // Switch to dynamic body
                projectileBody.setType(BodyDef.BodyType.DynamicBody);
                projectileBody.setLinearVelocity(launchVelocity.scl(1 / PPM)); // Scale velocity by PPM

                return true;
            }

        });
    }

    public void render(float delta) {
        stage.act(delta);
        stage.draw();
        // Update the Box2D world
        world.step(delta, 6, 2);

        // Update projectile position from Box2D body
        Vector2 bodyPosition = projectileBody.getPosition();
        projectilePosition.set(bodyPosition.x * PPM, bodyPosition.y * PPM);

        stage.act(delta);
        stage.draw();

        batch.begin();
        batch.draw(slingshotTexture, slingshotPosition.x, slingshotPosition.y, 40, 100);
        batch.draw(projectileTexture, projectilePosition.x - 10, projectilePosition.y - 10, 20, 20);
        batch.end();
        batch.begin();
        batch.draw(slingshotTexture, slingshotPosition.x , slingshotPosition.y , 40, 100);

        if (timeElapsed >= 0) {
            timeElapsed += delta;
            float x = projectileEquation.getX(timeElapsed);
            float y = projectileEquation.getY(timeElapsed);

            if (y <= 0) {
                timeElapsed = -1;
                projectilePosition.set(slingshotPosition);
            } else {
                projectilePosition.set(x, y);
            }
        }

        batch.draw(projectileTexture, projectilePosition.x - 10, projectilePosition.y + 40, 20, 20);
        batch.end();
    }

    public void dispose() {
        batch.dispose();
        slingshotTexture.dispose();
        projectileTexture.dispose();
        trajectoryPointTexture.dispose();
        stage.dispose();
    }
}
