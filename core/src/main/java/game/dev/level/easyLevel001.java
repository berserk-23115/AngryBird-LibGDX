package game.dev.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.dev.angryBirds;
import game.dev.catapult.catapult;

import java.util.ArrayList;

public class easyLevel001 implements Screen {
    private Texture WOOD_BLOCK_TEXTURE;
    private final angryBirds game;
    private OrthographicCamera gameCam;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private Viewport viewport;

    private SpriteBatch batch;
    private Sprite block;
    private ArrayList<Body> blockBodies;
    public static final float PPM = 100f;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Texture texture;
    private Body smallDynamicBody;
    float colWidth = Gdx.graphics.getWidth() / 12f; // Width of one column (float for precision)
    float rowHeight = Gdx.graphics.getHeight() / 12f; // Height of one row


    // Map dimensions (in pixels)
    private int mapWidth;
    private int mapHeight;
    private catapult slingshotGame;
    public easyLevel001(angryBirds game) {
        this.game = game;


        // Initialize camera and viewport
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(960 / PPM, 608 / PPM, gameCam);
        slingshotGame = new catapult(viewport);
        WOOD_BLOCK_TEXTURE = new Texture("TileMaps/BLOCK_WOOD_4X4_2.png");
        // Load map and initialize renderer
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("TileMaps/level-updated.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        // Get map dimensions in pixels
        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        // Initialize Box2D world and debug renderer
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();

        // Initialize sprite batch and block bodies
        batch = new SpriteBatch();
        block = new Sprite(new Texture("TileMaps/BLOCK_WOOD_4X4_2.png"));
        block.setSize(32 / PPM, 32 / PPM);
        blockBodies = new ArrayList<>();

        // Load additional texture


        // Create Box2D bodies from map objects
        for (RectangleMapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = object.getRectangle();

            // Define the body as a static body
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);

            // Create the body in the Box2D world
            Body body = world.createBody(bdef);

            // Define the shape of the body as a rectangle
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.density = 1f;
            fdef.friction = 0.4f;
            fdef.restitution = 0f;

            body.createFixture(fdef);
//            blockBodies.add(body);

            shape.dispose();
        }

        for(RectangleMapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = object.getRectangle();
            BodyDef bdef = new BodyDef();
            bdef.type = BodyDef.BodyType.DynamicBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);

            Body body = world.createBody(bdef);
            PolygonShape shape = new PolygonShape();
            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

            FixtureDef fdef = new FixtureDef();
            fdef.shape = shape;
            fdef.density = 1f;
            fdef.friction = 0.4f;
            fdef.restitution = 0f;
            fdef.filter.categoryBits=1;
            fdef.filter.maskBits=1;
            fdef.filter.groupIndex=1;

            body.createFixture(fdef);
            blockBodies.add(body);
            shape.dispose();

        }
//        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.DynamicBody;
//        bodyDef.position.set(160f / PPM, 370f / PPM); // Set initial position
//        smallDynamicBody = world.createBody(bodyDef);

        // Define the shape and fixture
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox((colWidth * 3 / PPM), (rowHeight * 0.9f / PPM)); // Half-width and half-height
//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = 1f; // Mass = density * volume
//        fixtureDef.friction = 0.5f;
//        fixtureDef.restitution = 0.3f; // Bounciness
//
//        smallDynamicBody.createFixture(fixtureDef);
//        shape.dispose();


        // Load the texture for the wood block
//        Texture woodBlockTexture = new Texture("TileMaps/BLOCK_WOOD_4X4_2.png");
//        Sprite woodBlockSprite = new Sprite(woodBlockTexture);
//        woodBlockSprite.setSize(32 / PPM, 32 / PPM); // Adjust size based on PPM

// Define the body as a static or dynamic body
//        BodyDef woodBlockBodyDef = new BodyDef();
//        woodBlockBodyDef.type = BodyDef.BodyType.DynamicBody ; // Change to DynamicBody if needed
//        woodBlockBodyDef.position.set(200f / PPM, 200f / PPM); // Set position in the world
//
//        Body woodBlockBody = world.createBody(woodBlockBodyDef);

// Define the shape of the body as a rectangle
//        PolygonShape woodBlockShape = new PolygonShape();
//        woodBlockShape.setAsBox(16 / PPM, 16 / PPM); // Half-width and half-height in meters
//
//        FixtureDef woodBlockFixtureDef = new FixtureDef();
//        woodBlockFixtureDef.shape = woodBlockShape;
//        woodBlockFixtureDef.density = 1f;
//        woodBlockFixtureDef.friction = 0.5f;
//        woodBlockFixtureDef.restitution = 0.3f;
//
//        woodBlockBody.createFixture(woodBlockFixtureDef);
//        woodBlockShape.dispose();
//
//// Add the body to the list for rendering
//        blockBodies.add(woodBlockBody);


        // Center the camera
        gameCam.position.set(mapWidth / 2f / PPM, mapHeight / 2f / PPM, 0);
        gameCam.update();
    }

    public void update(float delta) {
        // Update the Box2D world
        world.step(1/60f, 6, 2);

        // Update the camera
        gameCam.update();
        tiledMapRenderer.setView(gameCam);
    }

    @Override
    public void show() {
        // No additional initialization needed
    }

    @Override
    public void render(float delta) {
        update(delta);

        // Clear the screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render the tiled map
        tiledMapRenderer.render();
        slingshotGame.render(delta);

        // Render the Box2D debug renderer
        b2dr.render(world, gameCam.combined);


        // Render the sprites
        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        for (Body body : blockBodies) {
//            block.setPosition((body.getPosition().x * PPM) - block.getWidth() / 2,
//                (body.getPosition().y * PPM) - block.getHeight() / 2);
//            block.setRotation((float) Math.toDegrees(body.getAngle()));
//            block.draw(batch);
            batch.draw(WOOD_BLOCK_TEXTURE, body.getPosition().x - 16 / PPM, body.getPosition().y - 16 / PPM, 32 / PPM, 32 / PPM);
        }

        // Draw the AngryBird texture
        float rowHeight = Gdx.graphics.getHeight() / 12f;
        float colWidth = Gdx.graphics.getWidth() / 12f;
//        batch.draw(texture, 160f / PPM, 370f / PPM, colWidth * 6 / PPM, rowHeight * 1.8f / PPM);

        batch.end();
//        b2dr.render(world, stage.getCamera().combined);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        map.dispose();
        tiledMapRenderer.dispose();
        batch.dispose();
        world.dispose();
        b2dr.dispose();
        texture.dispose();
    }
}
