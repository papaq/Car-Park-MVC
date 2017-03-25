package model.car;

public class ElectricCar extends Car implements SortableByValue, SortableByMaxSpeed{

    private double value;

    private int maxSpeed;

    public ElectricCar(String brand, String model, int year, double value, int maxSpeed) {

        super(brand, model, year);

        this.value = value;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public double value() {

        return value;
    }

    @Override
    public int maxSpeed() {

        return maxSpeed;
    }

}
