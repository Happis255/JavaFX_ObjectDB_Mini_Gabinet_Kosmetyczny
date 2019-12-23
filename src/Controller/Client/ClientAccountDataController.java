package Controller.Client;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pliki_java.Klient;
import pliki_java.Kontakt;
import pliki_java.Pracownik;

import javax.persistence.Query;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientAccountDataController {

    @FXML
    public Text clientName;

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

    private Kontakt kontakt;
    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void doChange(ActionEvent actionEvent) {
        try{
            /* Imie */
            String new_name = change_input1.getText();

            /* Nazwisko */
            String new_surname = change_input2.getText();

            /* Data urodzenia */
            String new_date = change_input3.getText();

            /* E-Mail */
            String new_email = change_input4.getText();

            /* Telefon */
            int new_phone = Integer.parseInt(change_input5.getText());

            if (new_name.length() > 1 && new_surname.length() > 1 && new_date.length() > 1 && new_phone > 1 && new_email.length() > 1) {

                /* Dokonujemy zmiany w kliencie */
                Klient klientZmiana = mainController.getEm().find(Klient.class, mainController.getKlient_zalogowany_w_systemie());

                mainController.getEm().getTransaction().begin();
                klientZmiana.setImie(new_name);
                klientZmiana.setNazwisko(new_surname);
                try {
                    klientZmiana.setData_urodzenia(new SimpleDateFormat("dd/MM/yyyy").parse(new_date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mainController.getEm().getTransaction().commit();

                /* Dokonujemy zmiany w ksiazece kontaktowej */
                Kontakt kontakt_zmiana = mainController.getEm().find(Kontakt.class, this.kontakt);
                mainController.getEm().getTransaction().begin();
                kontakt_zmiana.setE_mail(new_email);
                kontakt_zmiana.setTelefon(new_phone);
                mainController.getEm().getTransaction().commit();

                /* Załadowanie od nowa strony */
                mainController.switchScreen("menu_klienta_dane", true);
            }
        } catch (Exception e){
            System.out.println("Błędne dane");
        }
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }

    public void loadData(){

        /* Pobieramy z bazy danych informacje o zalogowanym pracowniku */
        Query q1 =  mainController.getEm().createQuery("SELECT p FROM Klient p WHERE p.id = :id");
        q1.setParameter("id", mainController.getKlient_zalogowany_w_systemie().getId());

        /* Zapisujemy obiekt */
        Klient odczytanyKlient = (Klient) q1.getSingleResult();

        /* Pobieramy z bazy danych informacje o kontakcie zalogowanego pracownika */
        Query q2 =  mainController.getEm().createQuery("SELECT p FROM Kontakt p WHERE p.id = :id_kontaktu");
        q2.setParameter("id_kontaktu", odczytanyKlient.getId_kontaktu());

        /* Zapisujemy obiekt */
        this.kontakt = (Kontakt) q2.getSingleResult();

        /* Zapisujemy dane w rubrykach */
        clientName.setText(odczytanyKlient.getImie());
        clientSurname.setText(odczytanyKlient.getNazwisko());
        Date data_urodzin = odczytanyKlient.getData_urodzenia();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
        clientDate.setText(dateFormat.format(data_urodzin));
        clientEmail.setText(kontakt.getE_mail());
        clientStatus.setText(odczytanyKlient.getStatusKlienta().getNazwa());
        clientPhone.setText(Integer.toString(this.kontakt.getTelefon()));
    }
}
