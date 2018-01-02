package hu.tothgellert.ev3.robot2017;

import lejos.hardware.lcd.LCD;

//@SuppressWarnings( "unused" )
public class Kijelzo {
	// kijelző mérete: 17 x 8
	public static int SOR_FOCIM = 0;
	public static int SOR_1 = 1;
	public static int SOR_ETAP = 2;
	public static int SOR_SZAKASZ = 3;
	public static int SOR_MUVELET = 4;
	public static int SOR_SZENZOR = 5;
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

	public static void szakaszUzenet( String uzenet ) {
		LCD.clear( SOR_SZAKASZ );
		LCD.drawString( uzenet, 0, SOR_SZAKASZ );
		LCD.asyncRefresh();
	}

	public static void muveletUzenet( String uzenet ) {
		uzenet = szovegetLevag( uzenet );
		LCD.clear( SOR_MUVELET );
		LCD.drawString( uzenet, 0, SOR_MUVELET );
		LCD.asyncRefresh();
	}

	private static String szovegetLevag( String uzenet ) {
		if ( uzenet.length() <= LCD.DISPLAY_CHAR_WIDTH ) {
			return uzenet;
		}
		return uzenet.substring( 0, 17 );
	}

	public static void debug( String uzenet ) {
		LCD.clear( SOR_DEBUG );
		LCD.drawString( uzenet, 0, SOR_DEBUG );
		LCD.asyncRefresh();
	}

	public static void szenzor( String uzenet ) {
		LCD.clear( SOR_SZENZOR );
		LCD.drawString( uzenet, 0, SOR_SZENZOR );
		LCD.asyncRefresh();
	}

}
