package ModelTabeli;

public class WizytaGabinetu {
    private long id;
    private String imie_klienta;
    private String nazwisko_klienta;
    private String nazwa_uslugi;
    private String pracownik;
    private String data;
    private String status;

    public WizytaGabinetu(long id, String imie_klienta, String nazwisko_klienta, String nazwa_uslugi, String pracownik, String data, String status) {
        this.id = id;
        this.imie_klienta = imie_klienta;
        this.nazwisko_klienta = nazwisko_klienta;
        this.nazwa_uslugi = nazwa_uslugi;
        this.pracownik = pracownik;
        this.data = data;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImie_klienta() {
        return imie_klienta;
    }

    public void setImie_klienta(String imie_klienta) {
        this.imie_klienta = imie_klienta;
    }

    public String getNazwisko_klienta() {
        return nazwisko_klienta;
    }

    public void setNazwisko_klienta(String nazwisko_klienta) {
        this.nazwisko_klienta = nazwisko_klienta;
    }

    public String getNazwa_uslugi() {
        return nazwa_uslugi;
    }

    public void setNazwa_uslugi(String nazwa_uslugi) {
        this.nazwa_uslugi = nazwa_uslugi;
    }

    public String getPracownik() {
        return pracownik;
    }

    public void setPracownik(String pracownik) {
        this.pracownik = pracownik;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
