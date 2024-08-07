package GUI.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    public MenuPanel() {
        JMenuBar menuBar = new JMenuBar();

        // Create File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to File menu
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
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
        exitItem.addActionListener(e -> System.exit(0));
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "CPU Simulator v1.0\nWith custom assembly language.",
                        "About",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add the menu bar to this panel
        setLayout(new BorderLayout());
        add(menuBar, BorderLayout.NORTH);
    }
}
