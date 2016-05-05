package de.hdm.partnerboerse.shared.bo;

public class SearchProfile extends BusinessObject{

	private static final long serialVersionUID = 1L;

	private int fromAge = 0;
	
	private int toAge = 0;
	
	private int fromHeight = 0;
	
	private int toHeight = 0;

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
