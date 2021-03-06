package hu.tothgellert.ev3.kozos.etap;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuEsemeny;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuEsemenyFigyelo;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuKezelo;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.robot2017.FoEtap;
import hu.tothgellert.ev3.robot2017.Kijelzo;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

public class EtapFuttato implements BillentyuEsemenyFigyelo {
	public EtapFuttato() {
	}

	public void futtat( FoEtap etap ) {
		try {
			BillentyuKezelo.figyelotHozzaad( this );
			elokeszit( etap );
			tenylegesFuttatas( etap );
			befejez();
		} catch ( Throwable e ) {
			Sound.beepSequence();
			e.printStackTrace();
		} finally {
			BillentyuKezelo.figyelotElvesz();
		}
	}

	private void tenylegesFuttatas( FoEtap etap ) {
		try {
			etap.indit();
		} catch ( EtapMegszakitvaException e ) {
		} finally {
		}
	}

	private void befejez() {
		Kijelzo.etapUzenet( "" );
	}

	private void elokeszit( FoEtap etap ) {
		Sound.beep();
		FoEtap.etapInditas();
		Button.LEDPattern( BillentyuSzin.SARGA_NORMAL );
	}

	@Override
	public void billentyuLenyomasKezelese( BillentyuEsemeny esemeny ) {
		//billentyuEsemenytKiir( esemeny );
		if ( esemeny.escapeLenyomva() ) {
			FoEtap.etapMegszakitasKerese();
		}
	}

	@SuppressWarnings( "unused" )
	private void billentyuEsemenytKiir( BillentyuEsemeny esemeny ) {
		int xPozicio = LCD.DISPLAY_CHAR_WIDTH - 3;
		// hibas a metódus: LCD.clear( xPozicio, Kijelzo.SOR_FOCIM, 3 );
		BrickFinder.getDefault().getTextLCD().clear( xPozicio, Kijelzo.SOR_FOCIM, 3 );
		LCD.drawInt( esemeny.getBillentyuLenyomva(), xPozicio, Kijelzo.SOR_FOCIM );
		LCD.refresh();
	}
}
