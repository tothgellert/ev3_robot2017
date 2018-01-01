package hu.tothgellert.ev3.kozos.billentyu;

import lejos.hardware.Button;

public class BillentyuKezelo {
	private static BillentyuEsemenyFigyelo figyelo;
	private static BillentyuKezeloThread szal;
	private static BillentyuEsemenyreVaroFigyelo esemenyreVaroFigyelo = new BillentyuEsemenyreVaroFigyelo();

	private static class BillentyuKezeloThread extends Thread {
		public BillentyuKezeloThread() {
			super();
			setName( "BillentyuKezelo" );
			setDaemon( true );
		}

		@Override
		public void run() {
			while ( true ) {
				int billentyuLenyomva = Button.waitForAnyPress();
				if ( figyelo != null ) {
					esemenytElkuld( billentyuLenyomva );
				}
			}
		}

		private void esemenytElkuld( int billentyuLenyomva ) {
			BillentyuEsemeny esemeny = new BillentyuEsemeny( billentyuLenyomva );
			figyelo.billentyuLenyomasKezelese( esemeny );
		}
	}

	public static synchronized void inditHaMegNemFut() {
		if ( szal == null ) {
			szal = new BillentyuKezeloThread();
			szal.start();
		}
	}

	public static void figyelotHozzaad( BillentyuEsemenyFigyelo listener ) {
		inditHaMegNemFut();
		BillentyuKezelo.figyelo = listener;

	}

	public static void figyelotElvesz() {
		BillentyuKezelo.figyelo = null;
	}

	public static void varAmigEscapetElenged() {
		while ( (Button.getButtons() & Button.ID_ESCAPE) != 0 ) {
			Thread.yield();
		}
	}

	public static BillentyuEsemeny esemenyreVar() {
		inditHaMegNemFut();
		try {
			figyelo = esemenyreVaroFigyelo;
			synchronized ( esemenyreVaroFigyelo ) {
				try {
					esemenyreVaroFigyelo.wait();
				} catch ( InterruptedException e ) {
					return new BillentyuEsemeny( 0 );
				}
			}
			return esemenyreVaroFigyelo.getEsemeny();
		} finally {
			figyelo = null;
		}
	}
}
