package ordination;

import controller.Controller;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    @Test
    void TC1_givDosis() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 01, 12);

        //Act & Assert
        assertTrue(pnOrdination.givDosis(date));
    }

    @Test
    void TC2_givDosis_graensevaerdi() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 01, 13);

        //Act & Assert
        assertTrue(pnOrdination.givDosis(date));
    }

    @Test
    void TC3_givDosis() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 01, 29);

        //Act & Assert
        assertTrue(pnOrdination.givDosis(date));
    }

    @Test
    void TC4_givDosis_graensevaerdi() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 02, 15);


        //Act & Assert
        assertFalse(pnOrdination.givDosis(date));
    }

    @Test
    void TC5_givDosis() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 02, 14);

        //Act & Assert
        assertTrue(pnOrdination.givDosis(date));
    }

    @Test
    void TC6_givDosis_graensevaerdi() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 01, 11);

        //Act & Assert
        assertFalse(pnOrdination.givDosis(date));
    }

    @Test
    void TC7_givDosis_graensevaerdi() {
        //Arrange
        Controller testController = Controller.getTestController();

        LocalDate startDen = LocalDate.of(2021, 01, 12);
        LocalDate slutDen = LocalDate.of(2021, 02, 14);
        ordination.Patient patient = testController.opretPatient("123456-7890","Jens Jensen", 100);
        ordination.Laegemiddel laegemiddel = testController.opretLaegemiddel("Aspirin",
                1, 2, 3, "Styk");
        PN pnOrdination = testController.opretPNOrdination(startDen, slutDen, patient, laegemiddel, 10);

        LocalDate date = LocalDate.of(2021, 02, 13);

        //Act & Assert
        assertTrue(pnOrdination.givDosis(date));
    }

}