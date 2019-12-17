package Controller.Worker;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class WorkerOfferSettingController {

    public TextField offer_describe;
    public TextField offer_price;
    public TextField offer_time;
    public TextField offer_name;
    public TableView tabela_uslug;
    public TableColumn nazwa_uslugi;
    public TableColumn opis_uslugi;
    public TableColumn cena;
    public TableColumn czas;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }

    public void addOffer(ActionEvent actionEvent) {
    }

    public void removeOffer(ActionEvent actionEvent) {
    }
}
