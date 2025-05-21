import java.io.Serializable;

public class Tag implements Serializable {
    private String rodzaj;
    private int vat;

    public Tag(String rodzaj, int vat) {
        setRodzaj(rodzaj);
        setVat(vat);
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }
    @Override
    public String toString() {
        return "Rodzaj: " + rodzaj +
                ", Vat: " + vat;
    }
}
