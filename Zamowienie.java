package zAtrybutem_Ograniczenie;

import util.ObjectPlus;

import java.time.LocalDate;

public class Zamowienie extends ObjectPlus {
    private LocalDate dataZamowienia;
    private Klient klient;
    private Dostawa dostawa;
    public Zamowienie(Klient klient, Dostawa dostawa) {
        setKlient(klient);
        setDostawa(dostawa);
        klient.addZamowienie(this);
        dostawa.addZamowienie(this);
        addExtent();
    }

    public LocalDate getDataZamowienia() {
        return dataZamowienia;
    }

    public void setDataZamowienia(LocalDate dataZamowienia) {
        this.dataZamowienia = dataZamowienia;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public Dostawa getDostawa() {
        return dostawa;
    }

    public void setDostawa(Dostawa dostawa) {
        this.dostawa = dostawa;
    }

    public void removeFromExtent(){
        if (klient != null && dostawa != null) {
            klient.removeZamowienie(this);
            dostawa.removeZamowienie(this);
            removeFromExtent();
        }
    }
}
