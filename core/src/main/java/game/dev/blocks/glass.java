package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;

public class glass extends blocks{
    private Texture blockTexture;
    public glass(){
        super("Blocks/GlassBlock.png", 5);
    }
    public Texture getBlockTexture(){
        return blockTexture;
    }

}
