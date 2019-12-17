package Controller.Worker;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class WorkerCardDataController {

    public TextField change_input;

    public TableView tabela_ksiazeczki;
    public TableColumn opis;
    public TableColumn dane;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void doChange(ActionEvent actionEvent) {
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }
}
