package hu.tothgellert.ev3.robot2017.etap1;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class Etap1 extends AbsztraktEtap {
	public Etap1( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		etapKiiras();

		GurigaEsBehuz gurigaEsBehuz = new GurigaEsBehuz( this );
		Felho felho = new Felho( this );
		Kontener kontener = new Kontener( this );

		egyenesbeAll();
		gurigaEsBehuz.run();
		felho.run();
		kontener.run();
	}

	private void egyenesbeAll() {
		travel( -20 );
	}
}
