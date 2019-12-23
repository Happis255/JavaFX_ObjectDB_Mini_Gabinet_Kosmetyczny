package Controller.Client;

import Controller.MainScreenController;
import ModelTabeli.WizytaGabinetu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pliki_java.Klient;
import pliki_java.Pracownik;
import pliki_java.Usluga;
import pliki_java.Wizyta;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.util.List;

public class ClientAppoitmentViewController {

    @FXML
    public TableView <WizytaGabinetu> tabela_wizyt;

    @FXML
    public TableColumn <WizytaGabinetu, String> imie_klienta;

    @FXML
    public TableColumn <WizytaGabinetu, String> nazwisko_klienta;

    @FXML
    public TableColumn <WizytaGabinetu, String> nazwa_uslugi;

    @FXML
    public TableColumn <WizytaGabinetu, String> pracownik;

    @FXML
    public TableColumn <WizytaGabinetu, String> data;

    @FXML
    public TableColumn <WizytaGabinetu, String> status;

    private ObservableList<WizytaGabinetu> lista_danych = FXCollections.observableArrayList();
    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }

    public void declineAppoitment(ActionEvent actionEvent) {
        try {
            WizytaGabinetu zaznaczony = tabela_wizyt.getSelectionModel().getSelectedItem();
            if (zaznaczony.getId() > 0) {

                Query q1 = mainController.getEm().createQuery("SELECT p FROM Wizyta p WHERE p.id = :id");
                q1.setParameter("id", zaznaczony.getId());
                Wizyta finder = (Wizyta) q1.getSingleResult();

                Wizyta zmiana_wizyty = mainController.getEm().find(Wizyta.class, finder);

                mainController.getEm().getTransaction().begin();
                zmiana_wizyty.setStatus("Odrzucone");
                mainController.getEm().getTransaction().commit();
                mainController.switchScreen("menu_klienta_wizyty", true);
            }
        } catch (NullPointerException e){
            System.out.println("Nic nie zaznaczono");
        }
    }

    public void loadData(){
        try {
            Query q1 = mainController.getEm().createQuery("SELECT p FROM Wizyta p WHERE p.id_klienta = :id");
            q1.setParameter("id", mainController.getKlient_zalogowany_w_systemie().getId());

            List lista_wizyt = q1.getResultList();
            Wizyta temp_wizyta = null;
            Pracownik temp_pracownik = null;
            Klient temp_klient = null;
            Usluga temp_usluga = null;

            while (!lista_wizyt.isEmpty()) {

                temp_wizyta = (Wizyta) lista_wizyt.get(0);

                /* Pobieramy pracownika */
                Query q2 = mainController.getEm().createQuery("SELECT p FROM Pracownik p WHERE p.id = :id");
                q2.setParameter("id", temp_wizyta.getId_pracownika());
                temp_pracownik = (Pracownik) q2.getSingleResult();

                /* Pobieramy klienta */
                Query q3 = mainController.getEm().createQuery("SELECT p FROM Klient p WHERE p.id = :id");
                q3.setParameter("id", temp_wizyta.getId_klienta());
                temp_klient = (Klient) q3.getSingleResult();

                /* Pobieramy usluge */
                Query q4 = mainController.getEm().createQuery("SELECT p FROM Usluga p WHERE p.id = :id");
                q4.setParameter("id", temp_wizyta.getId_uslugi());
                temp_usluga = (Usluga) q4.getSingleResult();

                lista_danych.add(new WizytaGabinetu(temp_wizyta.getId(), temp_klient.getImie(), temp_klient.getNazwisko(), temp_usluga.getNazwa_uslugi(), temp_pracownik.getNazwisko(), new SimpleDateFormat("dd/MM/yyyy").format(temp_wizyta.getData_uslugi()), temp_wizyta.getStatus()));
                lista_wizyt.remove(0);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        setCellValuesFactory();
    }
    private void setCellValuesFactory() {
        imie_klienta.setCellValueFactory(new PropertyValueFactory<>("imie_klienta"));
        nazwisko_klienta.setCellValueFactory(new PropertyValueFactory<>("nazwisko_klienta"));
        nazwa_uslugi.setCellValueFactory(new PropertyValueFactory<>("nazwa_uslugi"));
        pracownik.setCellValueFactory(new PropertyValueFactory<>("pracownik"));
        data.setCellValueFactory(new PropertyValueFactory<>("data"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        tabela_wizyt.setItems(lista_danych);
    }

}
