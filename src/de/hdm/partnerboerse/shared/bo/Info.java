package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse Info realisiert eine beispielhafte Profilinformation.
 *
 */

public class Info extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Informationswert der Profilinfo
	 */

	private String informationValue = "";

	/**
	 * Auswahl einer Profilinfo
	 */

	private Selection selection;

	/**
	 * Beschreibung einer Profilinfo
	 */

	private Description description;

	/**
	 * Nutzerprofil der anzulegenden Info
	 */

	private Profile profile;

	/**
	 * Suchprofil der anzulegenden Info
	 */

	private SearchProfile searchProfile;

	/**
	 * Auslesen des Informationswertes einer Profilinfo
	 * 
	 * @return Informationswert
	 */

	public String getInformationValue() {
		return informationValue;
	}

	/**
	 * Setzen eines Informationswertes einer Profilinfo
	 * 
	 * @param informationValue
	 *            Informationswert
	 */
	public void setInformationValue(String informationValue) {
		this.informationValue = informationValue;
	}

	/**
	 * Auslesen einer Auswahl einer Profilinfo
	 * 
	 * @return Auswahl
	 */

	public Selection getSelection() {
		return selection;
	}

	/**
	 * Setzen einer Auswahl einer Profilinfo.
	 * 
	 * @param selection
	 *            Auswahl
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	/**
	 * Auslesen einer textuellen Beschreibung einer Profilinfo
	 * 
	 * @return Beschreibung
	 */
	public Description getDescription() {
		return description;
	}

	/**
	 * Setzen einer textuellen Beschreibung einer Profilinfo
	 * 
	 * @param description
	 *            Beschreibung
	 */
	public void setDescription(Description description) {
		this.description = description;
	}

	/**
	 * Auslesen des Nutzerprofils einer Profilinfo
	 * 
	 * @return Nutzerprofil
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * Setzen des Nutzerprofils einer Profilinfo
	 * 
	 * @param profile
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * Auslesen des Suchprofils einer Profilinfo
	 * 
	 * @return Suchprofil
	 */

	public SearchProfile getSearchProfile() {
		return searchProfile;
	}

	/**
	 * Setzen des Suchprofils einer Profilinfo
	 * 
	 * @param searchProfile
	 *            Suchprofil
	 */

	public void setSearchProfile(SearchProfile searchProfile) {
		this.searchProfile = searchProfile;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */

	public String toString() {
		return super.toString() + " " + this.informationValue;
	}
}
