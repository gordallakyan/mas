package Wielodziedziczenie;


public class Dostawca extends Osoba implements IDostawca {
    private int liczbaDostaw;

    public Dostawca(String imie, String nazwisko, int liczbaDostaw) {
        super(imie, nazwisko);
        this.liczbaDostaw = liczbaDostaw;
    }

    @Override
    public int getLiczbaDostaw() {
        return liczbaDostaw;
    }

    @Override
    public String getOpis() {
        return "Dostawca " + getImie() + " " + getNazwisko() +
                ", dostaw: " + liczbaDostaw;
    }
}