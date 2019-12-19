package Controller.Client;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientAppoitmentViewController {

    @FXML
    public TableView tabela_wizyt;

    @FXML
    public TableColumn imie_klienta;

    @FXML
    public TableColumn nazwisko_klienta;

    @FXML
    public TableColumn nazwa_uslugi;

    @FXML
    public TableColumn pracownik;

    @FXML
    public TableColumn data;

    @FXML
    public TableColumn status;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }

    public void declineAppoitment(ActionEvent actionEvent) {
    }
}
