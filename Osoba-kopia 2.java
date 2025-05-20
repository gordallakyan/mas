package Wielodziedziczenie;

import util.ObjectPlus;

public abstract class Osoba extends ObjectPlus {
    private String imie;
    private String nazwisko;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public String getImie() { return imie; }
    public String getNazwisko() { return nazwisko; }
    public abstract String getOpis();
}
