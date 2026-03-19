package UseCase9;

public class Weight {
    private double value;
    private WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
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

    public WeightUnit getUnit() {
        return unit;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj ) return true;
        if(obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Weight other = (Weight) obj;
        return Double.compare(unit.convertToBaseUnit(value), other.unit.convertToBaseUnit(other.value))==0;
    }

    public Weight convertTo(WeightUnit targetUnit){
        double base =  unit.convertToBaseUnit(value);
        double res = targetUnit.convertFromBaseUnit(base);
        return new Weight(res ,targetUnit);
    }

    public  Weight add(Weight thatWeight){
        double first = unit.convertToBaseUnit(value);
        double second = thatWeight.unit.convertToBaseUnit(thatWeight.value);
        double sum = first+second;
        double res = unit.convertFromBaseUnit(sum);
        return new Weight(res,this.unit);
    }

    public  Weight add(Weight thatWeight , WeightUnit targetUnit){
        double first = unit.convertToBaseUnit(value);
        double second = thatWeight.unit.convertToBaseUnit(thatWeight.value);
        double sum = first+second;
        double res =  targetUnit.convertFromBaseUnit(sum);
        return new Weight(res,targetUnit);
    }

    public Weight addAndConvert(Weight thatWeight , WeightUnit targetUnit){
        double first = unit.convertToBaseUnit(value);
        double second = thatWeight.unit.convertToBaseUnit(thatWeight.value);
        double sum = first+second;
        double res =  targetUnit.convertFromBaseUnit(sum);
        return new Weight(res,targetUnit);
    }

    public boolean compare(Weight thatWeight){
        double first = unit.convertToBaseUnit(value);
        double second = thatWeight.unit.convertToBaseUnit(thatWeight.value);
        return  Double.compare(first,second)==0;
    }

    private double convertFromBaseToTragetUnit(double weightInKg , WeightUnit targetUnit){
        return targetUnit.convertFromBaseUnit(weightInKg);
    }

    private double convertToBaseUnit(){
        return this.unit.convertToBaseUnit(this.value);
    }
    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}
