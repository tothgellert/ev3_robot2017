package hu.tothgellert.ev3.robot2017.etap2;

import hu.tothgellert.ev3.robot2017.AbsztraktEtap;

public class Etap2 extends AbsztraktEtap {

	public Etap2(AbsztraktEtap szuloEtap) {
		super(szuloEtap);
	}

	@Override
	public void run() {
		Szokokut szokokut = new Szokokut(this);
		WC Wc = new WC(this);

		szokokut.run();
		Wc.run();
	}

}
