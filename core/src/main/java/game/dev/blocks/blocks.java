package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;

public class blocks {
    private Texture blockTexture;
    private Integer Health;
    public blocks(String blockTexture, Integer Health) {
        this.blockTexture = new Texture(blockTexture);
        this.Health = Health;
    }


}