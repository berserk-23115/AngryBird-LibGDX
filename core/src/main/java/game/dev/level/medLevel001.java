
package game.dev.level;
import game.dev.Screens.*;
import game.dev.birds.bird;
import game.dev.birds.blue;
import game.dev.birds.chuck;
import game.dev.birds.red;
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
import game.dev.angryBirds;
import game.dev.blocks.concrete;
import game.dev.blocks.glass;
import game.dev.blocks.wood;
import game.dev.catapult.catapult;
import game.dev.pigs.kingpig;
import game.dev.pigs.mediumgpig;
import game.dev.pigs.pigs;
import game.dev.pigs.smallpig;

import java.util.ArrayList;
import java.util.Random;

public class medLevel001 implements Screen {
    private angryBirds game;
    private OrthographicCamera gameCam;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private Viewport viewport;
    private Stage stage;
    private Music click, music;

    private SpriteBatch batch;
    private ArrayList<Body> blockBodies;

    private ArrayList<blocks> blockBodies1,deadBlocks;
    private ArrayList<pigs> pigBodies1 = new ArrayList<>();
    private ArrayList<Body> bodiesToDestroy = new ArrayList<>();
    public ArrayList<Body> availableBirds = new ArrayList<>();
    public ArrayList<bird> avBirdsClass=new ArrayList<>();
    public ArrayList<pigs> deadPigs;



    // Add bird bodies to this list which are to be assigned to the catapult




    public static final float PPM = 100f;
    private World world;
    private Box2DDebugRenderer b2dr;

