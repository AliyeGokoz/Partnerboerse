package de.hdm.partnerboerse.shared.bo;

import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public class SearchProfile extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private int fromAge = 0;
	
	private int toAge = 0;
	
	private int fromHeight = 0;
	
	private int toHeight = 0;
	
	private HairColor hairColor = null;
	
	private Confession confession = null;
	
	private Gender gender = null;



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

	public HairColor getHairColor() {
		return hairColor;
	}

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}

	public Confession getConfession() {
		return confession;
	}

	public void setConfession(Confession confession) {
		this.confession = confession;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
