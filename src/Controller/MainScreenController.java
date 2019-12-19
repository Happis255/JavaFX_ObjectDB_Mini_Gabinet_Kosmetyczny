package Controller;

import Controller.Client.*;
import Controller.Worker.*;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import pliki_java.Klient;
import pliki_java.Kontakt;
import pliki_java.Ksiazeczka_Zdrowia;
import pliki_java.Pracownik;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MainScreenController {

    private EntityManagerFactory emf;
    private EntityManager em;

    private Klient klient_zalogowany_w_systemie;
    private Pracownik pracownik_zalogowany_w_systemie;

    @FXML
    private StackPane MainStackPane;

    @FXML
    public void initialize(){

        createDatabase();
        switchScreen("menu", true);
    }

    private void createDatabase() {
        /* Tworzymy połączenie z bazą danych, jesli nie istnieje, tworzymy bazę danych */
        emf = Persistence.createEntityManagerFactory("$objectdb/db/bazaDanychGabinetu.odb");
        em = emf.createEntityManager();
        long countClients = 0;

        try {
            Query q1 = em.createQuery("SELECT COUNT(p) FROM Klient p");
            countClients = (long) q1.getSingleResult();
            System.out.println("Liczba klientow w systemie: " + countClients);
            if (countClients == 2) return;
        } catch (Exception e) {
            System.out.println("Brak klientow w systemie, generuję nowych klientów...");
        }

        Kontakt kontakt;
        Klient klient;
        Pracownik pracownik;
        Ksiazeczka_Zdrowia ksiazeczka;


        /* Tworzymy dwóch przykładowych klientów gabinetu */
        em.getTransaction().begin();
        try {
            /* Tworzymy klienta nr 1 */
            kontakt = new Kontakt(609111131, "37-333", "Swierkowa", 20, 30, "Marzena255@gmail.com");
            klient = new Klient("Klient2", "321321", "Marzena", "Gąska", new SimpleDateFormat("dd/MM/yyyy").parse("21/04/1998"), "Kobieta", " ", 213141232, kontakt.getId());
            em.persist(kontakt);
            em.persist(klient);

            /* Tworzmy klienta nr 2*/
            kontakt = new Kontakt(688123111, "54-213", "Modrzewiowa", 30, 15, "Monika213@gmail.com");
            klient = new Klient("Klient", "123123", "Monika", "Butwiak", new SimpleDateFormat("dd/MM/yyyy").parse("10/10/1998"), "Kobieta", " ", 432123141, kontakt.getId());
            em.persist(kontakt);
            em.persist(klient);

            /* Tworzymy pracownika nr 1*/
            kontakt = new Kontakt(442333214, "32-423", "Sosnowska", 23, 12, "Marlena213@gmail.com");
            ksiazeczka = new Ksiazeczka_Zdrowia(false, false, false,"brak", false, false, "brak");
            pracownik = new Pracownik("admin", "admin", "Marlena", "Warszawska", new SimpleDateFormat("dd/MM/yyyy").parse("23/04/1997"), "Kobieta", "", kontakt.getId(), ksiazeczka.getId(), Pracownik.StopienWyksztalcenia.wyksztalcenie_wyzsze, 3000);

            em.persist(kontakt);
            em.persist(ksiazeczka);
            em.persist(pracownik);

            /* Tworzymy pracownika nr 2*/
            kontakt = new Kontakt(333245164, "12-213", "Lipowska", 32, 31, "Marysia213@gmail.com");
            ksiazeczka = new Ksiazeczka_Zdrowia(false, false, false,"brak", false, false, "brak");
            pracownik = new Pracownik("pracownik1", "123123", "Marja", "Krakowska", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1995"), "Kobieta", "", kontakt.getId(), ksiazeczka.getId(), Pracownik.StopienWyksztalcenia.wyksztalcenie_wyzsze, 5000);

            em.persist(kontakt);
            em.persist(ksiazeczka);
            em.persist(pracownik);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        em.getTransaction().commit();
    }

    public EntityManager getEm() {
        return em;
    }

    public Klient getKlient_zalogowany_w_systemie() {
        return klient_zalogowany_w_systemie;
    }

    public void setKlient_zalogowany_w_systemie(Klient klient_zalogowany_w_systemie) {
        this.klient_zalogowany_w_systemie = klient_zalogowany_w_systemie;
    }

    public Pracownik getPracownik_zalogowany_w_systemie() {
        return pracownik_zalogowany_w_systemie;
    }

    public void setPracownik_zalogowany_w_systemie(Pracownik pracownik_zalogowany_w_systemie) {
        this.pracownik_zalogowany_w_systemie = pracownik_zalogowany_w_systemie;
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
        if (loaderResource.equals("menu_klienta_dane"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Client/ClientAccountData.fxml"));
        if (loaderResource.equals("menu_klienta_oferta"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Client/ClientOfferView.fxml"));
        if (loaderResource.equals("menu_klienta_zapisz"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Client/ClientAppoitmentCreator.fxml"));
        if (loaderResource.equals("menu_klienta_wizyty"))
            loader = new FXMLLoader(this.getClass().getResource("/fxml/Client/ClientAppoitmentView.fxml"));

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

        if (loaderResource.equals("menu_klienta")){
            ClientMenuController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_klienta_dane")){
            ClientAccountDataController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_klienta_oferta")){
            ClientOfferViewController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_klienta_zapisz")){
            ClientAppoitmentCreatorController menuController = loader.getController();
            menuController.setMainController(this);
        }

        if (loaderResource.equals("menu_klienta_wizyty")){
            ClientAppoitmentViewController menuController = loader.getController();
            menuController.setMainController(this);
        }

        setScreen(pane);
        if (fadeAnimation) {
            FadeIn(1000, MainStackPane.getChildren().get(0));
        }
    }

}
