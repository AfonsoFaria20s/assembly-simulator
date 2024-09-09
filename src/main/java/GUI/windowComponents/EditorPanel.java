package GUI.windowComponents;

import MainClasses.Assembly;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class EditorPanel extends JPanel {
    private JTextPane editorTextPane;
    private JTextArea lineNumberArea;
    private JButton executeButton;
    private Assembly assembly;
    private RegistersPanel registersPanel;
    private MemoryPanel memoryPanel;

    public EditorPanel(RegistersPanel registersPanel, MemoryPanel memoryPanel) {
        this.registersPanel = registersPanel;
        this.memoryPanel = memoryPanel;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Editor"));

        editorTextPane = new JTextPane();
        editorTextPane.setFont(new Font("Monospaced", Font.PLAIN, 16));
        JScrollPane scrollPane = new JScrollPane(editorTextPane);
        scrollPane.setRowHeaderView(createLineNumberArea());

        executeButton = new JButton("Execute");

        add(scrollPane, BorderLayout.CENTER);
        add(executeButton, BorderLayout.SOUTH);

        assembly = new Assembly(editorTextPane.getDocument().getDefaultRootElement().getElementCount(), registersPanel, memoryPanel);

        executeButton.addActionListener(event -> {
            executeCode();
        });

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
        lineNumberArea.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
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
        String code = editorTextPane.getText();
        String[] program = code.split("\\r?\\n");

        try {
            assembly = new Assembly(program.length, registersPanel, memoryPanel);
            assembly.loadProgram(program);
            assembly.execute();
        } catch (Exception e) {
            e.printStackTrace();
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            JOptionPane.showMessageDialog(
                    parentFrame,
                    e.getMessage()+"\n"+e.getClass().getName(),
                    "Error",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public JTextPane getTextEditor() {
        return editorTextPane;
    }

    public RegistersPanel getRegistersPanel() {
        return registersPanel;
    }
}