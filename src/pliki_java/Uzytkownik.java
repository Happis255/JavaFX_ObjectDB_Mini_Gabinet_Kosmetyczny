package pliki_java;
import javax.jdo.annotations.Inheritance;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance
public class Uzytkownik implements Serializable {

    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private Date data_urodzenia;
    private String plec;
    private String informacje_dodatkowe;

    public Uzytkownik (String login, String haslo, String imie, String nazwisko, Date data_urodzenia, String plec, String informacje_dodatkowe) {
        this.login = login;
        this.haslo = haslo;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.data_urodzenia = data_urodzenia;
        this.plec = plec;
        this.informacje_dodatkowe = informacje_dodatkowe;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Date getData_urodzenia() {
        return data_urodzenia;
    }

    public String getPlec() {
        return plec;
    }

    public String getInformacje_dodatkowe() {
        return informacje_dodatkowe;
    }

    @Override
    public String toString() {
        return "Uzytkownik{" +
                "login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", data_urodzenia=" + data_urodzenia +
                ", plec='" + plec + '\'' +
                ", informacje_dodatkowe='" + informacje_dodatkowe + '\'' +
                '}';
    }
}
