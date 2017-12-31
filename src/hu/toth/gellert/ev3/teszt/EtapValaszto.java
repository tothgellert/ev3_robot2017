package hu.toth.gellert.ev3.teszt;

import lejos.hardware.Button;

public class EtapValaszto {
	private int valasztottEtap;
	private boolean kilepes;

	public void valaszt() {
		kilepes = false;
		for (;;) {
			int lenyomottGombok = Button.waitForAnyPress();
			if ((lenyomottGombok & Button.ID_LEFT) != 0) {
				valasztottEtap = 1;
				return;
			}
			if ((lenyomottGombok & Button.ID_UP) != 0) {
				valasztottEtap = 1;
				return;
			}
			if ((lenyomottGombok & Button.ID_RIGHT) != 0) {
				valasztottEtap = 1;
				return;
			}
			if ((lenyomottGombok & Button.ID_ESCAPE) != 0) {
				kilepes = true;
				return;
			}
		}
	}

	public int getValasztottEtap() {
		return valasztottEtap;
	}

	public boolean isKilepes() {
		return kilepes;
	}

}
