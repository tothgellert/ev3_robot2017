package hu.tothgellert.ev3.robot2017.etap1;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;
import hu.tothgellert.ev3.robot2017.FoEtap;

public class Etap1 extends FoEtap {
	public Etap1( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	protected double getJobbKerekMeret() {
		return 42.82;
	}

	@Override
	public void run() {
		etapKiiras();

		GurigaEsBehuz gurigaEsBehuz = new GurigaEsBehuz( this );
		Felho felho = new Felho( this );
		Kontener kontener = new Kontener( this );

		egyenesbeAll();
		gurigaEsBehuz.indit();
		felho.indit();
		kontener.indit();
	}

	private void egyenesbeAll() {
		travel( -20 );
	}
}
