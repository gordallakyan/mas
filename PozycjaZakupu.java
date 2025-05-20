package Kwalifikowana;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class PozycjaZakupu extends ObjectPlus {
    private int numerPozycji;
    private String nazwaTowaru;
    private int ilosc;
    private Zakup zakup;
    private List<Zakup> zakupList = new ArrayList<>();

    public PozycjaZakupu(int numerPozycji, String nazwaTowaru, int ilosc) {
        setNumerPozycji(numerPozycji);
        setNazwaTowaru(nazwaTowaru);
        setIlosc(ilosc);
        addExtent();
    }

    public int getNumerPozycji() {
        return numerPozycji;
    }

    public String getNazwaTowaru() {
        return nazwaTowaru;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setNumerPozycji(int numerPozycji) {
        this.numerPozycji = numerPozycji;
    }

    public void setNazwaTowaru(String nazwaTowaru) {
        this.nazwaTowaru = nazwaTowaru;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }
    public void addZakup(Zakup zakup){
        if (!zakupList.contains(zakup)){
            zakupList.add(zakup);
            zakup.addPozycja(this);
        }
    }
    public void usunPozycje(){
        zakupList.remove(zakup);
        zakup.usunPozycje();
        removeFromExtent();
    }


    @Override
    public String toString() {
        return "PozycjaZamowienia{" +
                "numerPozycji=" + numerPozycji +
                ", nazwaTowaru='" + nazwaTowaru + '\'' +
                ", ilosc=" + ilosc +
                '}';
    }
}