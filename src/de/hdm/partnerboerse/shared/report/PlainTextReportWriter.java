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
	public void process(PartnerProposalsProfilesReport r) {
		reportText = processSimpleReport(r);
	}

	@Override
	public void process(PartnerProposalsBySearchProfileReport r) {
		reportText = processCompositeReport(r);
	}

	private String processCompositeReport(CompositeReport r){
		StringBuffer buffer = new StringBuffer();
		Vector<Report> subReports = r.getSubReports();
		for (Report report : subReports) {
			if(report instanceof SimpleReport){
				SimpleReport simpleReport = (SimpleReport) report;
				buffer.append(processSimpleReport(simpleReport));
			} else if (report instanceof CompositeReport){
				CompositeReport compositeReport = (CompositeReport) report;
				buffer.append(processCompositeReport(compositeReport));
			}
		}
		return buffer.toString();
	}
	
	private String processSimpleReport(SimpleReport r){
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
	
	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();

	}

}
