package de.hdm.partnerboerse.shared.report;

import java.io.Serializable;
import java.util.Vector;

/**
 * Die Klasse <code>Row</code> stellt die Zeile einer Tabelle eines <code>SimpleReport</code>-Objekts dar. 
 * <code>Row</code> -Objekte implementieren das <code>Serializable</code>-Interface.
 * 
 * @see SimpleReport
 * @see Column
 */
public class Row implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 /**
	   * Speicherplatz für die Spalten der Zeile.
	   */
	
	private Vector<Column> columns = new Vector<Column>();

	/**
	   * Spalte hinzufügen
	   * 
	   * @param c das Spaltenobjekt
	   */
	
	public void addColumn(Column c) {
		this.columns.addElement(c);
	}

	 /**
	   * Eine benannten Spalte entfernen
	   * 
	   * @param c das zu entfernende Spaltenobjekt
	   */
	
	public void removeColumn(Column c) {
		this.columns.removeElement(c);
	}

	/**
	   * Sämtliche Spalten auslesen.
	   * 
	   * @return <code>Vector</code>-Objekt mit sämtlichen Spalten
	   */
	
	public Vector<Column> getColumns() {
		return this.columns;
	}

	  /** 
	   * Anzahl sämtlicher Spalten auslesen.
	   * 
	   * @return int , Anzahl der Spalten
	   */
	public int getNumColumns() {
		return this.columns.size();
	}

	 /**
	   *Einzelnes Spalten-Objekt auslesen.
	   * 
	   * @param i der Index der auszulesenden Spalte 
	   *       
	   * @return das gewünschte Spaltenobjekt.
	   */
	
	public Column getColumnAt(int i) {
		return this.columns.elementAt(i);
	}

}
