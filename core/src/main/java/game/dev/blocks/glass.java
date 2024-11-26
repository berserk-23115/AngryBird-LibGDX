package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class glass extends blocks{
    private Texture blockTexture;
    private Body body;
    public glass(Body body){
        super("Blocks/GlassBlock.png", 5,body);
    }
    public Texture getBlockTexture(){
        return blockTexture;
    }

}