    private Texture woodBlockTexture,pigBlockTexture;
    private Texture backBtnTexture,saveBtnTexture,reloadBtnTexture;

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
                    game.setScreen(new LevelSelector(game)); // Switch to the main screen
                    return true; // Event handled
                }
                return false; // Event not handled
            }
        } ;
        //  stage.addActor(backBtn);
        return processor2;
    }
    private InputProcessor createSaveBtn() {
        Rectangle inputArea = new Rectangle(70, 550, 105, 45);
        InputProcessor processor2 = new InputAdapter() {

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                // Convert screen coordinates to world coordinates if needed
                Vector2 back_pos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);

                // Check if the touch is within the input area
                if (inputArea.contains(back_pos.x, back_pos.y)) {
                    System.out.println("Button Clicked");
                    click.play(); // Play the click sound
                    // Switch to the main screen
                    return true; // Event handled
                }
                return false; // Event not handled
            }
        } ;
        //  stage.addActor(backBtn);
        return processor2;
    }


    private InputProcessor createReloadBtn() {
        Rectangle inputArea = new Rectangle(185, 550, 48, 48);
        InputProcessor processor2 = new InputAdapter() {

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                // Convert screen coordinates to world coordinates if needed
                Vector2 back_pos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);

                // Check if the touch is within the input area
                if (inputArea.contains(back_pos.x, back_pos.y)) {
                    System.out.println("Button Clicked");
                    click.play(); // Play the click sound
                    game.setScreen(new easyLevel001(game)); // Switch to the main screen
                    return true; // Event handled
                }
                return false; // Event not handled
            }
        } ;
        //  stage.addActor(backBtn);
        return processor2;
    }


    public void createBirdBodies(int count){
        for(int i =0;i<count;i++){
            Random random = new Random();
            int bType = random.nextInt(3);
            BodyDef birddef = new BodyDef();

            birddef.type = BodyDef.BodyType.DynamicBody;
            birddef.active = false;
            Body birdBody = world.createBody(birddef);
            switch (bType){
                case 0:
                    red lal=new red(birdBody);
                    avBirdsClass.add(lal);
                    birdBody.setUserData(lal);
                    break;
                case 1:
                    blue neela=new blue(birdBody);
                    avBirdsClass.add(neela);
                    birdBody.setUserData(neela);
                    break;
                case 2:
                    chuck pila=new chuck(birdBody);
                    avBirdsClass.add(pila);
                    birdBody.setUserData(pila);
                    break;
            }
            CircleShape projectileShape = new CircleShape();
            projectileShape.setRadius(10 / PPM); // Set radius based on texture size

            FixtureDef projectileFixtureDef = new FixtureDef();
            projectileFixtureDef.shape = projectileShape;
            projectileFixtureDef.density = 1f;
            projectileFixtureDef.friction = 0.2f;
            projectileFixtureDef.restitution = 0.3f;// Bounciness


            birdBody.createFixture(projectileFixtureDef);
            availableBirds.add(birdBody);

            projectileShape.dispose();


        }

        /// DUMMY BIRD --ADDED --->>>>>>
        BodyDef birdkilulli = new BodyDef();
        birdkilulli.type = BodyDef.BodyType.DynamicBody;
        birdkilulli.active = false;
        FixtureDef birdFixtureDef = new FixtureDef();
        birdFixtureDef.density = 1f;
        birdFixtureDef.friction = 0.2f;
        birdFixtureDef.restitution = 0.3f;

        Body birdBody = world.createBody(birdkilulli);
        availableBirds.add(birdBody);


        for(int i =0;i<avBirdsClass.size();i++){
            System.out.println(avBirdsClass.get(i).getPower());
        }

    }

    public medLevel001(angryBirds game) {
        this.game = game;

        // Initialize camera and viewport
        gameCam = new OrthographicCamera();
        viewport = new FitViewport(960 / PPM, 608 / PPM, gameCam);
        world = new World(new Vector2(0, -9.81f), true);
        createBirdBodies(4);







        // Add contact listener
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();








                //----------------------- BIRD n BLOCK---------------



                if((fixtureA.getBody().getUserData() instanceof blocks && fixtureB.getBody().getUserData() instanceof bird)||(fixtureA.getBody().getUserData() instanceof bird && fixtureB.getBody().getUserData() instanceof blocks)){
                    System.out.println("SAX SUX ..... ");


                    if(fixtureA.getBody().getUserData() instanceof wood){
                        System.out.println("choosi");



                        bird currBird=(bird) fixtureB.getBody().getUserData();
                        wood currWood= (wood) fixtureA.getBody().getUserData();




                        currWood.setHealth(currWood.getHealth()-currBird.getPower());
                        if(currWood.getHealth()<=0){
                            deadBlocks.add(currWood);
                            bodiesToDestroy.add(fixtureA.getBody());

                        }

                    }
                    else if(fixtureB.getBody().getUserData() instanceof wood) {
                        System.out.println("choosi");


                        bird currBird=(bird) fixtureA.getBody().getUserData();
                        wood currWood = (wood) fixtureB.getBody().getUserData();




                        currWood.setHealth(currWood.getHealth() - currBird.getPower());
                        if (currWood.getHealth() <= 0) {
                            deadBlocks.add(currWood);
                            bodiesToDestroy.add(fixtureB.getBody());
                        }
                    }
                    if(fixtureA.getBody().getUserData() instanceof glass){
                        System.out.println("choosi");


                        bird currBird=(bird) fixtureB.getBody().getUserData();
                        glass currGlass=(glass) fixtureA.getBody().getUserData();




                        currGlass.setHealth(currGlass.getHealth()-currBird.getPower());
                        if(currGlass.getHealth()<=0){
                            deadBlocks.add(currGlass);
                            bodiesToDestroy.add(fixtureA.getBody());}

                    }
                    else if(fixtureB.getBody().getUserData() instanceof glass) {
                        System.out.println("choosi");

                        bird currBird=(bird) fixtureA.getBody().getUserData();
                        glass currGlass = (glass) fixtureB.getBody().getUserData();



                        currGlass.setHealth(currGlass.getHealth() - currBird.getPower());
                        if (currGlass.getHealth() <= 0) {
                            deadBlocks.add(currGlass);
                            bodiesToDestroy.add(fixtureB.getBody());
                        }
                    }
                    if(fixtureA.getBody().getUserData() instanceof concrete){
                        System.out.println("choosi");

                        bird currBird=(bird) fixtureB.getBody().getUserData();
                        concrete currCon=(concrete) fixtureA.getBody().getUserData();


                        currCon.setHealth(currCon.getHealth()-currBird.getPower());
                        if(currCon.getHealth()<=0){
                            deadBlocks.add(currCon);
                            bodiesToDestroy.add(fixtureA.getBody());}

                    }
                    else if(fixtureB.getBody().getUserData() instanceof concrete){
                        System.out.println("choosi");

                        bird currBird=(bird) fixtureA.getBody().getUserData();
                        concrete currCon=(concrete) fixtureB.getBody().getUserData();


                        currCon.setHealth(currCon.getHealth()-currBird.getPower());
                        if(currCon.getHealth()<=0){
                            deadBlocks.add(currCon);
                            bodiesToDestroy.add(fixtureB.getBody());}
                    }


                    /// BlockHealth --- , if BlockHealth == 0 then destroy block
                }









                //----------------------- PIG n BIRD---------------







                if((fixtureA.getBody().getUserData() instanceof pigs && fixtureB.getBody().getUserData() instanceof bird)||(fixtureA.getBody().getUserData() instanceof bird&& fixtureB.getBody().getUserData() instanceof pigs)) {
                    System.out.println("SAX SUX .....1 ");


                    if (fixtureA.getBody().getUserData() instanceof smallpig) {
                        System.out.println("choosi");

                        bird currBird = (bird) fixtureB.getBody().getUserData();
                        smallpig currPig = (smallpig) fixtureA.getBody().getUserData();

                        currPig.setHealth(currPig.getHealth() - currBird.getPower());
                        if (currPig.getHealth() <= 0) {
                            deadPigs.add(currPig);
                            bodiesToDestroy.add(fixtureA.getBody());
                        }

                    } else if (fixtureB.getBody().getUserData() instanceof smallpig) {
                        System.out.println("choosi");

                        bird currBird = (bird) fixtureA.getBody().getUserData();
                        smallpig currPig = (smallpig) fixtureB.getBody().getUserData();

                        currPig.setHealth(currPig.getHealth() - currBird.getPower());
                        if (currPig.getHealth() <= 0) {
                            deadPigs.add(currPig);
                            bodiesToDestroy.add(fixtureB.getBody());
                        }
                    }
                    if (fixtureA.getBody().getUserData() instanceof mediumgpig) {
                        System.out.println("choosi");

                        bird currBird = (bird) fixtureB.getBody().getUserData();
                        mediumgpig currPig = (mediumgpig) fixtureA.getBody().getUserData();

                        currPig.setHealth(currPig.getHealth() - currBird.getPower());
                        if (currPig.getHealth() <= 0) {
                            deadPigs.add(currPig);
                            bodiesToDestroy.add(fixtureA.getBody());
                        }

                    } else if (fixtureB.getBody().getUserData() instanceof mediumgpig) {
                        System.out.println("choosi");

                        bird currBird = (bird) fixtureB.getBody().getUserData();
                        mediumgpig currPig = (mediumgpig) fixtureA.getBody().getUserData();

                        currPig.setHealth(currPig.getHealth() - currBird.getPower());
                        if (currPig.getHealth() <= 0) {
                            deadPigs.add(currPig);
                            bodiesToDestroy.add(fixtureB.getBody());
                        }
                    }

                    if(fixtureA.getBody().getUserData() instanceof kingpig){
                        System.out.println("choosi1");



                        bird currBird=(bird) fixtureB.getBody().getUserData();
                        pigs currWood= (pigs) fixtureA.getBody().getUserData();




                        currWood.setHealth(currWood.getHealth()-currBird.getPower());
                        if(currWood.getHealth()<=0){
                            System.out.println("yes");
                            if (currWood instanceof pigs) {
                                System.out.println("currPig is an instance of pigs.");
                                deadPigs.add(currWood);
                            } else {
                                System.out.println("currPig is not a valid pigs instance.");
                            }
                            //  deadPigs.add(currWood);
                            bodiesToDestroy.add(fixtureA.getBody());

                        }

                    }
                    else if(fixtureB.getBody().getUserData() instanceof kingpig) {
                        System.out.println("choosi");


                        bird currBird=(bird) fixtureA.getBody().getUserData();
                        pigs currWood = (pigs) fixtureB.getBody().getUserData();




                        currWood.setHealth(currWood.getHealth() - currBird.getPower());
                        if (currWood.getHealth() <= 0) {

                            if (currWood instanceof pigs) {
                                System.out.println("currPig is an instance of pigs.");
                                deadPigs.add(currWood);
                            } else {
                                System.out.println("currPig is not a valid pigs instance.");
                            }

                            bodiesToDestroy.add(fixtureB.getBody());
                        }
                    }







//
//
//                    if (fixtureA.getBody().getUserData() instanceof kingpig) {
//                        System.out.println("choosi");
//                        bird currBird = (bird) fixtureB.getBody().getUserData();
//                        kingpig currPig = (kingpig) fixtureA.getBody().getUserData();
//
//                        currPig.setHealth(currPig.getHealth() - currBird.getPower());
//                        if (currPig.getHealth() <= 0) {
//                            deadPigs.add(currPig);
//                            bodiesToDestroy.add(fixtureA.getBody());
//                        }
//
//                    } else if (fixtureB.getBody().getUserData() instanceof kingpig) {
//                        System.out.println("choosi");
//
//                        bird currBird = (bird) fixtureB.getBody().getUserData();
//                        kingpig currPig = (kingpig) fixtureA.getBody().getUserData();
//
//                        currPig.setHealth(currPig.getHealth() - currBird.getPower());
//                        if (currPig.getHealth() <= 0) {
//
//                            if (currPig instanceof pigs) {
//                                System.out.println("currPig is an instance of pigs.");
//                                deadPigs.add(currPig);
//                            } else {
//                                System.out.println("currPig is not a valid pigs instance.");
//                            }
//                            deadPigs.add(currPig);
//                            for(pigs p: deadPigs){
//                                System.out.println("pigs entered:- "+p);
//                            }
//                            bodiesToDestroy.add(fixtureB.getBody());
//                        }
//                    }


                }






                //----------------------- PIG n BLOCK---------------



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
        saveBtnTexture = new Texture("pauseMenu/saveBtn.png");
        reloadBtnTexture = new Texture("pauseMenu/reset.png");

        // Initialize stage and map

        // Use InputMultiplexer for handling multiple input processors
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);


        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("TileMaps/mediumLevel.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map, 1 / PPM);

        // Map dimensions
        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        // Initialize Box2D world
        b2dr = new Box2DDebugRenderer();

        // Initialize batch and blocks
        batch = new SpriteBatch();

        blockBodies1 = new ArrayList<>();
        deadPigs=new ArrayList<>();

        deadBlocks= new ArrayList<>();

        // Create static and dynamic bodies
        initializeBodies();

        // Create slingshot

        slingshotGame = new catapult(viewport, world, availableBirds,avBirdsClass);



        // Set camera position
        gameCam.position.set(mapWidth / 2f / PPM, mapHeight / 2f / PPM, 0);
        gameCam.update();
        stage = new Stage(viewport);


        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(slingshotGame.getInputProcessor());
        multiplexer.addProcessor(createBackButton());
        multiplexer.addProcessor(createSaveBtn());
        multiplexer.addProcessor(createReloadBtn());

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
        body.setUserData(Wood);
        blockBodies1.add(Wood);

        //wood Wood=new wood(body);
        shape.dispose();
    }




    private void createDynamicBody2(Rectangle rect) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.DynamicBody;
        bdef.position.set((rect.getX() + rect.getWidth() / 2) / PPM, (rect.getY() + rect.getHeight() / 2) / PPM);

        Body body = world.createBody(bdef);


        PolygonShape shape = new PolygonShape();
        shape.setAsBox(rect.getWidth() / 2 / PPM, rect.getHeight() / 2 / PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = 0.2f;
        fdef.friction = 0.2f;
        fdef.restitution = 0.2f;

        body.createFixture(fdef);
        mediumgpig king=new mediumgpig(body);
        body.setUserData(king);
        pigBodies1.add(king);
        //pigBodies.add(body);
        shape.dispose();
    }

    private void update(float delta) {
        world.step(1 / 60f, 6, 2);
        gameCam.update();
        tiledMapRenderer.setView(gameCam);
        for(pigs b: deadPigs){
            System.out.println("Pig in list: " + b);
//            for (pigs p : pigBodies1) {
//                System.out.println("Pig in list: " + p);
//                if (p.equals(b)) {
//                    System.out.println("Found matching pig: " + b);
//                }
//            }
            pigBodies1.remove(b);
            b.dispose();
        }
        deadPigs.clear();
        for(blocks b: deadBlocks){
            if(blockBodies1.contains(b)){
                System.out.println("ohhh yeeaahh");
            }
            blockBodies1.remove(b);
            b.dispose();
        }
        deadBlocks.clear();
        for (Body body : bodiesToDestroy) {
            body.setActive(false);
            System.out.println("Destroying body: " + body.getUserData());
            world.destroyBody(body);
        }
        bodiesToDestroy.clear();
    }

    @Override
    public void render(float delta) {
        update(delta);

//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (pigBodies1.size() == 0 ) {
            // Set to the Win Screen
            game.setScreen(new winScreenMedium(game));
        } else if (pigBodies1.size() > 0 && slingshotGame.getter()==1) {
            // Set to the Lose Screen
            game.setScreen(new loseScreenMedium(game));
        }

        tiledMapRenderer.render();
        slingshotGame.render(delta);
        b2dr.render(world, gameCam.combined);

        batch.setProjectionMatrix(gameCam.combined);
        batch.begin();
        for(blocks b : blockBodies1){
            batch.draw(b.getBlockTexture(), b.getBody().getPosition().x-30/ PPM, b.getBody().getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
        }
        batch.draw(backBtnTexture,10 / PPM, 550 / PPM,48/PPM,48/PPM);
        batch.draw(saveBtnTexture,70 / PPM, 550 / PPM,105/PPM,48/PPM);
        batch.draw(reloadBtnTexture,185 / PPM, 550 / PPM,48/PPM,48/PPM);
//        for (Body body : blockBodies) {
//            batch.draw(woodBlockTexture, body.getPosition().x-30/ PPM, body.getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
//        }
        for(pigs p : pigBodies1){
            batch.draw(p.getpigTexture(), p.getBody().getPosition().x-30/ PPM, p.getBody().getPosition().y-30/ PPM, 60 / PPM, 60 / PPM);
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
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

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
}
