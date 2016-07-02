package de.hdm.partnerboerse.shared.bo;

/**
 * 
 * Die Klasse <code>Option</code> realisiert eine beispielhafte
 * Auswahlmöglichkeit.
 *
 * @author Jana Kuch
 */

public class Option extends BusinessObject {

	private static final long serialVersionUID = 1L;

	/**
	 * Auswahlmöglichkeit für eine {@link Info}.
	 */

	private String option = "";

	/**
	 * Auswahl für eine {@link Info}
	 */

	private Selection selection;

	/**
	 * Auslesen der Auswahloption
	 * 
	 * @return Auswahloption
	 */

	public String getOption() {
		return option;
	}

	/**
	 * Setzen der Auswahloption
	 * 
	 * @param option
	 *            Auswahloption
	 */

	public void setOption(String option) {
		this.option = option;
	}

	/**
	 * Auslesen der Auswahl f�r eine {@link Info}
	 * 
	 * @return Auswahl
	 */

	public Selection getSelection() {
		return selection;
	}

	/**
	 * Setzen der Auswahl f�r eine {@link Info}
	 * 
	 * @param selection
	 *            Auswahl
	 */

	public void setSelection(Selection selection) {
		this.selection = selection;
	}

	/**
	 * Einfache textuelle Darstellung der jeweiligen Instanzen.
	 */

	public String toString() {
		return super.toString() + " " + this.option + " " + this.selection;
	}
}
