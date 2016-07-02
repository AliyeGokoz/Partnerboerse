package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse <code>Similarity</code> realisiert eine beispielhafte Liste von
 * Profil�hnlichkeiten.
 * 
 * @author Jana Kuch
 *
 */

public class Similarity extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Profil des eingeloggten Nutzers
	 */

	private Profile fromProfile = null;

	/**
	 * Profil, das zur �hnlichkeitsberechnung mit dem eingeloggten Profil
	 * herangezogen wird..
	 */

	private Profile toProfile = null;

	/**
	 * Berechneter �hnlichkeitswert
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
	 * Auslesen des Profils {@link Profile}, das zur �hnlichkeitsberechnung herangezogen wird.
	 * 
	 * @return Vergleichsprofil
	 */
	public Profile getToProfile() {
		return toProfile;
	}

	/**
	 * Setzen des Profils {@link Profile}, das zur �hnlichkeitsberechnung herangezogen wird.
	 * 
	 * @param toProfile
	 *            Vergleichsprofil
	 */
	public void setToProfile(Profile toProfile) {
		this.toProfile = toProfile;
	}

	/**
	 * Auslesen des �hnlichkeitswertes der beiden Profile
	 * 
	 * @return Aehnlichkeitswert
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
