package controller;

import model.CarParkModel;
import model.car.*;
import view.CarParkView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CarParkController {

    private CarParkView     theView;
    private CarParkModel    theModel;

    public CarParkController(CarParkView view, CarParkModel model) {

        theView     = view;
        theModel    = model;

        theView.setButtonAddCarListener(new CarButtonListener());
        theView.setButtonSortCars(new SortButtonListener());
        theView.setButtonCarsRange(new ShowRangeSpeed());
        theView.setButtonParkPrice(new CountParkPrice());
    }

    private class CarButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String brand = theView.getBrand().trim();
            String model = theView.getModel().trim();
            String year = theView.getYear().trim();
            String price = theView.getPrice().trim();
            String maxSpeed = theView.getMaxSpeed().trim();

            String type = theView.getComboCarTypeSelected();
            String fuel, fuelEfficiency;

            if (stringIsInvalid(brand, brand.isEmpty(),
                    "[A-Z]([a-z]*-[A-Z]+)*[a-z]*")) {

                theView.showErrorMessage(theView.LABEL_BRAND);
                return;
            }

            if (stringIsInvalid(model, model.isEmpty(),
                    "[A-Z]([a-z]*-[A-Z]+)*[a-z]*")) {

                theView.showErrorMessage(theView.LABEL_MODEL);
                return;
            }

            if (stringIsInvalid(year, year.isEmpty(),
                    "19[0-9][0-9]|200[0-9]|201[0-7]")) {

                theView.showErrorMessage(theView.LABEL_YEAR);
                return;
            }

            if (stringIsInvalid(price, price.isEmpty(),
                    "[1-9][0-9]{0,6}")) {

                theView.showErrorMessage(theView.LABEL_PRICE);
                return;
            }

            if (stringIsInvalid(maxSpeed, maxSpeed.isEmpty(),
                    "[1-9][0-9]{0,6}")) {

                theView.showErrorMessage(theView.LABEL_MAX_SPEED);
                return;
            }

            boolean electric = theView.constStringEqualsBundle(type, theView.TYPE_ELECTRIC);

            if (!electric) {

                // switch
                fuel = theView.getComboFuelTypeSelected();
                FuelType fuelType = getFuelType(fuel);

                fuelEfficiency = theView.getFuelEfficiency().trim();
                if (stringIsInvalid(fuelEfficiency, fuelEfficiency.isEmpty(),
                        "[0-9]*(\\.[0-9]+){0,1}")) {

                    theView.showErrorMessage(theView.LABEL_FUEL_EFFICIENCY);
                    return;
                }

                theModel.addPetrolPoweredCar(brand, model, Integer.parseInt(year),
                        fuelType, Integer.parseInt(price), Double.parseDouble(fuelEfficiency),
                        Integer.parseInt(maxSpeed));
            }
            else{

                theModel.addElectricCar(brand, model, Integer.parseInt(year),
                        Integer.parseInt(price), Integer.parseInt(maxSpeed));
            }
        }

        private FuelType getFuelType(String fuel)
        {
            FuelType fuelType;
            if (theView.constStringEqualsBundle(fuel, theView.PETROL_BENZINE))
                fuelType = FuelType.Benzine;
            else
            if (theView.constStringEqualsBundle(fuel, theView.PETROL_DIESEL))
                fuelType = FuelType.Diesel;
            else
            //if (theView.constStringEqualsBundle(fuel, theView.PETROL_GAS))
                fuelType = FuelType.Gas;

            return fuelType;
        }
    }

    private class SortButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String sortType = getSortType(theView.getComboSortTypeSelected());

            if (sortType.equals(theView.SORT_YEAR)) {

                sortYearAndPrint();
                return;
            }

            if (sortType.equals(theView.SORT_PRICE)) {

                sortPriceAndPrint();
                return;
            }

            if (sortType.equals(theView.SORT_MAX_SPEED)) {

                sortMaxSpeedAndPrint();
                return;
            }

            if (sortType.equals(theView.SORT_FUEL_EFF)) {

                sortFuelEffAndPrint();
                //return;
            }
        }

        private String getSortType(String sortSelected)
        {
            String sortType;
            if (theView.constStringEqualsBundle(sortSelected, theView.SORT_YEAR))
                sortType = theView.SORT_YEAR;
            else
            if (theView.constStringEqualsBundle(sortSelected, theView.SORT_PRICE))
                sortType = theView.SORT_PRICE;
            else
            if (theView.constStringEqualsBundle(sortSelected, theView.SORT_MAX_SPEED))
                sortType = theView.SORT_MAX_SPEED;
            else
                //if (theView.constStringEqualsBundle(sortSelected, theView.PETROL_GAS))
                sortType = theView.SORT_FUEL_EFF;

            return sortType;
        }

        private void sortYearAndPrint() {

            ArrayList<Car> sortedCars = theModel.sortCarsByYear();

            StringBuilder sb = new StringBuilder();

            for (Car car : sortedCars) {

                sb.append(theView.getLocale(theView.LABEL_BRAND))
                        .append(": ")
                        .append(car.getBrand())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MODEL))
                        .append(": ")
                        .append(car.getModel())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_YEAR))
                        .append(": ")
                        .append(car.getYear())
                        .append("\n\n");
            }

            theView.setOutputText(sb.toString());
        }

        private void sortPriceAndPrint() {

            ArrayList<Car> sortedCars = theModel.sortCarsByPrice();

            StringBuilder sb = new StringBuilder();

            for (Car car : sortedCars) {

                sb.append(theView.getLocale(theView.LABEL_BRAND))
                        .append(": ")
                        .append(car.getBrand())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MODEL))
                        .append(": ")
                        .append(car.getModel())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_YEAR))
                        .append(": ")
                        .append(car.getYear())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_PRICE))
                        .append(": ")
                        .append(((SortableByPrice)car).price())
                        .append("\n\n");
            }

            theView.setOutputText(sb.toString());
        }

        private void sortMaxSpeedAndPrint() {

            ArrayList<Car> sortedCars = theModel.sortCarsByMaxSpeed();

            StringBuilder sb = new StringBuilder();

            for (Car car : sortedCars) {

                sb.append(theView.getLocale(theView.LABEL_BRAND))
                        .append(": ")
                        .append(car.getBrand())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MODEL))
                        .append(": ")
                        .append(car.getModel())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_YEAR))
                        .append(": ")
                        .append(car.getYear())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MAX_SPEED))
                        .append(": ")
                        .append(((SortableByMaxSpeed)car).maxSpeed())
                        .append("\n\n");
            }

            theView.setOutputText(sb.toString());
        }

        private void sortFuelEffAndPrint() {

            ArrayList<Car> sortedCars = theModel.sortCarsByFuelEfficiency();

            StringBuilder sb = new StringBuilder();

            for (Car car : sortedCars) {

                sb.append(theView.getLocale(theView.LABEL_BRAND))
                        .append(": ")
                        .append(car.getBrand())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MODEL))
                        .append(": ")
                        .append(car.getModel())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_YEAR))
                        .append(": ")
                        .append(car.getYear())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_FUEL_EFFICIENCY).trim())
                        .append(": ")
                        .append(((SortableByFuelConsumption)car).fuelEfficiency())
                        .append("\n\n");
            }

            theView.setOutputText(sb.toString());
        }
    }

    private class ShowRangeSpeed implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String rangeFrom = theView.getRangeFrom().trim();
            String rangeTo = theView.getRangeTo().trim();

            if (stringIsInvalid(rangeFrom, rangeFrom.isEmpty(),
                    "[1-9][0-9]{0,6}")) {

                theView.showErrorMessage(theView.RANGE_FROM);
                return;
            }

            if (stringIsInvalid(rangeTo, rangeTo.isEmpty(),
                    "[1-9][0-9]{0,6}")) {

                theView.showErrorMessage(theView.RANGE_TO);
                return;
            }

            int from = Integer.parseInt(rangeFrom);
            int to = Integer.parseInt(rangeTo);

            if (from > to) {

                theView.showErrorMessage(theView.RANGE_TO);
                return;
            }

            ArrayList<Car> rangeCars = theModel.carsWithSpeedInRange(from, to);

            StringBuilder sb = new StringBuilder();

            for (Car car : rangeCars) {

                sb.append(theView.getLocale(theView.LABEL_BRAND))
                        .append(": ")
                        .append(car.getBrand())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MODEL))
                        .append(": ")
                        .append(car.getModel())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_YEAR))
                        .append(": ")
                        .append(car.getYear())
                        .append("\n")
                        .append(theView.getLocale(theView.LABEL_MAX_SPEED))
                        .append(": ")
                        .append(((SortableByMaxSpeed)car).maxSpeed())
                        .append("\n\n");
            }

            theView.setOutputText(sb.toString());
        }
    }

    private class CountParkPrice implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            int sumPrice = theModel.countParkValue();

            StringBuilder sb = new StringBuilder();

            sb.append(theView.getLocale(theView.LABEL_SUM_PRICE))
                    .append(" = ")
                    .append(sumPrice);


            theView.setOutputText(sb.toString());
        }
    }

    private Boolean stringIsInvalid(String str, Boolean extraCondition, String matchExpression) {

        return extraCondition || !str.matches(matchExpression);

    }

}
