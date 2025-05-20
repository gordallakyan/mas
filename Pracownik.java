package overlapping_XOR;

import dynamic.Osoba;

import java.util.EnumSet;
import java.util.Objects;

public class Pracownik extends Osoba {
    public static final double KASJER_MAX_SALARY = 7_000.0;
    public static final int PIEKARZ_MAX_PRODUCTS_DAY = 500;
    public static final int KIEROWCA_MAX_HOURS_DAY = 8;

    private EnumSet<Rola> role = EnumSet.noneOf(Rola.class);

    private Integer KASJER;
    private Integer PIEKARZ;
    private Integer KIEROWCA;

    private double zarobki;
    private int iloscProduktowDziennie;
    private int godzinyJazdy;

    public Pracownik(String imie, String nazwisko, EnumSet<Rola> role) {
        super(imie, nazwisko);
        setRole(role);
    }

    public Pracownik(String imie, String nazwisko) {
        super(imie, nazwisko);
    }

    public EnumSet<Rola> getRole() {
        return EnumSet.copyOf(role);
    }

    public void setRole(EnumSet<Rola> role) { // XOR
        if (role.contains(Rola.KASJER) && role.contains(Rola.PIEKARZ)) {
            throw new IllegalArgumentException("Pracownik nie może być jednocześnie Kasjerem i Piekarzem.");
        }
        this.role = role;

    }

    public void addRole(Rola rola) {
        role.add(Objects.requireNonNull(rola, "Rola nie może być null"));
    }


    public void removeRole(Rola rola) {
        role.remove(Objects.requireNonNull(rola, "Rola nie może być null"));
    }

    public Integer getKASJER() {
        if (!role.contains(Rola.KASJER)) {
            throw new IllegalArgumentException("Pracownik nie posiada roli KASJER");
        }
        return KASJER;
    }

    public void setKASJER(Integer KASJER) {
        if (!role.contains(Rola.KASJER)) {
            throw new IllegalArgumentException("Pracownik nie posiada roli KASJER");
        }
        this.KASJER = KASJER;
    }

    public Integer getPIEKARZ() {
        if (!role.contains(Rola.PIEKARZ)) {
            throw new IllegalArgumentException("Pracownik nie posiada roli PIEKARZ");
        }
        return PIEKARZ;
    }

    public void setPIEKARZ(Integer PIEKARZ) {
        if (!role.contains(Rola.PIEKARZ)) {
            throw new IllegalArgumentException("Pracownik nie posiada roli PIEKARZ");
        }
        this.PIEKARZ = PIEKARZ;
    }

    public Integer getKIEROWCA() {
        if (!role.contains(Rola.KIEROWCA)) {
            throw new IllegalArgumentException("Pracownik nie posiada roli KIEROWCA");
        }
        return KIEROWCA;
    }

    public void setKIEROWCA(Integer KIEROWCA) {
        if (!role.contains(Rola.KIEROWCA)) {
            throw new IllegalArgumentException("Pracownik nie posiada roli KIEROWCA");
        }
        this.KIEROWCA = KIEROWCA;
    }

    public void setZarobki(double zarobki) {
        if (!role.contains(Rola.KASJER)) {
            throw new IllegalStateException("Pracownik nie posiada roli KASJER");
        }
        if (zarobki > KASJER_MAX_SALARY) {
            throw new IllegalArgumentException("Kasjer nie może zarabiać więcej niż " + KASJER_MAX_SALARY + "!");
        }
        this.zarobki = zarobki;
    }

    public void setIloscProduktowDziennie(int iloscProduktowDziennie) {
        if (!role.contains(Rola.PIEKARZ)) {
            throw new IllegalStateException("Pracownik nie posiada roli PIEKARZ");
        }
        if (iloscProduktowDziennie > PIEKARZ_MAX_PRODUCTS_DAY) {
            throw new IllegalArgumentException("Piekarz nie może wypiekać więcej niż " + PIEKARZ_MAX_PRODUCTS_DAY + " produktów dziennie!");
        }
        this.iloscProduktowDziennie = iloscProduktowDziennie;
    }

    public void setGodzinyJazdy(int godzinyJazdy) {
        if (!role.contains(Rola.KIEROWCA)) {
            throw new IllegalStateException("Pracownik nie posiada roli KIEROWCA");
        }
        if (godzinyJazdy > KIEROWCA_MAX_HOURS_DAY) {
            throw new IllegalArgumentException("Kierowca nie może jeździć dłużej niż " + KIEROWCA_MAX_HOURS_DAY + "h dziennie!");
        }
        this.godzinyJazdy = godzinyJazdy;
    }

    public double getZarobki() {
        if (!role.contains(Rola.KASJER)) {
            throw new IllegalStateException("Pracownik nie posiada roli KASJER");
        }
        return zarobki;
    }

    public int getIloscProduktowDziennie() {
        if (!role.contains(Rola.PIEKARZ)) {
            throw new IllegalStateException("Pracownik nie posiada roli PIEKARZ");
        }
        return iloscProduktowDziennie;
    }

    public int getGodzinyJazdy() {
        if (!role.contains(Rola.KIEROWCA)) {
            throw new IllegalStateException("Pracownik nie posiada roli KIEROWCA");
        }
        return godzinyJazdy;
    }

    @Override
    public String toString() {
        return "Pracownik{" + System.lineSeparator() +
                super.toString() + System.lineSeparator() +
                "role=" + role +
                ", KASJER=" + KASJER +
                ", PIEKARZ=" + PIEKARZ +
                ", KIEROWCA=" + KIEROWCA +
                ", zarobki=" + zarobki +
                ", iloscProduktowDziennie=" + iloscProduktowDziennie +
                ", godzinyJazdy=" + godzinyJazdy +
                '}';
    }
}