package Controller.Worker;

import Controller.MainScreenController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pliki_java.Klient;
import pliki_java.Kontakt;
import pliki_java.Pracownik;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WorkerAccountDataController {

    @FXML
    public Text wokrerName;

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
    Kontakt kontakt;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void initialize(){
        change_input5.getItems().add(Pracownik.StopienWyksztalcenia.wyksztalcenie_podstawowe);
        change_input5.getItems().add(Pracownik.StopienWyksztalcenia.wyksztalcenie_gimnazjalne);
        change_input5.getItems().add(Pracownik.StopienWyksztalcenia.wyksztalcenie_zasadnicze);
        change_input5.getItems().add(Pracownik.StopienWyksztalcenia.wyksztalcenie_srednie);
        change_input5.getItems().add(Pracownik.StopienWyksztalcenia.wyksztalcenie_wyzsze);
    }

    public void loadData(){

        /* Pobieramy z bazy danych informacje o zalogowanym pracowniku */
        Query q1 =  mainController.getEm().createQuery("SELECT p FROM Pracownik p WHERE p.id = :id");
        q1.setParameter("id", mainController.getPracownik_zalogowany_w_systemie().getId());

        /* Zapisujemy obiekt */
        Pracownik odczytanyPracownik = (Pracownik) q1.getSingleResult();

        /* Pobieramy z bazy danych informacje o kontakcie zalogowanego pracownika */
        Query q2 =  mainController.getEm().createQuery("SELECT p FROM Kontakt p WHERE p.id = :id_kontaktu");
        q2.setParameter("id_kontaktu", odczytanyPracownik.getId_kontaktu());

        /* Zapisujemy obiekt */
        this.kontakt = (Kontakt) q2.getSingleResult();

        /* Zapisujemy dane w rubrykach */
        wokrerName.setText(odczytanyPracownik.getImie());
        workerSurname.setText(odczytanyPracownik.getNazwisko());
        Date data_urodzin = odczytanyPracownik.getData_urodzenia();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        workerDate.setText(dateFormat.format(data_urodzin));
        workerMoney.setText(Integer.toString(odczytanyPracownik.getWyplata()));
        workerDegree.setText(odczytanyPracownik.getStopienWyksztalcenia().getNazwa());
        workerPhone.setText(Integer.toString(this.kontakt.getTelefon()));
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }

    public void doChange(ActionEvent actionEvent) {

        /* Imie */
        String new_name = change_input1.getText();

        /* Nazwisko */
        String new_surname = change_input2.getText();

        /* Data urodzenia */
        String new_date = change_input3.getText();

        /* Wyplata, numer telefonu i stopien wyksztalcenia*/
        int wyplate = 0;
        int new_phone = 0;
        Pracownik.StopienWyksztalcenia new_education = null;
        try {
             wyplate = Integer.parseInt(change_input4.getText());
             new_phone = Integer.parseInt(change_input6.getText());
             new_education = (Pracownik.StopienWyksztalcenia) change_input5.getValue();
        } catch (Exception ignored){
        }

        if (new_name.length() > 1 && new_surname.length() > 1 && new_date.length() > 1 && new_education != null && wyplate > 1 && new_phone > 1){

            /* Dokonujemy zmiany w kliencie */
            Pracownik pracownik_zmiana = mainController.getEm().find(Pracownik.class, mainController.getPracownik_zalogowany_w_systemie());

            mainController.getEm().getTransaction().begin();
            pracownik_zmiana.setImie(new_name);
            pracownik_zmiana.setNazwisko(new_surname);
            try {
                pracownik_zmiana.setData_urodzenia(new SimpleDateFormat("dd/MM/yyyy").parse(new_date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            pracownik_zmiana.setWyplata(wyplate);
            pracownik_zmiana.setStopienWyksztalcenia(new_education);
            mainController.getEm().getTransaction().commit();

            /* Dokonujemy zmiany w ksiazece kontaktowej */
            Kontakt kontakt_zmiana = mainController.getEm().find(Kontakt.class, this.kontakt);
            mainController.getEm().getTransaction().begin();
            kontakt_zmiana.setTelefon(new_phone);
            mainController.getEm().getTransaction().commit();

            /* Załadowanie od nowa strony */
            mainController.switchScreen("menu_pracownika_dane_konta", true);
        }
    }
}
