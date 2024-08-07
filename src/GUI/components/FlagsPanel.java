package GUI.components;

import javax.swing.*;
import java.awt.*;

public class FlagsPanel extends JPanel {
    private JCheckBox zeroFlag;
    private JCheckBox carryFlag;
    private JCheckBox signFlag;

    public FlagsPanel() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Flags"));

        zeroFlag = new JCheckBox("Zero Flag");
        carryFlag = new JCheckBox("Carry Flag");
        signFlag = new JCheckBox("Sign Flag");

        add(zeroFlag);
        add(carryFlag);
        add(signFlag);
    }
}
