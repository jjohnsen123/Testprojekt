package controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.IdentityHashMap;
import java.util.List;

import ordination.*;
import storage.Storage;

public class Controller {
	private Storage storage;
	private static Controller controller;

	private Controller() {
		storage = new Storage();
	}

	public static Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	public static Controller getTestController() {
		return new Controller();
	}

	/**
	 * Hvis startDato er efter slutDato kastes en IllegalArgumentException og
	 * ordinationen oprettes ikke
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 * Pre: antal >= 0
	 * @param startDen start dato
	 * @param slutDen slut dato
	 * @param patient patienten
	 * @param laegemiddel laegemidlet
	 * @param antal antal
	 * @return PN ordination.
	 */
	public PN opretPNOrdination(LocalDate startDen, LocalDate slutDen,
								Patient patient, Laegemiddel laegemiddel, double antal) {
		if (startDen == null || slutDen == null || patient == null || laegemiddel == null || antal < 0) {
			throw new NullPointerException("Indtast oplysninger");
		} if (slutDen.isBefore(startDen)) {
			throw new IllegalArgumentException("Indtast korrekt dato");
		}
		PN pn = new PN(startDen, slutDen, patient, laegemiddel, antal);
		patient.addOrdination(pn);
		return pn;
	}

	/**
	 * Opretter og returnerer en DagligFast ordination. Hvis startDato er efter
	 * slutDato kastes en IllegalArgumentException og ordinationen oprettes ikke
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 * Pre: margenAntal, middagAntal, aftanAntal, natAntal >= 0
	 * @param startDen start dato
	 * @param slutDen slut dato
	 * @param patient patienten
	 * @param laegemiddel laegemidlet
	 * @param morgenAntal antal morgen
	 * @param middagAntal antal middag
	 * @param aftenAntal antal aften
	 * @param natAntal antal nat
	 * @return dagligfast ordination
	 */
	public DagligFast opretDagligFastOrdination(LocalDate startDen, LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,
												double morgenAntal, double middagAntal, double aftenAntal,
												double natAntal) {
		if(startDen == null || slutDen == null || patient == null || laegemiddel == null ||
				morgenAntal < 0 || middagAntal < 0 || aftenAntal < 0 || natAntal < 0) {
			throw new NullPointerException("Indtast oplysninger");
		} if(!checkStartFoerSlut(startDen, slutDen)) {
			throw new IllegalArgumentException("Indtast korrekt dato");
		}
		DagligFast dagligFast = new DagligFast(startDen, slutDen, patient, laegemiddel,
				morgenAntal, middagAntal, aftenAntal, natAntal);
		patient.addOrdination(dagligFast);
		return dagligFast;
	}

	/**
	 * Opretter og returnerer en DagligSkæv ordination. Hvis startDato er efter
	 * slutDato kastes en IllegalArgumentException og ordinationen oprettes ikke.
	 * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige kastes også en IllegalArgumentException.
	 *
	 * Pre: startDen, slutDen, patient og laegemiddel er ikke null
	 * Pre: alle tal i antalEnheder > 0
	 * @param startDen start dato
	 * @param slutDen slut dato
	 * @param patient patienten
	 * @param laegemiddel laegemidlet
	 * @param klokkeSlet klokkesletter
	 * @param antalEnheder antal enheder
	 * @return daglig skaev ordination
	 */
	public DagligSkaev opretDagligSkaevOrdination(LocalDate startDen,
												  LocalDate slutDen, Patient patient, Laegemiddel laegemiddel,
												  LocalTime[] klokkeSlet, double[] antalEnheder) {
		if (startDen == null || slutDen == null || patient == null || laegemiddel == null){
			throw new NullPointerException("Indtast oplysninger");
		} if (slutDen.isBefore(startDen)) {
			throw new IllegalArgumentException("Indtast korrekt dato");
		} if ((klokkeSlet.length != antalEnheder.length)) {
			throw new IllegalArgumentException("Indtast korrekt data. " +
					"Antal af klokkeslet skal stemme overens med antal enheder");
		}
		DagligSkaev dagligSkaev = new DagligSkaev(startDen, slutDen, patient, laegemiddel, klokkeSlet, antalEnheder);
		patient.addOrdination(dagligSkaev);
		return dagligSkaev;
	}

	/**
	 * En dato for hvornår ordinationen anvendes tilføjes ordinationen. Hvis
	 * datoen ikke er indenfor ordinationens gyldighedsperiode kastes en
	 * IllegalArgumentException
	 * Pre: ordination og dato er ikke null
	 * @param ordination ordinationen
	 * @param dato datoen for anveldelsen af ordinationen
	 */
	public void ordinationPNAnvendt(PN ordination, LocalDate dato) {
		if (ordination == null || dato == null) {
			throw new NullPointerException("Indtast oplysninger");
		}
		if (dato.isBefore(ordination.getStartDen()) || dato.isAfter(ordination.getSlutDen())) {
			throw new IllegalArgumentException("Indtast dato som er indenfor ordinationens gyldighedsperiode");
		}
		ordination.givDosis(dato);
	}

