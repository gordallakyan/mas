package dynamic;

import Kompozycja_BAG_OgWłasne.Produkt;

import java.util.ArrayList;
import java.util.List;

public class Kasjer extends Osoba {
    private int ilośćSprzedanychProduktow;

    private List<Produkt> produkty = new ArrayList<>();
    public void addProdukt(Produkt produkt) {
        if (!produkty.contains(produkt)) {
            produkty.add(produkt);
            produkt.addKasjer(this);
        }
    }
    public void removeProdukt(Produkt produkt) {
        if (produkty.remove(produkt)) {
            produkt.removeKasjer(this);
            removeFromExtent();
        }
    }


    public Kasjer(String imie, String nazwisko, int ilośćSprzedanychProduktow) {
        super(imie, nazwisko);
        this.ilośćSprzedanychProduktow = ilośćSprzedanychProduktow;
    }
    public Piekarz awans(int iloscWypiekanychProduktow){
        Piekarz piekarz = new Piekarz(this, iloscWypiekanychProduktow);
        this.produkty.clear();
        this.removeFromExtent();
        return piekarz;
    }
}
