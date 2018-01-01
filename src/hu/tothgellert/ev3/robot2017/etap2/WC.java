package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class WC extends AbsztraktEtap {

	public WC( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		allapotKiiras();

		arcBalraElore( 190, 45, false );
		arcJobbraElore( 190, 45, false );
		travel( 495 );
		setLinearSpeed( 400 );
		motorElsoKar.rotate( fordulat( 0.45 ) );
		setLinearSpeed( ALAP_SEBESSEG );
	}

}
