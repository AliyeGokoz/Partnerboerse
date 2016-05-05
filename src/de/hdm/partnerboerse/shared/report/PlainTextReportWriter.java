package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

public class PlainTextReportWriter extends ReportWriter {

	private String reportText = "";

	public void resetReportText() {
		this.reportText = "";

	}

	public String getHeader() {
		return "";
	}

	public String getTrailer() {
		return "___________________________________________";

	}

	@Override
	public void process(PartnerProposalsByNotViewedProfilesReport r) {
		this.resetReportText();
		StringBuffer result = new StringBuffer();
		result.append(r.getTitle() + "\n\n");
		result.append(r.getHeaderData() + "\n");
		result.append("Erstellt am:" + r.getCreated().toString() + "\n");
		Vector<Row> rows = r.getRows();

		for (Row row : rows) {
			for (int k = 0; k < row.getNumColumns(); k++) {
				result.append(row.getColumnAt(k) + "\t; \t");
			}
			result.append("\n");
		}
		result.append("\n");
		result.append(r.getImprint() + "\n");
		this.reportText = result.toString();
	}

	@Override
	public void process(PartnerProposalsBySearchProfileReport r) {
		this.resetReportText();
		StringBuffer result = new StringBuffer();
		result.append(r.getTitle() + "\n\n");
		result.append(r.getHeaderData() + "\n");
		result.append("Erstellt am:" + r.getCreated().toString() + "\n");
		Vector<Row> rows = r.getRows();

		for (Row row : rows) {
			for (int k = 0; k < row.getNumColumns(); k++) {
				result.append(row.getColumnAt(k) + "\t; \t");
			}
			result.append("\n");
		}
		result.append("\n");
		result.append(r.getImprint() + "\n");
		this.reportText = result.toString();
	}

	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();

	}

}
