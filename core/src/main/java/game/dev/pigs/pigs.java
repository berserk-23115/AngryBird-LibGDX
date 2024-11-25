package game.dev.pigs;

import com.badlogic.gdx.graphics.Texture;

public class pigs {
    private Texture pigTexture;
    private Integer Health;
    public pigs(Texture pigTexture, Integer Health) {
        this.pigTexture = pigTexture;
        this.Health = Health;
    }
}
