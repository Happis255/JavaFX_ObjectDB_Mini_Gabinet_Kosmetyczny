package ModelTabeli;

public class KsiazeczkaZdrowia {

    String opis;
    String dane;

    public KsiazeczkaZdrowia(String opis, String dane) {
        this.opis = opis;
        this.dane = dane;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getDane() {
        return dane;
    }

    public void setDane(String dane) {
        this.dane = dane;
    }
}
