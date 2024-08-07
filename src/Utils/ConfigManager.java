package Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        return config.optString("defaultDirectory", "");
    }

    public void setDefaultDirectory(String directory) {
        config.put("defaultDirectory", directory);
        saveConfig();
    }

    public List<String> getRecentFiles() {
        List<String> recentFiles = new ArrayList<>();
        JSONArray recentFilesArray = config.optJSONArray("recentFiles");

        if (recentFilesArray != null) {
            for (int i = 0; i < recentFilesArray.length(); i++) {
                recentFiles.add(recentFilesArray.getString(i));
            }
        }

        return recentFiles;
    }

    public void addRecentFile(String filePath) {
        JSONArray recentFilesArray = config.optJSONArray("recentFiles");

        if (recentFilesArray == null) {
            recentFilesArray = new JSONArray();
        }

        // Ensure no duplicate entries
        if (recentFilesArray.toList().contains(filePath)) {
            recentFilesArray.remove(recentFilesArray.toList().indexOf(filePath));
        }

        // Add new file to the top of the list
        recentFilesArray.put(0, filePath);

        // Optionally limit the number of recent files (e.g., to the last 10 files)
        if (recentFilesArray.length() > 10) {
            recentFilesArray.remove(10);
        }

        config.put("recentFiles", recentFilesArray);
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
