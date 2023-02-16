package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DagligFastTest {

    @Test
    void doegnDosis_TC1() {
        LocalDate startDen = LocalDate.of(2021,12,1);
        LocalDate slutDen = LocalDate.of(2021,12,9);
        Patient patient = new Patient("121256-0512", "Jane Jensen",63.4);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1,0.15, 0.16,"styk");
        DagligFast dagligFast = new DagligFast(startDen, slutDen, patient, laegemiddel, 1, 0, 0 ,3);

        double faktisk = dagligFast.doegnDosis();

        //Assert
        double forventet = 4;
        assertEquals(forventet, faktisk);
    }

    @Test
    void doegnDosis_TC2() {
        LocalDate startDen = LocalDate.of(2021,12,1);
        LocalDate slutDen = LocalDate.of(2021,12,9);
        Patient patient = new Patient("121256-0512", "Jane Jensen",63.4);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1,0.15, 0.16,"styk");
        DagligFast dagligFast = new DagligFast(startDen, slutDen, patient, laegemiddel, 0, 0, 0 ,0);

        double faktisk = dagligFast.doegnDosis();

        //Assert
        double forventet = 0;
        assertEquals(forventet, faktisk);
    }

    @Test
    void doegnDosis_TC3() {
        LocalDate startDen = LocalDate.of(2021,12,1);
        LocalDate slutDen = LocalDate.of(2021,12,9);
        Patient patient = new Patient("121256-0512", "Jane Jensen",63.4);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1,0.15, 0.16,"styk");
        DagligFast dagligFast = new DagligFast(startDen, slutDen, patient, laegemiddel, 1, 2, 0 ,-1);

        double faktisk = dagligFast.doegnDosis();

        //Assert
        Error forventet = new Error();
        assertEquals(forventet, faktisk);
    }
}