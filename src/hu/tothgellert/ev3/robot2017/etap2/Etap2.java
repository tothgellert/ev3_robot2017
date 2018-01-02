package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;
import hu.tothgellert.ev3.robot2017.FoEtap;

public class Etap2 extends FoEtap {

	public Etap2( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	protected double getJobbKerekMeret() {
		return 42.89;
	}

	@Override
	public void run() {
		etapKiiras();

		Szokokut szokokut = new Szokokut( this );
		WC wc = new WC( this );

		szokokut.indit();
		wc.indit();
	}

}
