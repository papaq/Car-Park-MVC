package model.car;

public class PetrolPoweredCar extends Car implements SortableByValue, SortableByFuelConsumption, SortableByMaxSpeed {

    private FuelType fuelType;

    private double value;

    private double kmPerL;

    private int maxSpeed;

    public PetrolPoweredCar(String brand, String model, int year,
                            FuelType fuelType, double value, double kmPerL,
                            int maxSpeed) {

        super(brand, model, year);

        this.fuelType = fuelType;
        this.value = value;
        this.kmPerL = kmPerL;
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

        return kmPerL;
    }

    @Override
    public int maxSpeed() {

        return maxSpeed;
    }

}
