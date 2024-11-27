package game.dev.pigs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

public class pigs {
    private Texture pigTexture;
    private Integer Health;
    private Body body;
    // public static ArrayList<Body> blockbodies1;
    public pigs(String pigTexture, Integer Health, Body body){
        this.pigTexture = new Texture(pigTexture);
        this.Health = Health;
        this.body = body;

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
        return pigTexture;
    }
    public Body getBody() {
        return body;
    }
    public void dispose(){
        pigTexture.dispose();
    }
}
