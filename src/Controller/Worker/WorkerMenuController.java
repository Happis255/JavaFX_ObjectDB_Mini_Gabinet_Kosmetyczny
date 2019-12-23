package Controller.Worker;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WorkerMenuController {

    private MainScreenController mainController;

    @FXML
    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void ManageAppoitment(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownik_wizyty", true);
    }

    @FXML
    public void ManageOffer(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika_oferta", true);
    }

    @FXML
    /* Przechodzimy do zarządzania książeczką */
    public void ManageCard(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika_dane_ksiazeczki", true);
    }

    @FXML
    /* Przechodzimy do zarządzania danymi konta */
    public void ViewAccountData(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika_dane_konta", true);
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void log_out(ActionEvent actionEvent) {
        mainController.setPracownik_zalogowany_w_systemie(null);
        mainController.switchScreen("menu", true);
    }
}
