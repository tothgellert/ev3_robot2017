package hu.tothgellert.ev3.kozos.etap;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuEsemeny;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuEsemenyFigyelo;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuKezelo;
import hu.tothgellert.ev3.robot2017.AbsztraktEtap;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

public class EtapFuttato implements BillentyuEsemenyFigyelo {
	public EtapFuttato() {
	}

	public void futtat(Runnable etap) {
		try {
			elokeszit(etap);
			tenylegesFuttatas(etap);
			lezar();
		} finally {
			BillentyuKezelo.figyelotElvesz();
		}
	}

	private void tenylegesFuttatas(Runnable etap) {
		try {
			etap.run();
		} catch (EtapMegszakitvaException e) {
		} finally {
		}
	}

	private void lezar() {
		LCD.clear(7);
	}

	private void elokeszit(Runnable etap) {
		BillentyuKezelo.figyelotHozzaad(this);
		AbsztraktEtap.etapInditas();
		AbsztraktEtap.etapUzenet(etap.getClass().getSimpleName());
		Button.LEDPattern(3);
	}

	@Override
	public void billentyuLenyomva(BillentyuEsemeny esemeny) {
		if ((esemeny.getBillentyuLenyomva() & Button.ID_ESCAPE) != 0) {
			AbsztraktEtap.etapMegszakitva();
		}
	}
}
