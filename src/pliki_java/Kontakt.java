package pliki_java;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Kontakt implements Serializable {

    static final long serialVersionUID = 1L;

    @Id @GeneratedValue
    private long id;

    private int telefon;
    private String ZIP_miasto;
    private String ulica;
    private int numer_budynku;
    private int numer_mieszkania;
    private String e_mail;

    @Override
    public String toString() {
        return "Kontakt{" +
                "id=" + id +
                ", telefon=" + telefon +
                ", ZIP_miasto='" + ZIP_miasto + '\'' +
                ", ulica='" + ulica + '\'' +
                ", numer_budynku=" + numer_budynku +
                ", numer_mieszkania=" + numer_mieszkania +
                ", e_mail='" + e_mail + '\'' +
                '}';
    }

    public Kontakt(int telefon, String ZIP_miasto, String ulica, int numer_budynku, int numer_mieszkania, String e_mail) {
        this.telefon = telefon;
        this.ZIP_miasto = ZIP_miasto;
        this.ulica = ulica;
        this.numer_budynku = numer_budynku;
        this.numer_mieszkania = numer_mieszkania;
        this.e_mail = e_mail;
    }
    public long getId() {
        return id;
    }

    public int getTelefon() {
        return telefon;
    }

    public String getZIP_miasto() {
        return ZIP_miasto;
    }

    public String getUlica() {
        return ulica;
    }

    public int getNumer_budynku() {
        return numer_budynku;
    }

    public int getNumer_mieszkania() {
        return numer_mieszkania;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }

    public void setZIP_miasto(String ZIP_miasto) {
        this.ZIP_miasto = ZIP_miasto;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setNumer_budynku(int numer_budynku) {
        this.numer_budynku = numer_budynku;
    }

    public void setNumer_mieszkania(int numer_mieszkania) {
        this.numer_mieszkania = numer_mieszkania;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }
}
