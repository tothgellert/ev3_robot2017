package hu.tothgellert.ev3.robot2017.etap1;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

class Kontener extends AbsztraktEtap {
	public Kontener( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		szakaszKiiras();

		eloremegy();
		befordul();
		tolatEsKitol();
		betol();
		hazamegy();
	}

	private void hazamegy() {
		arcBalraHatra( 350, 90, true );
		var( 500 );
		motorElsoKar.rotate( fordulat( -1.2 ), true );
		motorHatsoKar.rotate( fordulat( -0.59 ), true );
		varAmigMozog( "E.T. haza!!" );
	}

	private void eloremegy() {
		travel( 225 );
	}

	private void befordul() {
		rotate( 117 );
	}

	private void tolatEsKitol() {
		travel( -285 );
		motorElsoKar.rotate( fordulat( 1.2 ) );
	}

	private void betol() {
		travel( 130 );

	}
}