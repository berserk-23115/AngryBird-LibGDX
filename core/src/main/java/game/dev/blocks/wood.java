package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class wood extends blocks{
private Body body;

    public wood(Body body){

        super("Blocks/WoodBlock.png", 12, body);
        this.body = body;


    }
    public void hit1(){
        super.updateBlockTexture(new Texture("Blocks/WoodBlock_hit_1.png"));
    }

}
