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
    public void usunPozycje(){
        dostawa.removeZamowienie(this);
        klient.removeZamowienie(this);
    }

    public void removeFromExtent(){
        if (klient != null && dostawa != null) {
            klient.removeZamowienie(this);
            dostawa.removeZamowienie(this);
            super.removeFromExtent();
        }
    }
    @Override
    public String toString() {
        return "Zamowienie{" +
                "dataZamowienia=" + dataZamowienia +
                ", klient="  +
                ", dostawa=" + dostawa +
                '}';
    }
}
