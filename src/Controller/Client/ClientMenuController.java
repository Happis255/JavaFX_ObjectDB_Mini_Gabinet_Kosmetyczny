package Controller.Client;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ClientMenuController {

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void ViewAppoitments(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta_wizyty", true);
    }

    @FXML
    public void MakeAppoitment(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta_zapisz", true);
    }

    @FXML
    public void ViewOffer(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta_oferta", true);
    }

    @FXML
    public void ViewAccountData(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta_dane", true);
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
