import java.util.*;


public class Pracownik extends Osoba {

    public sealed interface DaneRoli permits Kasjer, Piekarz, Kierowca,
            Dostawca, Inspektor { }

    public static final class Kasjer implements DaneRoli {
        public static final double MAX_SALARY = 7_000.0;
        private double wynagrodzenie;
        public Kasjer(double wynagrodzenie){
            if(wynagrodzenie > MAX_SALARY)
                throw new IllegalArgumentException("Kasjer > MAX");
            this.wynagrodzenie = wynagrodzenie;
        }
        public double getWynagrodzenie(){ return wynagrodzenie; }
    }

    public static final class Piekarz implements DaneRoli {
        public static final int MAX_DZIENNIE = 500;
        private int sztukNaDzien;
        public Piekarz(int sztuk){
            if(sztuk > MAX_DZIENNIE)
                throw new IllegalArgumentException("Piekarz > MAX");
            this.sztukNaDzien = sztuk;
        }
        public int getSztukNaDzien(){ return sztukNaDzien; }
    }

    public static final class Kierowca implements DaneRoli {
        public static final int MAX_H_DZIENNIE = 8;
        private int hNaDzien;
        public Kierowca(int h){
            if(h > MAX_H_DZIENNIE)
                throw new IllegalArgumentException("Kierowca > MAX");
            this.hNaDzien = h;
        }
        public int getHNaDzien(){ return hNaDzien; }
    }

    public static final class Dostawca implements DaneRoli {
        private int liczbaDostaw;
        public Dostawca(int n){ this.liczbaDostaw = n; }
        public int getLiczbaDostaw(){ return liczbaDostaw; }
    }

    public static final class Inspektor implements DaneRoli {
        private int liczbaKontroli;
        public Inspektor(int n){ this.liczbaKontroli = n; }
        public int getLiczbaKontroli(){ return liczbaKontroli; }
    }

    /* ========= właściwe pola klasy Pracownik ========= */

    private EnumSet<RolaPracownika> role = EnumSet.noneOf(RolaPracownika.class);
    private EnumMap<RolaPracownika, DaneRoli> daneRoli = new EnumMap<>(RolaPracownika.class);

    public Pracownik(String imie, String nazwisko){ super(imie, nazwisko); }

    /* ========= metody add/remove roli ========= */

    public void dodajRole(RolaPracownika r, DaneRoli dane){
        Objects.requireNonNull(r); Objects.requireNonNull(dane);

        /* XOR: Kasjer ⊻ Piekarz */
        if( (r == RolaPracownika.KASJER  && role.contains(RolaPracownika.PIEKARZ)) ||
                (r == RolaPracownika.PIEKARZ && role.contains(RolaPracownika.KASJER)) )
            throw new IllegalArgumentException("Kasjer i Piekarz są wzajemnie wykluczające!");

        role.add(r);
        daneRoli.put(r, dane);
    }

    public void usunRole(RolaPracownika r){
        role.remove(r);
        daneRoli.remove(r);
    }

    public EnumSet<RolaPracownika> getRole(){ return EnumSet.copyOf(role); }

    /* ========= metody pomocnicze do pobierania danych konkretnej roli ========= */

    public Optional<Kasjer>    kasjer()    { return Optional.ofNullable((Kasjer)    daneRoli.get(RolaPracownika.KASJER)); }
    public Optional<Piekarz>   piekarz()   { return Optional.ofNullable((Piekarz)   daneRoli.get(RolaPracownika.PIEKARZ)); }
    public Optional<Kierowca>  kierowca()  { return Optional.ofNullable((Kierowca)  daneRoli.get(RolaPracownika.KIEROWCA)); }
    public Optional<Dostawca>  dostawca()  { return Optional.ofNullable((Dostawca)  daneRoli.get(RolaPracownika.DOSTAWCA)); }
    public Optional<Inspektor> inspektor() { return Optional.ofNullable((Inspektor) daneRoli.get(RolaPracownika.INSPEKTOR_JAKOSCI)); }

    /* ========= przykładowe gettery wykorzystujące Optional ========= */

    public double  getZarobkiKasjera()          { return kasjer().orElseThrow().getWynagrodzenie(); }
    public int     getSztukPiekarzDziennie()    { return piekarz().orElseThrow().getSztukNaDzien(); }
    public int     getGodzinKierowcyDziennie()  { return kierowca().orElseThrow().getHNaDzien(); }
    public int     getLiczbaDostaw()            { return dostawca().orElseThrow().getLiczbaDostaw(); }
    public int     getLiczbaKontroli()          { return inspektor().orElseThrow().getLiczbaKontroli(); }

    @Override public String toString(){
        return "Pracownik{\" + super.toString() + \", role=\" + role + \"}" +
                ", daneRoli=" + daneRoli + '}';
    }
}
