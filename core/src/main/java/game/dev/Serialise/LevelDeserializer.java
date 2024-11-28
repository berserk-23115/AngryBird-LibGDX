package game.dev.Serialise;


import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

public class LevelDeserializer {
    public static LevelData loadLevel(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, LevelData.class);
        } catch (IOException e) {
            System.err.println("Error loading level: " + e.getMessage());
            return null;
        }
    }
}
