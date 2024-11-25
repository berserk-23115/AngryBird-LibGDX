package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;

public class bird {
    private Texture birdTexture;
    private Integer Health;
    public bird(Texture birdTexture, Integer Health) {
        this.birdTexture = birdTexture;
        this.Health = Health;
    }
}
