// plik: src/Kwalifikowana/PozycjaZakupu.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PozycjaZakupu extends ObjectPlus {
    private int numerPozycji;
    private String nazwaTowaru;
    private int ilosc;
    private final List<Zakup> zakupList = new ArrayList<>(); // Asocjacja kwalifikowana z Zakup

    public PozycjaZakupu(int numerPozycji, String nazwaTowaru, int ilosc) {
        setNumerPozycji(numerPozycji);
        setNazwaTowaru(nazwaTowaru);
        setIlosc(ilosc);
        addExtent();
    }

    public int getNumerPozycji() {
        return numerPozycji;
    }

    public void setNumerPozycji(int numerPozycji) {
        this.numerPozycji = numerPozycji;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public List<Zakup> getZakupList() {
        return Collections.unmodifiableList(zakupList);
    }

    public void addZakup(Zakup zakup) { // Asocjacja kwalifikowana: dodaje powiązanie z zakupem
        if (zakup == null) {
            throw new IllegalArgumentException("Zakup nie może być null");
        }
        if (!zakupList.contains(zakup)) {
            zakupList.add(zakup);
            PozycjaZakupu istniejąca = zakup.znajdzPozycje(getNumerPozycji());
            if (istniejąca == null) {
                zakup.addPozycja(this);
            }
        }
    }

    public void usunZakup(Zakup zakup) { // Asocjacja kwalifikowana: usuwa powiązanie z zakupem
        if (zakupList.remove(zakup)) {
            zakup.usunPozycje(this);
        }
    }

    @Override
    public void removeFromExtent() {
        for (Zakup z : new ArrayList<>(zakupList)) {
            usunZakup(z);
        }
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "PozycjaZakupu{" +
                "numerPozycji=" + numerPozycji +
                ", nazwaTowaru='" + nazwaTowaru + '\'' +
                ", ilosc=" + ilosc +
                '}';
    }
}
