package ordination;

import java.time.LocalDate;
import java.time.LocalTime;

public class DagligFast extends Ordination {

    private final Dosis[] doser = new Dosis[4];
    private double morgenAntal;
    private double middagAntal;
    private double aftenAntal;
    private double natAntal;
    private LocalTime morgen = LocalTime.of(8,0,0);
    private LocalTime middag = LocalTime.of(14,0,0);
    private LocalTime aften = LocalTime.of(18,0,0);
    private LocalTime nat = LocalTime.of(22,0,0);

    /**
     * Initaliserer en ny ordination af typen Daglig Fast
     * @param startDen start dato
     * @param slutDen slut dato
     * @param patient patienten
     * @param morgenAntal antal doser om morgenen
     * @param middagAntal antal doser om middagen
     * @param aftenAntal antal doser om aftenen
     * @param natAntal antal doser om natten
     */
    public DagligFast(LocalDate startDen, LocalDate slutDen, ordination.Patient patient, ordination.Laegemiddel laegemiddel,
                      double morgenAntal, double middagAntal, double aftenAntal, double natAntal) {
        super(startDen, slutDen, patient, laegemiddel);

        if (morgenAntal > 0) {
            doser[0] = new Dosis(morgen, morgenAntal);
            this.morgenAntal = morgenAntal;
        }
        if (middagAntal > 0) {
            doser[1] = new Dosis(middag, middagAntal);
            this.middagAntal = middagAntal;
        }
         if (aftenAntal > 0) {
             doser[2] = new Dosis(aften, aftenAntal);
             this.aftenAntal = aftenAntal;
         }
         if (natAntal > 0) {
             doser[3] = new Dosis(nat, natAntal);
             this.natAntal = natAntal;
         }
    }


    /**
     * Returnere den samlede dosis
     * @return samlede dosis
     */
    @Override
    public double samletDosis() {
        int antalDage = antalDage();
        double samletDosis = antalDage * doegnDosis();
        return samletDosis;
    }

    /**
     * Returnere den daglige dosis
     * @return daglige dosis
     */
    @Override
    public double doegnDosis() {
        double doegnDosis = morgenAntal + middagAntal + aftenAntal + natAntal;
        return doegnDosis;
    }

    /**
     * Returnere ordinationstypen som en String
     * @return ordinationstypen
     */
    @Override
    public String getType() {
        return "Daglig Fast";
    }

    public Dosis[] getDoser() {
        return doser;
    }
}
