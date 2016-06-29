package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse Property realisiert eine beispielhafte Profileigenschaft.
 *
 */

public class Property extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Name einer Eigenschaft des Profils.
	 */

	private String propertyName = "";

	/**
	 * Textuelle Beschreibung der Eigenschaft für ein {@link Profile}
	 */

	private String textualDescriptionForProfile = "";

	/**
	 * Textuelle Beschreibung der Eigenschaft für ein {@link SearchProfiles}
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
	 * Auslesen der textuellen Beschreibung einer Eigenschaft für ein Profil
	 * 
	 * @return Textuelle Beschreibung für ein Profil
	 */
	public String getTextualDescriptionForProfile() {
		return textualDescriptionForProfile;
	}

	/**
	 * Setzen der textuellen Beschreibung einer Eigenschaft für ein Profil
	 * 
	 * @param textualDescriptionForProfile
	 *            textuelle Beschreibung für ein Profil
	 */

	public void setTextualDescriptionForProfile(String textualDescriptionForProfile) {
		this.textualDescriptionForProfile = textualDescriptionForProfile;
	}

	/**
	 * Auslesen der textuellen Beschreibung einer Eigenschaft für ein Suchprofil
	 * 
	 * @return Textuelle Beschreibung für ein Suchprofil
	 */

	public String getTextualDescriptionForSearchProfile() {
		return textualDescriptionForSearchProfile;
	}

	/**
	 * Setzen der textuellen Beschreibung einer Eigenschaft für ein Suchprofil
	 * 
	 * @param textualDescriptionForSearchProfile
	 *            Textuelle Beschreibung für ein Suchprofil
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
