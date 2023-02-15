package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination{

    private double antalEnheder;
    private ArrayList<LocalDate> datoer;

    public PN(LocalDate startDen, LocalDate slutDen, ordination.Patient patient, ordination.Laegemiddel laegemiddel,
              double antalEnheder) { //Usikker om det her er rigtigt
        super(startDen, slutDen, patient, laegemiddel);
        this.antalEnheder = antalEnheder;
        datoer = new ArrayList<>();
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givesDen
     * Returnerer true hvis givesDen er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givesDen ignoreres
     * @param givesDen
     * @return
     */
    public boolean givDosis(LocalDate givesDen) {
        if ((givesDen.isAfter(getStartDen()) || givesDen.isEqual(getStartDen()))
                && (givesDen.isBefore(getSlutDen()) || givesDen.isEqual(getSlutDen()))) {
            datoer.add(givesDen);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returnere den daglige dosis
     * @return daglige dosis
     */
    public double doegnDosis() {
        double doegnDosis = getAntalEnheder() / super.antalDage();
        return doegnDosis;
    }

    /**
     * Returnere ordinationstypen som en String
     * @return ordinationstypen
     */
    @Override
    public String getType() {
        return "PN";
    }

    /**
     * Returnere den samlede dosis
     * @return samlede dosis
     */
    public double samletDosis() {
        double samletDosis = getAntalEnheder() * super.antalDage();
        return samletDosis;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return antal gange givet
     */
    public int getAntalGangeGivet() {
        int antalGangeGiver = datoer.size();
        return antalGangeGiver;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

}
