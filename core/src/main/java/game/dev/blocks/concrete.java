package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class concrete extends blocks{
    private Texture blockTexture;
    private Body body;
    public concrete(Body body){
        super("Blocks/ConcreteBlock.png", 20,body);
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
