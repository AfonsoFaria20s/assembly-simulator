package GUI;

import GUI.settingsComponents.AutoSavePanel;
import GUI.settingsComponents.EditorPreferencesPanel;
import GUI.settingsComponents.ShortcutsPanel;
import GUI.settingsComponents.ThemeSettingsPanel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Settings extends JFrame {
    private ThemeSettingsPanel themeSettingsPanel = new ThemeSettingsPanel();
    private Window window;

    public Settings(Window window) {
        setTitle("Settings");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        this.window = window;

        // Main panel with a scrollable area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add settings panels
        mainPanel.add(themeSettingsPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between panels
        mainPanel.add(new EditorPreferencesPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new ShortcutsPanel());
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new AutoSavePanel());

        // Scroll pane for the main panel
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Bottom panel with Save and Cancel buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        saveButton.addActionListener(e -> {
            try {
                save(themeSettingsPanel.getTheme());
            } catch (UnsupportedLookAndFeelException ex) {
                throw new RuntimeException(ex);
            }
        });
        cancelButton.addActionListener(e -> cancel());

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void save(String theme) throws UnsupportedLookAndFeelException {
        window.updateTheme(theme);
        this.dispose();
    }

    public void cancel() {
        this.dispose();
    }
}
