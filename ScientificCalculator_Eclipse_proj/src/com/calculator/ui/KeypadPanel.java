package com.calculator.ui;

import com.calculator.core.CalculatorEngine;
import com.calculator.core.Operator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeypadPanel extends JPanel {
    private final CalculatorEngine engine;
    private final DisplayPanel display;
    public KeypadPanel(CalculatorEngine engine, DisplayPanel display){
        this.engine=engine; this.display=display;
        setLayout(new GridLayout(5,4,4,4));
        addBtn("C", ()->{ engine.clear(); display.setText(engine.getDisplayText()); });
        addBtn("±", ()-> display.setText(engine.toggleSign()));
        addBtn("%", ()-> display.setText(engine.percent()));
        addOp("÷", Operator.DIVIDE);

        addDigit("7"); addDigit("8"); addDigit("9"); addOp("×", Operator.MULTIPLY);
        addDigit("4"); addDigit("5"); addDigit("6"); addOp("−", Operator.SUBTRACT);
        addDigit("1"); addDigit("2"); addDigit("3"); addOp("+", Operator.ADD);
        addDigit("0");
        addBtn(".", ()-> display.setText(engine.enterDot()));
        addBtn("=", ()-> display.setText(engine.equal()));
        addOp("xʸ", Operator.POWER);

        setFocusable(true);
        addKeyListener(new KeyAdapter(){
            @Override public void keyTyped(KeyEvent e){
                char c=e.getKeyChar();
                if(Character.isDigit(c)) display.setText(engine.enterDigit(String.valueOf(c)));
                else switch(c){
                    case '.': display.setText(engine.enterDot()); break;
                    case '+': display.setText(engine.setOperator(Operator.ADD)); break;
                    case '-': display.setText(engine.setOperator(Operator.SUBTRACT)); break;
                    case '*': display.setText(engine.setOperator(Operator.MULTIPLY)); break;
                    case '/': display.setText(engine.setOperator(Operator.DIVIDE)); break;
                    case '=':
                    case '
': display.setText(engine.equal()); break;
                }
            }
        });
    }
    private void addDigit(String d){ addBtn(d, ()-> display.setText(engine.enterDigit(d))); }
    private void addOp(String label, Operator op){ addBtn(label, ()-> display.setText(engine.setOperator(op))); }
    private void addBtn(String text, Runnable action){
        JButton b=new JButton(text);
        b.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        b.addActionListener(e-> action.run());
        add(b);
    }
}
