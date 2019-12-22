package pliki_java;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Ksiazeczka_Zdrowia implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private Boolean rozrusznik;
    private Boolean hermofilia;
    private Boolean luszczyca;
    private String alergia;
    private Boolean goraczka;
    private Boolean ciaza;
    private String oslabienia;

    public Ksiazeczka_Zdrowia(Boolean rozrusznik, Boolean hermofilia, Boolean luszczyca, String alergia, Boolean goraczka, Boolean ciaza, String oslabienia) {
        this.rozrusznik = rozrusznik;
        this.hermofilia = hermofilia;
        this.luszczyca = luszczyca;
        this.alergia = alergia;
        this.goraczka = goraczka;
        this.ciaza = ciaza;
        this.oslabienia = oslabienia;
    }

    public long getId() {
        return id;
    }

    public Boolean getRozrusznik() {
        return rozrusznik;
    }

    public Boolean getHermofilia() {
        return hermofilia;
    }

    public Boolean getLuszczyca() {
        return luszczyca;
    }

    public String getAlergia() {
        return alergia;
    }

    public Boolean getGoraczka() {
        return goraczka;
    }

    public Boolean getCiaza() {
        return ciaza;
    }

    public String getOslabienia() {
        return oslabienia;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setRozrusznik(Boolean rozrusznik) {
        this.rozrusznik = rozrusznik;
    }

    public void setHermofilia(Boolean hermofilia) {
        this.hermofilia = hermofilia;
    }

    public void setLuszczyca(Boolean luszczyca) {
        this.luszczyca = luszczyca;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public void setGoraczka(Boolean goraczka) {
        this.goraczka = goraczka;
    }

    public void setCiaza(Boolean ciaza) {
        this.ciaza = ciaza;
    }

    public void setOslabienia(String oslabienia) {
        this.oslabienia = oslabienia;
    }
}
