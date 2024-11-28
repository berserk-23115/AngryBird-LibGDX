package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;
import java.util.ArrayList;

public class blocks implements Serializable {
    private static final long serialVersionUID = 1L;

    private transient Texture blockTexture;
    private Integer Health;
    private transient Body body;
    private String type;
    // public static ArrayList<Body> blockbodies1;
    public blocks(String blockTexture, Integer Health, Body body,String type){
        this.blockTexture = new Texture(blockTexture);
        this.Health = Health;
        this.body = body;
        this.type = type;

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

    public void reloadTexture() {
        if (blockTexture == null) {
            switch (type.toLowerCase()) {
                case "wood":
                    blockTexture = new Texture("blocks/wood.png");
                    break;
                case "glass":
                    blockTexture = new Texture("blocks/glass.png");
                    break;
                case "concrete":
                    blockTexture = new Texture("blocks/concrete.png");
                    break;
            }
        }
    }
    public void relodBodyPosition(float x, float y){
        body.setTransform(x,y,0);
    }
}
