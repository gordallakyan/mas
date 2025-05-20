package Kompozycja_BAG_OgWłasne;

import java.io.Serializable;

import util.ObjectPlus;

public class Składnik extends ObjectPlus implements Serializable {
    private int ilość;
    private String krajPochodzenia;
    private String nazwa;
    Produkt produkt;



    public Składnik(int ilość, String krajPochodzenia, String nazwa) {
        setIlość(ilość);
        setKrajPochodzenia(krajPochodzenia);
        setNazwa(nazwa);

    }


    @Override
    public void removeFromExtent(){ //Kompozycja między Produktem a składnikiem
        if (produkt != null){
            Produkt p = produkt;
            produkt = null;
            p.usunSkladnik(this);
        }
        super.removeFromExtent();
    }

    public int getIlość() {
        return ilość;
    }

    public void setIlość(int ilość) {
        if (ilość > Produkt.getMaxLiczbaSkładników() || ilość < 0){
            throw new IllegalArgumentException("Ilość składników jest nie poprawna");
        }
        this.ilość = ilość;
    }

    public String getKrajPochodzenia() {
        return krajPochodzenia;
    }

    public void setKrajPochodzenia(String krajPochodzenia) {
        if (krajPochodzenia == null || krajPochodzenia.isBlank()){
            throw new IllegalArgumentException("Niepoprawny kraj pochodzenia");
        }
        this.krajPochodzenia = krajPochodzenia;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (nazwa == null || nazwa.isBlank()){
            throw new IllegalArgumentException("Kompozycja.Składnik musi mieć nazwę");
        }
        this.nazwa = nazwa;
    }

    void setProdukt(Produkt p) {
        this.produkt = p;
    }

}