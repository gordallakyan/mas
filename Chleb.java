package wieloaspektowe;

public class Chleb extends Wypiek {
    private String rodzajMaki;

    public Chleb(StanWypieku stanWypieku, String rodzajMaki) {
        super(stanWypieku);
        this.rodzajMaki = rodzajMaki;
    }

    @Override
    public void wyswietlOpis() {
        System.out.println("Chleb z mąki: " + rodzajMaki +
                ", aktualny stan: " + getStanWypieku().getClass().getSimpleName());
    }

    public String getRodzajMaki() {
        return rodzajMaki;
    }

    public void setRodzajMaki(String rodzajMaki) {
        this.rodzajMaki = rodzajMaki;
    }
}