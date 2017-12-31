package hu.toth.gellert.ev3.teszt;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

@SuppressWarnings("unused")
public class Foprogram {
	private static final int alapSebbeseg = 120;
	private static RegulatedMotor motorBal;
	private static RegulatedMotor motorJobb;
	private static Chassis robot;
	private static RegulatedMotor motorElsoKar;
	private static RegulatedMotor motorHatsoKar;
	private static MovePilot pilot;

	public static void main(String[] args) {
		LCD.drawString("Robot 2017", 0, 3);
		Button.LEDPattern(3);

		inicializalas();
		while (etapValasztasEsFuttatas()) {

		}
		stop();
	}

	private static boolean etapValasztasEsFuttatas() {
		EtapValaszto etapValaszto = new EtapValaszto();
		etapValaszto.valaszt();
		if (etapValaszto.isKilepes()) {
			return false;
		}
		new KilepesGombThread().start();

		try {
			switch (etapValaszto.getValasztottEtap()) {
			case 1:
				etap1();
				break;
			case 2:
				// etap2();
				break;
			case 3:
				// etap3();
				break;
			default:
				break;
			}
		} catch (Interrupted e) {
		}
		return true;
	}

	private static void etap1() {
		egyenesbeAll();
		GurigaEsBehuz.run();
		Felho.run();
		Kontener.run();
	}

	private static void egyenesbeAll() {
		pilot.travel(-20);
	}

	private static class Kontener {
		private static void run() {
			eloremegy();
			befordul();
			tolatEsKitol();
			betol();
			hazamegy();
		}

		private static void hazamegy() {
			arcBalraHatra(350, 90, true);
			var(500);
			motorElsoKar.rotate(fordulat(-1.2), true);
			motorHatsoKar.rotate(fordulat(-0.59), true);
			varAmigMozog(pilot, "E.T. haza!!");
		}

		private static void eloremegy() {
			pilot.travel(235);
		}

		private static void befordul() {
			pilot.rotate(118);
		}

		private static void tolatEsKitol() {
			pilot.travel(-285);
			motorElsoKar.rotate(fordulat(1.2));
		}

		private static void betol() {
			pilot.travel(130);

		}
	}

	private static class GurigaEsBehuz {
		private static void run() {
			elindul();
			karokKi();
			varAmigMozog(pilot, "elore");
			lassanBetol();

			vezeteketBehuz();
			pilot.setLinearSpeed(90);
			motorJobb.rotate(fordulat(0.3));
			falnakNyom();
			motorJobb.rotate(fordulat(-0.05));
			pilot.setLinearSpeed(alapSebbeseg);
			varMigLeesikAViz();
		}

		private static void lassanBetol() {
			pilot.setLinearSpeed(80);
			pilot.travel(160);
		}

		private static void varMigLeesikAViz() {
			var(500, "leesik");
		}

		private static void vezeteketBehuz() {
			motorElsoKar.rotate(fordulat(-1.2));
		}

		private static void falnakNyom() {
			pilot.setLinearSpeed(350);
			pilot.travel(80);
			pilot.setLinearSpeed(50);
		}

		private static void karokKi() {
			motorElsoKar.rotate(fordulat(1.6), true);
			var(3000);
			motorHatsoKar.rotate(fordulat(0.59), true);
		}

		private static void elindul() {
			pilot.travel(725, true);
		}
	}

	private static class Felho {
		private static void run() {
			hatraIndul();
			kartBehuz();
			varAmigMozog(pilot, "hatra");
			pilot.setLinearSpeed(80);
			beforog();
			pilot.setLinearSpeed(160);
			hatramegy();
			esotKienged();
		}

		private static void esotKienged() {
			double billentesEsohoz = -0.15;
			int fordalatEsohoz = 14;

			motorHatsoKar.rotate(fordulat(billentesEsohoz));
			pilot.rotate(fordalatEsohoz);
			motorHatsoKar.rotate(fordulat(-billentesEsohoz));
			pilot.rotate(-fordalatEsohoz);

		}

		private static void hatramegy() {
			pilot.travel(-323);
		}

		private static void beforog() {
			arcJobbraHatra(95, 118, true);
			varAmigMozog(pilot, "beforog");
		}

		private static void kartBehuz() {
			var(2000);
			motorElsoKar.rotate(fordulat(-0.4));
		}

		private static void hatraIndul() {
			pilot.travel(-150, true);
		}
	}

	private static void var(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			throw new Interrupted();
		}
	}

	public static int fordulat(double korFordulat) {
		return (int) (360 * korFordulat);
	}

	private static void var(int millis, String uzenet) {
		allapotUzenet(uzenet);
		var(millis);
	}

	private static void allapotUzenet(String uzenet) {
		LCD.drawString(uzenet, 0, 5);
	}

	private static MovePilot letrehozPilot() {
		MovePilot pilot = new MovePilot(robot);
		pilot.setLinearSpeed(alapSebbeseg); // mm per second
		pilot.setAngularSpeed(50); // mm per second
		return pilot;
	}

	private static void varAmigMozog(MovePilot pilot, String uzenet) {
		allapotUzenet(uzenet);
		while (pilot.isMoving()) {
			Thread.yield();
		}
	}

	private static void inicializalas() {
		motorHatsoKar = new EV3LargeRegulatedMotor(MotorPort.A);
		motorBal = new EV3LargeRegulatedMotor(MotorPort.B);
		motorBal.resetTachoCount();
		motorJobb = new EV3LargeRegulatedMotor(MotorPort.C);
		motorJobb.resetTachoCount();
		motorElsoKar = new EV3LargeRegulatedMotor(MotorPort.D);
		// motorBal.synchronizeWith( new RegulatedMotor[] { motorJobb } );

		Wheel kerekBal = WheeledChassis.modelWheel(motorBal, 43.2).offset(-65.0);
		Wheel kerekJobb = WheeledChassis.modelWheel(motorJobb, 42.93).offset(65.0);

		robot = new WheeledChassis(new Wheel[] { kerekBal, kerekJobb }, WheeledChassis.TYPE_DIFFERENTIAL);
		pilot = letrehozPilot();
	}

	private static void stop() {
		motorBal.close();
		motorJobb.close();
		Button.LEDPattern(0);
	}

	private static void arcJobbraElore(double radius, double angle, boolean immedateReturn) {
		pilot.arc(radius, angle, immedateReturn);
	}

	private static void arcJobbraHatra(double radius, double angle, boolean immedateReturn) {
		pilot.arc(radius, -angle, immedateReturn);
	}

	private static void arcBalraElore(double radius, double angle, boolean immedateReturn) {
		pilot.arc(-radius, angle, immedateReturn);
	}

	private static void arcBalraHatra(double radius, double angle, boolean immedateReturn) {
		pilot.arc(-radius, -angle, immedateReturn);
	}
}
