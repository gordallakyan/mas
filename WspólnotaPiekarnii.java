package ZwykłaAsocjacja_UNIQUE;

import util.ObjectPlus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WspólnotaPiekarnii extends ObjectPlus {
    private int maxLiczbaPiekarnii;
    private List<Piekarnia> piekarniaList = new ArrayList<>();

    public void addPiekarnia(Piekarnia piekarnia){
        if (!piekarniaList.contains(piekarnia)){
            piekarniaList.add(piekarnia);
            piekarnia.addWspólnotaPiekarnii(this);
            addExtent();
        }
    }

    public WspólnotaPiekarnii(int maxLiczbaPiekarnii, List<Piekarnia> piekarniaList) {
        this.maxLiczbaPiekarnii = maxLiczbaPiekarnii;
        this.piekarniaList = piekarniaList;
    }

    public void removePiekarnia(Piekarnia piekarnia){
        if (piekarniaList.remove(piekarnia)){
            piekarniaList.remove(this);
            piekarnia.removeWspólnotaPiekarnii(this);
            removeFromExtent();
        }
    }

    @Override
    public String toString() {
        return "ZwykłaAsocjacja.WspólnotaPiekarnii{" +
                "maxLiczbaPiekarnii=" + maxLiczbaPiekarnii +
                ", piekarniaList=" + piekarniaList.stream()
                .map(p -> p.getNazwa()).collect(Collectors.toList()) +
                '}';
    }
}
