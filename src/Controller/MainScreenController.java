package Controller;

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

        setScreen(pane);
        if (fadeAnimation) {
            FadeIn(3000, MainStackPane.getChildren().get(0));
        }
    }
}
