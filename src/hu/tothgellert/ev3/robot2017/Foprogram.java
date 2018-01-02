package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.kozos.etap.EtapFuttato;
import hu.tothgellert.ev3.kozos.etap.TesztEtap;
import hu.tothgellert.ev3.robot2017.etap1.Etap1;
import hu.tothgellert.ev3.robot2017.etap2.Etap2;
import lejos.hardware.Button;
import lejos.hardware.Sound;

public class Foprogram extends AbsztraktEtap {

	public static void main( String[] args ) {
		new Foprogram().run();
	}

	public Foprogram() {
		super( null );
	}

	@Override
	public void run() {
		kezdoKepernyo();
		while ( etapValasztasEsFuttatas() ) {
		}
		Sound.beep();
		stop();
	}

	private void kezdoKepernyo() {
		Button.LEDPattern( BillentyuSzin.PIROS_NORMAL );
		Kijelzo.focim( "Robot 2017" );
	}

	private boolean etapValasztasEsFuttatas() {
		Button.LEDPattern( BillentyuSzin.ZOLD_NORMAL );
		Kijelzo.allapotUzenet( "" );
		Kijelzo.etapUzenet( "Valassz etapot!" );
		Sound.beep();

		EtapValaszto etapValaszto = new EtapValaszto();
		etapValaszto.valaszt();
		if ( etapValaszto.isKilepes() ) {
			return false;
		}

		AbsztraktEtap etap;
		switch ( etapValaszto.getValasztottEtap() ) {
			case 1:
				etap = new Etap1( this );
				break;
			case 2:
				etap = new Etap2( this );
				break;
			case 3:
				// etap = new Etap3( this );
				// break;
				return true;
			case 4:
				etap = new TesztEtap( this );
				break;
			default:
				return true;
		}
		etapotFuttat( etap );
		return true;
	}

	private void etapotFuttat( AbsztraktEtap etap ) {
		new EtapFuttato().futtat( etap );
	}

}
