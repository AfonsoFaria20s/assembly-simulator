package GUI.components;

import MainClasses.Assembly;
import Utils.FileHandler;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EditorPanel extends JPanel {
    private JTextPane editorTextPane;
    private JTextArea lineNumberArea;
    private JButton executeButton;
    private Assembly assembly;

    public EditorPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Editor"));

        editorTextPane = new JTextPane();
        editorTextPane.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(editorTextPane);
        scrollPane.setRowHeaderView(createLineNumberArea());

        executeButton = new JButton("Execute");

        add(scrollPane, BorderLayout.CENTER);
        add(executeButton, BorderLayout.SOUTH);

        executeButton.addActionListener(e -> executeCode());

        // Update line numbers when document changes
        editorTextPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateLineNumbers();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateLineNumbers();
            }
        });
    }

    private JTextArea createLineNumberArea() {
        lineNumberArea = new JTextArea();
        lineNumberArea.setEditable(false);
        lineNumberArea.setBorder(new LineBorder(Color.LIGHT_GRAY,1,false));
        lineNumberArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        updateLineNumbers();
        return lineNumberArea;
    }

    private void updateLineNumbers() {
        StringBuilder lineNumbers = new StringBuilder();
        int totalLines = editorTextPane.getDocument().getDefaultRootElement().getElementCount();
        for (int i = 0; i < totalLines; i++) {
            lineNumbers.append(String.format("0x%08X", i)).append(System.lineSeparator());
        }
        lineNumberArea.setText(lineNumbers.toString());
    }

    private void executeCode() {
        // Get the code from the editor text pane
        String code = editorTextPane.getText();

        // Split the code into lines
        String[] program = code.split("\\r?\\n");

        try {
            // Create an Assembly instance with appropriate size
            Assembly asm = new Assembly(program.length);
            asm.loadProgram(program);
            asm.execute();

        } catch (Exception e) {
            // Handle exceptions such as invalid instructions
            e.printStackTrace();
            System.err.println("Error executing code: " + e.getMessage());
        }
    }

    public JTextPane getTextEditor() {
        return editorTextPane;
    }
}
