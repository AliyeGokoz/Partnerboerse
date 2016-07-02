package de.hdm.partnerboerse.shared.bo;

import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

/**
 * Die Klasse <code>SearchProfile</code> realisiert ein beispielhaftes Suchprofil. Jedes
 * Suchprofil muss zu einem vorhanden {@link Profile} gehören.
 */

public class SearchProfile extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Name des Suchprofils.
	 */

	private String name = "";

	/**
	 * Altersuntergrenze
	 */

	private int fromAge = 0;

	/**
	 * Altersobergrenze
	 */

	private int toAge = 0;

	/**
	 * Minimale Körpergröße
	 */

	private int fromHeight = 0;

	/**
	 * Maximale Körpergröße
	 */

	private int toHeight = 0;

	/**
	 * Bevorzugte Haarfarbe
	 */

	private HairColor hairColor = null;

	/**
	 * Bevorzugte Konfession
	 */

	private Confession confession = null;

	/**
	 * Bevorzugtes Geschlecht
	 */

	private Gender gender = null;

	/**
	 * Zugehöriges Nutzerprofil
	 */

	private Profile profile;

	/**
	 * Nicht besuchtes Profil
	 */

	private boolean noVisited;

	/**
	 * Auslesen der Altersuntergrenze
	 */

	public int getFromAge() {
		return fromAge;
	}

	/**
	 * Setzen der Altersuntergrenze
	 */

	public void setFromAge(int fromAge) {
		this.fromAge = fromAge;
	}

	/**
	 * Auslesen der Altersobergrenze
	 */

	public int getToAge() {
		return toAge;
	}

	/**
	 * Setzen der Altersobergrenze
	 */

	public void setToAge(int toAge) {
		this.toAge = toAge;
	}

	/**
	 * Auslesen der minimalen Körpergröße
	 */

	public int getFromHeight() {
		return fromHeight;
	}

	/**
	 * Setzen der minimalen Körpergröße
	 */

	public void setFromHeight(int fromHeight) {
		this.fromHeight = fromHeight;
	}

	/**
	 * Auslesen der maximalen Körpergröße
	 */

	public int getToHeight() {
		return toHeight;
	}

	/**
	 * Setzen der maximalen Körpergröße
	 */

	public void setToHeight(int toHeight) {
		this.toHeight = toHeight;
	}

	/**
	 * Auslesen der bevorzugten Haarfarbe
	 */

	public HairColor getHairColor() {
		return hairColor;
	}

	/**
	 * Setzen der bevorzugten Haarfarbe
	 */

	public void setHairColor(HairColor hairColor) {
		this.hairColor = hairColor;
	}

	/**
	 * Auslesen der bevorzugten Konfession
	 */

	public Confession getConfession() {
		return confession;
	}

	/**
	 * Setzen der bevorzugten Konfession
	 */

	public void setConfession(Confession confession) {
		this.confession = confession;
	}

	/**
	 * Auslesen des bevorzugten Geschlechts
	 */

	public Gender getGender() {
		return gender;
	}

	/**
	 * Setzen des bevorzugten Geschlechts
	 */

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * Auslesen des Nutzerprofils
	 */

	public Profile getProfile() {
		return profile;
	}

	/**
	 * Setzen des Nutzerprofils
	 */

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * Auslesen des Namen des Suchprofils
	 */

	public String getName() {
		return name;
	}

	/**
	 * Setzen des Namens des Suchprofils
	 */

	public void setName(String name) {
		this.name = name;
	}

	/**
	* Prüfen, ob ein Profil noch nicht besucht wurde.
	*/

	public boolean isNoVisited() {
		return noVisited;
	}

	/**
	 * Setzen des nichtbesuchten Profils
	 */

	public void setNoVisited(boolean noVisited) {
		this.noVisited = noVisited;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */

	public String toString() {
		return super.toString() + " " + this.name + " " + this.fromAge + " " + this.toAge + " " + this.fromHeight + " "
				+ this.toHeight + " " + this.hairColor + " " + this.confession + " " + this.gender + " " + this.profile
				+ " " + this.noVisited;
	}

}
