//package game.dev.level;
//
//import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Screen;
//import com.badlogic.gdx.audio.Music;
//import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.Sprite;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.maps.objects.RectangleMapObject;
//import com.badlogic.gdx.maps.tiled.TiledMap;
//import com.badlogic.gdx.maps.tiled.TmxMapLoader;
//import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.math.Vector2;
//import com.badlogic.gdx.physics.box2d.*;
//import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.scenes.scene2d.Stage;
//import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
//import com.badlogic.gdx.scenes.scene2d.ui.Skin;
//import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
//import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
//import com.badlogic.gdx.utils.viewport.FitViewport;
//import com.badlogic.gdx.utils.viewport.Viewport;
//import game.dev.Screens.MainScreen;
//import game.dev.angryBirds;
//import game.dev.catapult.catapult;
//
//import java.util.ArrayList;
//
//public class easyLevel001 implements Screen {
//    private Texture WOOD_BLOCK_TEXTURE;
//    private final angryBirds game;
//    private OrthographicCamera gameCam;
//    private OrthogonalTiledMapRenderer tiledMapRenderer;
//    private TiledMap map;
//    private Viewport viewport;
//    private Stage stage;
//    private Music click,music;
//
//    private SpriteBatch batch;
//    private Sprite block;
//    private ArrayList<Body> blockBodies;
//    public static final float PPM = 100f;
//    private World world;
//    private Box2DDebugRenderer b2dr;
//
//
//    private Texture texture;
//    private Texture Backbtn;
//    float colWidth = Gdx.graphics.getWidth() / 12f; // Width of one column (float for precision)
//    float rowHeight = Gdx.graphics.getHeight() / 12f; // Height of one row
//
//
//    // Map dimensions (in pixels)
//    private int mapWidth;
//    private int mapHeight;
//    private catapult slingshotGame;
//    public easyLevel001(angryBirds game) {
//        this.game = game;
//        click = Gdx.audio.newMusic(Gdx.files.internal("music/click.ogg"));
//        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.wav"));
//
//        world = new World(new Vector2(0, -9.81f), true);
//        stage = new Stage(new FitViewport(960, 608, gameCam));
//        // Initialize camera and viewport
//        gameCam = new OrthographicCamera();
//        viewport = new FitViewport(960 / PPM, 608 / PPM, gameCam);
//        WOOD_BLOCK_TEXTURE = new Texture("TileMaps/BLOCK_WOOD_4X4_2.png");
//        // Load map and initialize renderer
//        TmxMapLoader mapLoader = new TmxMapLoader();
//        map = mapLoader.load("TileMaps/level-updated.tmx");
//        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);
//        Backbtn = new Texture("LevelScreen/backBtn.png");
//        Skin skin = new Skin();
//        skin.add("backBtn", Backbtn);
//        ImageButton.ImageButtonStyle backstyle = new ImageButton.ImageButtonStyle();
//        backstyle.imageUp = skin.getDrawable("backBtn");
//        ImageButton backBtn = new ImageButton(backstyle);
//        backBtn.setSize(100, 100);
//        backBtn.setPosition(10, 550);
//
//        backBtn.addListener(new ClickListener(){
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                click.play();
//                game.setScreen(new MainScreen(game));
//            }
//        });
//
//        // Get map dimensions in pixels
//        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
//        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);
//
//        // Initialize Box2D world and debug renderer
//        b2dr = new Box2DDebugRenderer();
//
//        // Initialize sprite batch and block bodies
//        batch = new SpriteBatch();
//        block = new Sprite(new Texture("TileMaps/BLOCK_WOOD_4X4_2.png"));
//        block.setSize(32 / PPM, 32 / PPM);
//        blockBodies = new ArrayList<>();
//
//        // Load additional texture
//
//
//        // Create Box2D bodies from map objects
//        for (RectangleMapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
//            Rectangle rect = object.getRectangle();
//
//            // Define the body as a static body
//            BodyDef bdef = new BodyDef();
//            bdef.type = BodyDef.BodyType.StaticBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);
//
//            // Create the body in the Box2D world
//            Body body = world.createBody(bdef);
//
//            // Define the shape of the body as a rectangle
//            PolygonShape shape = new PolygonShape();
//            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);
//
//            FixtureDef fdef = new FixtureDef();
//            fdef.shape = shape;
//            fdef.density = 1f;
//            fdef.friction = 10.0f;
//            fdef.restitution = 0.2f;
//
//            body.createFixture(fdef);
//
//
//            shape.dispose();
//        }
//
//        for(RectangleMapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
//            Rectangle rect = object.getRectangle();
//            BodyDef bdef = new BodyDef();
//            bdef.type = BodyDef.BodyType.DynamicBody;
//            bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);
//
//            Body body = world.createBody(bdef);
//            PolygonShape shape = new PolygonShape();
//            shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);
//
//            FixtureDef fdef = new FixtureDef();
//            fdef.shape = shape;
//            fdef.density = 0.5f;
//            fdef.friction = 0.2f;
//            fdef.restitution = 0.2f;
//
//
//            body.createFixture(fdef);
//            blockBodies.add(body);
//            shape.dispose();
//
//        }
//
//        slingshotGame = new catapult(viewport , world);
//
////
//
//        gameCam.position.set(mapWidth / 2f / PPM, mapHeight / 2f / PPM, 0);
//        gameCam.update();
//        stage.addActor(backBtn);
//    }
//
//    public void update(float delta) {
//        // Update the Box2D world
//        world.step(1/60f, 6, 2);
//
//        // Update the camera
//        gameCam.update();
//        tiledMapRenderer.setView(gameCam);
//    }
//
//    @Override
//    public void show() {
//        // No additional initialization needed
//    }
//
//    @Override
//    public void render(float delta) {
//        update(delta);
//
//        // Clear the screen
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//        // Render the tiled map
//        tiledMapRenderer.render();
//        slingshotGame.render(delta);
//
//        // Render the Box2D debug renderer
//        b2dr.render(world, gameCam.combined);
//
//
//        // Render the sprites
//        batch.setProjectionMatrix(gameCam.combined);
//        batch.begin();
//        for (Body body : blockBodies) {
////
//            batch.draw(WOOD_BLOCK_TEXTURE, body.getPosition().x - 16 / PPM, body.getPosition().y - 16 / PPM, 32 / PPM, 32 / PPM);
//        }
//
//        // Draw the AngryBird texture
//        float rowHeight = Gdx.graphics.getHeight() / 12f;
//        float colWidth = Gdx.graphics.getWidth() / 12f;
////        batch.draw(texture, 160f / PPM, 370f / PPM, colWidth * 6 / PPM, rowHeight * 1.8f / PPM);
//
//        batch.end();
//        stage.draw();
////        b2dr.render(world, stage.getCamera().combined);
//    }
//
//    @Override
//    public void resize(int width, int height) {
//        viewport.update(width, height);
//    }
//
//    @Override
//    public void pause() {}
//
//    @Override
//    public void resume() {}
//
//    @Override
//    public void hide() {}
//
//    @Override
//    public void dispose() {
//        map.dispose();
//        tiledMapRenderer.dispose();
//        batch.dispose();
//        world.dispose();
//        b2dr.dispose();
//        texture.dispose();
//    }
//}


