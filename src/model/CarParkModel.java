package model;

import model.car.*;

import java.util.ArrayList;

public class CarParkModel {

    private ArrayList<Car> cars = new ArrayList<>();

    public void addPetrolPoweredCar(String brand, String model, int year,
                  FuelType fuelType, int price, double kmPerL,
                  int maxSpeed) {

        cars.add(new PetrolPoweredCar(brand, model, year, fuelType, price, kmPerL, maxSpeed));
    }

    public void addElectricCar(String brand, String model, int year,
                               int price, int maxSpeed) {

        cars.add(new ElectricCar(brand, model, year, price, maxSpeed));
    }

    public ArrayList<Car> sortCarsByFuelEfficiency() {

        ArrayList<SortableByFuelConsumption> sortedByConsumption = new ArrayList<>();
        for (Car car : cars) {

            if (car instanceof SortableByFuelConsumption)
                sortedByConsumption.add((SortableByFuelConsumption)car);
        }

        sortedByConsumption.sort((carA, carB) -> {

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            double carAconsumption = carA.fuelEfficiency();
            double carBconsumption = carB.fuelEfficiency();

            if (carAconsumption < carBconsumption) return BEFORE;
            if (carAconsumption > carBconsumption) return AFTER;
            return EQUAL;
        });

        ArrayList<Car> sortedCars = new ArrayList<>();
        for (SortableByFuelConsumption car : sortedByConsumption) {

            sortedCars.add((Car)car);
        }

        return sortedCars;
    }

    public ArrayList<Car> sortCarsByMaxSpeed() {

        ArrayList<SortableByMaxSpeed> sortedBySpeed = new ArrayList<>();
        for (Car car : cars) {

            if (car instanceof SortableByMaxSpeed)
                sortedBySpeed.add((SortableByMaxSpeed)car);
        }

        sortedBySpeed.sort((carA, carB) -> {

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            double carAspeed = carA.maxSpeed();
            double carBspeed = carB.maxSpeed();

            if (carAspeed < carBspeed) return BEFORE;
            if (carAspeed > carBspeed) return AFTER;
            return EQUAL;
        });

        ArrayList<Car> sortedCars = new ArrayList<>();
        for (SortableByMaxSpeed car : sortedBySpeed)
            sortedCars.add((Car) car);

        return sortedCars;
    }

    public ArrayList<Car> sortCarsByPrice() {

        ArrayList<SortableByPrice> sortedByPrice = new ArrayList<>();
        for (Car car : cars) {

            if (car instanceof SortableByPrice)
                sortedByPrice.add((SortableByPrice)car);
        }

        sortedByPrice.sort((carA, carB) -> {

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            double carAPrice = carA.price();
            double carBPrice = carB.price();

            if (carAPrice < carBPrice) return BEFORE;
            if (carAPrice > carBPrice) return AFTER;
            return EQUAL;
        });

        ArrayList<Car> sortedCars = new ArrayList<>();
        for (SortableByPrice car : sortedByPrice) {

            sortedCars.add((Car)car);
        }

        return sortedCars;
    }

    public ArrayList<Car> sortCarsByYear() {

        ArrayList<Car> sortedByYear = new ArrayList<>(cars);

        sortedByYear.sort((carA, carB) -> {

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            double carAYear = carA.getYear();
            double carBYear = carB.getYear();

            if (carAYear < carBYear) return BEFORE;
            if (carAYear > carBYear) return AFTER;
            return EQUAL;
        });

        return sortedByYear;
    }

    public ArrayList<Car> carsWithSpeedInRange(int lowBorder, int highBorder) {

        ArrayList<SortableByMaxSpeed> carsInRange = new ArrayList<>();
        for (Car car : cars) {

            if (carSpeedInRange(car, lowBorder, highBorder)) {

                    carsInRange.add((SortableByMaxSpeed) car);
            }
        }

        ArrayList<Car> cars = new ArrayList<>();
        for (SortableByMaxSpeed car : carsInRange) {

            cars.add((Car)car);
        }

        return cars;
    }

    public int countParkValue() {

        int value = 0;

        for (Car car : cars) {

            if (car instanceof SortableByPrice) {

                value += ((SortableByPrice) car).price();
            }
        }

        return value;
    }

    private boolean carSpeedInRange(Car car, int left, int right) {

        if (car instanceof SortableByMaxSpeed) {

            SortableByMaxSpeed currentCar = (SortableByMaxSpeed) car;
            int speed = currentCar.maxSpeed();

            if (speed >= left && speed <= right)

                return true;
        }

        return false;
    }

}
