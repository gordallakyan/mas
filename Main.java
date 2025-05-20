

import Kompozycja_BAG_OgWłasne.Produkt;
import Kompozycja_BAG_OgWłasne.Składnik;

import java.util.Comparator;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Comparator<Produkt> byName = Comparator.comparing(Produkt::getNazwa); //ORDERED
        TreeSet<Produkt> set = new TreeSet<>(byName);

        Składnik s1 = new Składnik(1, "PL", "Mąka");
        Składnik s2 = new Składnik(1, "PL", "Woda");
        Składnik s3 = new Składnik(1, "PL", "Sól");

        Produkt p1 = new Produkt(1, "Chleb", s1);
        p1.dodajSkladnik(s1);
        p1.dodajSkladnik(s2);
        p1.dodajSkladnik(s3);

        Produkt p2 = new Produkt(2, "Bułka", s1);
        p2.dodajSkladnik(s1);
        p2.dodajSkladnik(s2);

        Produkt p3 = new Produkt(3, "Zawijka", s2);
        p3.dodajSkladnik(s2);
        p3.dodajSkladnik(s3);

        set.add(p1);
        set.add(p2);
        set.add(p3);


        for (Produkt p : set) {
            System.out.println(p.getNazwa() + " (id=" + p.getId() + ")");
        }
    }
}
