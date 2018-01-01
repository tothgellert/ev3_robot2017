package hu.tothgellert.ev3.kozos.billentyu;

import lejos.hardware.Button;

public class BillentyuEsemeny {

	private int billentyuLenyomva;

	public BillentyuEsemeny( int billentyuLenyomva ) {
		this.billentyuLenyomva = billentyuLenyomva;
	}

	public int getBillentyuLenyomva() {
		return billentyuLenyomva;
	}

	public boolean escapeLenyomva() {
		return (billentyuLenyomva & Button.ID_ESCAPE) != 0;
	}
}
