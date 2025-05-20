package Wielodziedziczenie;


public class InspektorJakosci extends Osoba implements IInspektorJakosci {
    private int liczbaSkontrolowanych;

    public InspektorJakosci(String imie, String nazwisko, int liczbaSkontrolowanych) {
        super(imie, nazwisko);
        this.liczbaSkontrolowanych = liczbaSkontrolowanych;
    }

    @Override
    public int getLiczbaSkontrolowanych() {
        return liczbaSkontrolowanych;
    }

    @Override
    public String getOpis() {
        return "Inspektor jakości " + getImie() + " " + getNazwisko() +
                ", skontrolowanych: " + liczbaSkontrolowanych;
    }
}