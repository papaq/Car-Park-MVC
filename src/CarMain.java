import controller.CarParkController;
import model.*;
import view.CarParkView;

public class CarMain {

    public static void main(String[] args) {

        CarParkModel theModel = new CarParkModel();
        CarParkView theView = new CarParkView();
        CarParkController CarParkController = new CarParkController(theView, theModel);

        theView.setVisible(true);
    }
}
