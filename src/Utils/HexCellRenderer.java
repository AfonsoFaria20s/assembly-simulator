package Utils;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class HexCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Check if the value is a String
        if (value instanceof String) {
            label.setText((String) value);
        } else if (value instanceof Number) {
            label.setText(String.format("0x%08X", ((Number) value).intValue()));
        }

        return label;
    }
}