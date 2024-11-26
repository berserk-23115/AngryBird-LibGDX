package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class red extends bird{
    private Body body;

    public red(Body body){

        super("Birds/red.png", 15, body);
        this.body = body;


    }
}
