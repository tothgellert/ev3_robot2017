package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuKezelo;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import lejos.hardware.Button;

public class EtapValaszto {
	private int valasztottEtap;
	private boolean kilepes;

	public EtapValaszto() {
	}

	public void valaszt() {
		kilepes = false;
		valasztottEtap = 0;
		Button.LEDPattern( BillentyuSzin.ZOLD_VILLOGO );
		for ( ;; ) {
			int lenyomottGombok = BillentyuKezelo.esemenyreVar().getBillentyuLenyomva();
			if ( (lenyomottGombok & Button.ID_LEFT) != 0 ) {
				valasztottEtap = 1;
				break;
			}
			if ( (lenyomottGombok & Button.ID_UP) != 0 ) {
				valasztottEtap = 2;
				break;
			}
			if ( (lenyomottGombok & Button.ID_RIGHT) != 0 ) {
				valasztottEtap = 3;
				break;
			}
			if ( (lenyomottGombok & Button.ID_ESCAPE) != 0 ) {
				kilepes = true;
				break;
			}
		}
		Button.LEDPattern( BillentyuSzin.ZOLD_NORMAL );
	}

	public int getValasztottEtap() {
		return valasztottEtap;
	}

	public boolean isKilepes() {
		return kilepes;
	}

}
