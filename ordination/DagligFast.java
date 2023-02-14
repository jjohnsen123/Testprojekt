package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {

    private final Dosis[] dosis = new Dosis[4];
    private double morgenAntal;
    private double middagAntal;
    private double aftenAntal;
    private double natAntal;
    private LocalTime morgen = LocalTime.of(8,0,0);
    private LocalTime middag = LocalTime.of(14,0,0);
    private LocalTime aften = LocalTime.of(18,0,0);
    private LocalTime nat = LocalTime.of(22,0,0);

    public DagligFast(LocalDate startDen, LocalDate slutDen, ordination.Patient patient,
                      double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {
        super(startDen, slutDen, patient);
        if (morgenAntal > 0) {
            dosis[0] = new Dosis(morgen, morgenAntal);
        }
        if (middagAntal > 0) {
            dosis[1] = new Dosis(middag, middagAntal);
        }
         if (aftenAntal > 0) {
             dosis[2] = new Dosis(aften, aftenAntal);
         }
         if (natAntal > 0) {
             dosis[3] = new Dosis(nat, natAntal);
         }
    }


    @Override
    public double samletDosis() {
        return 0;
    }

    @Override
    public double doegnDosis() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }

    // TODO
}
