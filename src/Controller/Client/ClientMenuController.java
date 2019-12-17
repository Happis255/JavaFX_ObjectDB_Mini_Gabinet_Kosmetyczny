package Controller.Client;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class ClientMenuController {

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void ViewAppoitments(ActionEvent actionEvent) {
    }

    public void MakeAppoitment(ActionEvent actionEvent) {
    }

    public void ViewOffer(ActionEvent actionEvent) {
    }

    public void ViewAccountData(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
