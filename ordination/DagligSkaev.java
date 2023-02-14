package ordination;

import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev{
    private LocalTime tid;
    private double antal;
    private final ArrayList<Dosis> doser = new ArrayList<>();

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }



}
