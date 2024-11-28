package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;
import java.util.ArrayList;

public class blocks implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Texture blockTexture;
    private Integer Health;
    private Body body;
    // public static ArrayList<Body> blockbodies1;
    public blocks(String blockTexture, Integer Health, Body body){
        this.blockTexture = new Texture(blockTexture);
        this.Health = Health;
        this.body = body;

    }
    public Texture getBlockTexture(){
        return blockTexture;
    }
    public void updateBlockTexture(Texture blockTexture){
        this.blockTexture=blockTexture;
    }

    public Body getBody() {
        return body;
    }
    public Integer getHealth(){
        return Health;
    }
    public void setHealth(Integer health) {
        Health = health;
    }
    public void dispose(){
        blockTexture.dispose();
    }
}
