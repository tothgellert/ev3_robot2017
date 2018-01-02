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
		Kijelzo.debug( "megszakitva" );
		LCD.asyncRefresh();
		throw new EtapMegszakitvaException();
	}

	protected void etapMegszakitasEllenorzese() {
		boolean szalMegszakitva = Thread.interrupted();
		LCD.drawChar( etapMegszakitva ? '1' : '0', 0, Kijelzo.SOR_SZENZOR );
		LCD.drawChar( szalMegszakitva ? '1' : '0', 1, Kijelzo.SOR_SZENZOR );
		LCD.refresh();
		if ( etapMegszakitva || szalMegszakitva ) {
			Sound.beepSequenceUp();
			etapMegszakitasa();
		}
	}

	public static void etapMegszakitasKerese() {
		Sound.beepSequence();
		Kijelzo.debug( "megszakitaskeres" );
		LCD.asyncRefresh();
		etapMegszakitva = true;
		//futtatoSzal.interrupt();
	}

	public static void etapInditas() {
		LCD.clear( Kijelzo.SOR_DEBUG );
		etapMegszakitva = false;
		futtatoSzal = Thread.currentThread();
	}
}
