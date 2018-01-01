package hu.tothgellert.ev3.robot2017;

import lejos.hardware.lcd.LCD;

@SuppressWarnings( "unused" )
public class Kijelzo {
	// kijelző mérete: 17 x 8
	public static int SOR_FOCIM = 0;
	public static int SOR_1 = 1;
	public static int SOR_ETAP = 2;
	public static int SOR_ALLAPOT = 3;
	public static int SOR_4 = 4;
	public static int SOR_5 = 5;
	public static int SOR_DEBUG = 6;

	public static void focim( String uzenet ) {
		//LCD.setAutoRefresh( false );
		LCD.clear();
		LCD.drawString( uzenet, 0, SOR_FOCIM );
		LCD.refresh();
	}

	public static void etapUzenet( String uzenet ) {
		LCD.clear( SOR_ETAP );
		LCD.drawString( uzenet, 0, SOR_ETAP );
		LCD.asyncRefresh();
	}

	protected static void allapotUzenet( String uzenet ) {
		LCD.clear( SOR_ALLAPOT );
		LCD.drawString( uzenet, 0, SOR_ALLAPOT );
		LCD.asyncRefresh();
	}

}
