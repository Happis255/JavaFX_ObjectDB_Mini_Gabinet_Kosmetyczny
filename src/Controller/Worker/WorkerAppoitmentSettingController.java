package Controller.Worker;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class WorkerAppoitmentSettingController {

    public TableView tabela_wizyt;
    public TableColumn imie_klienta;
    public TableColumn nazwisko_klienta;
    public TableColumn nazwa_uslugi;
    public TableColumn pracownik;
    public TableColumn data;
    public TableColumn status;
    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }

    public void acceptAppoitment(ActionEvent actionEvent) {
    }

    public void declineAppoitment(ActionEvent actionEvent) {
    }
}
