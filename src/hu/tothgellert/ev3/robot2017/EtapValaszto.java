package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuKezelo;
import lejos.hardware.Button;

public class EtapValaszto {
	private int valasztottEtap;
	private boolean kilepes;

	public void valaszt() {
		kilepes = false;
		valasztottEtap = 0;
		for (;;) {
			int lenyomottGombok = BillentyuKezelo.esemenyreVar().getBillentyuLenyomva();
			if ((lenyomottGombok & Button.ID_LEFT) != 0) {
				valasztottEtap = 1;
				return;
			}
			if ((lenyomottGombok & Button.ID_UP) != 0) {
				valasztottEtap = 2;
				return;
			}
			if ((lenyomottGombok & Button.ID_RIGHT) != 0) {
				valasztottEtap = 3;
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
