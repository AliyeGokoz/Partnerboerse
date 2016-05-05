package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

public class SimpleReport extends Report {

	private static final long serialVersionUID = 1L;
	private Vector<Row> table = new Vector<Row>();

	public void addRow(Row r) {
		this.table.addElement(r);
	}

	public void removeRow(Row r) {
		this.table.removeElement(r);
	}

	public Vector<Row> getRows() {
		return this.table;
	}

}
