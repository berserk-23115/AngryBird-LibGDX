//package game.dev.Serialise;
//
//import game.dev.Serialise.Serialise;
//
//import java.io.FileReader;
//import java.io.IOException;
//
//public class LevelDeserializer {
//    public LevelDeserializer(){
//
//    }
//    public static Serialise loadLevel(String filePath) {
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(filePath)) {
//            return gson.fromJson(reader, Serialise.class);
//        } catch (IOException e) {
//            System.err.println("Error loading level: " + e.getMessage());
//            return null;
//        }
//    }
//}
