package model.car;

public class PetrolPoweredCar extends Car implements SortableByPrice, SortableByFuelConsumption, SortableByMaxSpeed {

    private FuelType fuelType;

    private int price;

    private double fuelEfficiency;

    private int maxSpeed;

    public PetrolPoweredCar(String brand, String model, int year,
                            FuelType fuelType, int price, double fuelEfficiency,
                            int maxSpeed) {

        super(brand, model, year);

        this.fuelType = fuelType;
        this.price = price;
        this.fuelEfficiency = fuelEfficiency;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int price() {

        return price;
    }

    @Override
    public FuelType fuelType() {

        return fuelType;
    }

    @Override
    public double fuelEfficiency() {

        return fuelEfficiency;
    }

    @Override
    public int maxSpeed() {

        return maxSpeed;
    }

}
