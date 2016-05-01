package de.hdm.partnerboerse.shared.bo;

public class SearchProfile extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private int fromAge = 0;
	
	private int toAge = 0;
	
	private int fromHeight = 0;
	
	private int toHeight = 0;
	
	private enum hairColor{
		egal,
		braun,
		blond,
		schwarz,
		rot,
		grün,
		blau,
		orange,
		grau,
		lila,
		lilablassblau,
		kastanienrot,
		tabakrot,
		regenbogen,
		weiß,
		wasserstoffblond,
		himmelblau,
		glatze
	}
	
	private enum confession{
		egal,
		katholisch,
		evangelisch,
		buddhismus,
		rastafari,
		islam,
		pastafari,
		hinduistisch,
		konfessionslos
	}
	
	private enum gender{
		egal,
		weiblich,
		männlich,
		transgender
	}

	public int getFromAge() {
		return fromAge;
	}

	public void setFromAge(int fromAge) {
		this.fromAge = fromAge;
	}

	public int getToAge() {
		return toAge;
	}

	public void setToAge(int toAge) {
		this.toAge = toAge;
	}

	public int getFromHeight() {
		return fromHeight;
	}

	public void setFromHeight(int fromHeight) {
		this.fromHeight = fromHeight;
	}

	public int getToHeight() {
		return toHeight;
	}

	public void setToHeight(int toHeight) {
		this.toHeight = toHeight;
	}
}
