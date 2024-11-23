package game.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class LevelSelector implements Screen {
    private angryBirds game;
    private Texture backgroundImage;
    private Texture easyBtn, mediumBtn, hardBtn;
    private Texture ExitBtn, MusicOnBtn, MusicOffBtn, BackBtn;
    private Stage stage;
    private OrthographicCamera gameCam;


    public LevelSelector(angryBirds game){
        this.game = game;

        // Load textures
        backgroundImage = new Texture("LevelScreen/backdrop.jpeg");
        easyBtn = new Texture("LevelScreen/easyBtn.png");
        mediumBtn = new Texture("LevelScreen/mediumBtn.png");
        hardBtn = new Texture("LevelScreen/hardBtn.png");
        ExitBtn = new Texture("LevelScreen/exitBtn.png");
        MusicOnBtn = new Texture("LevelScreen/music_on.png");
        MusicOffBtn = new Texture("LevelScreen/music_off.png");
        BackBtn = new Texture("LevelScreen/backBtn.png");

        //Camera and ViewPort
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, 960, 608);

        //Stage for UI
        stage = new Stage(new FitViewport(960, 608, gameCam));
        Gdx.input.setInputProcessor(stage);

    }
    @Override

    public void show() {
        Skin skin = new Skin();
        skin.add("exitbtn",ExitBtn);
        skin.add("musicOn",MusicOnBtn);
        skin.add("musicOff",MusicOffBtn);
        skin.add("back",BackBtn);
        skin.add("easy",easyBtn);
        skin.add("medium",mediumBtn);
        skin.add("hard",hardBtn);


    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0, 960, 608);
        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {

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
    public void dispose() {

    }

}
