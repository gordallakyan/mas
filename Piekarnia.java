package ZwykłaAsocjacja_UNIQUE;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;

public class Piekarnia extends ObjectPlus {
    private int id;
    private String nazwa;
    private List<WspólnotaPiekarnii> wspólnotaPiekarniiList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Piekarnia(int id, String nazwa, List<WspólnotaPiekarnii> wspólnotaPiekarniiList) {
        this.id = id;
        this.nazwa = nazwa;
        this.wspólnotaPiekarniiList = wspólnotaPiekarniiList;
        addExtent();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) { //Uniqe
        if (ObjectPlus.getExtent(Piekarnia.class).stream().anyMatch(p -> p.getNazwa().equals(nazwa))){
            throw new IllegalArgumentException("Nazwa piekarni już istnieje");
        }
        this.nazwa = nazwa;
    }

    public List<WspólnotaPiekarnii> getWspólnotaPiekarniiList() {
        return wspólnotaPiekarniiList;
    }

    public void setWspólnotaPiekarniiList(List<WspólnotaPiekarnii> wspólnotaPiekarniiList) {
        this.wspólnotaPiekarniiList = wspólnotaPiekarniiList;
    }

    public void addWspólnotaPiekarnii(WspólnotaPiekarnii wspólnotaPiekarnii){
        if (!wspólnotaPiekarniiList.contains(wspólnotaPiekarnii)){
            wspólnotaPiekarniiList.add(wspólnotaPiekarnii);
            wspólnotaPiekarnii.addPiekarnia(this);
        }
    }
    public void removeWspólnotaPiekarnii(WspólnotaPiekarnii wspólnotaPiekarnii){
        if (wspólnotaPiekarniiList.remove(wspólnotaPiekarnii)){
            wspólnotaPiekarnii.removePiekarnia(this);
            removeFromExtent();
        }

    }

}
