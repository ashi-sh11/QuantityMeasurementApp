package org.example.UseCase9;

public enum LengthUnit {
    FEET(12.0),
    INCHES(1.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);
    private double factor;
    LengthUnit(double factor){
        this.factor = factor;
    }
    public double getFactor(){
        return factor;
    }
    public double convertToBaseUnit(double value){
        return value*getFactor();
    }
    public double convertFromBaseUnit(double baseValue){
        return baseValue/getFactor();
    }
}
