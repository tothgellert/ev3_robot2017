package hu.tothgellert.ev3.robot2017.etap1;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

class GurigaEsBehuz extends AbsztraktEtap {
	public GurigaEsBehuz( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		allapotKiiras();

		elindul();
		karokKi();
		varAmigMozog( "elore" );
		lassanBetol();

		vezeteketBehuz();
		setLinearSpeed( 90 );
		motorJobb.rotate( fordulat( 0.3 ) );
		falnakNyom();
		motorJobb.rotate( fordulat( -0.05 ) );
		setLinearSpeed( ALAP_SEBESSEG );
		varMigLeesikAViz();
	}

	private void lassanBetol() {
		setLinearSpeed( 80 );
		travel( 150 );
	}

	private void varMigLeesikAViz() {
		var( 500, "leesik" );
	}

	private void vezeteketBehuz() {
		motorElsoKar.rotate( fordulat( -1.1 ) );
	}

	private void falnakNyom() {
		setLinearSpeed( 350 );
		travel( 80 );
		setLinearSpeed( 50 );
	}

	private void karokKi() {
		motorElsoKar.rotate( fordulat( 1.6 ), true );
		var( 3000 );
		motorHatsoKar.rotate( fordulat( 0.59 ), true );
	}

	private void elindul() {
		travel( 725, true );
	}
}