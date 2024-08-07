package GUI.components;

import javax.swing.*;
import java.awt.*;
import Utils.Utils;
import Utils.HexCellRenderer;

public class RegistersPanel extends JPanel {
    private JTable registersTable;
    private Utils utils;
    private HexCellRenderer hexCellRenderer = new HexCellRenderer();

    public RegistersPanel() {
        utils = new Utils();
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Registers"));

        String[] columnNames = {"Register", "Value"};
        Object[][] data = {
                {"R0", 0},
                {"R1", 0},
                {"R2", 0},
                {"R3", 0},
                {"R4", 0},
                {"R5", 0},
                {"R6", 0},
                {"R7", 0},
        };

        registersTable = new JTable(data, columnNames);
        // Custom cell renderer to display hexadecimal values
        registersTable.getColumnModel().getColumn(1).setCellRenderer(hexCellRenderer);

        JScrollPane scrollPane = new JScrollPane(registersTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
