package hu.toth.gellert.ev3.teszt;

import lejos.hardware.Button;

public class KilepesGombThread extends Thread {

	public KilepesGombThread() {
		super("kilepesGomb");
		setDaemon(true);
	}

	@Override
	public void run() {
		while (true) {
			if (escapeLenyomva()) {
				programLeall();
				varAmigEscapetElenged();
				break;
			}
			Thread.yield();
		}
	}

	private boolean escapeLenyomva() {
		// Button.waitForAnyEvent()
		return (Button.getButtons() & Button.ID_ESCAPE) != 0;
	}

	private void varAmigEscapetElenged() {
		while (escapeLenyomva()) {
			Thread.yield();
		}
	}

	private void programLeall() {
		// foSzal.leall();
		Button.LEDPattern(0);
		System.exit(0);
	}
}
