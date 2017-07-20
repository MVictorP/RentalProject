package car_rental_gui;

import car_rental_db_connect.Controller;

public class Main {

    public static void main(String[] args) {
        Controller controller = Controller.Instance();

        ChooseCustomerFrame custFrame = new ChooseCustomerFrame(controller);
        custFrame.setVisible(true);

    }
}