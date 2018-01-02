package hu.tothgellert.ev3.robot2017;

import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public abstract class FoEtap extends AbsztraktEtap {

	public static final double KEREK_KOZEPPONT_TAVOLSAG = 65.0;

	public FoEtap( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	protected double getBalKerekMeret() {
		return 43.2;
	}

	protected double getJobbKerekMeret() {
		return 43.2;
	}

	@Override
	public void indit() {
		kerekInicializalas();
		letrehozUjPilotot();
		super.indit();
	}

	private void kerekInicializalas() {
		Wheel kerekBal = WheeledChassis.modelWheel( motorBal, getBalKerekMeret() ).offset( -KEREK_KOZEPPONT_TAVOLSAG );
		// etap1: Wheel kerekJobb = WheeledChassis.modelWheel(motorJobb, 42.88).offset(65.0);
		Wheel kerekJobb = WheeledChassis.modelWheel( motorJobb, getJobbKerekMeret() )
				.offset( KEREK_KOZEPPONT_TAVOLSAG );

		robot = new WheeledChassis( new Wheel[] { kerekBal, kerekJobb }, WheeledChassis.TYPE_DIFFERENTIAL );
	}

	private void letrehozUjPilotot() {
		MovePilot pilot = new MovePilot( robot );
		pilot.setLinearSpeed( ALAP_SEBESSEG ); // mm per second
		pilot.setAngularSpeed( 50 ); // mm per second
		Kijelzo.debug( "l=" + (int) pilot.getLinearAcceleration() + " a=" + (int) pilot.getAngularAcceleration() );
		// pilot.setAngularAcceleration( 8.0 );
		pilot.setLinearAcceleration( 50 );
		setPilot( pilot );
	}

}
