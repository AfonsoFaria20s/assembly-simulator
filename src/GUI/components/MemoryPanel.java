package  GUI.components;

import Utils.Utils;
import Utils.HexCellRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MemoryPanel extends JPanel {
    private JTable memoryTable;
    private Utils utils = new Utils();
    private HexCellRenderer hexCellRenderer = new HexCellRenderer();

    public MemoryPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Memory"));

        String[] columnNames = {"Address", "Value"};
        Object[][] data = new Object[256][2];
        for (int i = 0; i < 256; i++) {
            data[i][0] = i;
            data[i][1] = 0;
        }

        memoryTable = new JTable(data, columnNames);
        memoryTable.getColumnModel().getColumn(1).setCellRenderer(hexCellRenderer);
        memoryTable.getColumnModel().getColumn(0).setCellRenderer(new HexCellRendererTwo());
        JScrollPane scrollPane = new JScrollPane(memoryTable);
        add(scrollPane, BorderLayout.CENTER);
    }
}
class HexCellRendererTwo extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check if the value is a String
        if (value instanceof String) {
            label.setText((String) value);
        } else if (value instanceof Number) {
            label.setText(String.format("0x%02X", ((Number) value).intValue()));
        }

        return label;
    }
}