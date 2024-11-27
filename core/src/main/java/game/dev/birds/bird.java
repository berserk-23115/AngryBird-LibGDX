package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;

public class bird {
    private Texture birdTexture;
    private Integer power;
    private Body body;
    public bird(String blockTexture, Integer Health, Body body) {
        this.birdTexture = new Texture(blockTexture);
        this.power = Health;
        this.body=body;
    }
    public Texture getBirdTexture() {
        return birdTexture;
    }
    public Integer getPower() {
        return power;
    }
    public Body getbody(){
        return body;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
