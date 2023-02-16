package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {

    @Test
    void doegnDosis_TC1() {
        Controller controller = Controller.getTestController();
        LocalDate startDen = LocalDate.of(2023,2,1);
        LocalDate slutDen = LocalDate.of(2023,2,10);
        ordination.Patient patient = controller.opretPatient("12345678","Hans",70);
        ordination.Laegemiddel laegemiddel = controller.opretLaegemiddel("Acetylsalicylsyre",
                0.1,0.15, 0.16,"styk");

        LocalTime[] klokkeSlet = new LocalTime[6];
        klokkeSlet[0] = LocalTime.of(9,30);
        klokkeSlet[1] = LocalTime.of(10,30);
        klokkeSlet[2] = LocalTime.of(13,30);
        klokkeSlet[3] = LocalTime.of(14,30);
        klokkeSlet[4] = LocalTime.of(19,30);
        klokkeSlet[5] = LocalTime.of(20,30);

        double[] antalEnheder = new double[6];
        antalEnheder[0] = 2;
        antalEnheder[1] = 1;
        antalEnheder[2] = 2;
        antalEnheder[3] = 1;
        antalEnheder[4] = 2;
        antalEnheder[5] = 1;

        DagligSkaev dagligSkaev = new DagligSkaev(startDen,slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);

        double faktisk = dagligSkaev.doegnDosis();

        double forventet = 9;
        assertEquals(forventet,faktisk);
    }

    @Test
    void doegnDosis_TC2() {
        Controller controller = Controller.getTestController();
        LocalDate startDen = LocalDate.of(2023,2,1);
        LocalDate slutDen = LocalDate.of(2023,2,10);
        ordination.Patient patient = controller.opretPatient("12345678","Hans",70);
        ordination.Laegemiddel laegemiddel = controller.opretLaegemiddel("Acetylsalicylsyre", 0.1,0.15, 0.16,"styk");

        LocalTime[] klokkeSlet = new LocalTime[6];
        klokkeSlet[0] = LocalTime.of(9,30);
        klokkeSlet[1] = LocalTime.of(10,30);
        klokkeSlet[2] = LocalTime.of(13,30);
        klokkeSlet[3] = LocalTime.of(14,30);
        klokkeSlet[4] = LocalTime.of(19,30);
        klokkeSlet[5] = LocalTime.of(20,30);

        double[] antalEnheder = new double[6];
        antalEnheder[0] = 2;
        antalEnheder[1] = 1;
        antalEnheder[2] = 1;
        antalEnheder[3] = 1;
        antalEnheder[4] = 1;
        antalEnheder[5] = -2;

        DagligSkaev dagligSkaev = new DagligSkaev(startDen,slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);

        double faktisk = dagligSkaev.doegnDosis();

        Error forventet = new Error();
        assertEquals(forventet,faktisk);
    }
    @Test
    void doegnDosis_TC3() {
        Controller controller = Controller.getTestController();
        LocalDate startDen = LocalDate.of(2023,2,1);
        LocalDate slutDen = LocalDate.of(2023,2,10);
        ordination.Patient patient = controller.opretPatient("12345678","Hans",70);
        ordination.Laegemiddel laegemiddel = controller.opretLaegemiddel("Acetylsalicylsyre", 0.1,0.15, 0.16,"styk");

        LocalTime[] klokkeSlet = new LocalTime[6];
        klokkeSlet[0] = LocalTime.of(9,30);
        klokkeSlet[1] = LocalTime.of(10,30);
        klokkeSlet[2] = LocalTime.of(13,30);
        klokkeSlet[3] = LocalTime.of(14,30);
        klokkeSlet[4] = LocalTime.of(19,30);
        klokkeSlet[5] = LocalTime.of(20,30);

        double[] antalEnheder = new double[6];
        antalEnheder[0] = 0;
        antalEnheder[1] = 0;
        antalEnheder[2] = 0;
        antalEnheder[3] = 0;
        antalEnheder[4] = 0;
        antalEnheder[5] = 0;

        DagligSkaev dagligSkaev = new DagligSkaev(startDen,slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);

        double faktisk = dagligSkaev.doegnDosis();

        double forventet = 0;
        assertEquals(forventet,faktisk);
    }
}
