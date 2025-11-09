package com.calculator.core;

import java.util.ArrayList;
import java.util.List;

public class CalculatorEngine {
    private double currentValue=0;
    private double bufferValue=0;
    private Operator pendingOperator=null;
    private boolean startNewNumber=true;
    private boolean degreeMode=true;
    private double memory=0;
    private final List<String> history=new ArrayList<>();

    public String enterDigit(String d){
        if(startNewNumber){
            currentValue=0;
            startNewNumber=false;
        }
        String txt=(getDisplayText()+d).replaceFirst("^0+(?!$)","");
        currentValue=Double.parseDouble(txt);
        return txt;
    }
    public String enterDot(){
        String txt=getDisplayText();
        if(!txt.contains(".")) txt+=".";
        startNewNumber=false;
        return txt;
    }
    public String toggleSign(){ currentValue=-currentValue; return getDisplayText(); }

    public String applyUnary(UnaryFunction f){
        try{
            currentValue=f.apply(currentValue, degreeMode);
            history.add(f+"("+buffer(currentValue)+")="+currentValue);
        }catch(IllegalArgumentException ex){ return error(ex.getMessage()); }
        startNewNumber=true;
        return getDisplayText();
    }
    public String setOperator(Operator op){
        try{ resolvePending(); }catch(CalculatorException ex){ return error(ex.getMessage()); }
        pendingOperator=op;
        bufferValue=currentValue;
        startNewNumber=true;
        return getDisplayText();
    }
    public String equal(){
        try{ resolvePending(); }catch(CalculatorException ex){ return error(ex.getMessage()); }
        pendingOperator=null;
        startNewNumber=true;
        return getDisplayText();
    }
    private void resolvePending() throws CalculatorException{
        if(pendingOperator!=null){
            double result=pendingOperator.apply(bufferValue,currentValue);
            history.add(buffer(bufferValue)+" "+pendingOperator+" "+buffer(currentValue)+"="+result);
            currentValue=result;
        }
    }
    public void memoryStore(){ memory=currentValue; }
    public void memoryAdd(){ memory+=currentValue; }
    public void memorySub(){ memory-=currentValue; }
    public void memoryClear(){ memory=0; }
    public double memoryRecall(){ return memory; }

    public String percent(){
        currentValue=currentValue/100.0;
        return getDisplayText();
    }

    public void clear(){
        currentValue=0; bufferValue=0; pendingOperator=null; startNewNumber=true;
    }
    public void toggleDegree(){ degreeMode=!degreeMode; }
    public boolean isDegree(){ return degreeMode; }

    public String getDisplayText(){
        if(Double.isNaN(currentValue)||Double.isInfinite(currentValue)) return "Error";
        long asInt=(long)currentValue;
        return currentValue==asInt? Long.toString(asInt): Double.toString(currentValue);
    }
    public List<String> getHistory(){ return history; }

    private String buffer(double v){ return v==(long)v? Long.toString((long)v):Double.toString(v);}
    private String error(String msg){ currentValue=0; startNewNumber=true; return "Error: "+msg; }
}
