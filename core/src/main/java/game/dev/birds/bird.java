package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import java.io.Serializable;

public class bird implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Texture birdTexture;
    private Integer power;
    private Body body;
    int index=0,indexC=0;
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
    public void setIndex(int i){
        index=i;
    }
    public int getIndex(){
        return index;
    }
    public void setIndexC(int i){
        indexC=i;
    }
    public int getIndexC(){
        return indexC;
    }
}
