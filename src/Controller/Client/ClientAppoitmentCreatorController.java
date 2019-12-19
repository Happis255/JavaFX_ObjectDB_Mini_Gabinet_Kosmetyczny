package Controller.Client;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ClientAppoitmentCreatorController {

    @FXML
    public TextField dateInput;

    @FXML
    public TextField hourInput;

    @FXML
    public ChoiceBox serviceInput;

    @FXML
    public ChoiceBox workerInput;

    @FXML
    public Text price;

    private MainScreenController mainController;
    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }

    public void createAppoitment(ActionEvent actionEvent) {
    }
}
