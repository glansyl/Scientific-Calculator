package com.calculator.ui;

import com.calculator.core.CalculatorEngine;
import javax.swing.*;

public class MemoryPanel extends JPanel {
    public MemoryPanel(CalculatorEngine engine, DisplayPanel disp){
        setLayout(new java.awt.GridLayout(1,5,2,2));
        addButton("MS", e-> engine.memoryStore());
        addButton("MR", e-> disp.setText(Double.toString(engine.memoryRecall())));
        addButton("MC", e-> engine.memoryClear());
        addButton("M+", e-> engine.memoryAdd());
        addButton("M-", e-> engine.memorySub());
    }
    private void addButton(String text, java.awt.event.ActionListener al){
        JButton b=new JButton(text); b.addActionListener(al); add(b);
    }
}
