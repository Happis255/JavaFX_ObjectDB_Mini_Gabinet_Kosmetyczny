package Controller.Worker;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;

public class WorkerMenuController {

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void ManageAppoitment(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownik_wizyty", true);
    }

    public void ManageOffer(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika_oferta", true);
    }

    /* Przechodzimy do zarządzania książeczką */
    public void ManageCard(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika_dane_ksiazeczki", true);
    }

    /* Przechodzimy do zarządzania danymi konta */
    public void ViewAccountData(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika_dane_konta", true);
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
