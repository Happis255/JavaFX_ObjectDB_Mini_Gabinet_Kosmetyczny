package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginScreenController {

    public PasswordField password_input;

    public TextField login_input;

    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void zaloguj_do_systemu(ActionEvent actionEvent) {
    }
}
