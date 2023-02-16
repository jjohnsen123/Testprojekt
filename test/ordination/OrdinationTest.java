package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class OrdinationTest {

    @Test
    void antalDage() {
        LocalDate startDen = LocalDate.of(2021,12,1);
        LocalDate slutDen = LocalDate.of(2021,12,10);
        Patient patient = new Patient("121256-0512", "Jane Jensen",63.4);
        Laegemiddel laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1,0.15, 0.16,"styk");
        Ordination ordination = new Ordination(startDen, slutDen, patient, laegemiddel) {
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
        };

        int faktisk = ordination.antalDage();

        //Assert
        int forventet = 10;
        assertEquals(forventet, faktisk);

    }
}