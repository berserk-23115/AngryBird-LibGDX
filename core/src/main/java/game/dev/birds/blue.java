package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class blue extends bird{
    private Body body;

    public blue(Body body){

        super("Birds/blue.png", 8, body,"blue");
        this.body = body;


    }
}
