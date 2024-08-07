package GUI.components;

import Utils.ConfigManager;
import Utils.FileHandler;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    private FileHandler fileHandler;
    private ConfigManager configManager = new ConfigManager("src/data/data.json");

    public MenuPanel(EditorPanel editorPanel, Window window) {
        fileHandler = new FileHandler(editorPanel, (GUI.Window) window, configManager);

        JMenuBar menuBar = new JMenuBar();

        // Create File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem closeItem = new JMenuItem("Close");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to File menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);
        fileMenu.add(settings);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        // Add File menu to menu bar
        menuBar.add(fileMenu);

        // Create Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        // Add menu items to Help menu
        helpMenu.add(aboutItem);
        // Add Help menu to menu bar
        menuBar.add(helpMenu);

        // Add action listeners
        openItem.addActionListener(e -> fileHandler.openFile());
        saveItem.addActionListener(e -> fileHandler.saveFile());
        closeItem.addActionListener(e -> fileHandler.closeFile());
        exitItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "CPU Simulator v1.0\nWith custom assembly language.",
                "About",
                JOptionPane.INFORMATION_MESSAGE));

        // Add the menu bar to this panel
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
    }
}
