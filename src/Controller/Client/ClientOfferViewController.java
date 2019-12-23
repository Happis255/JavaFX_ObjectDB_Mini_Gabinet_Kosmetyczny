package Controller.Client;

import Controller.MainScreenController;
import ModelTabeli.OfertaGabinetu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pliki_java.Usluga;

import javax.persistence.Query;
import java.util.List;

public class ClientOfferViewController {

    @FXML
    public TableView <OfertaGabinetu> tabela_uslug;

    @FXML
    public TableColumn <OfertaGabinetu, String>  nazwa_uslugi;

    @FXML
    public TableColumn <OfertaGabinetu, String>  opis_uslugi;

    @FXML
    public TableColumn <OfertaGabinetu, Integer>  cena;

    @FXML
    public TableColumn <OfertaGabinetu, Integer>  czas;

    private ObservableList<OfertaGabinetu> lista_danych = FXCollections.observableArrayList();
    private MainScreenController mainController;
    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_klienta", true);
    }

    public void loadData(){
        try {
            Query q1 = mainController.getEm().createQuery("SELECT p FROM Usluga p");
            List lista_uslug = q1.getResultList();
            Usluga temp = null;
            while (!lista_uslug.isEmpty()) {
                temp = (Usluga) lista_uslug.get(0);
                lista_danych.add(new OfertaGabinetu(temp.getNazwa_uslugi(), temp.getOpis_uslugi(), temp.getKoszt(), temp.getCzas(), temp.getId()));
                lista_uslug.remove(0);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        setCellValuesFactory();
    }

    private void setCellValuesFactory() {
        nazwa_uslugi.setCellValueFactory(new PropertyValueFactory<>("nazwa_uslugi"));
        opis_uslugi.setCellValueFactory(new PropertyValueFactory<>("opis_uslugi"));
        cena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        czas.setCellValueFactory(new PropertyValueFactory<>("czas"));
        tabela_uslug.setItems(lista_danych);
    }

}
