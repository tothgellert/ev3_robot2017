package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class Szokokut extends AbsztraktEtap {

	public Szokokut( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		allapotKiiras();

		travel( -20 );
		travel( 615 );
		setLinearSpeed( 700 );
		motorElsoKar.rotate( fordulat( -0.4 ) );
		var( 250 );
		setLinearSpeed( ALAP_SEBESSEG );
		rotate( 2 );
	}
}