package game.dev.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.dev.Screens.MainScreen;
import game.dev.angryBirds;
import game.dev.catapult.catapult;

import java.util.ArrayList;

public class easyLevel001 implements Screen {
    private final angryBirds game;
    private OrthographicCamera gameCam;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private Viewport viewport;
    private Stage stage;
    private Music click, music;

    private SpriteBatch batch;
    private ArrayList<Body> blockBodies;
    public static final float PPM = 100f;
    private World world;
    private Box2DDebugRenderer b2dr;


    private Texture WOOD_BLOCK_TEXTURE;
    private Texture Backbtn;

    private int mapWidth;
    private int mapHeight;

    private catapult slingshotGame;

    public easyLevel001(angryBirds game) {
        this.game = game;

        // Initialize camera and viewport
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(960 / PPM, 608 / PPM, gameCam);

        // Load assets
        click = Gdx.audio.newMusic(Gdx.files.internal("music/click.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.wav"));
        WOOD_BLOCK_TEXTURE = new Texture("TileMaps/BLOCK_WOOD_4X4_2.png");
        Backbtn = new Texture("LevelScreen/backBtn.png");
        // Placeholder texture

        // Initialize stage and map
        stage = new Stage(viewport);
        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("TileMaps/level-updated.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        // Map dimensions
        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        // Initialize Box2D world
        world = new World(new Vector2(0, -9.81f), true);
        b2dr = new Box2DDebugRenderer();

        // Initialize batch and blocks
        batch = new SpriteBatch();
        blockBodies = new ArrayList<>();

        // Create static and dynamic bodies
        initializeBodies();

        // Create slingshot
        slingshotGame = new catapult(viewport, world);

        // Set camera position
        gameCam.position.set(mapWidth / 2f / PPM, mapHeight / 2f / PPM, 0);
        gameCam.update();

        // Create back button
        createBackButton();
    }

    private void initializeBodies() {
        for (RectangleMapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            createStaticBody(object.getRectangle());
        }

        for (RectangleMapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            createDynamicBody(object.getRectangle());
        }
    }

    private void createStaticBody(Rectangle rect) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);

        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 1f;
        fdef.friction = 10.0f;
        fdef.restitution = 0.2f;

        body.createFixture(fdef);
        shape.dispose();
    }

    private void createDynamicBody(Rectangle rect) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);

        Body body = world.createBody(bdef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 0.5f;
        fdef.friction = 0.2f;
        fdef.restitution = 0.2f;

        body.createFixture(fdef);
        blockBodies.add(body);
        shape.dispose();
    }

    private void createBackButton() {
        Skin skin = new Skin();
        skin.add("backBtn", Backbtn);
        ImageButton.ImageButtonStyle backstyle = new ImageButton.ImageButtonStyle();
        backstyle.imageUp = skin.getDrawable("backBtn");

        ImageButton backBtn = new ImageButton(backstyle);
        backBtn.setSize(100, 100);
        backBtn.setPosition(10, 550);

        backBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                game.setScreen(new MainScreen(game));
            }
        });

        stage.addActor(backBtn);
    }

    private void update(float delta) {
        world.step(1 / 60f, 6, 2);
        gameCam.update();
        tiledMapRenderer.setView(gameCam);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tiledMapRenderer.render();
        slingshotGame.render(delta);
        b2dr.render(world, gameCam.combined);

        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        for (Body body : blockBodies) {
            batch.draw(WOOD_BLOCK_TEXTURE, body.getPosition().x - 16 / PPM, body.getPosition().y - 16 / PPM, 32 / PPM, 32 / PPM);
        }
        batch.end();

        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        map.dispose();
        tiledMapRenderer.dispose();
        batch.dispose();
        world.dispose();
        b2dr.dispose();
        WOOD_BLOCK_TEXTURE.dispose();
        Backbtn.dispose();

        click.dispose();
        music.dispose();
    }

    // Unused overrides
    @Override
    public void show() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
