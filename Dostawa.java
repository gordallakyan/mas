import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dostawa extends ObjectPlus {
    private String godzinaDostawy;
    private final List<Składnik> składniki = new ArrayList<>(); // Asocjacja wiele–do–wielu między Dostawa a Składnik
    private final List<Zamowienie> zamówienia = new ArrayList<>(); // Asocjacja jeden–do–wielu między Dostawa a Zamówienie

    public Dostawa(String godzinaDostawy) {
        if (godzinaDostawy == null || godzinaDostawy.isBlank()) {
            throw new IllegalArgumentException("Godzina dostawy nie może być pusta");
        }
        this.godzinaDostawy = godzinaDostawy;
        addExtent();
    }

    public String getGodzinaDostawy() {
        return godzinaDostawy;
    }

    public List<Składnik> getSkładniki() {
        return Collections.unmodifiableList(składniki);
    }

    public void addSkładnik(Składnik s) { // Asocjacja wiele–do–wielu między Dostawa a Składnik
        if (s == null) {
            throw new IllegalArgumentException("Składnik nie może być null");
        }
        if (!składniki.contains(s)) {
            składniki.add(s);
            s.addDostawa(this);
        }
    }

    public void removeSkładnik(Składnik s) { // Asocjacja wiele–do–wielu między Dostawa a Składnik
        if (s == null) {
            return;
        }
        if (składniki.remove(s)) {
            s.removeDostawa(this);
        }
    }

    public List<Zamowienie> getZamówienia() {
        return Collections.unmodifiableList(zamówienia);
    }

    public void addZamowienie(Zamowienie z) { // Asocjacja jeden–do–wielu między Dostawa a Zamówienie
        if (z == null) {
            throw new IllegalArgumentException("Zamówienie nie może być null");
        }
        if (!zamówienia.contains(z)) {
            zamówienia.add(z);
            z.setDostawa(this);
        }
    }

    public void removeZamowienie(Zamowienie z) { // Asocjacja jeden–do–wielu między Dostawa a Zamówienie
        if (z == null) {
            return;
        }
        if (zamówienia.remove(z)) {
            z.setDostawa(null);
        }
    }

    @Override
    public void removeFromExtent() {
        for (Składnik s : new ArrayList<>(składniki)) {
            removeSkładnik(s);
        }
        for (Zamowienie z : new ArrayList<>(zamówienia)) {
            removeZamowienie(z);
        }
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Dostawa{" +
                "godzinaDostawy='" + godzinaDostawy + '\'' +
                ", składniki=" + składniki.size() +
                ", zamówienia=" + zamówienia.size() +
                '}';
    }
}