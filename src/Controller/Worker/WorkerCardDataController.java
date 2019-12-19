package Controller.Worker;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class WorkerCardDataController {

    @FXML
    public TextField change_input;

    @FXML
    public TableView tabela_ksiazeczki;

    @FXML
    public TableColumn opis;

    @FXML
    public TableColumn dane;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void doChange(ActionEvent actionEvent) {
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }
}
