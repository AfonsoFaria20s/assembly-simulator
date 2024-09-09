package GUI;

import GUI.windowComponents.EditorPanel;
import GUI.windowComponents.FlagsPanel;
import GUI.windowComponents.MenuPanel;
import GUI.windowComponents.MemoryPanel;
import GUI.windowComponents.RegistersPanel;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private MenuPanel menuPanel;

    public Window(File dataFile) throws IOException {
        setTitle("Assembly Simulator");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // UIManager.setLookAndFeel();
        try {
            UIManager.setLookAndFeel( new FlatLightLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        // Create panels for registers, memory, flags, editor, and menu
        RegistersPanel registersPanel = new RegistersPanel();
        MemoryPanel memoryPanel = new MemoryPanel();
        FlagsPanel flagsPanel = new FlagsPanel();
        EditorPanel editorPanel = new EditorPanel(registersPanel, memoryPanel);

        // Pass the dataFile to the MenuPanel
        menuPanel = new MenuPanel(editorPanel, this, dataFile);

        // Left panel (registers and memory)
        JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, registersPanel, memoryPanel);
        leftSplitPane.setDividerLocation(200);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(leftSplitPane, BorderLayout.CENTER);
        leftPanel.add(flagsPanel, BorderLayout.SOUTH);

        // Right panel (editor)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(editorPanel, BorderLayout.CENTER);

        // Main split pane to divide left and right panels
        JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        mainSplitPane.setDividerLocation(250);

        // Add the menu panel to the frame
        add(menuPanel, BorderLayout.NORTH);
        // Add main split pane to the frame
        add(mainSplitPane, BorderLayout.CENTER);
    }

    public void openLastFile(String lastOpenFilePath) {
        menuPanel.getFileHandler().openFile(new File(lastOpenFilePath));
    }

    public void updateTitle(String title) {
        this.setTitle("Assembly Simulator - " + title);
    }
}
