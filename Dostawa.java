package zAtrybutem_Ograniczenie;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class Dostawa extends ObjectPlus {
    private String godzinaDostawy;
    private List<Zamowienie> klientList = new ArrayList<>();

    public Dostawa(String godzinaDostawy) {
        this.godzinaDostawy = godzinaDostawy;
    }
    public void addKlient(Klient klient){
        new Zamowienie(klient,this);
        addExtent();
    }
    public void addZamowienie(Zamowienie zamowienie){
     if (!klientList.contains(zamowienie) && zamowienie != null) {
         klientList.add(zamowienie);
     }
    }
    public void removeZamowienie(Zamowienie zamowienie){
        if (klientList.remove(zamowienie)){
            klientList.remove(this);
        }
    }

    public void setGodzinaDostawy(String godzinaDostawy) { //Ograniczenie Atrybutu
        if (godzinaDostawy == null || godzinaDostawy.isBlank()) {
            throw new IllegalArgumentException("godzinaDostawy nie może być pusta");
        }
        if (!godzinaDostawy.matches("\\d+")) {
            throw new IllegalArgumentException("godzinaDostawy nie może zawierać liter i musi być liczbą");
        }
        int godzina;
        try {
            godzina = Integer.parseInt(godzinaDostawy);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("godzinaDostawy musi być liczbą całkowitą");
        }
        if (godzina < 8 || godzina > 16) {
            throw new IllegalArgumentException("godzinaDostawy musi być w zakresie 8–16");
        }
        this.godzinaDostawy = godzinaDostawy;
    }


}
