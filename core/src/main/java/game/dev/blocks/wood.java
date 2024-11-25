package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;

public class wood extends blocks{


    public wood(){

        super("Blocks/WoodBlock.png", 10);

    }
    public void hit1(){
        super.updateBlockTexture(new Texture("Blocks/WoodBlock_hit_1.png"));
    }

}
