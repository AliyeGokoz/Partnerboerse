package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse <code>Property</code> realisiert eine beispielhafte
 * Profileigenschaft. Die Klasse hat die Subklassen {@link Description} und
 * {@link Selection}.
 * 
 * @author Jana Kuch
 *
 */

public class Property extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Name einer Eigenschaft.
	 */

	private String propertyName = "";

	/**
	 * Textuelle Beschreibung der Eigenschaft f�r ein {@link Profile}
	 */

	private String textualDescriptionForProfile = "";

	/**
	 * Textuelle Beschreibung der Eigenschaft f�r ein {@link SearchProfiles}
	 */

	private String textualDescriptionForSearchProfile = "";

	/**
	 * Auslesen der Eigenschaft
	 * 
	 * @return Eigenschaft
	 */

	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Setzen der Eigenschaft
	 * 
	 * @param propertyName
	 *            Eigenschaftsname
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Auslesen der textuellen Beschreibung einer Eigenschaft f�r ein {@link Profile}
	 * 
	 * @return Textuelle Beschreibung f�r ein Profil
	 */
	public String getTextualDescriptionForProfile() {
		return textualDescriptionForProfile;
	}

	/**
	 * Setzen der textuellen Beschreibung einer Eigenschaft f�r ein {@link Profile}
	 * 
	 * @param textualDescriptionForProfile
	 *            textuelle Beschreibung f�r ein Profil
	 */

	public void setTextualDescriptionForProfile(String textualDescriptionForProfile) {
		this.textualDescriptionForProfile = textualDescriptionForProfile;
	}

	/**
	 * Auslesen der textuellen Beschreibung einer Eigenschaft f�r ein {@link SearchProfile}
	 * 
	 * @return Textuelle Beschreibung f�r ein Suchprofil
	 */

	public String getTextualDescriptionForSearchProfile() {
		return textualDescriptionForSearchProfile;
	}

	/**
	 * Setzen der textuellen Beschreibung einer Eigenschaft f�r ein {@link SearchProfile}
	 * 
	 * @param textualDescriptionForSearchProfile
	 *            Textuelle Beschreibung f�r ein Suchprofil
	 */
	public void setTextualDescriptionForSearchProfile(String textualDescriptionForSearchProfile) {
		this.textualDescriptionForSearchProfile = textualDescriptionForSearchProfile;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */
	public String toString() {
		return super.toString() + " " + this.propertyName + " " + this.textualDescriptionForProfile + " "
				+ this.textualDescriptionForSearchProfile;
	}
}
