package GUI.windowComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FlagsPanel extends JPanel {
    private JCheckBox zeroFlag;
    private JCheckBox signFlag;

    public FlagsPanel() {
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createTitledBorder("Flags"));

        zeroFlag = new JCheckBox("Zero Flag");
        zeroFlag.setEnabled(false);
        signFlag = new JCheckBox("Sign Flag");
        signFlag.setEnabled(false);
        add(zeroFlag);
        add(signFlag);
    }

    public void updateFlags(int result) {
        zeroFlag.setSelected(result==0);
        signFlag.setSelected(result<0);

        this.revalidate();
        this.repaint();
    }

    public void resetFlags() {
        zeroFlag.setSelected(false);
        signFlag.setSelected(false);

        this.revalidate();
        this.repaint();
    }
}
