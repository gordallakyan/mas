package wieloaspektowe;

import util.ObjectPlus;

public abstract class StanWypieku extends ObjectPlus {
    private Wypiek wypiek;


    public StanWypieku(Wypiek wypiek) {
        this.wypiek = wypiek;
        wypiek.setStanWypieku(this);

    }
    public void setWypiek(Wypiek wypiek) {
        this.wypiek = wypiek;
    }

}