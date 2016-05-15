package de.hdm.partnerboerse.shared.bo;

import java.util.Date;

public class Profile extends BusinessObject {

	private static final long serialVersionUID = 1L;

	private String firstName = "";

	private String lastName = "";

	private Date dateOfBirth = null;

	private String eMail = "";

	private int height = 0;

	private boolean smoker = false;
	
	private HairColor hairColor = null;
	
	private Confession confession = null;
	
	private Gender gender = null;

	public enum HairColor {
		BROWN("braun"), BLOND("blond"), BLACK("schwarz"), RED("rot"), GREY(
				"grau"), OTHERS("sonstiges");

		private final String name;

		private HairColor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Confession {
		PROTESTANT("evangelisch"), CATHOLIC("katholisch"), BUDDHISTIC(
				"buddistisch"), HINDU("hinduistisch"), MUSLIM("muslimisch"), JEWISH(
				"j�disch"), NO_CONFESSION("keine Konfession"), OTHERS("andere");

		private final String name;

		private Confession(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Gender {
		FEMALE("weiblich"), MALE("m�nnlich"), OTHERS("andere");

		private final String name;

		private Gender(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isSmoker() {
		return smoker;
	}

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
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

	public String toString() {
		return super.toString() + this.firstName + " " + this.lastName + " "
				+ this.eMail + " " + this.dateOfBirth + " " + this.smoker + " "
				+ this.height;
	}


}
