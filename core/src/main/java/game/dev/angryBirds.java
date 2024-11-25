package game.dev;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.dev.Screens.splashBirdScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */


public class angryBirds extends Game {

    public SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();

        setScreen(new splashBirdScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
