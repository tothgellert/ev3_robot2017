package hu.tothgellert.ev3.kozos.billentyu;

public class BillentyuEsemenyreVaroFigyelo implements BillentyuEsemenyFigyelo {

	private BillentyuEsemeny esemeny;

	@Override
	public void billentyuLenyomva( BillentyuEsemeny esemeny ) {
		this.esemeny = esemeny;
		synchronized ( this ) {
			esemeny.notifyAll();
		}
	}

	public BillentyuEsemeny getEsemeny() {
		return esemeny;
	}

}
