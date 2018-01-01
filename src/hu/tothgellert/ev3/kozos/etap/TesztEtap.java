package hu.tothgellert.ev3.kozos.etap;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;

public class TesztEtap extends AbsztraktEtap {

	public TesztEtap( AbsztraktEtap szuloEtap ) {
		super( szuloEtap );
	}

	@Override
	public void run() {
		LCD.clear( 1 );
		for ( int i = 1; i <= 10; i++ ) {
			Delay.msDelay( 1000 );
			if ( Thread.interrupted() ) {
				break;
			}
			LCD.drawInt( i, 0, 1 );
		}
	}

}
