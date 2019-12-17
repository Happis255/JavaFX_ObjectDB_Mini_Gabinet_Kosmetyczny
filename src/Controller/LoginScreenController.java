package Controller;

import javafx.application.Platform;
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

        String login = login_input.getText();
        String haslo = password_input.getText();

        if (login != null && haslo != null) {
            /* Sprawdzamy w bazie danych czy jest taki użytkownik */

            /* Pobieramy informacje o typie konta */

            /* Jesli pracownik:
            -> stworz zalogowanego pracownika
            -> panel pracownika */

            //rozwiazanie tymczasowe
            if (login.equals("admin") && haslo.equals("admin"))
            mainController.switchScreen("menu_pracownika", true);


            /* Jesli klient
            -> stworz zalogowanego klienta
            -> panel klienta */

            //rozwiazanie tymczasowe
            if (login.equals("Klient") && haslo.equals("123123"))
            mainController.switchScreen("menu_klienta", true);
        }
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
