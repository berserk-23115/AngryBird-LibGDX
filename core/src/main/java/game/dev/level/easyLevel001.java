package game.dev.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2D;
import game.dev.angryBirds;

public class easyLevel001 implements Screen {
    private angryBirds game;
    private OrthographicCamera gameCam;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private TiledMap map;
    private TmxMapLoader mapLoader;

    // Map dimensions (in pixels)
    private int mapWidth;
    private int mapHeight;

    public easyLevel001(angryBirds game) {
        this.game = game;

        // Initialize camera
        gameCam = new OrthographicCamera();
        gameCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Dynamic viewport

        // Load map and initialize renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("TileMaps/level-updated.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(map);

        // Get map dimensions in pixels
        mapWidth = map.getProperties().get("width", Integer.class) * map.getProperties().get("tilewidth", Integer.class);
        mapHeight = map.getProperties().get("height", Integer.class) * map.getProperties().get("tileheight", Integer.class);

        // Set camera position to the center of the map
        gameCam.position.set(mapWidth / 2f, mapHeight / 2f, 0);
        gameCam.update();
    }

    public void update(float delta) {
        // Update the camera
        gameCam.update();
        tiledMapRenderer.setView(gameCam);
    }

    @Override
    public void show() {
        // Initialize Box2D (physics engine)
        Box2D.init();
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1); // Black background
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Update game logic
        update(delta);

        // Render the tiled map
        tiledMapRenderer.render();
    }

    @Override
    public void resize(int width, int height) {
        // Adjust the viewport if the window size changes
        gameCam.viewportWidth = width;
        gameCam.viewportHeight = height;
        gameCam.update();
    }

    @Override
    public void pause() {
        // Handle game pause logic (if needed)
    }

    @Override
    public void resume() {
        // Handle game resume logic (if needed)
    }

    @Override
    public void hide() {
        // Clean up resources when this screen is hidden
    }

    @Override
    public void dispose() {
        // Dispose of resources to avoid memory leaks
        if (map != null) map.dispose();
        if (tiledMapRenderer != null) tiledMapRenderer.dispose();
    }
}
