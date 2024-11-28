package game.dev.pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.io.Serializable;

public class pigs {
    private static final long serialVersionUID = 1L;

    private Texture texture;
    private Integer Health;
    private Body body;
    private String type;
    // public static ArrayList<Body> blockbodies1;
    public pigs(String pigTexture, Integer Health, Body body,String type){
        this.texture = new Texture(pigTexture);
        this.Health = Health;
        this.body = body;
        this.type = type;

    }

    public Integer getHealth() {
        return Health;
    }
    public void setHealth(Integer health) {
        Health = health;
    }
    public Body getbody(){
        return body;
    }
    public Texture getpigTexture(){
        return texture;
    }
    public Body getBody() {
        return body;
    }
    public void dispose(){
        texture.dispose();
    }
    public void relodBodyPosition(float x, float y){
        body.setTransform(x,y,0);
    }
    public String getType(){
        return type;
    }
    public void reloadTexture() {
        if (texture == null) {
            switch (type.toLowerCase()) {
                case "kingpig":
                    texture = new Texture("pigs/kingpig.png");
                    break;
                case "mediumgpig":
                    texture = new Texture("pigs/mediumgpig.png");
                    break;
                case "smallpig":
                    texture = new Texture("pigs/smallpig.png");
                    break;
                default:
                    texture = new Texture("pigs/defaultpig.png");
                    break;
            }
        }
    }
}
