package Controller.Client;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClientOfferViewController {

    @FXML
    public TableView tabela_uslug;

    @FXML
    public TableColumn nazwa_uslugi;

    @FXML
    public TableColumn opis_uslugi;

    @FXML
    public TableColumn cena;

    @FXML
    public TableColumn czas;

    private MainScreenController mainController;
    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }
}
