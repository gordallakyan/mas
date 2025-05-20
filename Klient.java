package zAtrybutem_Ograniczenie;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class Klient extends ObjectPlus {
    private String pesel;
    private List<Zamowienie> dostawyList = new ArrayList<>();



    public Klient(String pesel) {
        this.pesel = pesel;
    }
    public void addDostawa(Dostawa dostawa){
        new Zamowienie(this, dostawa);
        addExtent();
    }
    public void addZamowienie(Zamowienie zamowienie){
        if (!dostawyList.contains(zamowienie) && zamowienie != null) {
            dostawyList.add(zamowienie);
        }
    }
    public void removeZamowienie(Zamowienie zamowienie){
        if (dostawyList.remove(zamowienie)){
            dostawyList.remove(this);
        }
    }
    }

