package GUI;

import GUI.components.*;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Window extends JFrame {
    private final RegistersPanel registersPanel = new RegistersPanel();
    public Window() throws IOException {
        setTitle("Assembly Simulator");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Create panels for registers, memory, flags, editor, and menu
        RegistersPanel registersPanel = new RegistersPanel();
        MemoryPanel memoryPanel = new MemoryPanel();
        FlagsPanel flagsPanel = new FlagsPanel();
        EditorPanel editorPanel = new EditorPanel(registersPanel, memoryPanel); // Pass RegistersPanel to EditorPanel
        MenuPanel menuPanel = new MenuPanel(editorPanel, this);

        // Left panel (registers and memory)
        JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, registersPanel, memoryPanel);
        leftSplitPane.setDividerLocation(200);

        // Left side with flags at the bottom
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

        File file = new File("src/data/data.json");
    }

    public void updateTitle(String title) {
        this.setTitle("Assembly Simulator - " + title);
    }
}
