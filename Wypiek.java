package wieloaspektowe;

import util.ObjectPlus;

public abstract class Wypiek extends ObjectPlus {
    private StanWypieku stanWypieku;

    public Wypiek(StanWypieku stanWypieku) {
        this.stanWypieku = stanWypieku;
        stanWypieku.setWypiek(this);
    }

    public StanWypieku getStanWypieku() {
        return stanWypieku;
    }


    public void setStanWypieku(StanWypieku nowyStan) {
        this.stanWypieku = nowyStan;
        nowyStan.setWypiek(this);
    }

    public abstract void wyswietlOpis();
}