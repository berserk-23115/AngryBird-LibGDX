package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class glass extends blocks{
    private Body body;

    public glass(Body body){

        super("Blocks/GlassBlock.png", 8, body,"glass");
        this.body = body;


    }


}
