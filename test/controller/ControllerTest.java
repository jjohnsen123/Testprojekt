package controller;

import ordination.Laegemiddel;
import ordination.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
private Laegemiddel laegemiddel;
@BeforeEach
void setUp(){
    this.laegemiddel = new Laegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
}
    @Test
    void TC1_anbefaletDosisPrDoegn_0kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("130790-1800","Jørgen Jørgensen",0);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1,laegemiddel);

        //Assert
        double forventetResultat = 0;
        assertEquals(forventetResultat,faktiskResultat);
    }
    @Test
    void TC3_anbefaletDosisPrDoegn_15_5kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("120698-1853","John Le",15.5);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1, laegemiddel);

        //Assert
        double forventetResultat = 1.55;
        assertEquals(forventetResultat, faktiskResultat);

    }
    @Test
    void TC4_anbefaletDosisPrDoegn_24kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("150299-8772","Bo Boesen",24.0);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1, laegemiddel);

        //Assert
        double forventetResultat = 2.4;
        assertEquals(forventetResultat, faktiskResultat,0.001);

    }
    @Test
    void TC6_anbefaletDosisPrDoegn_25kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("111156-0613","Janus Janusen", 25);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1, laegemiddel);

        //Assert
        double forventetResultat = 3.75;
        assertEquals(forventetResultat, faktiskResultat,0.001);

    }
    @Test
    void TC5_anbefaletDosisPrDoegn_89_4kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("050972-1233", "Hans Jørgensen", 89.4);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1, laegemiddel);

        //Assert
        double forventetResultat = 13.41;
        assertEquals(forventetResultat, faktiskResultat,0.001);

    }
    //------------------------------------------------------------------------------------------------------------------
    @Test
    void TC2_anbefaletDosisPrDoegn_119kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("150100-2152","Lone Lonesen",119.0);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1,laegemiddel);

        //Assert
        double forventetResultat = 17.85;
        assertEquals(forventetResultat,faktiskResultat,0.001);
    }
    @Test
    void TC8_anbefaletDosisPrDoegn_120kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("110577-2674","Torben Torbensen",120);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1,laegemiddel);

        //Assert
        double forventetResultat = 18;
        assertEquals(forventetResultat,faktiskResultat,0.001);
    }
    @Test
    void TC9_anbefaletDosisPrDoegn_121kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("091268-8888","Søren Sørensen",121);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1,laegemiddel);

        //Assert
        double forventetResultat = 19.36;
        assertEquals(forventetResultat,faktiskResultat,0.001);
    }
    @Test
    void TC7_anbefaletDosisPrDoegn_160kg() {
        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("021160-9911", "Ole Olesen",160);

        //Act
        double faktiskResultat = controller.anbefaletDosisPrDoegn(patient1,laegemiddel);

        //Assert
        double forventetResultat = 25.6;
        assertEquals(forventetResultat,faktiskResultat,0.001);
    }
    //------------------------------------------------------------------------------------------------------------------
    @Test
    void NullPointerException_anbefaletDosisPrDoegn() {

        //Arrange
        Controller controller = Controller.getController();
        Patient patient1 = new Patient("021160-9911", "Ole Olesen",160);

        //Act & Assert

        Exception exception = assertThrows(NullPointerException.class, () -> {
            double faktiskResultat = controller.anbefaletDosisPrDoegn(null,laegemiddel);
        });
        assertEquals(exception.getMessage(), "Indtast oplysninger");
    }

}