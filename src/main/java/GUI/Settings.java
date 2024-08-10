package GUI;

import GUI.settingsComponents.AutoSavePanel;
import GUI.settingsComponents.EditorPreferencesPanel;
import GUI.settingsComponents.ShortcutsPanel;
import GUI.settingsComponents.ThemeSettingsPanel;

import javax.swing.*;
import java.awt.*;

public class Settings extends JFrame {

    public Settings() {
        setTitle("Settings");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        // Main panel with a scrollable area
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add settings panels
        mainPanel.add(new ThemeSettingsPanel());
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

        saveButton.addActionListener(e -> save());
        cancelButton.addActionListener(e -> cancel());

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void save() {
        this.dispose();
    }

    public void cancel() {
        this.dispose();
    }
}
