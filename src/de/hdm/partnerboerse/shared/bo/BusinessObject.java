package de.hdm.partnerboerse.shared.bo;

import java.io.Serializable;

/**
 * Die Klasse <code>BusinessObject</code> stellt die Basisklasse f�r die im
 * Projekt relevanten Business-Object Klassen {@link Blocking},
 * {@link Description}, {@link FavoritesList}, {@link Info}, {@link Option},
 * {@link Profile}, {@link Property0}, {@link SearchProfile}, {@link Selection},
 * {@link Similarity} und {@link VisitList} dar.
 * 
 * @author Jana Kuch
 *
 */
public abstract class BusinessObject implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Identifikationsnummer einer Instanz der Klasse, diese muss eindeutig
	 * sein.
	 */

	private int id = 0;

	/**
	 * Auslesen der Identifikationsnummer
	 * 
	 * @return Identifikationsnummer
	 */

	public int getId() {
		return this.id;
	}

	/**
	 * Setzen der Identifikationsnummer
	 * 
	 * @param id
	 *            Identifikationsnummer
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanz.
	 */
	public String toString() {
		return this.getClass().getName() + " #" + this.id;
	}

	/**
	 * Inhaltlicher Vergleich der Objekte anhand der ID
	 */
	public boolean equals(Object o) {
		if (o != null && o instanceof BusinessObject) {
			BusinessObject bo = (BusinessObject) o;
			try {
				if (bo.getId() == this.id)
					return true;
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * Erstellung des Objekt-Hashcodes anhand der ID
	 */
	public int hashCode() {
		return this.id;
	}

}
