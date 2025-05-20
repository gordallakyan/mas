package KlasaAbstrakcyjna;



public class Kasjer extends Osoba {
    private int iloscSprzedanychProduktow;

    public Kasjer(String imie, String nazwisko, int iloscSprzedanychProduktow) {
        super(imie, nazwisko);
        this.iloscSprzedanychProduktow = iloscSprzedanychProduktow;
    }

    public Kasjer(Kasjer osoba, int iloscSprzedanychProduktow) {
        super(osoba);
        this.iloscSprzedanychProduktow = iloscSprzedanychProduktow;
    }

    @Override
    public String getOpis() {
        return "Kasjer: " + getImie() + " " + getNazwisko() + ", sprzedanych produktów: " + iloscSprzedanychProduktow;
    }
}
