package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class chuck extends bird{
    private Body body;

    public chuck(Body body){

        super("Blocks/WoodBlock.png", 10, body);
        this.body = body;


    }
}
