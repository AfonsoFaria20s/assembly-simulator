package GUI.settingsComponents;

import javax.swing.*;
import java.awt.*;

public class ThemeSettingsPanel extends JPanel {

    public ThemeSettingsPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Theme Settings"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Create components for theme settings
        JLabel themeLabel = new JLabel("Theme:");
        JComboBox<String> themeComboBox = new JComboBox<>(new String[]{"Light", "Dark"});
        JLabel fontSizeLabel = new JLabel("Font Size:");
        JSpinner fontSizeSpinner = new JSpinner(new SpinnerNumberModel(14, 8, 72, 1));

        // Add components to panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(themeLabel, gbc);

        gbc.gridx = 1;
        add(themeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(fontSizeLabel, gbc);

        gbc.gridx = 1;
        add(fontSizeSpinner, gbc);
    }
}
