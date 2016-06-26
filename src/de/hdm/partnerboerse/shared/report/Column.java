package de.hdm.partnerboerse.shared.report;

import java.io.Serializable;

/**
 * Spalte eines <code>Row</code>-Objekts. <code>Column</code>-Objekte
 * implementieren das <code>Serializable</code>-Interface. Somit sind Objekte
 * dieser Klasse über das Netzwerk übertragbar.
 */

public class Column implements Serializable {

	private static final long serialVersionUID = 1L;

	private Paragraph value;

	/**
	 * Implementierung eines No-Argument-Konstruktors, um serialisierbare
	 * Klassen mittels GWT-RPC zu transportieren.
	 */
	public Column() {
	}

	/**
	 * Konstruktor, der die Angabe eines Werts, also einen Spalteneintrag
	 * erzwingt.
	 * 
	 * @param s
	 *            ist der Wert, der durch das Column-Objekt dargestellt werden
	 *            soll.
	 * @see #Column()
	 */

	public Column(Paragraph s) {
		this.value = s;
	}

	/**
	 * Der Spaltenwert wird ausgelesen.
	 */

	public Paragraph getVaulue() {
		return this.value;
	}

	/**
	 * Der aktuelle Spaltenwert wird überschrieben.
	 * 
	 * @param newValue
	 *            ist der neue Spaltenwert
	 */

	public void setValue(Paragraph newValue) {
		this.value = newValue;
	}

	// @Override
	// public String toString() {
	// return this.value;
	// }
	//

}
