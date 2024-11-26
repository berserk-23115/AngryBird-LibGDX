package game.dev.Screens;

import com.badlogic.gdx.Gdx;
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

public class pauseMenu implements Screen {
    private angryBirds game;
    private Stage stage;
    private Sound clickSound;
    private Texture pauseMenuTexture, saveBtnTexture, resetBtnTexture, exitBtnTexture, playBtnTexture;

    public pauseMenu(angryBirds game) {
        this.game = game;

        // Load assets
        pauseMenuTexture = new Texture("pauseMenu/pauseMenu.png");
        saveBtnTexture = new Texture("pauseMenu/saveBtn.png");
        resetBtnTexture = new Texture("pauseMenu/reset.png");
        exitBtnTexture = new Texture("LevelScreen/exitbtn.png");
        playBtnTexture = new Texture("LevelScreen/playbtn.png");
        clickSound = Gdx.audio.newSound(Gdx.files.internal("music/click.ogg"));

        // Initialize stage and viewport
        stage = new Stage(new ScreenViewport());

        // Create buttons
        ImageButton saveButton = createButton(saveBtnTexture, 370, 240);
        ImageButton resetButton = createButton(resetBtnTexture, 490, 240);
        ImageButton exitButton = createButton(exitBtnTexture, 10, 10);
        exitButton.setSize(50,46);
        ImageButton playButton = createButton(playBtnTexture, 550, 240);

        // Add button listeners
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                // Add save functionality here
            }
        });

        resetButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                // Add reset functionality here
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                game.setScreen(new LevelSelector(game));
            }
        });

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                // Add resume functionality here
            }
        });

        // Add buttons to stage
        stage.addActor(saveButton);
        stage.addActor(resetButton);
        stage.addActor(exitButton);
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
        // No implementation needed
    }

    @Override
    public void render(float delta) {
        // Clear the screen


        // Draw the background
        game.batch.begin();
        game.batch.draw(pauseMenuTexture, 350, 200, 272, 213);
        game.batch.end();

        // Draw the stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        // No implementation needed
    }

    @Override
    public void resume() {
        // No implementation needed
    }

    @Override
    public void hide() {
        // No implementation needed
    }

    @Override
    public void dispose() {
        // Dispose resources
        stage.dispose();
        clickSound.dispose();
        pauseMenuTexture.dispose();
        saveBtnTexture.dispose();
        resetBtnTexture.dispose();
        exitBtnTexture.dispose();
        playBtnTexture.dispose();
    }
}
