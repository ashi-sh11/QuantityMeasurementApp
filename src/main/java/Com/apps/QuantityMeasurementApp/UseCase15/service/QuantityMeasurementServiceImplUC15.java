package Com.apps.QuantityMeasurementApp.UseCase15.service;

import Com.apps.QuantityMeasurementApp.UseCase15.dto.QuantityDTOUC15;
import Com.apps.QuantityMeasurementApp.UseCase15.exception.QuantityMeasurementExceptionUC15;

public class QuantityMeasurementServiceImplUC15 implements IQuantityMeasurementServiceUC15 {

    @Override
    public QuantityDTOUC15 add(QuantityDTOUC15 first, QuantityDTOUC15 second) {

        if (first == null || second == null) {
            throw new QuantityMeasurementExceptionUC15("Quantities cannot be null");
        }

        if (!first.getMeasurementType().equalsIgnoreCase(second.getMeasurementType())) {
            throw new QuantityMeasurementExceptionUC15("Measurement type mismatch");
        }

        double base1 = convertToBase(first);
        double base2 = convertToBase(second);

        double sumBase = base1 + base2;

        double result = convertFromBase(sumBase, first.getUnitName(), first.getMeasurementType());

        return new QuantityDTOUC15(result, first.getUnitName(), first.getMeasurementType());
    }

    @Override
    public QuantityDTOUC15 convert(QuantityDTOUC15 dto, String targetUnit) {

        if (dto == null) {
            throw new QuantityMeasurementExceptionUC15("Input cannot be null");
        }

        double base = convertToBase(dto);

        double result = convertFromBase(base, targetUnit, dto.getMeasurementType());

        return new QuantityDTOUC15(result, targetUnit, dto.getMeasurementType());
    }

    // 🔥 CORE FIX METHOD
    private double convertToBase(QuantityDTOUC15 dto) {

        if (dto == null) {
            throw new QuantityMeasurementExceptionUC15("DTO cannot be null");
        }

        String unit = dto.getUnitName().toUpperCase();
        double value = dto.getValue();

        switch (dto.getMeasurementType().toUpperCase()) {

            case "WEIGHT":
                return switch (unit) {
                    case "KILOGRAM" -> value;
                    case "GRAM" -> value / 1000;
                    case "POUND" -> value * 0.453592;
                    default -> throw new QuantityMeasurementExceptionUC15("Invalid unit");
                };

            case "LENGTH":
                return switch (unit) {
                    case "FEET" -> value * 12;
                    case "INCHES" -> value;
                    case "YARDS" -> value * 36;
                    case "CENTIMETERS" -> value * 0.393701;
                    default -> throw new QuantityMeasurementExceptionUC15("Invalid unit");
                };

            case "VOLUME":
                return switch (unit) {
                    case "LITRE" -> value;
                    case "MILLILITRE" -> value / 1000;
                    case "GALLON" -> value * 3.78541;
                    default -> throw new QuantityMeasurementExceptionUC15("Invalid unit");
                };

            default:
                throw new QuantityMeasurementExceptionUC15("Invalid measurement type");
        }
    }

    private double convertFromBase(double base, String targetUnit, String type) {

        targetUnit = targetUnit.toUpperCase();

        switch (type.toUpperCase()) {

            case "WEIGHT":
                return switch (targetUnit) {
                    case "KILOGRAM" -> base;
                    case "GRAM" -> base * 1000;
                    case "POUND" -> base / 0.453592;
                    default -> throw new QuantityMeasurementExceptionUC15("Invalid unit");
                };

            case "LENGTH":
                return switch (targetUnit) {
                    case "FEET" -> base / 12;
                    case "INCHES" -> base;
                    case "YARDS" -> base / 36;
                    case "CENTIMETERS" -> base / 0.393701;
                    default -> throw new QuantityMeasurementExceptionUC15("Invalid unit");
                };

            case "VOLUME":
                return switch (targetUnit) {
                    case "LITRE" -> base;
                    case "MILLILITRE" -> base * 1000;
                    case "GALLON" -> base / 3.78541;
                    default -> throw new QuantityMeasurementExceptionUC15("Invalid unit");
                };

            default:
                throw new QuantityMeasurementExceptionUC15("Invalid measurement type");
        }
    }
}