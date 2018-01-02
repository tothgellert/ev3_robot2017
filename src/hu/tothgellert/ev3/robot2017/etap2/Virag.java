package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;
import hu.tothgellert.ev3.robot2017.FoEtap;
import hu.tothgellert.ev3.robot2017.Kijelzo;
import lejos.robotics.Color;

public class Virag extends AbsztraktEtap {

	public Virag( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	protected void run() {
		szakaszKiiras();

		elfordulAWctol();
		feketetKeresBallal();
		feketetKeresJobbal();
		atfordulAPalyan();
		egyenesbeAll();
	}

	private void elfordulAWctol() {
		muveletKiiras( "elfordul" );
		rotate( 30 );
	}

	private void feketetKeresBallal() {
		muveletKiiras( "feketetKeresBallal" );

		setLinearSpeed( 50 );
		travel( 400, true );
		while ( pilotMozog() ) {
			int balSzinKod = szinSzenzorBal.getColorID();
			Kijelzo.szenzor( "szin=" + balSzinKod );
			if ( balSzinKod == Color.BLACK ) {
				break;
			}
			Thread.yield();
		}
//		Kijelzo.szenzor( "mozog=" + pilotMozog() + " szin=" + szinSzenzorBal.getColorID() );
		stop();
	}

	private void feketetKeresJobbal() {
		muveletKiiras( "feketetKeresJobbal" );

		//setLinearSpeed( 20 );
		//motorJobb.rotate( 360, true );
		//while ( motorJobb.isMoving() ) {
		setLinearSpeed( 20 );
		arcBalraElore( FoEtap.KEREK_KOZEPPONT_TAVOLSAG - 10, 360, true );
		while ( pilotMozog() ) {
			int jobbSzinKod = szinSzenzorJobb.getColorID();
			Kijelzo.szenzor( "szin=" + jobbSzinKod );
			if ( jobbSzinKod == Color.BLACK ) {
				break;
			}
			Thread.yield();
		}
		stop();
		setLinearSpeed( ALAP_SEBESSEG );
	}

	private void atfordulAPalyan() {
		muveletKiiras( "atfordulAPalyan" );
		arcBalraElore( 200, 90 );
		travel( 110 );
		arcBalraElore( 200, 95 );
	}

	private void egyenesbeAll() {
		setLinearSpeed( ALAP_SEBESSEG / 4 );
		travel( -180 );
		setLinearSpeed( ALAP_SEBESSEG );
	}

}
