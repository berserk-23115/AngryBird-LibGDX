package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class wood extends blocks{


    public wood(Body body){

        super("Blocks/WoodBlock.png", 10);
        blockbodies1.add(body);


    }
    public void hit1(){
        super.updateBlockTexture(new Texture("Blocks/WoodBlock_hit_1.png"));
    }

}
