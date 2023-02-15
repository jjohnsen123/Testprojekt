package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private LocalTime tid;
    private double antal;
    private LocalTime[] klokkeSlet;
    private double[] antalEnheder;
    private final ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, ordination.Patient patient, ordination.Laegemiddel laegemiddel,
                       LocalTime[] klokkeSlet, double[] antalEnheder) {
        super(startDen, slutDen, patient, laegemiddel);
        for (LocalTime tid : klokkeSlet) {
            this.tid = tid;
            for (double antal : antalEnheder) {
                this.antal = antal;
                opretDosis(tid, antal);
            }
        }
    }

    public void opretDosis(LocalTime tid, double antal) {
        Dosis dosis = new Dosis(tid, antal);
        doser.add(dosis);
    }


    @Override
    public double samletDosis() {
        return antalDage() * doegnDosis();
    }

    @Override
    public double doegnDosis() {
        double sum = 0.0;
        for (Dosis dosis : doser){
            sum += dosis.getAntal();
        }
        return sum;
    }

    @Override
    public String getType() {
        return "Dagligt skæv";
    }

    public ArrayList<Dosis> getDoser() {
        return doser;
    }
}
