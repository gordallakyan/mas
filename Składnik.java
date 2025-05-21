import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Składnik extends ObjectPlus implements Serializable {
    private int ilość;
    private String krajPochodzenia;
    private String nazwa;
    private Produkt produkt; // Kompozycja między Produktem a Składnikiem
    private final List<Dostawa> dostawy = new ArrayList<>(); // Asocjacja wiele–do–wielu między Składnik a Dostawa

    public Składnik(int ilość, String krajPochodzenia, String nazwa) {
        setIlość(ilość);
        setKrajPochodzenia(krajPochodzenia);
        setNazwa(nazwa);
        addExtent();
    }

    public int getIlość() {
        return ilość;
    }

    public void setIlość(int ilość) {
        if (ilość < 0 || ilość > Produkt.getMaxLiczbaSkładników()) {
            throw new IllegalArgumentException("Ilość składników jest niepoprawna");
        }
        this.ilość = ilość;
    }

    public String getKrajPochodzenia() {
        return krajPochodzenia;
    }

    public void setKrajPochodzenia(String krajPochodzenia) {
        if (krajPochodzenia == null || krajPochodzenia.isBlank()) {
            throw new IllegalArgumentException("Niepoprawny kraj pochodzenia");
        }
        this.krajPochodzenia = krajPochodzenia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa == null || nazwa.isBlank()) {
            throw new IllegalArgumentException("Składnik musi mieć nazwę");
        }
        this.nazwa = nazwa;
    }

    public Produkt getProdukt() {
        return produkt;
    }


    public void usunProdukt() {
        if (produkt != null) {
            Produkt old = produkt;
            produkt = null;
            old.usunSkladnik(this);
        }
    }

    public List<Dostawa> getDostawy() {
        return Collections.unmodifiableList(dostawy);
    }

    /** Asocjacja wiele–do–wielu: dodaje powiązanie z Dostawą */
    public void addDostawa(Dostawa d) {
        if (d == null) {
            throw new IllegalArgumentException("Dostawa nie może być null");
        }
        if (!dostawy.contains(d)) {
            dostawy.add(d);
            d.addSkładnik(this);
        }
    }

    /** Asocjacja wiele–do–wielu: usuwa powiązanie z Dostawą */
    public void removeDostawa(Dostawa d) {
        if (d == null) {
            return;
        }
        if (dostawy.remove(d)) {
            d.removeSkładnik(this);
        }
    }

    @Override
    public void removeFromExtent() {
        if (produkt != null) {
            Produkt old = produkt;
            produkt = null;
            old.usunSkladnik(this);
        }
        for (Dostawa d : new ArrayList<>(dostawy)) {
            removeDostawa(d);
        }
        super.removeFromExtent();
    }

    @Override
    public String toString() {
        return "Składnik{" +
                "ilość=" + ilość +
                ", krajPochodzenia='" + krajPochodzenia + '\'' +
                ", nazwa='" + nazwa + '\'' +
                ", dostawy=" + dostawy.size() +
                ", produkt=" + (produkt != null ? produkt.getId() : "null") +
                '}';
    }
}
