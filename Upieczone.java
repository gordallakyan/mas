package wieloaspektowe;

public class Upieczone extends StanWypieku {
    private int temperaturaWewnetrzna;

    public Upieczone(int temperaturaWewnetrzna) {
        super(null);
        this.temperaturaWewnetrzna = temperaturaWewnetrzna;

    }

    public int getTemperaturaWewnetrzna() {
        return temperaturaWewnetrzna;
    }

    public void setTemperaturaWewnetrzna(int temperaturaWewnetrzna) {
        this.temperaturaWewnetrzna = temperaturaWewnetrzna;
    }
}