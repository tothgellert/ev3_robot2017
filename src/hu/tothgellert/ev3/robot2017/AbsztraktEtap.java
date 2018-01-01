package hu.tothgellert.ev3.robot2017;

import hu.tothgellert.ev3.kozos.billentyu.BillentyuSzin;
import hu.tothgellert.ev3.kozos.etap.MegszakithatoEtap;
import lejos.hardware.Button;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

public abstract class AbsztraktEtap extends MegszakithatoEtap {
	public static final int ALAP_SEBESSEG = 120;

	protected Chassis robot;
	protected RegulatedMotor motorBal;
	protected RegulatedMotor motorJobb;
	protected RegulatedMotor motorElsoKar;
	protected RegulatedMotor motorHatsoKar;
	private MovePilot pilot;

	protected abstract void run();

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

	protected void setPilot( MovePilot pilot ) {
		this.pilot = pilot;
	}

	public void indit() {
		Thread.interrupted();
		run();
	}

	protected void etapKiiras() {
		Kijelzo.etapUzenet( getClass().getSimpleName() + " fut" );
	}

	protected void allapotKiiras() {
		Kijelzo.allapotUzenet( getClass().getSimpleName() );
	}

	protected void varAmigMozog( String uzenet ) {
		etapMegszakitasEllenorzese();
		Kijelzo.allapotUzenet( uzenet );
		while ( pilot.isMoving() ) {
			Thread.yield();
			etapMegszakitasEllenorzese();
		}
	}

	protected void var( int millis ) {
		etapMegszakitasEllenorzese();
		Delay.msDelay( millis );
		etapMegszakitasEllenorzese();
	}

	protected int fordulat( double korFordulat ) {
		return (int) (360 * korFordulat);
	}

	protected void var( int millis, String uzenet ) {
		etapMegszakitasEllenorzese();
		Kijelzo.allapotUzenet( uzenet );
		var( millis );
	}

	@Override
	protected void stop() {
		pilot.stop();
		motorElsoKar.stop();
		motorHatsoKar.stop();
		Button.LEDPattern( BillentyuSzin.KIKAPCSOLVA );
	}

	protected void arcJobbraElore( double radius, double angle ) {
		arcJobbraElore( radius, angle, false );
	}

	protected void arcJobbraElore( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( radius, angle, immedateReturn );
	}

	protected void arcJobbraHatra( double radius, double angle ) {
		arcJobbraHatra( radius, angle, false );
	}

	protected void arcJobbraHatra( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( radius, -angle, immedateReturn );
	}

	protected void arcBalraElore( double radius, double angle ) {
		arcBalraElore( radius, angle, false );
	}

	protected void arcBalraElore( double radius, double angle, boolean immedateReturn ) {
		etapMegszakitasEllenorzese();
		pilot.arc( -radius, angle, immedateReturn );
	}

	protected void arcBalraHatra( double radius, double angle ) {
		arcBalraHatra( radius, angle, false );
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
}
