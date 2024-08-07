package GUI.components;

import javax.swing.*;
import java.awt.*;

public class EditorPanel extends JPanel {
    private JTextArea editorTextArea;
    private JButton executeButton;

    public EditorPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Editor"));

        editorTextArea = new JTextArea(10, 70);
        JScrollPane scrollPane = new JScrollPane(editorTextArea);

        executeButton = new JButton("Execute");

        add(scrollPane, BorderLayout.CENTER);
        add(executeButton, BorderLayout.SOUTH);

        executeButton.addActionListener(e -> executeCode());
    }

    private void executeCode() {
        String code = editorTextArea.getText();
        // TODO: Add code execution logic here
        System.out.println("Executing code:\n" + code);
    }
}
