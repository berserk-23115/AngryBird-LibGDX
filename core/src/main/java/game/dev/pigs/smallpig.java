package game.dev.pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class smallpig extends pigs{
    public smallpig(Body body) {
        super("Pigs/pig.png", 5,body,"smallpig");
    }
}
