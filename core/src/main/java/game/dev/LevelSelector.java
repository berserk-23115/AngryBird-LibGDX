package game.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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
import game.dev.level.easyLevel001;

import javax.swing.*;

public class LevelSelector implements Screen {
    private angryBirds game;
    private Texture backgroundImage;
    private Texture easyBtn, mediumBtn, hardBtn;
    private Texture ExitBtn, MusicOnBtn, MusicOffBtn, BackBtn;
    private Stage stage;
    private OrthographicCamera gameCam;
    private Texture playbtn,GameTitle;
    private Music click , music;




    public LevelSelector(angryBirds game){
        this.game = game;

        // Load textures
        backgroundImage = new Texture("LevelScreen/backdrop.jpeg");

        //Camera and ViewPort
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, 960, 608);
        click = Gdx.audio.newMusic(Gdx.files.internal("music/click.ogg"));
        music = Gdx.audio.newMusic(Gdx.files.internal("music/game.wav"));

        //Stage for UI
        stage = new Stage(new FitViewport(960, 608, gameCam));
        Gdx.input.setInputProcessor(stage);


    }
    @Override

    public void show() {
        easyBtn = new Texture("LevelScreen/tile_easy.png");
        mediumBtn = new Texture("LevelScreen/tile_medium.png");
        hardBtn = new Texture("LevelScreen/tile_hard.png");
        ExitBtn = new Texture("LevelScreen/exitBtn.png");
        MusicOnBtn = new Texture("LevelScreen/music_on.png");
        MusicOffBtn = new Texture("LevelScreen/music_off.png");
        BackBtn = new Texture("LevelScreen/backBtn.png");
        playbtn = new Texture("LevelScreen/playbtn.png");
        GameTitle = new Texture("LevelScreen/GAME STYLE.png");






        Skin skin = new Skin();
        skin.add("exitbtn",ExitBtn);
        skin.add("musicOn",MusicOnBtn);
        skin.add("musicOff",MusicOffBtn);
        skin.add("back",BackBtn);
        skin.add("easy",easyBtn);
        skin.add("medium",mediumBtn);
        skin.add("hard",hardBtn);
        skin.add("play",playbtn);





        ImageButton.ImageButtonStyle exitBtnStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle musicOnStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle musicOffStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle backStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle easyStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle mediumStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle hardStyle = new ImageButton.ImageButtonStyle();
        ImageButton.ImageButtonStyle playStyle = new ImageButton.ImageButtonStyle();

        exitBtnStyle.up = skin.getDrawable("exitbtn");
        musicOnStyle.up = skin.getDrawable("musicOn");
        musicOffStyle.up = skin.getDrawable("musicOff");
        backStyle.up = skin.getDrawable("back");
        easyStyle.up = skin.getDrawable("easy");
        mediumStyle.up = skin.getDrawable("medium");
        hardStyle.up = skin.getDrawable("hard");
        playStyle.up = skin.getDrawable("play");


        ImageButton exitBtn = new ImageButton(exitBtnStyle);
        ImageButton musicOn = new ImageButton(musicOnStyle);
        ImageButton musicOff = new ImageButton(musicOffStyle);
        ImageButton back = new ImageButton(backStyle);
        ImageButton easy = new ImageButton(easyStyle);
        ImageButton medium = new ImageButton(mediumStyle);
        ImageButton hard = new ImageButton(hardStyle);


        ImageButton p1 = new ImageButton(playStyle);
        ImageButton p2 = new ImageButton(playStyle);
        ImageButton p3 = new ImageButton(playStyle);


        p1.setSize(50,50);
        p2.setSize(50,50);
        p3.setSize(50,50);




        medium.setSize(200,200);
        easy.setSize(200,200);
        hard.setSize(200,200);

        exitBtn.setSize(60,50);
        musicOn.setSize(50,50);
        musicOff.setSize(100,100);

        back.setSize(40,40);


        back.setPosition(20,550);
        exitBtn.setPosition(10,10);
        musicOn.setPosition(890,10);
        hard.setPosition(610,225);
        medium.setPosition(370,225);
        easy.setPosition(140,225);

        p1.setPosition(220,210);
        p2.setPosition(450,210);
        p3.setPosition(690,210);




        //// Add click listeners to buttons
        p1.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                game.setScreen(new easyLevel001(game));
            }
        });

        p2.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                game.setScreen(new easyLevel001(game));
            }
        });

        p3.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                game.setScreen(new easyLevel001(game));
            }
        });



        exitBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                Gdx.app.exit();
            }
        });

        musicOn.addListener(new ClickListener(){
            private boolean isMusicOn = true;

            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                if (isMusicOn) {
                    musicOn.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("LevelScreen/music_off.png")));
                    // Add code to stop the music
                    music.pause();
                } else {
                    musicOn.getStyle().up = new TextureRegionDrawable(new TextureRegion(new Texture("LevelScreen/music_on.png")));
                    // Add code to play the music
                    music.play();
                }
                isMusicOn = !isMusicOn;
            }
        });

        back.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                click.play();
                game.setScreen(new MainScreen(game));
            }
        });









        stage.addActor(exitBtn);
        stage.addActor(musicOn);
        stage.addActor(back);
        stage.addActor(easy);
        stage.addActor(medium);
        stage.addActor(hard);


        stage.addActor(p1);
        stage.addActor(p2);
        stage.addActor(p3);



//        stage.addActor();


    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(backgroundImage, 0, 0, 960, 608);
        game.batch.draw(GameTitle,355,530,226,58 );
        game.batch.end();
        stage.act(delta);
        stage.draw();

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
