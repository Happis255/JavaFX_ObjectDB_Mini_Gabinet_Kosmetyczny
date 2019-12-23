package Controller.Client;

import Controller.MainScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import pliki_java.Pracownik;
import pliki_java.Usluga;
import pliki_java.Wizyta;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;

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

    @FXML
    public void createAppoitment(ActionEvent actionEvent) {

        try {
            String new_date = dateInput.getText();
            String new_hour = hourInput.getText();
            String new_offer = (String) serviceInput.getValue();
            String new_worker = (String) workerInput.getValue();

            if (new_date.length() > 2 && new_hour.length() > 2 && new_offer.length() > 2 && new_worker.length() > 2) {

                /* Pobieramy wybraną usługę */
                Query q1 =  mainController.getEm().createQuery("SELECT p FROM Usluga p WHERE p.nazwa_uslugi = :nazwa");
                q1.setParameter("nazwa", new_offer);
                Usluga wybrana_usluga = (Usluga) q1.getSingleResult();

                /* Pobieramy wybranego pracownika */
                Query q2 =  mainController.getEm().createQuery("SELECT p FROM Pracownik p WHERE p.nazwisko = :nazwisko");
                q2.setParameter("nazwisko", new_worker);
                Pracownik wybrany_pracownik = (Pracownik) q2.getSingleResult();

                /* Tworzymy wizyte */
                mainController.getEm().getTransaction().begin();
                Wizyta tworzona_wizyta = new Wizyta(mainController.getKlient_zalogowany_w_systemie().getId(), wybrany_pracownik.getId(), wybrana_usluga.getId(),  new SimpleDateFormat("dd/MM/yyyy").parse(new_date), "brak");
                mainController.getEm().persist(tworzona_wizyta);
                mainController.getEm().getTransaction().commit();
                System.out.println("Dodano wizyte");
                mainController.switchScreen("menu_klienta_zapisz", true);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadData(){

        /* Pobieramy z bazy danych informacje o mozliwych uslugach */
        Query q1 =  mainController.getEm().createQuery("SELECT p FROM Usluga p");
        List lista_uslug = q1.getResultList();
        Usluga temp_usluga;

        while (!lista_uslug.isEmpty()) {
            temp_usluga = (Usluga) lista_uslug.get(0);
            serviceInput.getItems().add(temp_usluga.getNazwa_uslugi());
            lista_uslug.remove(0);
        }

        /* Pobieramy z bazy danych informacje o pracownikach */
        Query q2 =  mainController.getEm().createQuery("SELECT p FROM Pracownik p");
        List lista_pracownikow = q2.getResultList();
        Pracownik temp_pracownik;

        while (!lista_pracownikow.isEmpty()) {
            temp_pracownik = (Pracownik) lista_pracownikow.get(0);
            workerInput.getItems().add(temp_pracownik.getNazwisko());
            lista_pracownikow.remove(0);
        }

        /* Tworzymy wątek do aktualizacji ceny */
        new Thread()
        {
            public void run() {
                String nazwa_wybranej_uslugi = " ";
                while (true){
                    try {
                        nazwa_wybranej_uslugi = (String) serviceInput.getValue();
                    } catch (Exception ignored){
                    }
                    if (nazwa_wybranej_uslugi != null) {
                        Query q3 =  mainController.getEm().createQuery("SELECT p FROM Usluga p WHERE p.nazwa_uslugi = :nazwa");
                        q3.setParameter("nazwa", nazwa_wybranej_uslugi);

                        Usluga wybrana_usluga = (Usluga) q3.getSingleResult();
                        price.setText(wybrana_usluga.getKoszt() + " zł");
                    }
                }
            }
        }.start();
    }
}
