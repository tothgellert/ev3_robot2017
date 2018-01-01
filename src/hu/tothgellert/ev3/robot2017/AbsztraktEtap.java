package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.kozos.etap.EtapMegszakitvaException;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public abstract class AbsztraktEtap implements Runnable {
	public static final int ALAP_SEBESSEG = 120;

	protected static boolean etapMegszakitva;
	private static Thread futtatoSzal;

	protected RegulatedMotor motorBal;
	protected RegulatedMotor motorJobb;
	protected Chassis robot;
	protected RegulatedMotor motorElsoKar;
	protected RegulatedMotor motorHatsoKar;
	private MovePilot pilot;

	public AbsztraktEtap( AbsztraktEtap szuloEtap ) {
		if ( szuloEtap == null ) {
			return;
		}
		adatokatMasol( szuloEtap );
	}

	private void adatokatMasol( AbsztraktEtap szuloEtap ) {
		this.motorBal = szuloEtap.motorBal;
		this.motorJobb = szuloEtap.motorJobb;
		this.motorElsoKar = szuloEtap.motorElsoKar;
		this.motorHatsoKar = szuloEtap.motorHatsoKar;
		this.pilot = szuloEtap.pilot;
		this.robot = szuloEtap.robot;
	}

	protected void varAmigMozog( String uzenet ) {
		etapMegszakitasEllenorzese();
		allapotUzenet( uzenet );
		while ( pilot.isMoving() ) {
			Thread.yield();
		}
	}

	protected void var( int millis ) {
		etapMegszakitasEllenorzese();
		Delay.msDelay( millis );
	}

	protected int fordulat( double korFordulat ) {
		return (int) (360 * korFordulat);
	}

	protected void var( int millis, String uzenet ) {
		etapMegszakitasEllenorzese();
		allapotUzenet( uzenet );
		var( millis );
	}

	protected void allapotUzenet( String uzenet ) {
		LCD.clear( 6 );
		LCD.drawString( uzenet, 0, 6 );
		LCD.asyncRefresh();
	}

	protected void stop() {
		pilot.stop();
		motorElsoKar.stop();
		motorHatsoKar.stop();
		Button.LEDPattern( BillentyuSzin.KIKAPCSOLVA );
	}

	protected void arcJobbraElore( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( radius, angle, immedateReturn );
	}

	protected void arcJobbraHatra( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( radius, -angle, immedateReturn );
	}

	protected void arcBalraElore( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( -radius, angle, immedateReturn );
	}

	protected void arcBalraHatra( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( -radius, -angle, immedateReturn );
	}

	protected void travel( double distance ) {
		etapMegszakitasEllenorzese();
		pilot.travel( distance );
	}

	protected void travel( double distance, boolean immediateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.travel( distance, immediateReturn );
	}

	public void rotate( double angle ) {
		etapMegszakitasEllenorzese();
		pilot.rotate( angle );
	}

	public void rotate( double angle, boolean immediateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.rotate( angle, immediateReturn );
	}

	public void setLinearSpeed( double speed ) {
		pilot.setLinearSpeed( speed );
	}

	private void etapMegszakitasEllenorzese() {
		if ( etapMegszakitva ) {
			stop();
			LCD.clear( 8 );
			LCD.drawString( "megszakitva", 0, 8 );
			// EtapFuttato.etapUzenet( "megszakitva" );
			LCD.asyncRefresh();
			throw new EtapMegszakitvaException();
		}
	}

	protected void setPilot( MovePilot pilot ) {
		this.pilot = pilot;
	}

	public static void etapMegszakitva() {
		LCD.drawString( "megszakitva", 0, 8 );
		LCD.asyncRefresh();
		etapMegszakitva = true;
		futtatoSzal.interrupt();
	}

	public static void etapInditas() {
		LCD.clear( 8 );
		etapMegszakitva = false;
		futtatoSzal = Thread.currentThread();
		szalMegszakitottAllapotanakTorlese();
	}

	private static void szalMegszakitottAllapotanakTorlese() {
		Thread.interrupted();
	}
}
