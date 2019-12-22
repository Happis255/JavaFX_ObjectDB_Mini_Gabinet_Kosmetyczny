package pliki_java;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Wizyta implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;
    private long id_klienta;
    private long id_pracownika;
    private long id_uslugi;
    private Date data_uslugi;
    private String dodatkowe_uwagi;
    private String status = "Niepotwierdzona";

    public Wizyta(long id_klienta, long id_pracownika, long id_uslugi, Date data_uslugi, String dodatkowe_uwagi) {
        this.id_klienta = id_klienta;
        this.id_pracownika = id_pracownika;
        this.id_uslugi = id_uslugi;
        this.data_uslugi = data_uslugi;
        this.dodatkowe_uwagi = dodatkowe_uwagi;
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

    public long getId_klienta() {
        return id_klienta;
    }

    public void setId_klienta(int id_klienta) {
        this.id_klienta = id_klienta;
    }

    public long getId_pracownika() {
        return id_pracownika;
    }

    public void setId_pracownika(int id_pracownika) {
        this.id_pracownika = id_pracownika;
    }

    public long getId_uslugi() {
        return id_uslugi;
    }

    public void setId_uslugi(int id_uslugi) {
        this.id_uslugi = id_uslugi;
    }

    public Date getData_uslugi() {
        return data_uslugi;
    }

    public void setData_uslugi(Date data_uslugi) {
        this.data_uslugi = data_uslugi;
    }

    public String getDodatkowe_uwagi() {
        return dodatkowe_uwagi;
    }

    public void setDodatkowe_uwagi(String dodatkowe_uwagi) {
        this.dodatkowe_uwagi = dodatkowe_uwagi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
