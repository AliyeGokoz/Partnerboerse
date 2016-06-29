package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse Similarity realisiert eine beispielhafte Liste von
 * Profilähnlichkeiten.
 *
 */

public class Similarity extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Profil des eingeloggten Nutzers
	 */

	private Profile fromProfile = null;

	/**
	 * Profil, das zur Ähnlichkeitsberechnung mit dem eingeloggten Profil
	 * herangezogen wird..
	 */

	private Profile toProfile = null;

	/**
	 * Berechneter Ähnlichkeitswert
	 */

	private double similarityValue = 0;

	/**
	 * Auslesen des Nutzerprofils
	 * 
	 * @return Nutzerprofil
	 */

	public Profile getFromProfile() {
		return fromProfile;
	}

	/**
	 * Setzen des Nutzerprofils
	 * 
	 * @param fromProfile
	 *            Nutzerprofil
	 */
	public void setFromProfile(Profile fromProfile) {
		this.fromProfile = fromProfile;
	}

	/**
	 * Auslesen des Profils, das zur Ähnlichkeitsberechnung herangezogen wird.
	 * 
	 * @return Vergleichsprofil
	 */
	public Profile getToProfile() {
		return toProfile;
	}

	/**
	 * Setzen des Profils, das zur Ähnlichkeitsberechnung herangezogen wird.
	 * 
	 * @param toProfile
	 *            Vergleichsprofil
	 */
	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	/**
	 * Auslesen des Ähnlichkeitswertes der beiden Profile
	 * 
	 * @return Ähnlichkeitswert
	 */
	public double getSimilarityValue() {
		return similarityValue;
	}

	/**
	 * Setzen des Ähnlichkeitswertes der beiden Profile
	 * 
	 * @param similarityValue
	 */
	public void setSimilarityValue(double similarityValue) {
		this.similarityValue = similarityValue;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */
	public String toString() {
		return super.toString() + this.fromProfile + " " + this.toProfile + " " + this.similarityValue;
	}

}
