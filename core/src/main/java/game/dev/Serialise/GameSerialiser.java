//package game.dev.Serialise;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class GameSerializer {
//
//    public static void saveLevel(Serialise levelData, String filePath) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        try (FileWriter writer = new FileWriter(filePath)) {
//            gson.toJson(levelData, writer);
//            System.out.println("Level serialized successfully to " + filePath);
//        } catch (IOException e) {
//            System.err.println("Error saving level: " + e.getMessage());
//        }
//    }
//}
