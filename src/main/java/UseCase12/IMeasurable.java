package UseCase12;

public interface IMeasurable {
    public double getConversionFactor();
    public double convertToBaseUnit(double value);
    public double convertFromBaseUnit(double value);
    public String getUnitName();

}
