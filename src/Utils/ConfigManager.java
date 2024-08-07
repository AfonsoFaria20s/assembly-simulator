package Utils;

import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConfigManager {
    private JSONObject config;

    public ConfigManager(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            config = new JSONObject(content);
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in production code
        }
    }

    public String getDefaultDirectory() {
        return config.getString("defaultDirectory");
    }

    public void setDefaultDirectory(String directory) {
        config.put("defaultDirectory", directory);
        saveConfig();
    }

    private void saveConfig() {
        try {
            Files.write(Paths.get("src/data/data.json"), config.toString(4).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            // Handle exceptions properly in production code
        }
    }

    // Additional getters and setters for other settings
}
