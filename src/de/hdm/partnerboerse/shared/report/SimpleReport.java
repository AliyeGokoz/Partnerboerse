package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

/**
 * <p>
 * Diese Klasse ist ein einfacher Report, dessen Superklasse <code>Report</code> ist.
 * Sie enthält eine Tabelle mit Positionsdaten, die auf die Hilfsklassen <code>Row</code> 
 * und <code>Column</code> zurückgreift.
 * </p>
 * 
 * @see Row
 * @see Column
 * @author alenagerlinskaja
 */

public class SimpleReport extends Report {

	private static final long serialVersionUID = 1L;
	
	 /**
	   * Tabelle mit Positionsdaten. Die Tabelle wird zeilenweise in diesem
	   * <code>Vector</code> abgelegt.
	   * @author alenagerlinskaja
	   */
	private Vector<Row> table = new Vector<Row>();
	
	 /**
	   * Hinzufügen einer Zeile.
	   * 
	   * @param r die zu hinzufügende Zeile
	   * @author alenagerlinskaja
	   */
	public void addRow(Row r) {
		this.table.addElement(r);
	}

	 /**
	   * Entfernen einer Zeile.
	   * 
	   * @param r die zu entfernende Zeile.
	   * @author alenagerlinskaja
	   */
	
	public void removeRow(Row r) {
		this.table.removeElement(r);
	}

	/**
	   * Auslesen aller Positionsdaten.
	   * 
	   * @return die Tabelle der Positionsdaten
	   * @author alenagerlinskaja
	   */
	
	public Vector<Row> getRows() {
		return this.table;
	}

}
