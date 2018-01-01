package hu.tothgellert.ev3.robot2017.etap1;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class Felho extends AbsztraktEtap {
	public Felho(AbsztraktEtap szuloEtap) {
		super(szuloEtap);
	}

	public void run() {
		hatraIndul();
		kartBehuz();
		varAmigMozog("hatra");
		setLinearSpeed(80);
		beforog();
		setLinearSpeed(160);
		hatramegy();
		esotKienged();
	}

	private void esotKienged() {
		double billentesEsohoz = -0.15;
		int fordalatEsohoz = 14;

		motorHatsoKar.rotate(fordulat(billentesEsohoz));
		rotate(fordalatEsohoz);
		motorHatsoKar.rotate(fordulat(-billentesEsohoz));
		rotate(-fordalatEsohoz);

	}

	private void hatramegy() {
		travel(-323);
	}

	private void beforog() {
		arcJobbraHatra(95, 118, true);
		varAmigMozog("beforog");
	}

	private void kartBehuz() {
		var(2000);
		motorElsoKar.rotate(fordulat(-0.4));
	}

	private void hatraIndul() {
		travel(-150, true);
	}

}