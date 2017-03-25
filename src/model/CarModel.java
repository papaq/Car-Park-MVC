package model;

import model.car.*;

import java.util.ArrayList;
import java.util.Collections;

public class CarModel {

    private ArrayList<Car> cars = new ArrayList<>();

    public void addPetrolPoweredCar(String brand, String model, int year,
                  FuelType fuelType, double value, double kmPerL,
                  int maxSpeed) {

        cars.add(new PetrolPoweredCar(brand, model, year, fuelType, value, kmPerL, maxSpeed));
    }

    public void addElectricCar(String brand, String model, int year,
                               double value, int maxSpeed) {

        cars.add(new ElectricCar(brand, model, year, value, maxSpeed));
    }

    public ArrayList<Car> sortCarsByFuelConsumption() {

        ArrayList<SortableByFuelConsumption> sortedByConsumption = new ArrayList<>();
        for (Car car : cars) {

            if (car instanceof SortableByFuelConsumption)
                sortedByConsumption.add((SortableByFuelConsumption)car);
        }

        Collections.sort(sortedByConsumption, (carA, carB) -> {

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            double carAconsumption = carA.kilometersPerLiter();
            double carBconsumption = carB.kilometersPerLiter();

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

        Collections.sort(sortedBySpeed, (carA, carB) -> {

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
        for (SortableByMaxSpeed car : sortedBySpeed) {

            sortedCars.add((Car)car);
        }

        return sortedCars;
    }

    public ArrayList<Car> sortCarsByValue() {

        ArrayList<SortableByValue> sortedByValue = new ArrayList<>();
        for (Car car : cars) {

            if (car instanceof SortableByValue)
                sortedByValue.add((SortableByValue)car);
        }

        Collections.sort(sortedByValue, (carA, carB) -> {

            final int BEFORE = -1;
            final int EQUAL = 0;
            final int AFTER = 1;

            double carAvalue = carA.value();
            double carBvalue = carB.value();

            if (carAvalue < carBvalue) return BEFORE;
            if (carAvalue > carBvalue) return AFTER;
            return EQUAL;
        });

        ArrayList<Car> sortedCars = new ArrayList<>();
        for (SortableByValue car : sortedByValue) {

            sortedCars.add((Car)car);
        }

        return sortedCars;
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

    public double countParkValue() {

        double value = 0;

        for (Car car : cars) {

            if (car instanceof SortableByValue) {

                value += ((SortableByValue) car).value();
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
