package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse Blocking realisiert eine beispielhafte Kontaktsperre.
 *
 */

public class Blocking extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Profil des eingeloggten Nutzers, von diesem Profil geht die Kontaktsperre
	 * aus.
	 */

	private Profile fromProfile = null;

	/**
	 * Das zu sperrende Profil.
	 */

	private Profile toProfile = null;

	/**
	 * Auslesen des Profils des eingeloggten Nutzers.
	 * 
	 * @return Nutzerprofil
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
	 * Auslesen des zu sperrenden Profils
	 * 
	 * @return Profil das gesperrt wird
	 */

	public Profile getToProfile() {
		return toProfile;
	}

	/**
	 * Setzen des zu sperrenden Profils
	 * 
	 * @param toProfile
	 *            Profil das gesperrt wird
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
