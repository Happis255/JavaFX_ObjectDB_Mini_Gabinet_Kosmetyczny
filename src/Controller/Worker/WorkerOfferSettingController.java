package Controller.Worker;

import Controller.MainScreenController;
import ModelTabeli.KsiazeczkaZdrowia;
import ModelTabeli.OfertaGabinetu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pliki_java.Ksiazeczka_Zdrowia;
import pliki_java.Usluga;

import javax.persistence.Query;
import java.util.List;

public class WorkerOfferSettingController {

    @FXML
    public TextField offer_describe;

    @FXML
    public TextField offer_price;

    @FXML
    public TextField offer_time;

    @FXML
    public TextField offer_name;

    @FXML
    public TableView <OfertaGabinetu> tabela_uslug;

    @FXML
    public TableColumn <OfertaGabinetu, String> nazwa_uslugi;

    @FXML
    public TableColumn <OfertaGabinetu, String> opis_uslugi;

    @FXML
    public TableColumn <OfertaGabinetu, Integer> cena;

    @FXML
    public TableColumn <OfertaGabinetu, Integer> czas;

    private ObservableList<OfertaGabinetu> lista_danych = FXCollections.observableArrayList();
    private MainScreenController mainController;

    public void setMainController(MainScreenController mainController) {
        this.mainController = mainController;
    }

    public void backMenu(ActionEvent actionEvent) {
        mainController.switchScreen("menu_pracownika", true);
    }

    @FXML
    public void addOffer(ActionEvent actionEvent) {
        try {
            String nazwa = offer_name.getText();
            String opis = offer_describe.getText();
            int cena = Integer.parseInt(offer_price.getText());
            int czas = Integer.parseInt(offer_time.getText());

            if (nazwa.length() > 1 && opis.length() > 1 && cena > 0 && czas > 0) {
                mainController.getEm().getTransaction().begin();
                Usluga przykladowa_usluga = new Usluga(nazwa, opis, cena, czas);
                mainController.getEm().persist(przykladowa_usluga);
                mainController.getEm().getTransaction().commit();
                mainController.switchScreen("menu_pracownika_oferta", true);
            }
        } catch (NumberFormatException e){
            System.out.println("Bledne dane w formach.");
        }
    }

    @FXML
    public void removeOffer(ActionEvent actionEvent) {
        try {
            OfertaGabinetu zaznaczony = tabela_uslug.getSelectionModel().getSelectedItem();
            if (zaznaczony.getId() > 0) {

                Query q1 = mainController.getEm().createQuery("SELECT p FROM Usluga p WHERE p.id = :id");
                q1.setParameter("id", zaznaczony.getId());
                Usluga finder = (Usluga) q1.getSingleResult();

                Usluga do_usuniecia = mainController.getEm().find(Usluga.class, finder);

                mainController.getEm().getTransaction().begin();
                mainController.getEm().remove(do_usuniecia);
                mainController.getEm().getTransaction().commit();

                mainController.switchScreen("menu_pracownika_oferta", true);
            }
        } catch (NullPointerException e){
            System.out.println("Nic nie zaznaczono");
        }
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
