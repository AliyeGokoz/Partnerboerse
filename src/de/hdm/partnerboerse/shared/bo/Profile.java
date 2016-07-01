package de.hdm.partnerboerse.shared.bo;

/**
 * Die Klasse Profile realisiert ein beispielhaftes Nutzerprofil.
 * Jedes Nutzerprofil kann mehrere {@link SearchProfile} anlegen.
 */

import java.util.Date;

public class Profile extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Vorname des Nutzers
	 */

	private String firstName = "";

	/**
	 * Nachname des Nutzers
	 */

	private String lastName = "";

	/**
	 * Geburtsdatum des Nutzers
	 */

	private Date dateOfBirth = null;

	/**
	 * Email-Adresse des Nutzers
	 */

	private String eMail = "";

	/**
	 * Körpergröße des Nutzers
	 */

	private int height = 0;

	/**
	 * Raucherstatus des Nutzers
	 */

	private boolean smoker = false;

	/**
	 * Haarfarbe des Nutzers
	 */

	private HairColor hairColor = HairColor.DEFAULT;

	/**
	 * Konfession des Nutzers
	 */

	private Confession confession = Confession.DEFAULT;

	/**
	 * Geschlecht des Nutzers
	 */

	private Gender gender = null;

	/**
	 * Sexuelle Orientierung des Nutzers
	 */

	private Orientation orientation = null;

	/**
	 * Ähnlichkeitswert des Nutzers mit einem anderen Profil
	 */

	private double similarityValue;

	/**
	 * Enumeration der Haarfarbe. Spezifaktion der Haarfarbe durch folgende
	 * Werte: nicht gesetzt, braun, blond, schwarz, rot, grau und sonstiges.
	 */

	public static enum HairColor {

		DEFAULT("nicht gesetzt"), BROWN("braun"), BLOND("blond"), BLACK("schwarz"), RED("rot"), GREY("grau"), OTHERS(
				"sonstiges");

		private final String name;

		private HairColor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Enumeration der Konfession. Spezifaktion der Konfektion durch folgende
	 * Werte: nicht gesetzt, evangelisch, katholisch, buddistisch, hinduistisch,
	 * muslimisch, jüdisch, keine Konfession, andere.
	 */

	public static enum Confession {

		DEFAULT("nicht gesetzt"), PROTESTANT("evangelisch"), CATHOLIC("katholisch"), BUDDHISTIC("buddistisch"), HINDU(
				"hinduistisch"), MUSLIM(
						"muslimisch"), JEWISH("jÃ¼disch"), NO_CONFESSION("keine Konfession"), OTHERS("andere");

		private final String name;

		private Confession(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Enumeration des Geschlechts. Spezifaktion des Geschlecht durch folgende
	 * Werte: männlich, weiblich und andere.
	 */

	public static enum Gender {
		FEMALE("weiblich"), MALE("mÃ¤nnlich"), OTHERS("andere");

		private final String name;

		private Gender(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Enumeration der sexuellen Orientierung. Spezifaktion der sexuellen
	 * Orientierung durch folgende Werte: Hetero, Bi und Homo.
	 */

	public static enum Orientation {
		HETERO("Hetero"), BI("Bi"), HOMO("Homo");

		private final String name;

		private Orientation(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * Auslesen des Vornamen.
	 */

	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setzen des Vornamen.
	 */

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Auslesen des Nachnamen.
	 */

	public String getLastName() {
		return lastName;
	}

	/**
	 * Setzen des Nachnamen.
	 */

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Auslesen des aktuellen Nutzeralters.
	 */

	public int getAge() {
		return (int) ((System.currentTimeMillis() - getDateOfBirth().getTime()) / 1000 / 60 / 60 / 24 / 365);
	}

	/**
	 * Auslesen des Geburtsdatums.
	 */

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Setzen des Geburtsdatums.
	 */

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Auslesen der Email-Adresse.
	 */

	public String geteMail() {
		return eMail;
	}

	/**
	 * Setzen der Email-Adresse.
	 */

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	/**
	 * Auslesen der Körpergröße.
	 */

	public int getHeight() {
		return height;
	}

	/**
	 * Setzen der Körpergröße.
	 */

	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * 
	 */

	public boolean isSmoker() {
		return smoker;
	}

	/**
	 * Setzen des Raucherstatus.
	 */

	public void setSmoker(boolean smoker) {
		this.smoker = smoker;
	}

	/**
	 * Auslesen der Haarfarbe.
	 */

	public HairColor getHairColor() {
		return hairColor;
	}

	/**
	 * Setzen der Haarfarbe.
	 */

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * Auslesen der Konfession.
	 */

	public Confession getConfession() {
		return confession;
	}

	/**
	 * Setzen des Konfession.
	 */

	public void setConfession(Confession confession) {
		this.confession = confession;
	}

	/**
	 * Auslesen des Geschlechts.
	 */

	public Gender getGender() {
		return gender;
	}

	/**
	 * Setzen des Geschlechts.
	 */

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Auslesen der sexuellen Orientierung.
	 */

	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * Setzen der sexuellen Orientierung.
	 */

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * Auslesen des Ähnlichkeitswertes.
	 */

	public double getSimilarityValue() {
		return similarityValue;
	}

	/**
	 * Setzen des Ähnlichkeitswertes.
	 */

	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */

	public String toString() {
		return super.toString() + this.firstName + " " + this.lastName + " " + this.eMail + " " + this.dateOfBirth + " "
				+ this.smoker + " " + this.height + " " + this.hairColor + " " + this.confession + " " + this.gender
				+ " " + this.orientation + " " + this.similarityValue;
	}

}
