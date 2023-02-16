package ordination;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class DagligSkaev extends Ordination {

    private LocalTime[] klokkeSlet;
    private double[] antalEnheder;
    private final ArrayList<Dosis> doser = new ArrayList<>();

    public DagligSkaev(LocalDate startDen, LocalDate slutDen, ordination.Patient patient, ordination.Laegemiddel laegemiddel,
                       LocalTime[] klokkeSlet, double[] antalEnheder) {
        super(startDen, slutDen, patient, laegemiddel);
        this.klokkeSlet = klokkeSlet;
        this.antalEnheder = antalEnheder;
        for (int i = 0; i < antalEnheder.length; i++) {
            LocalTime tid = klokkeSlet[i];
            double antal = antalEnheder[i];
            opretDosis(tid, antal);
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
        for (Double antal : antalEnheder){
            sum += antal;
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
