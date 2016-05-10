package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

public class CompositeReport extends Report {

	private static final long serialVersionUID = 1L;

	private Vector<Report> subReports = new Vector<Report>();;

	public void addSubReport(Report r) {
		this.subReports.addElement(r);
	}

	public void removeSubReport(Report r) {
		this.subReports.removeElement(r);
	}

	public int getNumSubReports() {
		return this.subReports.size();
	}

	public Report getSubReportAt(int i) {
		return this.subReports.elementAt(i);
	}

}