package hu.tothgellert.ev3.robot2017;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public abstract class FoEtap extends AbsztraktEtap {

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
		inicializalas();
		letrehozUjPilotot();
		super.indit();
	}

	private void inicializalas() {
		motorHatsoKar = new EV3LargeRegulatedMotor( MotorPort.A );
		motorBal = new EV3LargeRegulatedMotor( MotorPort.B );
		motorBal.resetTachoCount();
		motorJobb = new EV3LargeRegulatedMotor( MotorPort.C );
		motorJobb.resetTachoCount();
		motorElsoKar = new EV3LargeRegulatedMotor( MotorPort.D );
		// motorBal.synchronizeWith( new RegulatedMotor[] { motorJobb } );

		Wheel kerekBal = WheeledChassis.modelWheel( motorBal, getBalKerekMeret() ).offset( -65.0 );
		// etap1: Wheel kerekJobb = WheeledChassis.modelWheel(motorJobb, 42.88).offset(65.0);
		Wheel kerekJobb = WheeledChassis.modelWheel( motorJobb, getJobbKerekMeret() ).offset( 65.0 );

		robot = new WheeledChassis( new Wheel[] { kerekBal, kerekJobb }, WheeledChassis.TYPE_DIFFERENTIAL );
	}

	private void letrehozUjPilotot() {
		MovePilot pilot = new MovePilot( robot );
		pilot.setLinearSpeed( ALAP_SEBESSEG ); // mm per second
		pilot.setAngularSpeed( 50 ); // mm per second
		setPilot( pilot );
	}

}
