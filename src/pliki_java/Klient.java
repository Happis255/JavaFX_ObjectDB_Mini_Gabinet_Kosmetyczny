package pliki_java;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Klient extends Uzytkownik implements Serializable {

    static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private long id;
    private long id_kontaktu;
    private Klient.StatusKlienta statusKlienta;
    private String uwagi_klienta;
    private int numer_karty;


    public Klient(
            String login,
            String haslo,
            String imie,
            String nazwisko,
            Date data_urodzenia,
            String plec,
            String informacje_dodatkowe,
            int numer_karty,
            long id_kontaktu) {
        super(login, haslo, imie, nazwisko, data_urodzenia, plec, informacje_dodatkowe);
        this.statusKlienta = Klient.StatusKlienta.NOWY_UZYTKOWNIK;
        this.numer_karty = numer_karty;
        this.id_kontaktu = id_kontaktu;
    }

    public enum StatusKlienta{
        NOWY_UZYTKOWNIK ("Nowy użytkownik"), STALY_KLIENT ("Stały klient"), KLIENT_PREMIUM ("Klient premium");
        String nazwa;
        StatusKlienta(String nazwa){
            this.nazwa = nazwa;
        }
        public String getNazwa(){
            return nazwa;
        }
    }

    public long getId() {
        return id;
    }

    public StatusKlienta getStatusKlienta() {
        return statusKlienta;
    }

    public String getUwagi_klienta() {
        return uwagi_klienta;
    }

    public int getNumer_karty() {
        return numer_karty;
    }

    public long getId_kontaktu() {
        return id_kontaktu;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "id=" + id +
                ", id_kontaktu=" + id_kontaktu +
                ", statusKlienta=" + statusKlienta +
                ", uwagi_klienta='" + uwagi_klienta + '\'' +
                ", numer_karty=" + numer_karty +
                "} " + super.toString();
    }
}
