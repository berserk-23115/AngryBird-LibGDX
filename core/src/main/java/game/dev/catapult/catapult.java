package game.dev.catapult;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.dev.birds.bird;
import game.dev.birds.blue;
import org.checkerframework.checker.units.qual.A;

import java.io.Serializable;
import java.util.ArrayList;

public class catapult implements Serializable {
    private static final long serialVersionUID = 1L;
    private transient World world;
    private transient SpriteBatch batch;
    private transient OrthographicCamera camera;
    private transient Texture slingshotTexture, projectileTexture, trajectoryPointTexture;
    private ProjectileEquation projectileEquation;
    private transient Vector2 slingshotPosition;
    private transient Vector2 projectilePosition;
    private Controller controller;
    private transient TrajectoryActor trajectoryActor;
    private transient boolean isDragging;
    private transient Vector2 dragStart;
    private transient float timeElapsed;
    private transient Stage stage;
    private transient Box2DDebugRenderer debugRenderer; // Debug renderer
    public static final float PPM = 100f;
    transient Body projectileBody;
    private transient ArrayList<bird> avBirdClass=new ArrayList<>();
    private transient ArrayList<Body> chidiyas = new ArrayList<>();
    private transient bird projectBodyClass;
    private long release_time=0;
   // private Integer index=0,indexC=0;
   private int Boolbird=0;
   boolean released=false;


    public static class Controller {
        public float power = 50f;
        public float angle = 0f;
    }
public int getter(){
        return Boolbird;
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
                float x = (projectileEquation.getX(t) + getX()) / PPM;
                float y = (projectileEquation.getY(t) + getY()) / PPM + 40 / PPM;

                if (y < 0) break; // Stop rendering if trajectory goes below ground level

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

    public catapult(Viewport viewport, World duniya, ArrayList<Body> chidiyas, ArrayList<bird> avBirdClass) {

        world = duniya;
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false);
        debugRenderer = new Box2DDebugRenderer();
        this.avBirdClass=avBirdClass;// Initialize debug renderer
        this.chidiyas=chidiyas;

        // Load textures
        slingshotTexture = new Texture("TileMaps/catapult.png");

        trajectoryPointTexture = new Texture("TileMaps/dot.png");

        slingshotPosition = new Vector2(60, 90);
        projectilePosition = new Vector2(60,90);


        // Sirji Position Set for the projectile BIRDS IN RENDER FUNCITON using ArrayList Bodies



        // Initialize projectileEquation
        projectileEquation = new ProjectileEquation();
        projectileEquation.gravity = -9.8f;
        projectileBody= chidiyas.get(avBirdClass.get(0).getIndex());
        projectBodyClass=avBirdClass.get(avBirdClass.get(0).getIndexC());


        // Initialize controller
        controller = new Controller();
        controller.power = 0f;
        controller.angle = 0f;

        // Create trajectory actor
        TextureRegion trajectoryTextureRegion = new TextureRegion(trajectoryPointTexture);
        trajectoryActor = new TrajectoryActor(controller, -9.8f, trajectoryTextureRegion);
        trajectoryActor.setPosition(slingshotPosition.x+15, slingshotPosition.y+20);

        // Setup stage
        stage = new Stage(viewport);
        stage.addActor(trajectoryActor);

        // Input handling
        dragStart = new Vector2();
        isDragging = false;


    }
    public InputProcessor getInputProcessor(){
        Rectangle InputArea = new Rectangle(slingshotPosition.x-50, slingshotPosition.y-50, 150,150);

        InputProcessor processor1 = new InputAdapter(){
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                Vector2 pos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                if(InputArea.contains(pos.x, pos.y)) {
                    System.out.println("Touched Catapult");

                    projectileBody.setTransform((slingshotPosition.x+15)/PPM, (slingshotPosition.y+55)/ PPM,0);
                    dragStart.set(screenX, Gdx.graphics.getHeight() - screenY);
                    isDragging = true;
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                Vector2 pos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                if(InputArea.contains(pos.x, pos.y)) {
                    System.out.println("Dragging Catapult");
                    if (isDragging) {
                        Vector2 dragEnd = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                        float maxDragDistance = 50f; // Maximum dragging distance

                        float distance = dragStart.dst(dragEnd);
                        if (distance > maxDragDistance) {
                            dragEnd = dragStart.cpy().lerp(dragEnd, maxDragDistance / distance);
                        }

                        controller.power = dragStart.dst(dragEnd) * 3; // Scale power
                        controller.angle = dragEnd.sub(dragStart).angleDeg() - 180;

                        projectilePosition.set(dragEnd.x, dragEnd.y);
                    }
                    return true;}return false;
            }

            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                Vector2 pos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
                if(InputArea.contains(pos.x, pos.y)) {
                    System.out.println("Input area touch released!");
                    isDragging = false;

                    projectileEquation.startVelocity.set(controller.power, 0f);
                    projectileEquation.startVelocity.setAngleDeg(controller.angle);

                    Vector2 launchVelocity = new Vector2(controller.power, 0f);
                    launchVelocity.setAngleDeg(controller.angle);

                    projectileBody.setTransform((slingshotPosition.x+15) / PPM, (slingshotPosition.y +50)/ PPM, 0); // Reset position
                    projectileBody.setType(BodyDef.BodyType.DynamicBody); // Set to Dynamic
                    projectileBody.setActive(true); // Activate
                    projectileBody.setLinearVelocity(launchVelocity.scl(10 / PPM));
                    ArrayList<Vector2> trajectoryPoints = new ArrayList<>();
                    float t = 0f;
                    while (true) {
                        float x = (projectileEquation.getX(t) + slingshotPosition.x + 15) / PPM;
                        float y = (projectileEquation.getY(t) + slingshotPosition.y + 20) / PPM;
                        if (y < 0) break; // Stop if trajectory goes below ground level
                        trajectoryPoints.add(new Vector2(x, y));
                        t += 0.1f; // Adjust time step as needed
                    }
                    release_time=System.currentTimeMillis();
                    released=true;


                    return true;}
                return false;
            }

        };

