
package game.dev.level;
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

    private void createBackButton() {
        Skin skin = new Skin();
        skin.add("backBtn", backBtnTexture);
        ImageButton.ImageButtonStyle backStyle = new ImageButton.ImageButtonStyle();
        backStyle.up = skin.getDrawable("backBtn");

        ImageButton backBtn = new ImageButton(backStyle);
        backBtn.setSize(50 / PPM, 50 / PPM);
        backBtn.setPosition(10 / PPM, 550 / PPM);

        Rectangle inputArea = new Rectangle(10, 550, 50, 50);


       // backBtn.setBounds(10/PPM, 550/PPM, 50/PPM, 50/PPM);
        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Vector2 pos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
                if(inputArea.contains(pos.x, pos.y)) {
                System.out.println("Button Clicked");
                click.play();
                game.setScreen(new MainScreen(game));}
            }
        });
        stage.addActor(backBtn);
    }

    public easyLevel001(angryBirds game) {
        this.game = game;

        // Initialize camera and viewport
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(960 / PPM, 608 / PPM, gameCam);
        world = new World(new Vector2(0, -9.81f), true);


        //// Add contact listener
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
        blockBodies = new ArrayList<>();
        pigBodies = new ArrayList<>();

        // Create static and dynamic bodies
        initializeBodies();

        // Create slingshot
        slingshotGame = new catapult(viewport, world);


        // Set camera position
        gameCam.position.set(mapWidth / 2f / PPM, mapHeight / 2f / PPM, 0);
        gameCam.update();
        stage = new Stage(viewport);



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
        blockBodies.add(body);
        wood Wood=new wood(body);
        shape.dispose();
    }

    private void createDynamicBody2(Rectangle rect) {
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
        pigBodies.add(body);
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
        for(Body body : blocks.blockbodies1){
            batch.draw(woodBlockTexture, body.getPosition().x-30/ PPM, body.getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);

        }
        for (Body body : blockBodies) {
            batch.draw(woodBlockTexture, body.getPosition().x-30/ PPM, body.getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
        }
        for(Body body : pigBodies){
            batch.draw(pigBlockTexture, body.getPosition().x-30/ PPM, body.getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
        }
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
