package KlasaAbstrakcyjna;

import util.ObjectPlus;

public abstract class Osoba extends ObjectPlus {
    private String imie;
    private String nazwisko;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Osoba(Osoba osoba) {
        this.imie = osoba.imie;
        this.nazwisko = osoba.nazwisko;
    }

    public String getImie() {
        return imie;
    }
    public abstract String getOpis();

    public String getNazwisko() {
        return nazwisko;
    }
}