        return processor1;
    }

    public void render(float delta) {
        world.step(1/60f, 6, 2);
        avBirdClass.get(0).setIndexC(avBirdClass.get(0).getIndex());
        Vector2 bodyPosition = projectileBody.getPosition();
        projectilePosition.set(bodyPosition.x * PPM, bodyPosition.y * PPM);
        if(avBirdClass.get(0).getIndex()==5){avBirdClass.get(0).setIndex(3);Boolbird=1;avBirdClass.get(0).setIndexC(3);}
        if(avBirdClass.get(0).getIndex()==4){avBirdClass.get(0).setIndexC(3);}



        batch.begin();
        batch.draw(slingshotTexture, slingshotPosition.x, slingshotPosition.y, 40, 100);
        projectBodyClass=avBirdClass.get(avBirdClass.get(0).getIndexC());

        batch.draw(projectBodyClass.getBirdTexture(), projectilePosition.x-10 , projectilePosition.y -10, 20, 20);

        long delay=System.currentTimeMillis()-release_time;
        if(released&&delay>2000){
            avBirdClass.get(0).setIndex(avBirdClass.get(0).getIndex()+1);
            projectileBody=chidiyas.get(avBirdClass.get(0).getIndex());
            projectilePosition.set(slingshotPosition.x+15, slingshotPosition.y+20);
          released=false;

        }
        if(avBirdClass.get(0).getIndex()==4){Boolbird=1;}

//        projectBodyClass=avBirdClass.get(avBirdClass.get(0).getIndexC());
//
//        batch.draw(slingshotTexture, slingshotPosition.x, slingshotPosition.y, 40, 100);
//        batch.draw(projectBodyClass.getBirdTexture(), projectilePosition.x-10 , projectilePosition.y -10, 20, 20);
//      //  System.out.println("x:-"+(projectilePosition.x-10)+" y:-"+(projectilePosition.y-10));
        // Assuming avBirdClass is an ArrayList or similar collection of birds
        float startX = 50/PPM; // Starting X position
        float startY = 100/PPM; // Starting Y position
        float gap = 30/PPM;    // Gap between birds (adjust as needed)



            // Loop through the list safely
            for (int i = avBirdClass.get(0).getIndexC() + 1; i < avBirdClass.size(); i++) {
                bird cur = avBirdClass.get(i); // Safe access

                // Calculate positions
                float floatX = startX + (i * gap) * PPM; // X position
                float floatY = startY;                   // Y position (static or can vary)

                // Draw the bird
                batch.draw(cur.getBirdTexture(), floatX, floatY, 30, 30);
            }
        batch.end();


        debugRenderer.render(world, camera.combined.cpy().scl(PPM));

        stage.act(delta);
        stage.draw();
//        if (projectilePosition.y < 0) {
////            resetProjectile(); // Call to reset
//        }

    }



    public void dispose() {
        batch.dispose();
        slingshotTexture.dispose();
        projectileTexture.dispose();
        trajectoryPointTexture.dispose();
        stage.dispose();
        world.dispose();
        debugRenderer.dispose(); // Dispose debug renderer
    }
}
