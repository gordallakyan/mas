package wieloaspektowe;

public class Surowe extends StanWypieku {
    private boolean czyWyroslo;

    public Surowe(boolean czyWyroslo) {
        super(null);
    }

    public boolean isCzyWyroslo() {
        return czyWyroslo;
    }

    public void setCzyWyroslo(boolean czyWyroslo) {
        this.czyWyroslo = czyWyroslo;
    }
}