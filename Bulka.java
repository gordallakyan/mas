package wieloaspektowe;

public class Bulka extends Wypiek {
    private double waga;

    public Bulka(StanWypieku stanWypieku, double waga) {
        super(stanWypieku);
        this.waga = waga;
    }

    @Override
    public void wyswietlOpis() {
        System.out.println("Bułka o wadze: " + waga + " g, stan: " +
                getStanWypieku().getClass().getSimpleName());
    }

    public double getWaga() {
        return waga;
    }

    public void setWaga(double waga) {
        this.waga = waga;
    }
}