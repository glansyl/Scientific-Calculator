package com.calculator.ui;

import com.calculator.core.CalculatorEngine;
import com.calculator.core.UnaryFunction;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ScientificPanel extends JPanel {
    public ScientificPanel(CalculatorEngine engine, DisplayPanel disp){
        setLayout(new GridLayout(4,4,4,4));
        List<UnaryFunction> funcs=Arrays.asList(
            UnaryFunction.SIN, UnaryFunction.COS, UnaryFunction.TAN, UnaryFunction.SQRT,
            UnaryFunction.ASIN, UnaryFunction.ACOS, UnaryFunction.ATAN, UnaryFunction.FACT,
            UnaryFunction.LN, UnaryFunction.LOG, UnaryFunction.EXP_E, UnaryFunction.EXP_10
        );
        for(UnaryFunction f: funcs) addButton(f.toString(), ()-> disp.setText(engine.applyUnary(f)));
        addButton("DEG", ()-> {
            engine.toggleDegree();
            ((JButton)getComponent(getComponentCount()-1)).setText(engine.isDegree()?"DEG":"RAD");
        });
    }
    private void addButton(String text, Runnable action){
        JButton b=new JButton(text);
        b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        b.addActionListener(e-> action.run());
        add(b);
    }
}
