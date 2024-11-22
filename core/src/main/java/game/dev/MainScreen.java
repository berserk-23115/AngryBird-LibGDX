package game.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MainScreen implements Screen {
    private Music click, music;
    private angryBirds game;
    private Texture exitButton, MusicOnButton,MusicOffButton, logo;
    private Texture backgroundImg;
    private OrthographicCamera gameCam;

    private Texture NewGameMenuButton,LoadGameMenuButton;

    public MainScreen(angryBirds game) {


        this.game = game;
        exitButton = new Texture("MainScreen/exitbtn.png");
        logo = new Texture("MainScreen/AngryBird.png");
        MusicOnButton = new Texture("MainScreen/music_on.png");
        MusicOffButton = new Texture("MainScreen/music_off.png");
        backgroundImg = new Texture("MainScreen/backdrop.jpeg");

        NewGameMenuButton = new Texture("MainScreen/menuB1.png");
        LoadGameMenuButton = new Texture("MainScreen/menuB2.png");
        click = Gdx.audio.newMusic(Gdx.files.internal("music/click.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));

        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, 960, 608); // Set a default resolution
    }

    @Override
    public void show() {
        Skin skin = new Skin();
        skin.add("NewGameMenuButton", new TextureRegion(NewGameMenuButton));
        skin.add("LoadGameMenuButton", new TextureRegion(LoadGameMenuButton));
        skin.add("exitbtn", new TextureRegion(exitButton));
        skin.add("musicbtn",new TextureRegion(MusicOnButton));
        skin.add("musoffbtn",new TextureRegion(MusicOffButton));


        }

    @Override
    public void render(float delta) {
        // Set the background color (RGBA values)
        Gdx.gl.glClearColor(0.1f, 0.2f, 0.4f, 1f); // Example: dark blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  // Clear the screen

        // Render the texture
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(backgroundImg, 0, 0);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        // Update the camera viewport to handle resizing
        gameCam.setToOrtho(false, width, height);
    }

    @Override
    public void pause() {
        // Handle pause logic if necessary
    }

    @Override
    public void resume() {
        // Handle resume logic if necessary
    }

    @Override
    public void hide() {
        // Clean up resources when the screen is hidden
        dispose();
    }

    @Override
    public void dispose() {
        // Dispose of resources to avoid memory leaks
        backgroundImg.dispose();
    }
}


