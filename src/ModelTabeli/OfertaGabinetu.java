package ModelTabeli;

public class OfertaGabinetu {

    String nazwa_uslugi;
    String opis_uslugi;
    int cena;
    int czas;

    public OfertaGabinetu(String nazwa, String opis, int cena, int czas) {
        this.nazwa_uslugi = nazwa;
        this.opis_uslugi = opis;
        this.cena = cena;
        this.czas = czas;
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

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getCzas() {
        return czas;
    }

    public void setCzas(int czas) {
        this.czas = czas;
    }
}
