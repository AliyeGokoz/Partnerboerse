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

	private HairColor hairColor = HairColor.DEFAULT;

	private Confession confession = Confession.DEFAULT;

	private Gender gender = null;
	
	private Similarity similarity = null;

	private String hobby = null;

	private Sport sport = null;

	private Music music = null;

	private Film film = null;

	public enum HairColor {

		DEFAULT("nicht gesetzt"), BROWN("braun"), BLOND("blond"), BLACK("schwarz"), RED(
				"rot"), GREY("grau"), OTHERS("sonstiges");


		private final String name;

		private HairColor(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Confession {

		DEFAULT("nicht gesetzt"), PROTESTANT("evangelisch"), CATHOLIC("katholisch"), BUDDHISTIC(
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

	public enum Sport {
		SOCCER("Fußball"), BASKETBALL("Basketball"), HIKING("Wandern"), VOLLEYBALL("Volleyball"), FOLK_DANCE(
				"Volkstanz"), TENNIS("Tennis"), DIKING("Tauchen"), TAEKWONDO("Taekwondo"), ROPE_SKIPPING(
						"Seilspringen"), SWIMMING("Schwimmen"), RUGBY("Rugby"), OTHERS("andere");

		private final String name;

		private Sport(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Music {
		ROCK("Rock"), POP("Pop"), ELECTRO("Electro"), HOUSE("House"), FOLK_MUSIC("Volksmusik"), OTHERS("andere");

		private final String name;

		private Music(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	public enum Film {
		HORROR("Horror"), DRAMA("Drama"), COMEDY("Komödie"), ACTION("Action"), MYSTERY("Mystery"), FANTASY(
				"Fantasy"), ROMANCE("Romantik"), EROTIC("Erotik"), SCIENCE_FICTION("Science Fiction"), THRILLER(
						"Thriller"), WESTERN("Western"), SPORTS("Sport"), CARTOON("Zeichentrick"), OTHERS("andere");

		private final String name;

		private Film(String name) {
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

	public int getAge(){
		return (int) ((System.currentTimeMillis() - getDateOfBirth().getTime()) / 1000 / 60 / 60 / 24 / 365);
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

	public String getHobby() {
		return hobby;
	}
	
	public Similarity getSimilarity() {
		return similarity;
	}


	public void setSimilarity(Similarity similarity) {
		this.similarity = similarity;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public Sport getSport() {
		return sport;
	}

	public void setSport(Sport sport) {
		this.sport = sport;
	}

	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public Film getFilm() {
		return film;
	}

	public void setFilm(Film film) {
		this.film = film;
	}

	public String toString() {
		return super.toString() + this.firstName + " " + this.lastName + " " + this.eMail + " " + this.dateOfBirth + " "
				+ this.smoker + " " + this.height + " " + this.hairColor + " " + this.confession + " " + this.gender
				+ " " + this.hobby + " " + this.sport + " " + this.music + " " + this.film;
	}


}
