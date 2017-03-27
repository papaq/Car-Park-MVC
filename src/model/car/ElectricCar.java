package model.car;

public class ElectricCar extends Car implements SortableByPrice, SortableByMaxSpeed{

    private int price;

    private int maxSpeed;

    public ElectricCar(String brand, String model, int year, int price, int maxSpeed) {

        super(brand, model, year);

        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    @Override
    public int price() {

        return price;
    }

    @Override
    public int maxSpeed() {

        return maxSpeed;
    }

}
