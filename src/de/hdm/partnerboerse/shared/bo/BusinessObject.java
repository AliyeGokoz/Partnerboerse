package de.hdm.partnerboerse.shared.bo;

import java.io.Serializable;

/**
 * Die Klasse <code>BusinessObject</code> stellt die Basisklasse für die im
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

	private int id = 0;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String toString() {
		return this.getClass().getName() + " #" + this.id;
	}

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

	public int hashCode() {
		return this.id;
	}

}
