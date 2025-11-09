package com.calculator.ui;

import javax.swing.*;
import java.awt.*;

public class DisplayPanel extends JPanel {
    private final JTextField display=new JTextField("0");
    public DisplayPanel(){
        setLayout(new BorderLayout());
        display.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(display, BorderLayout.CENTER);
    }
    public void setText(String t){ display.setText(t); }
    public String getText(){ return display.getText(); }
}
