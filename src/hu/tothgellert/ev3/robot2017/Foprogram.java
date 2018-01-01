package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.kozos.etap.EtapFuttato;
import hu.tothgellert.ev3.robot2017.etap1.Etap1;
import hu.tothgellert.ev3.robot2017.etap2.Etap2;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;

public class Foprogram extends AbsztraktEtap {

	public static void main( String[] args ) {
		new Foprogram().run();
	}

	public Foprogram() {
		super( null );
	}

	@Override
	public void run() {
		kezdoKepernyo();
		inicializalas();
		while ( etapValasztasEsFuttatas() ) {
		}
		Sound.beep();
		stop();
	}

	private void kezdoKepernyo() {
		Button.LEDPattern( BillentyuSzin.PIROS_NORMAL );
		LCD.drawString( "Robot 2017", 0, 3 );
	}

	private boolean etapValasztasEsFuttatas() {
		Button.LEDPattern( BillentyuSzin.ZOLD_NORMAL );
		allapotUzenet( "" );
		EtapFuttato.etapUzenet( "Valassz etapot!" );
		Sound.beep();

		EtapValaszto etapValaszto = new EtapValaszto();
		etapValaszto.valaszt();
		if ( etapValaszto.isKilepes() ) {
			return false;
		}

		AbsztraktEtap etap;
		switch ( etapValaszto.getValasztottEtap() ) {
		case 1:
			etap = new Etap1( this );
			break;
		case 2:
			etap = new Etap2( this );
			break;
		case 3:
			// etap3();
			// break;
		default:
			return true;
		}
		etapotFuttat( etap );
		return true;
	}

	private void etapotFuttat( AbsztraktEtap etap ) {
		new EtapFuttato().futtat( etap );
	}

	private MovePilot letrehozPilot() {
		MovePilot pilot = new MovePilot( robot );
		pilot.setLinearSpeed( ALAP_SEBESSEG ); // mm per second
		pilot.setAngularSpeed( 50 ); // mm per second
		return pilot;
	}

	private void inicializalas() {
		motorHatsoKar = new EV3LargeRegulatedMotor( MotorPort.A );
		motorBal = new EV3LargeRegulatedMotor( MotorPort.B );
		motorBal.resetTachoCount();
		motorJobb = new EV3LargeRegulatedMotor( MotorPort.C );
		motorJobb.resetTachoCount();
		motorElsoKar = new EV3LargeRegulatedMotor( MotorPort.D );
		// motorBal.synchronizeWith( new RegulatedMotor[] { motorJobb } );

		Wheel kerekBal = WheeledChassis.modelWheel( motorBal, 43.2 ).offset( -65.0 );
		// etap1: Wheel kerekJobb = WheeledChassis.modelWheel(motorJobb,
		// 42.88).offset(65.0);
		Wheel kerekJobb = WheeledChassis.modelWheel( motorJobb, 42.89 ).offset( 65.0 );

		robot = new WheeledChassis( new Wheel[] { kerekBal, kerekJobb }, WheeledChassis.TYPE_DIFFERENTIAL );
		setPilot( letrehozPilot() );
	}
}
