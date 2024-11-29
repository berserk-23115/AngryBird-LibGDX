package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class concrete extends blocks{
    private Body body;

    public concrete(Body body){

        super("Blocks/ConcreteBlock.png", 24, body,"concrete");
        this.body = body;


    }

}
