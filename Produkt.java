package Kompozycja_BAG_OgWłasne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dynamic.Kasjer;
import util.ObjectPlus;


public class Produkt  extends ObjectPlus implements Serializable {
    private int id;
    private String nazwa;
    private Składnik składnik;
    private String nazwaPoOrmiańsku; //atr. opcjonalny
    private final List<Tag> tagi = new ArrayList<>(); //atr. powtarzalny
    private static int maxLiczbaSkładników = 4; //atr. klasowy
    private List<Składnik> składnikList = new ArrayList<>(); //Kompozycja między Produktem a składnikiem
    private List<Kasjer> kasjerzy = new ArrayList<>();


    public void dodajSkladnik(Składnik składnik) {  // BAG
        składnikList.add(składnik);
    }

    public void addKasjer(Kasjer kasjer) {
        if (!kasjerzy.contains(kasjer)) {
            kasjerzy.add(kasjer);
            kasjer.addProdukt(this);
        }
    }
    public void removeKasjer(Kasjer kasjer) {
        if (kasjerzy.remove(kasjer)) {
            kasjer.removeProdukt(this);
            removeFromExtent();  // Usunięcie z ekstensji, jeśli chcemy wyrzucić obiekt z systemu
        }
    }


    public void usunSkladnik (Składnik składnik){ //Kompozycja między Produktem a składnikiem
        składnikList.remove(składnik);
        składnik.removeFromExtent();
    }
    @Override
    public void removeFromExtent(){ //Kompozycja między Produktem a składnikiem
        while (!składnikList.isEmpty()){
            usunSkladnik(składnikList.get(0));
        }
        super.removeFromExtent();

    }
    public double wyliczVat() { //atr.pochodny
        if (tagi.isEmpty()) {
            return 0;
        }
        double totalVat = 0;
        for (Tag tag : tagi) {
            totalVat += tag.getVat();
        }
        return totalVat / tagi.size();
    }


    public static Produkt wyszukajPoID(int id){  //metoda klasowa
        return ObjectPlus.getExtent(Produkt.class)
                .stream().filter(produkt -> produkt.getId() == id).
                findFirst().
                orElse(null);
    }


    public Produkt(int id, String nazwa, Składnik składnik){
        try {
            setId(id);
            setNazwa(nazwa);
            setSkładnik(składnik);
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }
    public Produkt(int id, String nazwa, Składnik składnik, String nazwaPoOrmiańsku){ //przeciazenie
        try {
            setId(id);
            setNazwa(nazwa);
            setSkładnik(składnik);
            setNazwaPoOrmiańsku(nazwaPoOrmiańsku);
        }
        catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        var extent = ObjectPlus.getExtent(Produkt.class);
        if (extent != null){
            for (Object obj : extent){
                if (obj instanceof  Produkt p && p != this){
                    if (p.id == id)
                        throw new IllegalArgumentException("ID: " + id + " is already used");
                }
            }
        }
        this.id = id;
    }

    public static int getMaxLiczbaSkładników() {
        return maxLiczbaSkładników;
    }

    public static void setMaxLiczbaSkładników(int maxLiczbaSkładników) {
        Produkt.maxLiczbaSkładników = maxLiczbaSkładników;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public Składnik getSkładnik() {
        return składnik;
    }

    public void setSkładnik(Składnik składnik) { //Ograniczene własne
        if (tagi.stream().anyMatch(tag -> tag.getVat() > 10)
                && nazwa != null && nazwa.length() > 5
                && składnikList.size() == 3) {
            System.out.println("Piekarnia wybuchnie za 3,21");
        }
        else
            this.składnik = składnik;
    }


    public String getNazwaPoOrmiańsku() {
        return nazwaPoOrmiańsku;
    }

    public List<Tag> getTagi() {
        return Collections.unmodifiableList(tagi);
    }

    public void addTag(Tag tag) {
        if (tag.getVat() < 0 && tag.getVat() > 100){
            throw new IllegalArgumentException("Vat nie poprawny");
        }

        tagi.add(tag);

    }

    public void removeTag(String rodzaj){
        tagi.removeIf(tag -> tag.getRodzaj().equals(rodzaj));
    }

    public void setNazwaPoOrmiańsku(String nazwaPoOrmiańsku) {
        if (nazwaPoOrmiańsku != null){
            if (nazwaPoOrmiańsku.isBlank())
                throw new IllegalArgumentException("Ormiańska nazwa produktu nie może być pusta");
        }
        this.nazwaPoOrmiańsku = nazwaPoOrmiańsku;
    }
    @Override
    public String toString() { //przesloniecie
        if (nazwaPoOrmiańsku != null) {
            return "\n{ " + "\nId: " +
                    id + "\nNazwa:" +
                    nazwa + " \nNazwa po Ormiańsku: " +
                    nazwaPoOrmiańsku + "\nTagi: " +
                    tagi + "\n}" + "\n";
        }
        return "\n{ " + "\nId: " +
                id + "\nNazwa: " +
                nazwa + "\nTagi: " +
                tagi + "\n}" + "\n";
    }

}



