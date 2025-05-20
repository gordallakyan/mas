package KlasaAbstrakcyjna;

public class Piekarz extends Osoba {
    public Piekarz(String imie, String nazwisko) {
        super(imie, nazwisko);
    }
    public Piekarz(Piekarz osoba) {
        super(osoba);
    }
    public String getOpis() {
        return "Piekarz: " + getImie() + " " + getNazwisko();
    }
}
