package game.dev.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import game.dev.angryBirds;

public class splashBirdScreen implements Screen {
    private angryBirds game;
    private Texture bgImage;
    private ShapeRenderer shapeRenderer;
    private AssetManager assetManager;
    private float progress;

    public splashBirdScreen( angryBirds game){
        this.game = game;
        bgImage = new Texture("SplashScreen/firstScreen.png");
        shapeRenderer= new ShapeRenderer();
        assetManager = new AssetManager();
    }
    @Override
    public void show() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                game.setScreen(new winScreen(game));
            }
        }, 3);

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        // Draw the splash image centered
//        game.batch.draw(bgImage,
//                Gdx.graphics.getWidth() / 2f - bgImage.getWidth() / 2f,
//                Gdx.graphics.getHeight() / 2f - bgImage.getHeight() / 2f);
        game.batch.draw(bgImage,0,0,960,608);
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
        bgImage.dispose();
    }

}
