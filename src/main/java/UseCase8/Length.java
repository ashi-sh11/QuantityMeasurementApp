package UseCase8;

public class Length {
    private double value;
    private LengthUnit unit ;

    public Length(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }
    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
    public boolean compare(Length thatLength){
        return Double.compare(unit.convertToBaseUnit(value), thatLength.unit.convertToBaseUnit(thatLength.value)) == 0;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if (obj == null ) return false;
        if(!(obj instanceof Length)) return false;
        Length length = (Length) obj;
        return Double.compare(this.unit.convertToBaseUnit(this.value),length.unit.convertToBaseUnit(length.value))==0;
    }

    public Length convertTo(LengthUnit targetUnit){
        double base = unit.convertToBaseUnit(value);
        double result = targetUnit.convertFromBaseUnit(base);
        return new Length(round(result),targetUnit);
    }

    public Length add(Length thatLength){
        double first = unit.convertToBaseUnit(value);
        double second = thatLength.unit.convertToBaseUnit(thatLength.value);
        double sum = first + second;
        double result = unit.convertFromBaseUnit(sum);
        return new Length(round(result), unit);
    }


    public Length add(Length l1 ,Length l2){
        double first = l1.unit.convertToBaseUnit(l1.value);
        double second = l2.unit.convertToBaseUnit(l2.value);
        double sum = first+second;
        double res = l1.unit.convertFromBaseUnit(sum);
        return  new Length(round(res),l1.unit);
    }

    public Length add(Length l1 ,Length l2 , LengthUnit targetUnit){
        double first = l1.unit.convertToBaseUnit(l1.value);
        double second = l2.unit.convertToBaseUnit(l2.value);
        double sum = first+second;
        double res = targetUnit.convertFromBaseUnit(sum);
        return  new Length(round(res),targetUnit);
    }

    public Length addAndConvert(Length l1 , LengthUnit targetUnit){
        double first = unit.convertToBaseUnit(value);
        double second = l1.unit.convertToBaseUnit(l1.value);
        double sum = first+second;
        double res = targetUnit.convertFromBaseUnit(sum);
        return  new Length(round(res),targetUnit);
    }

    public double convertToBaseUnit(){
        return round(unit.convertToBaseUnit(value));
    }

    public double convertBaseToTargetUnit(double lengthInInches , LengthUnit targetUnit){
        return round(targetUnit.convertFromBaseUnit(lengthInInches));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
