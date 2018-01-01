package hu.tothgellert.ev3.kozos.billentyu;

import lejos.hardware.Button;

public class BillentyuKezelo {
	private static BillentyuEsemenyFigyelo listener;

	private static EsemenyreVaroFigyelo esemenyreVaroFigyelo = new EsemenyreVaroFigyelo();

	private static class BillentyuKezeloThread extends Thread {
		public BillentyuKezeloThread() {
			super("BillentyuKezelo");
			setDaemon(true);
		}

		@Override
		public void run() {
			while (true) {
				int billentyuLenyomva = Button.waitForAnyPress();
				if (listener != null) {
					BillentyuEsemeny esemeny = new BillentyuEsemeny(billentyuLenyomva);
					listener.billentyuLenyomva(esemeny);
				}
			}
		}
	}

	public static void indit() {
		new BillentyuKezeloThread().start();
	}

	public static void addListener(BillentyuEsemenyFigyelo listener) {
		BillentyuKezelo.listener = listener;

	}

	public static void varAmigEscapetElenged() {
		while ((Button.getButtons() & Button.ID_ESCAPE) != 0) {
			Thread.yield();
		}
	}

	public static void removeListener() {
		BillentyuKezelo.listener = null;
	}

	public static BillentyuEsemeny esemenyreVar() {
		try {
			listener = esemenyreVaroFigyelo;
			synchronized (esemenyreVaroFigyelo) {
				try {
					esemenyreVaroFigyelo.wait();
				} catch (InterruptedException e) {
				}
			}
			return esemenyreVaroFigyelo.getEsemeny();
		} finally {
			listener = null;
		}
	}
}
