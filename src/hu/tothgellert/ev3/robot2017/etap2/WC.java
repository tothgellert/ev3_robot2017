package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class WC extends AbsztraktEtap {

	public WC( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		allapotKiiras();

		arcBalraElore( 186, 45, false );
		arcJobbraElore( 186.8, 44, false );
		travel( 485 );
		setLinearSpeed( 400 );
		motorElsoKar.rotate( fordulat( 0.5 ) );
		setLinearSpeed( ALAP_SEBESSEG );
		motorElsoKar.rotate( fordulat( -0.45 ) );
		var( 2000 );
		rotate( 16 );

	}

}
