package game.dev.Serialise;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import game.dev.blocks.blocks;
import game.dev.pigs.pigs;
import game.dev.birds.bird;

public class LevelData {
    @SerializedName("blocks")
    private List<Block> blocks;

    @SerializedName("pigs")
    private List<Pig> pigs;

    @SerializedName("bird")
    private List<Bird> birds;


    // Getters and setters
    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
    public void setPigs(List<Pig> pigs) {
        this.pigs = pigs;
    }
    public void setBirds(List<Bird> birds) {
        this.birds = birds;
    }
    public static class Block {
        private String type;
        private float x;
        private float y;
        private int width=60,height=60;
        private float health;


        public Block(blocks block){
            this.type=block.getType();
            this.x=block.getBody().getPosition().x;
            this.y=block.getBody().getPosition().y;
            this.health=block.getHealth();
        }



        // Constructor, getters, and setters
    }

    public static class Pig {
        private float x;
        private float y;
        private int health;
        private String type;
        private int width=60,height=60;
        public Pig(pigs pig){
            this.type=pig.getType();
            this.x=pig.getBody().getPosition().x;
            this.y=pig.getBody().getPosition().y;
            this.health=pig.getHealth();
        }
        // Constructor, getters, and setters
    }

    public static class Bird {
        private String type;
        private float x;
        private float y;
        private int width=30,height=30;
        private int power;
        public Bird(bird bird){
            this.type=bird.getType();
            this.x=bird.getbody().getPosition().x;
            this.y=bird.getbody().getPosition().y;
            this.power=bird.getPower();
        }

        // Constructor, getters, and setters
    }


}
