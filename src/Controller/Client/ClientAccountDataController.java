package Controller.Client;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ClientAccountDataController {

    @FXML
    public Text clientSurname;

    @FXML
    public Text clientDate;

    @FXML
    public Text clientEmail;

    @FXML
    public Text clientStatus;

    @FXML
    public Text clientPhone;

    @FXML
    public TextField change_input1;

    @FXML
    public TextField change_input2;

    @FXML
    public TextField change_input3;

    @FXML
    public TextField change_input4;

    @FXML
    public TextField change_input5;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void doChange(ActionEvent actionEvent) {
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }
}
