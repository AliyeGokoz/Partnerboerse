package de.hdm.partnerboerse.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * <code>Report</code> ist die Basisklasse aller Reports. Diese Klasse
 * implementiert das <code>Serializable</code>-Interface, damit Reports vom
 * Server an den Client gesendet werden können.
 * 
 * @see Report
 * @author alenagerlinskaja
 */

public class Report implements Serializable {

	public static final long serialVersionUID = 1L;

	// Stellt ein kleines Impressum für die hier realisierten Reports dar.
	private Paragraph imprint = null;

	// Stellt die Kopfdaten des Berichts dar.
	private Paragraph headerData = null;

	// (individueller) Titel des Berichts.
	private String title = "Love24/7";

	// Datum der Erstellung des Berichts.
	private Date created = new Date();

	/**
	 * Impressum auslesen.
	 * 
	 * @return Text des Impressums
	 * @author alenagerlinskaja
	 */

	public Paragraph getImprint() {
		return imprint;
	}

	/**
	 * Impressum setzen.
	 * 
	 * @param imprint
	 *            Text des Impressums
	 * @author alenagerlinskaja
	 */

	public void setImprint(Paragraph imprint) {
		this.imprint = imprint;
	}

	/**
	 * Kopfdaten auslesen.
	 * 
	 * @return Text der Kopfdaten.
	 * @author alenagerlinskaja
	 *
	 */

	public Paragraph getHeaderData() {
		return headerData;
	}

	/**
	 * Kopfdaten setzen.
	 * 
	 * @param headerData
	 *            Text der Kopfdaten.
	 */

	public void setHeaderData(Paragraph headerData) {
		this.headerData = headerData;
	}

	/**
	 * Berichtstitel auslesen.
	 * 
	 * @return Titeltext
	 * @author alenagerlinskaja
	 */

	public String getTitle() {
		return title;
	}

	/**
	 * Berichttitel setzen.
	 * 
	 * @param title
	 *            Titeltext
	 * @author alenagerlinskaja
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Erstellungsdatum auslesen.
	 * 
	 * @return Erstellungsdatum des Berichts
	 * @author alenagerlinskaja
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Erstellungsdatum setzen.
	 * 
	 * @param created
	 *            , ist der Zeitpunkt der Erstellung
	 * @author alenagerlinskaja
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

}
