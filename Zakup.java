// plik: src/Kwalifikowana/Zakup.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Zakup extends ObjectPlus {
    private String numerZakupu;
    private Map<Integer, PozycjaZakupu> pozycjeMap = new HashMap<>();              // Asocjacja kwalifikowana z PozycjaZakupu
    private final List<Produkt> produkty = new ArrayList<>();                      // Asocjacja wiele–do–wielu z Produktem

    public Zakup(String numerZakupu) {
        if (numerZakupu == null || numerZakupu.isBlank()) {
            throw new IllegalArgumentException("Numer zakupu nie może być pusty");
        }
        this.numerZakupu = numerZakupu;
        addExtent();
    }

    public String getNumerZakupu() {
        return numerZakupu;
    }

    public Map<Integer, PozycjaZakupu> getPozycjeMap() {
        return Collections.unmodifiableMap(pozycjeMap);
    }

    public void addPozycja(PozycjaZakupu pozycjaZakupu) {
        int klucz = pozycjaZakupu.getNumerPozycji();
        if (!pozycjeMap.containsKey(klucz)) {
            pozycjeMap.put(klucz, pozycjaZakupu);
            pozycjaZakupu.addZakup(this);
        } else {
            throw new IllegalArgumentException(
                    "Pozycja o numerze " + klucz + " już istnieje w zakupie: " + numerZakupu
            );
        }
    }

    public void usunPozycje(PozycjaZakupu pozycjaZakupu) {
        pozycjeMap.remove(pozycjaZakupu.getNumerPozycji());
        pozycjaZakupu.usunZakup(this);
    }

    public PozycjaZakupu znajdzPozycje(int numerPozycji) {
        return pozycjeMap.get(numerPozycji);
    }

    public List<Produkt> getProdukty() {
        return Collections.unmodifiableList(produkty);
    }

    public void addProdukt(Produkt p) {
        if (p == null) {
            throw new IllegalArgumentException("Produkt nie może być null");
        }
        produkty.add(p);
        p.addZakup(this);
    }

    public void removeProdukt(Produkt p) {
        if (p == null) {
            return;
        }
        if (produkty.remove(p)) {
            p.removeZakup(this);
        }
    }

    @Override
    public void removeFromExtent() {
        // usuń wszystkie pozycje
        for (PozycjaZakupu poz : pozycjeMap.values()) {
            poz.usunZakup(this);
        }
        pozycjeMap.clear();
        // usuń wszystkie produkty
        for (Produkt p : new ArrayList<>(produkty)) {
            removeProdukt(p);
        }
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Zakup{" +
                "numerZakupu='" + numerZakupu + '\'' +
                ", pozycjeMap=" + pozycjeMap +
                ", produkty=" + produkty.stream()
                .map(produkt -> String.valueOf(produkt.getId()))
                .toList() +
                '}';
    }
}
