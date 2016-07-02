package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

/**
 * Diese Klasse stellt eine Menge einzelner Absätze (
 * <code>SimpleParagraph</code>-Objekte) dar. Als Unterabschnitte werden sie in
 * einem <code>Vector</code> abgelegt und verwaltet.
 * @author Alena
 */

public class CompositeParagraph extends Paragraph {

	private static final long serialVersionUID = 1L;

	/**
	 * Vorbereitung des Speicherorts der Unterabschnitte.
	 */
	private final Vector<SimpleParagraph> subParagraphs = new Vector<>();

	/**
	 * Hinzufügen eines Unterabschnitts.
	 * 
	 * @param p
	 *            ist der hinzuzufügende Unterabschnitt.
	 * @author alenagerlinskaja
	 */

	public void addParagraph(SimpleParagraph p) {
		this.subParagraphs.addElement(p);
	}

	/**
	 * Entfernen eines Unterabschnitts.
	 * 
	 * @param p
	 *            ist der zu entfernende Unterabschnitt.
	 * @author alenagerlinskaja
	 */
	public void removeParagraph(SimpleParagraph p) {
		this.subParagraphs.remove(p);
	}

	/**
	 * Auslesen sämtlicher Unterabschnitte.
	 * 
	 * @return <code>Vector</code>, der sämtliche Unterabschnitte enthält.
	 * @author alenagerlinskaja
	 */

	public Vector<SimpleParagraph> getParagraph() {
		return this.subParagraphs;
	}

	/**
	 * Anzahl der Unterabschnitte auslesen.
	 * 
	 * @return Anzahl der Unterabschnitte.
	 * @author alenagerlinskaja
	 */
	public int getNumParagraphs() {
		return this.subParagraphs.size();

	}

	/**
	 * Einzelner Unterabschnitt auslesen.
	 * 
	 * @param i
	 *            ist der Index des gewünschten Unterabschnitts.
	 * 
	 * @return gewünschter Unterabschnitt.
	 * @author alenagerlinskaja
	 */

	public SimpleParagraph getParagraphAt(int i) {
		return this.subParagraphs.elementAt(i);
	}

	/**
	 * <code>CompositeParagraph</code> wird in in einen <code>String</code>
	 * umgewandelt.
	 * @author alenagerlinskaja
	 */

	@Override
	public String toString() {

		// Anlegen eines leeren Buffers
		StringBuffer b = new StringBuffer();

		// Schleife über alle Unterabschnitte
		for (int i = 0; i < subParagraphs.size(); i++) {
			SimpleParagraph p = this.subParagraphs.elementAt(i);

			// Umwandeln des Unterabschnitts in einen String. Anschließendes
			// anhängen an den Buffer.
			b.append(p.toString() + "\n");
		}

		// Buffer wird in einen String umgewandelt und zurückgegeben.
		return b.toString();

	}

}
