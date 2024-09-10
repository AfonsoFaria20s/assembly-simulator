package GUI.windowComponents;

import GUI.Settings;
import Utils.ConfigManager;
import Utils.FileHandler;
import GUI.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.TimerTask;

public class MenuPanel extends JPanel {
    private FileHandler fileHandler;

    public MenuPanel(EditorPanel editorPanel, Window window, File dataFile) {
        // Pass dataFile to ConfigManager
        ConfigManager configManager = new ConfigManager(dataFile);

        // Pass ConfigManager to FileHandler
        fileHandler = new FileHandler(editorPanel, window, configManager, new File(configManager.getProgramFolder()));

        JMenuBar menuBar = new JMenuBar();

        // Create File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        JMenuItem closeItem = new JMenuItem("Close");
        JMenuItem settings = new JMenuItem("Settings");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to File menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(saveAsItem);
        fileMenu.add(closeItem);
        fileMenu.add(settings);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        // Add File menu to menu bar
        menuBar.add(fileMenu);

        JMenu runMenu = new JMenu("Run");
        JMenuItem executeFile = new JMenuItem("Execute");
        JMenuItem pauseExecution = new JMenuItem("Pause");
        JMenuItem resumeExecution = new JMenuItem("Resume");
        JMenuItem stopExecution = new JMenuItem("Stop");
        JSeparator separator = new JSeparator();
        JMenuItem resetExecution = new JMenuItem("Reset values");

        runMenu.add(executeFile);
        runMenu.add(pauseExecution);
        runMenu.add(resumeExecution);
        runMenu.add(stopExecution);
        runMenu.add(separator);
        runMenu.add(resetExecution);
        menuBar.add(runMenu);

        // Add action listeners
        openItem.addActionListener(e -> fileHandler.openFile());
        saveItem.addActionListener(e -> fileHandler.saveFile());
        saveAsItem.addActionListener(e -> fileHandler.saveAsFile());
        closeItem.addActionListener(e -> fileHandler.closeFile());
        settings.addActionListener(e -> new Settings().setVisible(true));
        exitItem.addActionListener(e -> System.exit(0));

        // Create Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        // Add menu items to Help menu
        helpMenu.add(aboutItem);
        // Add Help menu to menu bar
        menuBar.add(helpMenu);

        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(null,
                "CPU Simulator v1.0\nWith custom assembly language.",
                "About",
                JOptionPane.INFORMATION_MESSAGE));

        executeFile.addActionListener( e -> editorPanel.executeCode());
        pauseExecution.addActionListener(e -> editorPanel.pauseCode());
        resumeExecution.addActionListener(e -> editorPanel.resumeCode());
        stopExecution.addActionListener(e -> editorPanel.stopCode());
        resetExecution.addActionListener(e -> editorPanel.resetValues());

        // Add the menu bar to this panel
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}
