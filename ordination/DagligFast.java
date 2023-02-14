package ordination;

import java.time.LocalTime;

public class DagligFast {

    private final Dosis[] dosis = new Dosis[4];
    private double morgenAntal;
    private double middagAntal;
    private double aftenAntal;
    private double natAntal;
    private LocalTime morgen = LocalTime.of(8,0,0);
    private LocalTime middag = LocalTime.of(14,0,0);
    private LocalTime aften = LocalTime.of(18,0,0);
    private LocalTime nat = LocalTime.of(22,0,0);


    public DagligFast(double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {
        dosis[0] = new Dosis(morgen, morgenAntal);
        dosis[1] = new Dosis(middag, middagAntal);
        dosis[2] = new Dosis(aften, aftenAntal);
        dosis[3] = new Dosis(nat, natAntal);
    }

    // TODO
}
