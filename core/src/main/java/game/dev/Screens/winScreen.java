package game.dev.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.dev.angryBirds;
import game.dev.level.easyLevel001;

import static com.sun.java.swing.ui.CommonUI.createButton;

public class winScreen  implements Screen {
    private angryBirds game;
    private Stage stage;
    private Sound clickSound;
    private Texture winMenuTexture,  resetBtnTexture,menuBtnTexture,playBtnTexture;
    public winScreen(angryBirds game) {
        this.game = game;

        // Load assets
        winMenuTexture = new Texture("loseScreen/levelWin.png");
        menuBtnTexture = new Texture("loseScreen/menuBtn.png");
        resetBtnTexture = new Texture("pauseMenu/reset.png");
        playBtnTexture = new Texture("LevelScreen/playbtn.png");

        clickSound = Gdx.audio.newSound(Gdx.files.internal("music/click.ogg"));

        // Initialize stage and viewport
        stage = new Stage(new ScreenViewport());

        // Create buttons

        ImageButton resetButton = createButton(resetBtnTexture,375 , 240);
        ImageButton menuButton = createButton(menuBtnTexture,455 , 190);
        ImageButton playButton = createButton(playBtnTexture, 555, 240);



        // Add button listeners


        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                game.setScreen(new easyLevel001(game));
                // Add reset functionality here
            }
        });
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                game.setScreen(new LevelSelector(game));

                // Add resume functionality here
            }
        });



        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                game.setScreen(new LevelSelector(game));
                // Add resume functionality here
            }
        });

        // Add buttons to stage
        stage.addActor(menuButton);
        stage.addActor(resetButton);
        stage.addActor(playButton);


        // Set the stage as the input processor
        Gdx.input.setInputProcessor(stage);
    }
    private ImageButton createButton(Texture texture, float x, float y) {
        TextureRegionDrawable drawable = new TextureRegionDrawable(texture);
        ImageButton button = new ImageButton(drawable);
        button.setPosition(x, y);
        return button;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(winMenuTexture, 350, 200, 272, 168);
        game.batch.end();

        // Draw the stage
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
