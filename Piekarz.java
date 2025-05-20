package dynamic;

public class Piekarz extends Osoba {
    public int iloscWypiekanychProduktow;
    public Piekarz(String imie, String nazwisko, int iloscWypiekanychProduktow) {
        super(imie, nazwisko);
        this.iloscWypiekanychProduktow = iloscWypiekanychProduktow;

    }

    public Piekarz(Kasjer osoba, int iloscWypiekanychProduktow){
        super(osoba);
        this.iloscWypiekanychProduktow = iloscWypiekanychProduktow;
    }
}
