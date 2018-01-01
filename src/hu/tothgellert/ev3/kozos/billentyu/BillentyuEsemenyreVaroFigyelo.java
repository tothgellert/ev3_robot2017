package hu.tothgellert.ev3.kozos.billentyu;

public class BillentyuEsemenyreVaroFigyelo implements BillentyuEsemenyFigyelo {

	private BillentyuEsemeny esemeny;

	@Override
	public void billentyuLenyomasKezelese( BillentyuEsemeny esemeny ) {
		this.esemeny = esemeny;
		synchronized ( this ) {
			this.notifyAll();
		}
	}

	public BillentyuEsemeny getEsemeny() {
		return esemeny;
	}

}
