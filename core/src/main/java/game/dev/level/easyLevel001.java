
package game.dev.level;
import game.dev.Screens.pauseMenu;
import game.dev.blocks.blocks;

import com.badlogic.gdx.*;
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
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.dev.Screens.MainScreen;
import game.dev.angryBirds;
import game.dev.blocks.wood;
import game.dev.catapult.catapult;
import game.dev.pigs.kingpig;
import game.dev.pigs.pigs;

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
    private ArrayList<Body> blockBodies,pigBodies;
    private ArrayList<blocks> blockBodies1;
    private ArrayList<pigs> pigBodies1;


    // Add bird bodies to this list which are to be assigned to the catapult
    private ArrayList<Body> AssignedBirds;



    public static final float PPM = 100f;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Texture woodBlockTexture,pigBlockTexture;
    private Texture backBtnTexture;

    private int mapWidth;
    private int mapHeight;

    private catapult slingshotGame;

    private InputProcessor createBackButton() {
        Rectangle inputArea = new Rectangle(10, 550, 50, 50);
        InputProcessor processor2 = new InputAdapter() {

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                // Convert screen coordinates to world coordinates if needed
                Vector2 back_pos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);

                // Check if the touch is within the input area
                if (inputArea.contains(back_pos.x, back_pos.y)) {
                    System.out.println("Button Clicked");
                    click.play(); // Play the click sound
                    game.setScreen(new pauseMenu(game)); // Switch to the main screen
                    return true; // Event handled
                }
                return false; // Event not handled
            }
        } ;
      //  stage.addActor(backBtn);
        return processor2;
    }

    public easyLevel001(angryBirds game) {
        this.game = game;

        // Initialize camera and viewport
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(960 / PPM, 608 / PPM, gameCam);
        world = new World(new Vector2(0, -9.81f), true);


        // Add contact listener
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if((fixtureA.getBody().getUserData()=="Block" && fixtureB.getBody().getUserData()=="Bird")||(fixtureA.getBody().getUserData()=="FUddi" && fixtureB.getBody().getUserData()=="Block")){
                    System.out.println("SAX SUX ..... ");

                    /// BlockHealth --- , if BlockHealth == 0 then destroy block
                }


            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });

        // Load assets
        click = Gdx.audio.newMusic(Gdx.files.internal("music/click.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.wav"));
        woodBlockTexture = new Texture("TileMaps/BLOCK_WOOD_4X4_2.png");
        backBtnTexture = new Texture("LevelScreen/backBtn.png");
        pigBlockTexture = new Texture("Pigs/kingping.png");

        // Initialize stage and map

        // Use InputMultiplexer for handling multiple input processors
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("TileMaps/level-updated.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        // Map dimensions
        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        // Initialize Box2D world
        b2dr = new Box2DDebugRenderer();

        // Initialize batch and blocks
        batch = new SpriteBatch();
       // blockBodies = new ArrayList<>();
        blockBodies1 = new ArrayList<>();
        pigBodies1 = new ArrayList<>();

        // Create static and dynamic bodies
        initializeBodies();

        // Create slingshot
        slingshotGame = new catapult(viewport, world);



        // Set camera position
        gameCam.position.set(mapWidth / 2f / PPM, mapHeight / 2f / PPM, 0);
        gameCam.update();
        stage = new Stage(viewport);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(slingshotGame.getInputProcessor());
        multiplexer.addProcessor(createBackButton());

        Gdx.input.setInputProcessor(multiplexer);

    }

    private void initializeBodies() {
        for (RectangleMapObject object : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)) {
            createStaticBody(object.getRectangle());
        }

        for (RectangleMapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
            createDynamicBody(object.getRectangle());
        }

        //PIGS ...
        for(RectangleMapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            createDynamicBody2(object.getRectangle());
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
        body.setUserData("Block");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 0.5f;
        fdef.friction = 0.2f;
        fdef.restitution = 0.2f;

        body.createFixture(fdef);
      //  blockBodies.add(body);
        wood Wood=new wood(body);
        blockBodies1.add(Wood);

        //wood Wood=new wood(body);
        shape.dispose();
    }

    private void createDynamicBody2(Rectangle rect) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);

        Body body = world.createBody(bdef);
        body.setUserData("Pig");

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 0.2f;
        fdef.friction = 0.2f;
        fdef.restitution = 0.2f;

        body.createFixture(fdef);
        kingpig king=new kingpig(body);
        pigBodies1.add(king);
        //pigBodies.add(body);
        shape.dispose();
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
        for(blocks b : blockBodies1){
            batch.draw(woodBlockTexture, b.getBody().getPosition().x-30/ PPM, b.getBody().getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);

        }
        batch.draw(backBtnTexture,10 / PPM, 550 / PPM,50/PPM,50/PPM);
//        for (Body body : blockBodies) {
//            batch.draw(woodBlockTexture, body.getPosition().x-30/ PPM, body.getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
//        }
        for(pigs p : pigBodies1){
            batch.draw(pigBlockTexture, p.getBody().getPosition().x-30/ PPM, p.getBody().getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
        }
//        for(Body body : pigBodies){
//            batch.draw(pigBlockTexture, body.getPosition().x-30/ PPM, body.getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
//        }

        batch.end();
        stage.act(delta);
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
        woodBlockTexture.dispose();
        backBtnTexture.dispose();
        click.dispose();
        music.dispose();
    }

    @Override
    public void show() {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
