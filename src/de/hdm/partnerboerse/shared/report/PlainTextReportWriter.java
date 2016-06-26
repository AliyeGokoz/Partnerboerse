package de.hdm.partnerboerse.shared.report;

import java.util.Vector;

/**
 * Ein <code>ReportWriter</code>, der Reports mittels Plain Text formatiert. In
 * der Variable <code>reportText</code> wird das im Zielformat vorliegende
 * Ergebnis abgelegt. Nach anschließendem Aufrufen der entsprechenden
 * Prozessierungsmethode kann das Ergebnis mit <code>getReportText()</code>
 * ausgelesen werden.
 */

public class PlainTextReportWriter extends ReportWriter {

	// Diese Variable wird mit dem Ergebnis einer Umwandlung belegt.

	private String reportText = "";

	// Variable wird zurückgesetzt.
	public void resetReportText() {
		this.reportText = "";

	}

	/**
	 * Produzieren eines Header-Texts.
	 * 
	 * @return Text
	 */
	public String getHeader() {
		return "";
	}

	/**
	 * Produzieren eines Trailer-Texts.
	 * 
	 * @return Text
	 */

	public String getTrailer() {
		return "___________________________________________";

	}

	/**
	 * Übergebener Report prozessieren und im Zielformat ablegen. Später kann
	 * mittels <code>getReportText()</code> ein Auslesen des Ergebnisses
	 * erfolgen.
	 * 
	 * @param r
	 *            ist der zu prozessierende Report.
	 */

	@Override
	public void process(PartnerProposalsProfilesReport r) {
		reportText = processSimpleReport(r);
	}

	/**
	 * Übergebener Report prozessieren und im Zielformat ablegen. Später kann
	 * mittels <code>getReportText()</code> ein Auslesen des Ergebnisses
	 * erfolgen.
	 * 
	 * @param r
	 *            ist der zu prozessierende Report.
	 */

	@Override
	public void process(PartnerProposalsBySearchProfileReport r) {
		reportText = processCompositeReport(r);
	}

	// TODO
	private String processCompositeReport(CompositeReport r) {
		StringBuffer buffer = new StringBuffer();
		Vector<Report> subReports = r.getSubReports();
		for (Report report : subReports) {
			if (report instanceof SimpleReport) {
				SimpleReport simpleReport = (SimpleReport) report;
				buffer.append(processSimpleReport(simpleReport));
			} else if (report instanceof CompositeReport) {
				CompositeReport compositeReport = (CompositeReport) report;
				buffer.append(processCompositeReport(compositeReport));
			}
		}
		return buffer.toString();
	}

	// TODO
	private String processSimpleReport(SimpleReport r) {
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
		return result.toString();
	}

	/**
	 * Das Ergebnis der zuletzt aufgerufenen Prozessierungsmethode wird
	 * ausgelesen.
	 * 
	 * @return ein String der aus einem einfachen Text besteht.
	 */

	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();

	}

}
