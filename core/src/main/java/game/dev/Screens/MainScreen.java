package game.dev.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import game.dev.Serialise.LevelData;
import game.dev.Serialise.LevelDeserializer;
import game.dev.angryBirds;
import game.dev.level.easyLevel001;
import game.dev.level.hardLevel001;
import game.dev.level.medLevel001;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainScreen implements Screen {
    private angryBirds game;
    private OrthographicCamera gameCam;
    private Texture backgroundImg;
    private Music click, music;
    private Stage stage;
    private Texture logo;


    // Buttons
    private Texture exitButton, musicOnButton, musicOffButton, newGameButton, loadGameButton;

    public MainScreen(angryBirds game) {
        this.game = game;

        // Load textures
//        logo = new Texture("MainScreen/AngryBird.png");
//        backgroundImg = new Texture("MainScreen/backdrop.jpeg");
//        exitButton = new Texture("MainScreen/exitbtn.png");
//        musicOnButton = new Texture("MainScreen/music_on.png");
//        musicOffButton = new Texture("MainScreen/music_off.png");
//        newGameButton = new Texture("MainScreen/menuB1.png");
//        loadGameButton = new Texture("MainScreen/menuB2.png");

        // Load music
        click = Gdx.audio.newMusic(Gdx.files.internal("music/click.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.wav"));

        // Camera and viewport
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, 960, 608);

        // Stage for UI
        stage = new Stage(new FitViewport(960, 608, gameCam));
        Gdx.input.setInputProcessor(stage); // Set input processor to handle button clicks
    }

    @Override
    public void show() {
        logo = new Texture("MainScreen/AngryBird.png");
        backgroundImg = new Texture("MainScreen/backdrop.jpeg");
        exitButton = new Texture("MainScreen/exitbtn.png");
        musicOnButton = new Texture("MainScreen/music_on.png");
        musicOffButton = new Texture("MainScreen/music_off.png");
        newGameButton = new Texture("MainScreen/menuB1.png");
        loadGameButton = new Texture("MainScreen/menuB2.png");




        // Create a skin and add button textures
        Skin skin = new Skin();
        skin.add("newGame", newGameButton);
        skin.add("loadGame", loadGameButton);
        skin.add("exit", exitButton);
        skin.add("Logo", logo);
        skin.add("musicOn", musicOnButton);
        skin.add("musicOff", musicOffButton);

        ImageButton.ImageButtonStyle newGameStyle = new ImageButton.ImageButtonStyle();
        newGameStyle.up = skin.getDrawable("newGame");

        ImageButton.ImageButtonStyle loadGameStyle = new ImageButton.ImageButtonStyle();
        loadGameStyle.up = skin.getDrawable("loadGame");

        ImageButton.ImageButtonStyle exitStyle = new ImageButton.ImageButtonStyle();
        exitStyle.up = skin.getDrawable("exit");

        ImageButton.ImageButtonStyle logoStyle = new ImageButton.ImageButtonStyle();
        logoStyle.up = skin.getDrawable("Logo");

        ImageButton.ImageButtonStyle musicOnStyle = new ImageButton.ImageButtonStyle();
        musicOnStyle.up = skin.getDrawable("musicOn");

        ImageButton.ImageButtonStyle musicOffStyle = new ImageButton.ImageButtonStyle();
        musicOffStyle.up = skin.getDrawable("musicOff");



        // Create ImageButtons
        ImageButton newGameBtn = new ImageButton(newGameStyle);
        ImageButton loadGameBtn = new ImageButton(loadGameStyle);
        ImageButton exitBtn = new ImageButton(exitStyle);
        ImageButton logoBtn    = new ImageButton(logoStyle);
        ImageButton musicOnBtn = new ImageButton(musicOnStyle);
        ImageButton musicOffBtn = new ImageButton(musicOffStyle);




        // Set button positions











        logoBtn.setPosition(275,500);


        exitBtn.setSize(50,50);
        loadGameBtn.setSize(300,300);
        newGameBtn.setSize(300,300);
        musicOnBtn.setSize(50,50);




        newGameBtn.setPosition(150, 150);
        loadGameBtn.setPosition(500, 150);
        exitBtn.setPosition(10, 10);
        musicOnBtn.setPosition(880,10);

        musicOnBtn.addListener(new ClickListener(){
            private boolean isMusicOn = true;
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                if (isMusicOn) {
                    musicOnBtn.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("LevelScreen/music_off.png")));
                    // Add code to stop the music
                    music.pause();
                } else {
                    musicOnBtn.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("LevelScreen/music_on.png")));
                    // Add code to play the music
                    music.play();
                }
                isMusicOn = !isMusicOn;
            }
        });

        // Add click listeners to buttons
        newGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(); // Play click sound
                game.setScreen(new LevelSelector(game)); // Switch to the game screen
                System.out.println("New Game Button Clicked");
            }
        });

        loadGameBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(); // Play click sound
                // Load the saved game
                LevelData loadedLevel = LevelDeserializer.loadLevel("level.json");
                if(loadedLevel.getLevel()==1){
                    game.setScreen(new easyLevel001(game, loadedLevel));
                }
                else if (loadedLevel.getLevel()==2){ {
                    game.setScreen(new medLevel001(game, loadedLevel));
                }
                } else if (loadedLevel.getLevel()==3){
                    game.setScreen(new hardLevel001(game, loadedLevel));
                }


                System.out.println("Load Game Button Clicked");
            }


        });

        exitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play(); // Play click sound
                System.out.println("Exit Button Clicked");
                Gdx.app.exit(); // Exit the application
            }
        });

        // Add buttons to the stage
        stage.addActor(newGameBtn);
        stage.addActor(loadGameBtn);
        stage.addActor(exitBtn);
        stage.addActor(logoBtn);
        stage.addActor(musicOnBtn);
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0.1f, 0.2f, 0.4f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the background
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0);
        game.batch.end();

        // Render the stage (UI)
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {

        backgroundImg.dispose();
        stage.dispose();
        click.dispose();
        music.dispose();
    }
}
