package MainClasses;

import GUI.Window;
import Utils.ConfigManager;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    private static ConfigManager configManager;
    public static void generateDefaultProgramDirectory(File programFolder, File projectsFolder, File dataFile, File tempFile) throws IOException {
        createIfExists(programFolder, true);
        createIfExists(projectsFolder, true);
        createIfExists(tempFile, false);
        createDataJsonIfNotExists(dataFile);
    }

    public static void createIfExists(File file, boolean isFolder) throws IOException {
        if(isFolder) {
            if (!file.exists()) {
                boolean created = file.mkdirs(); // mkdirs() creates directories including any necessary but nonexistent parent directories
                if (created) {
                    System.out.println("Directory created: " + file.getAbsolutePath());
                } else {
                    System.out.println("Directory already exists or could not be created.");
                }
            } else {
                System.out.println("Directory already exists: " + file.getAbsolutePath());
            }
        } else {
            try (FileWriter writer = new FileWriter(file.getAbsolutePath())) {
                writer.write("");
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void createDataJsonIfNotExists(File dataFile) {
        if (!dataFile.exists()) {
            try (FileWriter writer = new FileWriter(dataFile)) {
                String defaultJsonContent = "{\n" +
                        "    \"lastOpen\": [\"\"],\n" +
                        "    \"settings\": {\n" +
                        "        \"theme\": \"light\",\n" +
                        "        \"fontSize\": 14,\n" +
                        "        \"autoSaveInterval\": 5\n" +
                        "    },\n" +
                        "    \"editorPreferences\": {\n" +
                        "        \"numberType\": \"hex\",\n" +
                        "        \"lineWrapping\": true,\n" +
                        "        \"highlightCurrentLine\": true\n" +
                        "    },\n" +
                        "    \"defaultDirectory\": \"\",\n" +
                        "    \"shortcuts\": {\n" +
                        "        \"openFile\": \"Ctrl+O\",\n" +
                        "        \"saveFile\": \"Ctrl+S\"\n" +
                        "    }\n" +
                        "}";
                writer.write(defaultJsonContent);
                System.out.println("data.json created with default content: " + dataFile.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to create data.json: " + e.getMessage());
            }
        } else {
            System.out.println("data.json already exists: " + dataFile.getAbsolutePath());
        }
    }

    public static void main(String[] args) throws IOException {
        // Initialize user home and directories
        String userHome = System.getProperty("user.home");
        File programFolder = new File(userHome, "Assembly Simulator");
        File projectsFolder = new File(programFolder, "Projects");
        File dataFile = new File(programFolder, "data.json");
        File tempFile = new File(programFolder, "temp.asm");

        // Generate directories and create the data.json file if it doesn't exist
        generateDefaultProgramDirectory(programFolder, projectsFolder, dataFile, tempFile);

        // Pass the data file to the Window class
        try {
            Window window = new Window(dataFile);

            // Load the last open file (if any)
            configManager = new ConfigManager(dataFile);
            String lastOpenFilePath = configManager.getLastOpenFilePath();
            if (lastOpenFilePath != null && !lastOpenFilePath.isEmpty()) {
                window.openLastFile(lastOpenFilePath);
            }

            window.repaint();
            window.revalidate();

        } catch (IOException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
            System.exit(1); // Exit the program if the window fails to open
        }
    }
}