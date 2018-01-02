package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.kozos.etap.EtapFuttato;
import hu.tothgellert.ev3.robot2017.etap1.Etap1;
import hu.tothgellert.ev3.robot2017.etap2.Etap2;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class FoProgram extends AbsztraktEtap {

	public static void main( String[] args ) {
		new FoProgram().run();
	}

	public FoProgram() {
		super( null );
	}

	@Override
	public void run() {
		kezdoKepernyo();
		portInicializalas();
		while ( etapValasztasEsFuttatas() ) {
		}
		Sound.beep();
		stop();
	}

	private void kezdoKepernyo() {
		Button.LEDPattern( BillentyuSzin.PIROS_NORMAL );
		Kijelzo.focim( "Arpad Robot 2017" );
	}

	private boolean etapValasztasEsFuttatas() {
		Button.LEDPattern( BillentyuSzin.ZOLD_NORMAL );
		Kijelzo.szakaszUzenet( "" );
		Kijelzo.etapUzenet( "Valassz etapot!" );
		Sound.beep();

		EtapValaszto etapValaszto = new EtapValaszto();
		etapValaszto.valaszt();
		if ( etapValaszto.isKilepes() ) {
			return false;
		}

		FoEtap etap;
		switch ( etapValaszto.getValasztottEtap() ) {
			case 1:
				etap = new Etap1( this );
				break;
			case 2:
				etap = new Etap2( this );
				break;
			case 3:
				// etap = new Etap3( this );
				// break;
				return true;
			case 4:
				etap = new TesztEtap( this );
				break;
			default:
				return true;
		}
		etapotFuttat( etap );
		return true;
	}

	private void portInicializalas() {
		szinSzenzorBal = new EV3ColorSensor( SensorPort.S4 );
		szinSzenzorJobb = new EV3ColorSensor( SensorPort.S3 );

		motorHatsoKar = new EV3LargeRegulatedMotor( MotorPort.A );
		motorBal = new EV3LargeRegulatedMotor( MotorPort.B );
		motorBal.resetTachoCount();
		motorJobb = new EV3LargeRegulatedMotor( MotorPort.C );
		motorJobb.resetTachoCount();
		motorElsoKar = new EV3LargeRegulatedMotor( MotorPort.D );
		// motorBal.synchronizeWith( new RegulatedMotor[] { motorJobb } );
	}

	private void etapotFuttat( FoEtap etap ) {
		new EtapFuttato().futtat( etap );
	}

	@Override
	protected void stop() {
		super.stop();
		Button.LEDPattern( BillentyuSzin.KIKAPCSOLVA );
	}
}
