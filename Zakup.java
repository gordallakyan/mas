package Kwalifikowana;

import util.ObjectPlus;

import java.util.HashMap;
import java.util.Map;

public class Zakup extends ObjectPlus {
    private String numerZakupu;
    PozycjaZakupu pozycjaZakupu;

    private Map<Integer, PozycjaZakupu> pozycjeMap = new HashMap<>();

    public Zakup(String numerZakupu) {
        this.numerZakupu = numerZakupu;
    }

    public String getnumerZakupu() {
        return numerZakupu;
    }



    public void addPozycja(PozycjaZakupu pozycjaZakupu) {
        if(pozycjeMap.containsKey(pozycjaZakupu.getNumerPozycji())) {
            pozycjeMap.put(pozycjaZakupu.getNumerPozycji(), pozycjaZakupu);
            pozycjaZakupu.addZakup(this);
        }
    }

    public void usunPozycje() {
        pozycjeMap.remove(pozycjaZakupu.getNumerPozycji());
        pozycjaZakupu.usunPozycje();
        removeFromExtent();

    }


    public PozycjaZakupu znajdzPozycje(int numerPozycji) {
        return pozycjeMap.get(numerPozycji);
    }

    @Override
    public String toString() {
        return "Zamowienie{" +
                "numerZakupu='" + numerZakupu + '\'' +
                ", pozycjeMap=" + pozycjeMap +
                '}';
    }
}