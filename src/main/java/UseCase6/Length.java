package UseCase6;

public class Length {
    private double value;
    private LengthUnit unit;

    public enum LengthUnit{
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);
        private double value;

        LengthUnit(double value){
            this.value=value;
        }

        public double getFactor(){
            return value;
        }
    }
    public double getValue(){
        return value;
    }

    public double convertToBaseUnit(){
        return value* unit.getFactor();
    }

    public boolean compare(Length thatLength){
        return Double.compare(this.convertToBaseUnit() , thatLength.convertToBaseUnit()) == 0;
    }

    public Length(double value , LengthUnit unit){
        this.value=value;
        this.unit=unit;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if(obj == null) return false;
        if (!(obj instanceof Length)) return false;
        Length length = (Length) obj;
        return Double.compare(this.convertToBaseUnit() , length.convertToBaseUnit()) == 0;
    }

    public Length convertTo(LengthUnit targetUnit){
        double base = this.convertToBaseUnit();
        double res = convertFromBaseToTargetUnit(base,targetUnit);
        return  new Length(res,targetUnit);
    }

    public double convertFromBaseToTargetUnit(double lengthInInches, LengthUnit targetUnit){
        if(targetUnit == null)  throw new IllegalArgumentException("Target Unit cannot be null");
        if (!Double.isFinite(lengthInInches)) {
            throw new IllegalArgumentException("Invalid numeric value");
        }
        return lengthInInches / targetUnit.getFactor();
    }

    public  Length add (Length l1 ){
        if(l1 == null || this == null)  throw new IllegalArgumentException("Length cannot be null");
        double first = this.convertToBaseUnit();
        double second = l1.convertToBaseUnit();
        double sum = first+second;
        double ans =  convertFromBaseToTargetUnit(sum,this.unit);
        return new Length(ans , this.unit);
    }
    public static Length add(Length l1, Length l2, LengthUnit targetUnit) {
        if (l1 == null || l2 == null || targetUnit == null) {
            throw new IllegalArgumentException("Invalid input");
        }
        double l1Feet = l1.convertToBaseUnit();
        double l2Feet = l2.convertToBaseUnit();
        double sumFeet = l1Feet + l2Feet;
        double result = l1.convertFromBaseToTargetUnit(sumFeet,targetUnit);
        return new Length(result, targetUnit);
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
