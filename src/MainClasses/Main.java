package MainClasses;

import Utils.ConfigManager;
import Utils.LoadingScreen;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {
    private static ConfigManager configManager = new ConfigManager("src/data/data.json");
    private static String userHome;
    private static File programFolder;
    private static File projectsFolder;

    public static String getUserHome() {
        return userHome;
    }

    public static void setUserHome(String userHome) {
        Main.userHome = userHome;
    }

    public static void generateDefaultProgramDirectory(File programFolder, File projectsFolder) {
        createIfExists(programFolder);
        createIfExists(projectsFolder);
    }

    public static void createIfExists(File file) {
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
    }

    public static void main(String[] args) {
        // Initialize user home and directories
        userHome = System.getProperty("user.home");
        programFolder = new File(userHome, "Assembly Simulator");
        projectsFolder = new File(programFolder, "Projects");
        generateDefaultProgramDirectory(programFolder, projectsFolder);
        configManager.setDefaultDirectory(programFolder.getAbsolutePath());

        // Start the main application window
        SwingUtilities.invokeLater(() -> {
            LoadingScreen loadingScreen = new LoadingScreen();
            loadingScreen.setVisible(true);

            loadingScreen.dispose();
            Window window = new Window();
        });
    }
}
