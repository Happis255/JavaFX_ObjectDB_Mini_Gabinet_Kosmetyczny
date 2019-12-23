package Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pliki_java.Klient;
import pliki_java.Pracownik;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class LoginScreenController {


    @FXML
    public PasswordField password_input;

    @FXML
    public TextField login_input;

    @FXML
    public Text info;

    private MainScreenController mainController;

    public void initialize(){
        info.setText(" ");
    }

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void zaloguj_do_systemu(ActionEvent actionEvent) {

        /* Klienci w systemie */
        TypedQuery<Klient> query_client =
                mainController.getEm().createQuery("SELECT p FROM Klient p", Klient.class);
        List<Klient> results = query_client.getResultList();
        for (Klient p : results) {
            System.out.println(p);
        }

        /* Pracownicy w systemie */
        TypedQuery<Pracownik> query_workers =
                mainController.getEm().createQuery("SELECT p FROM Pracownik p", Pracownik.class);
        List<Pracownik> results2 = query_workers.getResultList();
        for (Pracownik p : results2) {
            System.out.println(p);
        }

        String login = login_input.getText();
        String haslo = password_input.getText();

        if (login.length() > 0 && haslo.length() > 0) {

            /* Sprawdzamy w bazie danych czy jest taki użytkownik - klient*/
            Query q1 =  mainController.getEm().createQuery("SELECT p FROM Klient p WHERE p.haslo = :haslo AND p.login = :login");
            q1.setParameter("haslo", haslo);
            q1.setParameter("login", login);

            try{
                mainController.setKlient_zalogowany_w_systemie((Klient) q1.getSingleResult());
                mainController.switchScreen("menu_klienta", true);
                System.out.println("Zalogowano klienta.");
                System.out.println(mainController.getKlient_zalogowany_w_systemie());
                return;
            } catch (Exception e){
                System.out.println("Nie wykryto klienta, sprawdzamy czy pracownik");
            }

            /* Sprawdzamy w bazie danych czy jest taki użytkownik - pracownik*/
            Query q2 =  mainController.getEm().createQuery("SELECT p FROM Pracownik p WHERE p.haslo = :haslo AND p.login = :login");
            q2.setParameter("haslo", haslo);
            q2.setParameter("login", login);

            try{
                mainController.setPracownik_zalogowany_w_systemie((Pracownik) q2.getSingleResult());
                mainController.switchScreen("menu_pracownika", true);
                System.out.println("Zalogowano pracownika.");
                System.out.println(mainController.getPracownik_zalogowany_w_systemie());
                return;
            } catch (Exception e){
                System.out.println("Nie wykryto pracownika. Popraw dane");
            }
            info.setText("Błędny login lub hasło");
        } else {
            info.setText("Uzupełnij pola tekstowe");
        }
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}
