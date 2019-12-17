package Controller.Worker;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class WorkerAccountDataController {

    public Text workerSurname;
    public Text workerDate;
    public Text workerMoney;
    public Text workerDegree;
    public Text workerPhone;
    public TextField change_input1;
    public TextField change_input2;
    public TextField change_input3;
    public TextField change_input4;
    public TextField change_input6;
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
