package hu.tothgellert.ev3.kozos.etap;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuEsemeny;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuEsemenyFigyelo;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuKezelo;
import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.robot2017.AbsztraktEtap;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class EtapFuttato implements BillentyuEsemenyFigyelo {
	public EtapFuttato() {
	}

	public void futtat( Runnable etap ) {
		try {
			elokeszit( etap );
			tenylegesFuttatas( etap );
			lezar();
		} finally {
			BillentyuKezelo.figyelotElvesz();
		}
	}

	private void tenylegesFuttatas( Runnable etap ) {
		try {
			//etap.run();
			etapUzenet( "etap fut..." );
			Delay.msDelay( 10000 );
		} catch ( EtapMegszakitvaException e ) {
		} finally {
		}
	}

	private void lezar() {
		LCD.clear( 7 );
		LCD.refresh();
	}

	private void elokeszit( Runnable etap ) {
		Sound.beep();
		BillentyuKezelo.figyelotHozzaad( this );
		AbsztraktEtap.etapInditas();
		etapUzenet( etap.getClass().getSimpleName() );
		LCD.asyncRefresh();
		Button.LEDPattern( BillentyuSzin.SARGA_NORMAL );
	}

	public static void etapUzenet( String uzenet ) {
		LCD.clear( 5 );
		LCD.drawString( uzenet, 0, 5 );
		LCD.asyncRefresh();
	}

	@Override
	public void billentyuLenyomva( BillentyuEsemeny esemeny ) {
		if ( (esemeny.getBillentyuLenyomva() & Button.ID_ESCAPE) != 0 ) {
			AbsztraktEtap.etapMegszakitva();
		}
	}
}
