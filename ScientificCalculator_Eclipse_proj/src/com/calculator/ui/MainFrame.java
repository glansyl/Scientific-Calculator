package com.calculator.ui;

import com.calculator.core.CalculatorEngine;
import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CalculatorEngine engine=new CalculatorEngine();
    private final DisplayPanel display=new DisplayPanel();
    public MainFrame(){
        super("Scientific Calculator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        JPanel top=new JPanel(new BorderLayout());
        top.add(display, BorderLayout.CENTER);
        top.add(new MemoryPanel(engine, display), BorderLayout.SOUTH);

        JPanel center=new JPanel(new BorderLayout(5,5));
        center.add(new ScientificPanel(engine, display), BorderLayout.NORTH);
        center.add(new KeypadPanel(engine, display), BorderLayout.CENTER);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        pack();
        setSize(420,550);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> new MainFrame().setVisible(true));
    }
}
