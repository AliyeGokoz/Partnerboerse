package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse FavoritesList realisiert einen beispielhaften Merkzettel.
 *
 */

public class FavoritesList extends BusinessObject {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Profil des eingeloggten Nutzers, von diesem Profil geht die Speicherung
	 * auf einem Merkzettel aus.
	 */

	private Profile fromProfile = null;

	/**
	 * Das Profil, dass auf einen Merkzettel hinzugefügt wird.
	 */

	private Profile toProfile = null;

	/**
	 * Auslesen des Profils des eingeloggten Nutzers.
	 * 
	 * @return Profil des eingeloggten Nutzers
	 */

	public Profile getFromProfile() {
		return fromProfile;
	}

	/**
	 * Setzen des Profils des eingeloggten Nutzers.
	 * 
	 * @param fromProfile
	 *            Nutzerprofil
	 */

	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}

	/**
	 * Auslesen des Profils, dass zu einem Merkzettel hinzugefügt wird.
	 * 
	 * @return hinzuzufügendes Profil
	 */
	public Profile getToProfile() {
		return toProfile;
	}

	/**
	 * Setzen des zu einem Merkzettel hinzuzufügenden Profils
	 * 
	 * @param toProfile
	 *            hinzuzufügendes Profil
	 */

	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */
	public String toString() {
		return super.toString() + this.fromProfile + " " + this.toProfile;
	}

}
