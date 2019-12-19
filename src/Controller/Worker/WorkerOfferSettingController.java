package Controller.Worker;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class WorkerOfferSettingController {

    @FXML
    public TextField offer_describe;

    @FXML
    public TextField offer_price;

    @FXML
    public TextField offer_time;

    @FXML
    public TextField offer_name;

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

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }

    @FXML
    public void addOffer(ActionEvent actionEvent) {
    }

    @FXML
    public void removeOffer(ActionEvent actionEvent) {
    }
}
