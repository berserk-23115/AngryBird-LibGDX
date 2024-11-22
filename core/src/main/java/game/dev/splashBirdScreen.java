package game.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;

public class splashBirdScreen extends InputAdapter implements Screen {
    private angryBirds game;
    private Texture bgImage;

    public splashBirdScreen( angryBirds game){
        this.game = game;
        bgImage = new Texture("SplashScreen/firstScreen.png");
    }
    @Override
    public void show() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new MainScreen(game));
            }
        }, 3);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        // Draw the splash image centered
        game.batch.draw(bgImage,
                Gdx.graphics.getWidth() / 2f - bgImage.getWidth() / 2f,
                Gdx.graphics.getHeight() / 2f - bgImage.getHeight() / 2f);
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
