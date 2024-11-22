package game.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MainScreen implements Screen {
    private angryBirds game;
    private Texture image;
    private OrthographicCamera gameCam;
    private Viewport gamescreenport;

    public MainScreen( angryBirds game){
        this.game = game;
        image = new Texture("libgdx.png");
        gameCam = new OrthographicCamera();
        gamescreenport = new FitViewport(720,480,gameCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
    game.batch.begin();
    game.batch.setProjectionMatrix(gameCam.combined);
    game.batch.draw(image, 0, 0);
    game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamescreenport.update(width,height);

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
