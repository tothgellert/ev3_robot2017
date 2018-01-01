package hu.tothgellert.ev3.kozos.etap;

import hu.tothgellert.ev3.robot2017.Kijelzo;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public abstract class MegszakithatoEtap {
	protected static boolean etapMegszakitva;
	private static Thread futtatoSzal;

	protected abstract void stop();

	private void etapMegszakitasa() {
		stop();
		LCD.drawString( "megszakitva", 0, Kijelzo.SOR_DEBUG );
		LCD.asyncRefresh();
		throw new EtapMegszakitvaException();
	}

	protected void etapMegszakitasEllenorzese() {
		if ( etapMegszakitva || Thread.interrupted() ) {
			etapMegszakitasa();
		}
	}

	public static void etapMegszakitasKerese() {
		Sound.twoBeeps();
		LCD.drawString( "megszakitaskeres", 0, Kijelzo.SOR_DEBUG );
		LCD.asyncRefresh();
		etapMegszakitva = true;
		futtatoSzal.interrupt();
	}

	public static void etapInditas() {
		LCD.clear( Kijelzo.SOR_DEBUG );
		etapMegszakitva = false;
		futtatoSzal = Thread.currentThread();
	}
}
