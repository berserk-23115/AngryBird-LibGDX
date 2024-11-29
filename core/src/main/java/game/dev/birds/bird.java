package game.dev.birds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import java.io.Serializable;

public class bird {
    private static final long serialVersionUID = 1L;

    private Texture birdTexture;
    private Integer power;
    private Body body;
    int index=0,indexC=0;
    private String type;
    public bird(String blockTexture, Integer Health, Body body, String type) {
        this.birdTexture = new Texture(blockTexture);
        this.power = Health;
        this.body=body;
        this.type =type;
    }
    public bird(Integer Health){
        this.power = Health;
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

    public void reloadTexture() {
        if (birdTexture == null) {
            switch (type.toLowerCase()) {
                case "red":
                    birdTexture = new Texture("birds/red.png");
                    break;
                case "blue":
                    birdTexture = new Texture("birds/blue.png");
                    break;
                case "chuck":
                    birdTexture = new Texture("birds/chuck.png");
                    break;
                default:
                    birdTexture = new Texture("birds/default.png");
                    break;
            }
        }
    }
    public void relodBodyPosition(float x, float y){
        body.setTransform(x,y,0);
    }
    public String getType(){
        return type;
    }
}
