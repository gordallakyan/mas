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
    public void removeFromExtent(){
        for (Zamowienie zamowienie : dostawyList){
            zamowienie.usunPozycje();
        }
        dostawyList.clear();
        removeFromExtent();
    }

    @Override
    public String toString() {
        return "Klient{" +
                "pesel='" + pesel + '\'' +
                ", dostawyList=" + dostawyList.stream().map(zamowienie -> zamowienie.getDataZamowienia()).toList() +
                '}';
    }
}

