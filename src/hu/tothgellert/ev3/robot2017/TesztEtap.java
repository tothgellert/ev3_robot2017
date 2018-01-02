package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.robot2017.etap2.Virag;

public class TesztEtap extends FoEtap {

	public TesztEtap( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		Virag virag = new Virag( this );
		virag.indit();
	}

}
