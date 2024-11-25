package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;

public class concrete extends blocks{
    private Texture blockTexture;
    public concrete(){
        super("Blocks/ConcreteBlock.png", 20);
    }
    public Texture getBlockTexture(){
        return blockTexture;
    }
    public void hit1(){
        super.updateBlockTexture(new Texture("Blocks/ConcreteBlock_hit_1.png"));
    }
    public void hit2(){
        super.updateBlockTexture(new Texture("Blocks/ConcreteBlock_hit_2.png"));
    }

}
