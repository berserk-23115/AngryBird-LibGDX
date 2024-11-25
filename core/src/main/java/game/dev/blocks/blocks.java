package game.dev.blocks;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;

import java.util.ArrayList;

public class blocks {
    private Texture blockTexture;
    private Integer Health;
    public static ArrayList<Body> blockbodies1;
    public blocks(String blockTexture, Integer Health) {
        this.blockTexture = new Texture(blockTexture);
        this.Health = Health;
        blockbodies1=new ArrayList<>();
    }
    public Texture getBlockTexture(){
        return blockTexture;
    }
    public void updateBlockTexture(Texture blockTexture){
        this.blockTexture=blockTexture;
    }
}
