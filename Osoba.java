public abstract class Osoba extends ObjectPlus {
    private String imie;
    private String nazwisko;

    public Osoba(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    public Osoba(Osoba osoba){
        this.imie = osoba.imie;
        this.nazwisko = osoba.nazwisko;
    }

}
