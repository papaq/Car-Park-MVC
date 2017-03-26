package controller;

import model.CarParkModel;
import view.CarParkView;

public class CarParkController {

    CarParkView theView;
    CarParkModel theModel;

    public CarParkController(CarParkView view, CarParkModel model) {

        theView = view;
        theModel = model;
    }


}
