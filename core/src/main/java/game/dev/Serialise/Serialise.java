//package game.dev.Serialise;
//
//import game.dev.birds.bird;
//import game.dev.blocks.blocks;
//import game.dev.pigs.pigs;
//
//import java.util.ArrayList;
//import java.util.List;
//import com.google.gson.annotations.SerializedName;
//
//import static game.dev.catapult.catapult.PPM;
//
//public class Serialise {
//
//    @SerializedName("blocks")
//    private List<Block> blocks;
//
//    @SerializedName("pigs")
//    private List<Pig> pigs;
//
//    @SerializedName("birds")
//    private List<Bird> birds;
//
//
//    public static class Block{
//        private float posX;
//        private float posY;
//        private float width=60/PPM;
//        private float height=60/PPM;
//        private float health;
//        private String type;
//
//    }
//
//
//    public static class Pig{
//        private float posX;
//        private float posY;
//        private float width=60/PPM;
//        private float height=60/PPM;
//        private int health;
//        private String type;
//
//    }
//
//
//
//    public static class Bird{
//        private float posX;
//        private float posY;
//        private float width= 30;
//        private float height= 30;
//        private String type;
//
//    }
//}
