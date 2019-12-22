package Controller.Worker;

import Controller.MainScreenController;
import ModelTabeli.KsiazeczkaZdrowia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pliki_java.Ksiazeczka_Zdrowia;
import pliki_java.Pracownik;

import javax.persistence.Query;

public class WorkerCardDataController {

    @FXML
    public TextField change_input;

    @FXML
    public TableView <KsiazeczkaZdrowia> tabela_ksiazeczki;

    @FXML
    public TableColumn <KsiazeczkaZdrowia, String> opis;

    @FXML
    public TableColumn <KsiazeczkaZdrowia, String> dane;

    private ObservableList<KsiazeczkaZdrowia> lista_danych = FXCollections.observableArrayList();

    private MainScreenController mainController;
    private Ksiazeczka_Zdrowia ksiazeczka_zdrowia;
    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void doChange(ActionEvent actionEvent) {

        String zmiana = change_input.getText();
        KsiazeczkaZdrowia zaznaczony = tabela_ksiazeczki.getSelectionModel().getSelectedItem();

        if (zmiana.length() > 1 && zaznaczony != null){
            Ksiazeczka_Zdrowia ksiazeczka_zmiana = mainController.getEm().find(Ksiazeczka_Zdrowia.class, this.ksiazeczka_zdrowia);
            String opis = zaznaczony.getOpis();
            mainController.getEm().getTransaction().begin();
            if (opis == "Rozrusznik Serca"){
                ksiazeczka_zmiana.setRozrusznik(valueOfString(zmiana));
            }
            if (opis == "Hermofilia"){
                ksiazeczka_zmiana.setHermofilia(valueOfString(zmiana));
            }
            if (opis == "Luszczyca"){
                ksiazeczka_zmiana.setLuszczyca(valueOfString(zmiana));
            }
            if (opis == "Alergie"){
                ksiazeczka_zmiana.setAlergia(zmiana);
            }
            if (opis == "Gorączka"){
                ksiazeczka_zmiana.setGoraczka(valueOfString(zmiana));
            }
            if (opis == "Ciaża"){
                ksiazeczka_zmiana.setCiaza(valueOfString(zmiana));
            }
            if (opis == "Osłabienia"){
                ksiazeczka_zmiana.setOslabienia(zmiana);
            }
            mainController.getEm().getTransaction().commit();
            mainController.switchScreen("menu_pracownika_dane_ksiazeczki", true);
        }
    }

    public void loadData(){
        Query q1 =  mainController.getEm().createQuery("SELECT p FROM Ksiazeczka_Zdrowia p WHERE p.id = :id");
        q1.setParameter("id", mainController.getPracownik_zalogowany_w_systemie().getId_ksiazeczkiZdrowia());
        ksiazeczka_zdrowia = (Ksiazeczka_Zdrowia) q1.getSingleResult();

        lista_danych.add(new KsiazeczkaZdrowia(
                "Rozrusznik Serca", valueOfBoolean(ksiazeczka_zdrowia.getRozrusznik())));
        lista_danych.add(new KsiazeczkaZdrowia(
                "Hermofilia", valueOfBoolean(ksiazeczka_zdrowia.getHermofilia())));
        lista_danych.add(new KsiazeczkaZdrowia(
                "Luszczyca", valueOfBoolean(ksiazeczka_zdrowia.getLuszczyca())));
        lista_danych.add(new KsiazeczkaZdrowia(
                "Alergie", ksiazeczka_zdrowia.getAlergia()));
        lista_danych.add(new KsiazeczkaZdrowia(
                "Gorączka", valueOfBoolean(ksiazeczka_zdrowia.getGoraczka())));
        lista_danych.add(new KsiazeczkaZdrowia(
                "Ciaża", valueOfBoolean(ksiazeczka_zdrowia.getCiaza())));
        lista_danych.add(new KsiazeczkaZdrowia(
                "Osłabienia", ksiazeczka_zdrowia.getOslabienia()));
        setCellValuesFactory();
    }

    private String valueOfBoolean(boolean x){
        if (x) return "tak"; else return "nie";
    }
    private boolean valueOfString(String x){
        if (x.equals("tak") || x.equals("Tak")) return true; else return false;
    }
    private void setCellValuesFactory() {
        opis.setCellValueFactory(new PropertyValueFactory<>("opis"));
        dane.setCellValueFactory(new PropertyValueFactory<>("dane"));
        tabela_ksiazeczki.setItems(lista_danych);
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }
}
