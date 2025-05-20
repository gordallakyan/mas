package Wielodziedziczenie;


public class DostawcaInspektor extends Dostawca implements IInspektorJakosci {
    private int liczbaSkontrolowanych;

    public DostawcaInspektor(String imie, String nazwisko,
        int liczbaDostaw, int liczbaSkontrolowanych) {
        super(imie, nazwisko, liczbaDostaw);
        this.liczbaSkontrolowanych = liczbaSkontrolowanych;
    }

    @Override
    public int getLiczbaSkontrolowanych() {
        return liczbaSkontrolowanych;
    }

    @Override
    public String getOpis() {
        return "Dostawca-Inspektor " + getImie() + " " + getNazwisko() +
                ", dostaw: " + getLiczbaDostaw() +
                ", skontrolowanych: " + liczbaSkontrolowanych;
    }
}
