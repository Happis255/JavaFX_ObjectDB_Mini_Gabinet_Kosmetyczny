package pliki_java;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Usluga implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private String nazwa_uslugi;
    private String opis_uslugi;
    private int koszt;
    private int czas;

    public Usluga(String nazwa_uslugi, String opis_uslugi, int koszt, int czas) {
        this.nazwa_uslugi = nazwa_uslugi;
        this.opis_uslugi = opis_uslugi;
        this.koszt = koszt;
        this.czas = czas;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNazwa_uslugi() {
        return nazwa_uslugi;
    }

    public void setNazwa_uslugi(String nazwa_uslugi) {
        this.nazwa_uslugi = nazwa_uslugi;
    }

    public String getOpis_uslugi() {
        return opis_uslugi;
    }

    public void setOpis_uslugi(String opis_uslugi) {
        this.opis_uslugi = opis_uslugi;
    }

    public int getKoszt() {
        return koszt;
    }

    public void setKoszt(int koszt) {
        this.koszt = koszt;
    }

    public int getCzas() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas = czas;
    }
}
