package game.dev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class MainScreen implements Screen {
    private angryBirds game;
    private Texture image;
    private OrthographicCamera gameCam;

    public MainScreen(angryBirds game) {
        this.game = game;
        image = new Texture("libgdx.png"); // Ensure the path is correct for your image
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, 960, 608); // Set a default resolution
    }

    @Override
    public void show() {
        // Initialize resources here if needed
    }

    @Override
    public void render(float delta) {
        // Set the background color (RGBA values)
        Gdx.gl.glClearColor(0.1f, 0.2f, 0.4f, 1f); // Example: dark blue
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  // Clear the screen

        // Render the texture
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(image, 0, 0);
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
        image.dispose();
    }
}
