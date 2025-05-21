// plik: src/Kompozycja/Produkt.java

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Produkt extends ObjectPlus implements Serializable {
    private int id;
    private String nazwa;
    private String nazwaPoOrmiańsku; // atrybut opcjonalny
    private final List<Tag> tagi = new ArrayList<>(); // atrybut powtarzalny
    private static int maxLiczbaSkładników = 4; // atrybut klasowy
    private final List<Składnik> składnikList = new ArrayList<>(); // Kompozycja między Produktem a Składnikiem
    private final List<Zakup> zakupy = new ArrayList<>(); // Asocjacja wielu–do–wielu między Produktem a Zakupem

    public Produkt(int id, String nazwa, Składnik składnik) {
        try {
            setId(id);
            setNazwa(nazwa);
            dodajSkladnik(składnik);
            addExtent();
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public Produkt(int id, String nazwa, Składnik składnik, String nazwaPoOrmiańsku) {
        try {
            setId(id);
            setNazwa(nazwa);
            setNazwaPoOrmiańsku(nazwaPoOrmiańsku);
            dodajSkladnik(składnik);
            addExtent();
        } catch (Exception e) {
            e.printStackTrace();
            removeFromExtent();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        var extent = ObjectPlus.getExtent(Produkt.class);
        if (extent != null) {
            for (Object obj : extent) {
                if (obj instanceof Produkt p && p != this && p.id == id) {
                    throw new IllegalArgumentException("ID: " + id + " is already used");
                }
            }
        }
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNazwaPoOrmiańsku() {
        return nazwaPoOrmiańsku;
    }

    public void setNazwaPoOrmiańsku(String nazwaPoOrmiańsku) {
        if (nazwaPoOrmiańsku != null && nazwaPoOrmiańsku.isBlank()) {
            throw new IllegalArgumentException("Ormiańska nazwa produktu nie może być pusta");
        }
        this.nazwaPoOrmiańsku = nazwaPoOrmiańsku;
    }

    public List<Tag> getTagi() {
        return Collections.unmodifiableList(tagi);
    }

    public void addTag(Tag tag) {
        if (tag.getVat() < 0 || tag.getVat() > 100) {
            throw new IllegalArgumentException("Vat nie poprawny");
        }
        tagi.add(tag);
    }

    public void removeTag(String rodzaj) {
        tagi.removeIf(tag -> tag.getRodzaj().equals(rodzaj));
    }

    public double wyliczVat() {
        if (tagi.isEmpty()) {
            return 0;
        }
        double totalVat = 0;
        for (Tag tag : tagi) {
            totalVat += tag.getVat();
        }
        return totalVat / tagi.size();
    }

    public void dodajSkladnik(Składnik składnik) { // Kompozycja między Produktem a Składnikiem
        składnikList.add(składnik);
    }

    public void usunSkladnik(Składnik składnik) { // Kompozycja między Produktem a Składnikiem
        składnikList.remove(składnik);
    }

    public static int getMaxLiczbaSkładników() {
        return maxLiczbaSkładników;
    }

    public static void setMaxLiczbaSkładników(int max) {
        maxLiczbaSkładników = max;
    }

    public static Produkt wyszukajPoID(int id) {
        return ObjectPlus.getExtent(Produkt.class)
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Zakup> getZakupy() {
        return Collections.unmodifiableList(zakupy);
    }

    public void addZakup(Zakup z) { // Asocjacja między Produktem a Zakupem
        if (z == null) {
            throw new IllegalArgumentException("Zakup nie może być null");
        }
        zakupy.add(z);
        z.addProdukt(this);
    }

    public void removeZakup(Zakup z) { // Asocjacja między Produktem a Zakupem
        if (z == null) {
            return;
        }
        if (zakupy.remove(z)) {
            z.removeProdukt(this);
        }
    }

    @Override
    public void removeFromExtent() {
        for (Zakup z : new ArrayList<>(zakupy)) {
            removeZakup(z);
        }
        składnikList.clear();
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                (nazwaPoOrmiańsku != null ? ", nazwaPoOrmiańsku='" + nazwaPoOrmiańsku + '\'' : "") +
                ", zakupy=" + zakupy.stream().map(Zakup::getNumerZakupu).collect(Collectors.toList()) +
                '}';
    }
}