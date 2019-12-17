package Controller;

import Controller.Client.ClientMenuController;
import Controller.Worker.*;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import java.io.IOException;

public class MainScreenController {

    @FXML
    private StackPane MainStackPane;

    @FXML
    public void initialize(){
        switchScreen("menu", true);
    }

    /* Animacja przejścia okien z białego ekranu do ekranu normalnego */
    public static void FadeIn(int time, Node X){
        FadeTransition fadein = new FadeTransition();
        fadein.setDuration(Duration.millis(time));
        fadein.setNode(X);
        fadein.setFromValue(0);
        fadein.setToValue(1);
        fadein.play();
    }

    /* Metoda odpowiedzialna za ładowanie nowego okna aplikacji */
    public void setScreen(Pane pane){
        MainStackPane.getChildren().clear();
        MainStackPane.getChildren().add(pane);
    }


    /* Zwraca odpowiedni FXMLLoader określony przez Stringa */
    public FXMLLoader getFXMLLoader(String loaderResource){
        FXMLLoader loader = null;
        if (loaderResource.equals("menu"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Login.fxml"));

        if (loaderResource.equals("menu_pracownika"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Worker/MainWorker.fxml"));
        if (loaderResource.equals("menu_pracownika_dane_konta"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Worker/WorkerAccountData.fxml"));
        if (loaderResource.equals("menu_pracownika_dane_ksiazeczki"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Worker/WorkerCardSetting.fxml"));
        if (loaderResource.equals("menu_pracownika_oferta"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Worker/WorkerOfferSetting.fxml"));
        if (loaderResource.equals("menu_pracownik_wizyty"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Worker/WorkerAppoitmentSetting.fxml"));

        if (loaderResource.equals("menu_klienta"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Client/MainClient.fxml"));
        return loader;
    }

    /* Metoda odpowiedzialna za ładowanie nowego okna aplikacji */
    public void switchScreen(String loaderResource, boolean fadeAnimation){

        FXMLLoader loader = getFXMLLoader(loaderResource);
        Pane pane = null;

        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* Tworzenie odpowiedniego controllera */
        /* Tworzenie kontrolera menu */
        if (loaderResource.equals("menu")){
            LoginScreenController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_klienta")){
            ClientMenuController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_pracownika")){
            WorkerMenuController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_pracownika_dane_konta")){
            WorkerAccountDataController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_pracownika_dane_ksiazeczki")){
            WorkerCardDataController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_pracownika_oferta")){
            WorkerOfferSettingController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_pracownik_wizyty")){
            WorkerAppoitmentSettingController menuController = loader.getController();
            menuController.setMainController(this);
        }

        setScreen(pane);
        if (fadeAnimation) {
            FadeIn(1000, MainStackPane.getChildren().get(0));
        }
    }
}
