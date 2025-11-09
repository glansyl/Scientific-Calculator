package com.calculator.core;

public enum Operator {
    ADD("+"){ public double apply(double a,double b){ return a+b;} },
    SUBTRACT("−"){ public double apply(double a,double b){ return a-b;} },
    MULTIPLY("×"){ public double apply(double a,double b){ return a*b;} },
    DIVIDE("÷"){ public double apply(double a,double b) throws CalculatorException{
        if(b==0) throw new CalculatorException("Division by zero");
        return a/b;
    }},
    POWER("^"){ public double apply(double a,double b){ return Math.pow(a,b);} };

    private final String symbol;
    Operator(String s){ symbol=s; }
    public abstract double apply(double a,double b) throws CalculatorException;
    @Override public String toString(){ return symbol; }
}
