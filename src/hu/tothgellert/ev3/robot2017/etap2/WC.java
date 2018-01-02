package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class WC extends AbsztraktEtap {

	public WC( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		szakaszKiiras();

		rafordul();
		utazik();
		lehuzzaAWct();
		varAmigLefolyik();
		kartFelhuz();
	}

	private void kartFelhuz() {
		motorElsoKar.rotate( fordulat( -0.45 ) );
	}

	private void rafordul() {
		arcBalraElore( 186.5, 45, false );
		arcJobbraElore( 186.2, 44, false );
	}

	private void utazik() {
		travel( 485 );
	}

	private void lehuzzaAWct() {
		motorElsoKar.rotate( fordulat( 0.5 ) );
	}

	private void varAmigLefolyik() {
		var( 2000 );
	}

}
