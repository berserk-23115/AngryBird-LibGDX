package game.dev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.sun.java.swing.ui.SplashScreen;
import game.dev.level.easyLevel001;
import jdk.tools.jmod.Main;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */


public class angryBirds extends Game {

    public SpriteBatch batch;


    @Override
    public void create() {
        batch = new SpriteBatch();

        setScreen(new LevelSelector(this));
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
