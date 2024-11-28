package game.dev.Serialise;
import com.badlogic.gdx.physics.box2d.Body;
import game.dev.blocks.blocks;
import game.dev.pigs.pigs;
import game.dev.birds.bird;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class saveGame implements Serializable{
    private static final long serialVersionUID = 1L;

    private ArrayList<blocks> Blocks;
    private ArrayList<pigs> Pigs;
    private ArrayList<bird> Birds;
    private int levelType;//0-easy , 1-medium , 2-hard
    public saveGame(ArrayList<blocks> Blocks, ArrayList<pigs> Pigs, ArrayList<bird> Birds,int levelType){
        this.Blocks = Blocks;
        this.Pigs = Pigs;
        this.Birds = Birds;
        this.levelType = levelType;
    }
    public int getLevelType(){
        return levelType;
    }

    public ArrayList<bird> getBirds() {
            return Birds;
    }
    public ArrayList<pigs> getPigs() {
        return Pigs;
    }
    public ArrayList<blocks> getBlocks() {
        return Blocks;
    }
    public ArrayList<Body> getBirdBody(){
        ArrayList<Body> birdBody = new ArrayList<Body>();
        for(bird b:Birds){
            birdBody.add(b.getbody());
        }
        return birdBody;
    }
    public ArrayList<Body> getPigBody(){
        ArrayList<Body> pigBody = new ArrayList<Body>();
        for(pigs p:Pigs){
            pigBody.add(p.getBody());
        }
        return pigBody;
    }
    public ArrayList<Body> getBlockBody(){
        ArrayList<Body> blockBody = new ArrayList<Body>();
        for(blocks b:Blocks){
            blockBody.add(b.getBody());
        }
        return blockBody;
    }
}
