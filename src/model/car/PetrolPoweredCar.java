package model.car;

public class PetrolPoweredCar extends Car implements SortableByValue, SortableByFuelConsumption, SortableByMaxSpeed {

    private FuelType fuelType;

    private double value;

    private double fuelEfficiency;

    private int maxSpeed;

    public PetrolPoweredCar(String brand, String model, int year,
                            FuelType fuelType, double value, double fuelEfficiency,
                            int maxSpeed) {

        super(brand, model, year);

        this.fuelType = fuelType;
        this.value = value;
        this.fuelEfficiency = fuelEfficiency;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public double value() {

        return value;
    }

    @Override
    public FuelType fuelType() {

        return fuelType;
    }

    @Override
    public double kilometersPerLiter() {

        return fuelEfficiency;
    }

    @Override
    public int maxSpeed() {

        return maxSpeed;
    }

}
