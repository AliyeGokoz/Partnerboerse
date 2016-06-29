package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse VisitList realisiert eine beispielhafte Liste von Profilbesuchen .
 *
 */

public class VisitList extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Profil des eingeloggten Nutzers
	 */

	private Profile fromProfile = null;

	/**
	 * Profil, das besucht wird.
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
	 * Auslesen des Profils, dass besucht wird.
	 * 
	 * @return besuchtes Profil
	 */
	public Profile getToProfile() {
		return toProfile;
	}

	/**
	 * Setzen des besuchten Profils
	 * 
	 * @param toProfile
	 *            besuchtes Profil
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