	/**
	 * Den anbefalede dosis for den pågældende patient (der skal tages hensyn
	 * til patientens vægt). Det er en forskellig enheds faktor der skal
	 * anvendes, og den er afhængig af patientens vægt.
	 * Pre: patient og lægemiddel er ikke null
	 * @param patient patienten
	 * @param laegemiddel laegemidlet
	 * @return anbefalede dosis for patienten
	 */
	public double anbefaletDosisPrDoegn(Patient patient, Laegemiddel laegemiddel) {
		if (patient == null || laegemiddel == null) {
			throw new NullPointerException("Indtast oplysninger");
		}
		if (patient.getVaegt() < 25) {
			return laegemiddel.getEnhedPrKgPrDoegnLet() * patient.getVaegt();
		} else if (patient.getVaegt() <= 120) {
			return laegemiddel.getEnhedPrKgPrDoegnNormal() * patient.getVaegt();
		} else if (patient.getVaegt() > 120) {
			return laegemiddel.getEnhedPrKgPrDoegnTung() * patient.getVaegt();
		}
		return -1;
	}

	/**
	 * For et givent vægtinterval og et givent lægemiddel, hentes antallet af
	 * ordinationer.
	 * @param vægtStart start vaegt
	 * @param vægtSlut slut vaegt
	 * @param laegemiddel laegemidlet
	 * @return ordinationer pr vaegt pr laegemiddel
	 */
	public int antalOrdinationerPrVægtPrLægemiddel(double vægtStart,
												   double vægtSlut, Laegemiddel laegemiddel) {
		int antalOrdinationer = 0;
		for (Patient p : getAllPatienter()) {
			if (p.getVaegt() >= vægtStart && p.getVaegt() <= vægtSlut) {
				for (Ordination ordination : p.getOrdinationer()) {
					if (ordination.getLaegemiddel().equals(laegemiddel)) {
						antalOrdinationer++;
					}
				}
			}
		}
		return antalOrdinationer;
	}

	public List<Patient> getAllPatienter() {
		return storage.getAllPatienter();
	}

	public List<Laegemiddel> getAllLaegemidler() {
		return storage.getAllLaegemidler();
	}

	/**
	 * Metode der kan bruges til at checke at en startDato ligger før en
	 * slutDato.
	 *
	 * @return true hvis startDato er før slutDato, false ellers.
	 */
	private boolean checkStartFoerSlut(LocalDate startDato, LocalDate slutDato) {
		boolean result = true;
		if (slutDato.compareTo(startDato) < 0) {
			result = false;
		}
		return result;
	}

	public Patient opretPatient(String cpr, String navn, double vaegt) {
		Patient p = new Patient(cpr, navn, vaegt);
		storage.addPatient(p);
		return p;
	}

	public Laegemiddel opretLaegemiddel(String navn,
										double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal,
										double enhedPrKgPrDoegnTung, String enhed) {
		Laegemiddel lm = new Laegemiddel(navn, enhedPrKgPrDoegnLet,
				enhedPrKgPrDoegnNormal, enhedPrKgPrDoegnTung, enhed);
		storage.addLaegemiddel(lm);
		return lm;
	}

	public void createSomeObjects() {
		this.opretPatient("121256-0512", "Jane Jensen", 63.4);
		this.opretPatient("070985-1153", "Finn Madsen", 83.2);
		this.opretPatient("050972-1233", "Hans Jørgensen", 89.4);
		this.opretPatient("011064-1522", "Ulla Nielsen", 59.9);
		this.opretPatient("090149-2529", "Ib Hansen", 87.7);
		this.opretPatient("130790-1800","Jørgen Jørgensen",0);
		this.opretPatient("150299-8772","Bo Boesen",24.0);
		this.opretPatient("111156-0613","Janus Janusen", 25);
		this.opretPatient("091268-8888","Søren Sørensen",121);
		this.opretPatient("021160-9911", "Ole Olesen",160);
		this.opretPatient("120698-1853","John Le",15.5);
		this.opretPatient("110577-2674","Torben Torbensen",120);
		this.opretPatient("150100-2152","Lone Lonesen",119.0);

		this.opretLaegemiddel("Acetylsalicylsyre", 0.1, 0.15, 0.16, "Styk");
		this.opretLaegemiddel("Paracetamol", 1, 1.5, 2, "Ml");
		this.opretLaegemiddel("Fucidin", 0.025, 0.025, 0.025, "Styk");
		this.opretLaegemiddel("Methotrexat", 0.01, 0.015, 0.02, "Styk");

		this.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(1),
				123);

		this.opretPNOrdination(LocalDate.of(2021, 2, 12), LocalDate.of(2021, 2, 14),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(0),
				3);

		this.opretPNOrdination(LocalDate.of(2021, 1, 20), LocalDate.of(2021, 1, 25),
				storage.getAllPatienter().get(3), storage.getAllLaegemidler()
						.get(2),
				5);

		this.opretPNOrdination(LocalDate.of(2021, 1, 1), LocalDate.of(2021, 1, 12),
				storage.getAllPatienter().get(0), storage.getAllLaegemidler()
						.get(1),
				123);

		this.opretDagligFastOrdination(LocalDate.of(2021, 1, 10),
				LocalDate.of(2021, 1, 12), storage.getAllPatienter().get(1),
				storage.getAllLaegemidler().get(1), 2, 0, 1, 0);

		LocalTime[] kl = { LocalTime.of(12, 0), LocalTime.of(12, 40),
				LocalTime.of(16, 0), LocalTime.of(18, 45) };
		double[] an = { 0.5, 1, 2.5, 3 };

		this.opretDagligSkaevOrdination(LocalDate.of(2021, 1, 23),
				LocalDate.of(2021, 1, 24), storage.getAllPatienter().get(1),
				storage.getAllLaegemidler().get(2), kl, an);
	}

}
