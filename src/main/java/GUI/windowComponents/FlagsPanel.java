package GUI.windowComponents;

import javax.swing.*;
import java.awt.*;

public class FlagsPanel extends JPanel {
    private JCheckBox zeroFlag;
    private JCheckBox signFlag;
    private JCheckBox carryFlag;      // Added for Carry Flag
    private JCheckBox overflowFlag;   // Added for Overflow Flag

    public FlagsPanel() {
        setLayout(new GridLayout(5, 1));  // Adjusted for 4 flags
        setBorder(BorderFactory.createTitledBorder("Flags"));

        zeroFlag = new JCheckBox("Zero Flag");
        zeroFlag.setEnabled(false);
        signFlag = new JCheckBox("Sign Flag");
        signFlag.setEnabled(false);
        carryFlag = new JCheckBox("Carry Flag");       // Added
        carryFlag.setEnabled(false);
        overflowFlag = new JCheckBox("Overflow Flag"); // Added
        overflowFlag.setEnabled(false);

        add(zeroFlag);
        add(signFlag);
        add(carryFlag);
        add(overflowFlag);   // Added to layout
    }

    public void updateFlags(int result, boolean carry, boolean overflow) {
        zeroFlag.setSelected(result == 0);
        signFlag.setSelected(result < 0);
        carryFlag.setSelected(carry);           // Update carry flag
        overflowFlag.setSelected(overflow);     // Update overflow flag

        this.revalidate();
        this.repaint();
    }

    public void resetFlags() {
        zeroFlag.setSelected(false);
        signFlag.setSelected(false);
        carryFlag.setSelected(false);       // Reset carry flag
        overflowFlag.setSelected(false);    // Reset overflow flag

        this.revalidate();
        this.repaint();
    }
}
