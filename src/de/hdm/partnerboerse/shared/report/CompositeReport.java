package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

/**
 * Erstellung eines Reports aus der Zusammensetzung von Teil-Reports (vgl. <code>subReports</code>)
 * @author alenagerlinskaja
 *
 */

public class CompositeReport extends Report {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Vertritt ein Array mit variabler Länge von Teil-Report
	 * Objekten.
	 * @author alenagerlinskaja
	 */
	private Vector<Report> subReports = new Vector<Report>();
	
	/**
	 * Hinzufügen von Teil-Reports
	 * @param r 
	 * 		   der Teil-Report der hinzuzufügen ist
	 */
	public void addSubReport(Report r) {
		this.subReports.addElement(r);
	}
	
	/**
	 * Entfernen von Teil-Reports
	 * @param r
	 * 		   der Teil-Report der zu entfernen ist
	 */
	public void removeSubReport(Report r) {
		this.subReports.removeElement(r);
	}
	
	/**
	 * @see CompositeReport#subReports
	 * @return subReports
	 */
	public Vector<Report> getSubReports() {
		return subReports;
	}

}
