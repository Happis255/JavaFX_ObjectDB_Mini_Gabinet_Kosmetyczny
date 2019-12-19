package pliki_java;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Pracownik extends Uzytkownik implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private long id_kontaktu;
    private long id_ksiazeczkiZdrowia;
    private Pracownik.StopienWyksztalcenia stopienWyksztalcenia;
    private int wyplata;

    public Pracownik(
            String login,
            String haslo,
            String imie,
            String nazwisko,
            Date data_urodzenia,
            String plec,
            String informacje_dodatkowe,
            long id_kontaktu,
            long id_ksiazeczkiZdrowia,
            StopienWyksztalcenia stopienWyksztalcenia,
            int wyplata) {
        super(login, haslo, imie, nazwisko, data_urodzenia, plec, informacje_dodatkowe);
        this.id_kontaktu = id_kontaktu;
        this.id_ksiazeczkiZdrowia = id_ksiazeczkiZdrowia;
        this.stopienWyksztalcenia = stopienWyksztalcenia;
        this.wyplata = wyplata;
    }

    public enum StopienWyksztalcenia{
        wyksztalcenie_podstawowe, wyksztalcenie_gimnazjalne, wyksztalcenie_zasadnicze, wyksztalcenie_srednie, wyksztalcenie_wyzsze;
    }

    public long getId() {
        return id;
    }

    public long getId_kontaktu() {
        return id_kontaktu;
    }

    public long getId_ksiazeczkiZdrowia() {
        return id_ksiazeczkiZdrowia;
    }

    public StopienWyksztalcenia getStopienWyksztalcenia() {
        return stopienWyksztalcenia;
    }

    public int getWyplata() {
        return wyplata;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id=" + id +
                ", id_kontaktu=" + id_kontaktu +
                ", id_ksiazeczkiZdrowia=" + id_ksiazeczkiZdrowia +
                ", stopienWyksztalcenia=" + stopienWyksztalcenia +
                ", wyplata=" + wyplata +
                "} " + super.toString();
    }
}
