package Controller.Worker;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class WorkerAccountDataController {


    @FXML
    public Text workerSurname;

    @FXML
    public Text workerDate;

    @FXML
    public Text workerMoney;

    @FXML
    public Text workerDegree;

    @FXML
    public Text workerPhone;

    @FXML
    public TextField change_input1;

    @FXML
    public TextField change_input2;

    @FXML
    public TextField change_input3;

    @FXML
    public TextField change_input4;

    @FXML
    public TextField change_input6;

    @FXML
    public ChoiceBox change_input5;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void initialize(){
        change_input5.getItems().add("wykształcenie podstawowe");
        change_input5.getItems().add("wykształcenie gimnazjalne");
        change_input5.getItems().add("wykształcenie zasadnicze");
        change_input5.getItems().add("wykształcenie średnie");
        change_input5.getItems().add("wykształcenie wyższe");
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }

    public void doChange(ActionEvent actionEvent) {
    }
}
