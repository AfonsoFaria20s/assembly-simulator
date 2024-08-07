package Utils;

import GUI.Window;
import GUI.components.EditorPanel;

import javax.swing.*;
import java.io.*;

public class FileHandler {

    private File file;
    private JFileChooser fileChooser;
    private EditorPanel editorPanel;
    private Window window;
    private ConfigManager configManager;

    public FileHandler(EditorPanel editorPanel, Window window, ConfigManager configManager) {
        this.editorPanel = editorPanel;
        this.window = window;
        this.configManager = configManager;
        fileChooser = new JFileChooser(configManager.getDefaultDirectory());
    }

    public void openFile() {
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                editorPanel.getTextEditor().setText(content.toString());

                // Update title to current directory/file
                window.updateTitle(file.getAbsolutePath());

                // Save a copy to temporary file
                saveToTempFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error reading the file: " + ex.getMessage(),
                        "File Read Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveToTempFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Temporary/temp.txt"))) {
            writer.write(editorPanel.getTextEditor().getText());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving to temporary file: " + ex.getMessage(),
                    "Temporary File Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveFile() {
        if (file == null) {
            int returnValue = fileChooser.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                return; // Save operation canceled by the user
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(editorPanel.getTextEditor().getText());
            saveToTempFile(); // Update the temp file after saving
            // Update title to current directory/file
            window.updateTitle(file.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving the file: " + ex.getMessage(),
                    "File Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void closeFile() {
        if (file == null) return;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(editorPanel.getTextEditor().getText());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving the file: " + ex.getMessage(),
                    "File Save Error", JOptionPane.ERROR_MESSAGE);
        }

        try (BufferedReader mainFileReader = new BufferedReader(new FileReader(file));
             BufferedReader tempFileReader = new BufferedReader(new FileReader("src/Temporary/temp.txt"))) {

            StringBuilder mainContent = new StringBuilder();
            String line;
            while ((line = mainFileReader.readLine()) != null) {
                mainContent.append(line).append("\n");
            }

            StringBuilder tempContent = new StringBuilder();
            while ((line = tempFileReader.readLine()) != null) {
                tempContent.append(line).append("\n");
            }

            if (!mainContent.toString().equals(tempContent.toString())) {
                int response = JOptionPane.showConfirmDialog(null,
                        "The file has been modified. Do you want to save changes?",
                        "Confirm Save", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    saveFile();
                }
            }

            file = null;
            editorPanel.getTextEditor().setText("");

            // Update title to default text
            window.updateTitle("Assembly Simulator");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error during file close operation: " + ex.getMessage(),
                    "File Close Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
