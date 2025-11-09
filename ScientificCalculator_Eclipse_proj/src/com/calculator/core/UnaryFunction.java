package com.calculator.core;

public enum UnaryFunction {
    SIN("sin"){ public double apply(double v, boolean deg){ return Math.sin(toRad(v,deg)); } },
    COS("cos"){ public double apply(double v, boolean deg){ return Math.cos(toRad(v,deg)); } },
    TAN("tan"){ public double apply(double v, boolean deg){ return Math.tan(toRad(v,deg)); } },
    ASIN("asin"){ public double apply(double v, boolean deg){ return toDeg(Math.asin(v),deg); } },
    ACOS("acos"){ public double apply(double v, boolean deg){ return toDeg(Math.acos(v),deg); } },
    ATAN("atan"){ public double apply(double v, boolean deg){ return toDeg(Math.atan(v),deg); } },
    LN("ln"){ public double apply(double v, boolean d){ return Math.log(v);} },
    LOG("log"){ public double apply(double v, boolean d){ return Math.log10(v);} },
    SQRT("√"){ public double apply(double v, boolean d){ return Math.sqrt(v);} },
    EXP_E("eˣ"){ public double apply(double v, boolean d){ return Math.exp(v);} },
    EXP_10("10ˣ"){ public double apply(double v, boolean d){ return Math.pow(10,v);} },
    FACT("n!"){ public double apply(double v, boolean d){ return factorial(v);} };

    private final String label;
    UnaryFunction(String l){ label=l; }
    public abstract double apply(double v, boolean degreeMode);

    private static double toRad(double v, boolean deg){ return deg?Math.toRadians(v):v;}
    private static double toDeg(double v, boolean deg){ return deg?Math.toDegrees(v):v;}

    private static double factorial(double x){
        if(x<0 || x!=Math.floor(x)) throw new IllegalArgumentException("n! only for non‑negative integers");
        double r=1;
        for(int i=2;i<= (int)x;i++) r*=i;
        return r;
    }
    @Override public String toString(){ return label; }
}
