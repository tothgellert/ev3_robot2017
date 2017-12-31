package hu.toth.gellert.ev3.teszt;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public class EgyebKodok {
/*
	private static void tesztMenet() {
		MovePilot pilot = letrehozPilot();

		pilot.travel(500, true); // mm
		varAmigMozog(pilot, "elore");

		pilot.rotate(180, true); // degree clockwise
		varAmigMozog(pilot, "fordul");

		pilot.travel(500, true); // move backward for 50 cm
		varAmigMozog(pilot, "vissza");

		LCD.drawString("vart", 0, 5);
	}

	private static void tesztMenet2() {
		int rotationsToGo = 10;
		motorBal.startSynchronization();
		motorBal.rotateTo(rotationsToGo * 360, true);
		motorJobb.rotateTo(rotationsToGo * 360, true);
		motorBal.endSynchronization();
	}

	private static void varAmigMegy() {
		while (motorBal.isMoving()) {
			Thread.yield();
		}
	}

	private static void lagyInditas(int elredendoSebesseg) {
		int sebessegLepeskoz = 40;
		int kesleltetesLepesekKozottMs = 200;

		Button.LEDPattern(3);
		boolean start = true;
		int sebesseg = sebessegLepeskoz;

		for (;;) {
			motorBal.startSynchronization();
			motorBal.setSpeed(sebesseg);
			motorJobb.setSpeed(sebesseg);
			if (start) {
				motorBal.forward();
				motorJobb.forward();
				start = false;
			}
			motorBal.endSynchronization();

			if (sebesseg >= elredendoSebesseg) {
				break;
			}
			Delay.msDelay(kesleltetesLepesekKozottMs);

			sebesseg += sebessegLepeskoz;
			if (sebesseg > elredendoSebesseg) {
				sebesseg = elredendoSebesseg;
			}
		}

		Button.LEDPattern(1);
	}
	*/
}
