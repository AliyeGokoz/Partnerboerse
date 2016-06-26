package de.hdm.partnerboerse.shared.report;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Vector;


public class HTMLReportWriter extends ReportWriter {
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	private String reportText = "";
	// private String reportTextTwo = "";

	public void resetReportText() {
		this.reportText = "";
	}

	public String paragraph2HTML(Paragraph p) {
		if (p instanceof CompositeParagraph) {
			return this.paragraph2HTML((CompositeParagraph) p);
		} else {
			return this.paragraph2HTML((SimpleParagraph) p);
		}
	}

	public String paragraph2HTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getNumParagraphs(); i++) {
			result.append("<p>" + p.getParagraphAt(i) + "</p>");
		}

		return result.toString();
	}

	public String paragraph2HTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";

	}

	public String getHeader() {
		StringBuffer result = new StringBuffer();

		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}

	public String getTrailer() {
		return "</body></html>";
	}

	@Override
	public void process(PartnerProposalsProfilesReport r) {
		this.resetReportText();

		reportText = processSimpleReport(r);
	}

	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		PartnerProposalsProfilesReport notViewedReport = new PartnerProposalsProfilesReport();
		Row row = new Row();
		Column column = new Column();
		SimpleParagraph par = new SimpleParagraph("Text");
		column.setValue(par);
		row.addColumn(column);
		notViewedReport.addRow(row);
		// PartnerProposalsByNotViewedProfilesReport notViewedReportTwo = new
		// PartnerProposalsByNotViewedProfilesReport();
		// notViewedReportTwo.addRow(row);

		HTMLReportWriter htmlReportWriter = new HTMLReportWriter();
		// PartnerProposalsByNotViewedProfilesReport compositeReport = new
		// PartnerProposalsByNotViewedProfilesReport();
		SimpleParagraph simpleParagraph = new SimpleParagraph();
		simpleParagraph.setText("Hallo");
		notViewedReport.setImprint(simpleParagraph);
		notViewedReport.setTitle("Blargel");
		notViewedReport.setHeaderData(simpleParagraph);
		//
		// compositeReport.addSubReport(notViewedReport);
		// compositeReport.addSubReport(notViewedReportTwo);
		htmlReportWriter.process(notViewedReport);
		System.out.println(htmlReportWriter.reportText);
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("report.html"), "utf-8"))) {
			writer.write(htmlReportWriter.reportText);
		}
	}

	@Override
	public void process(PartnerProposalsBySearchProfileReport r) {
		this.resetReportText();

		reportText = processCompositeReport(r);
	}

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

	private String processSimpleReport(SimpleReport r) {
		StringBuffer result = new StringBuffer();

		result.append("<H1>" + r.getTitle() + "</H1>");
		result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
		result.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData()) + "</b></td>");
		result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint()) + "</td>");
		result.append("</tr><tr><td></td><td>" + sdf.format(r.getCreated()) + "</td></tr></table>");

		Vector<Row> rows = r.getRows();
		result.append("<table style=\"width:400px\">");

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			result.append("<tr>");
			for (int k = 0; k < row.getNumColumns(); k++) {
				if (i == 0) {
					result.append("<td style=\"background:silver;font-weight:bold\">"
							+ paragraph2HTML(row.getColumnAt(k).getVaulue()) + "</td>");
				} else {
					if (i > 1) {
						result.append("<td style=\"border-top:1px solid silver\">"
								+ paragraph2HTML(row.getColumnAt(k).getVaulue()) + "</td>");
					} else {
						result.append("<td valign=\"top\">" + paragraph2HTML(row.getColumnAt(k).getVaulue()) + "</td>");
					}
				}
			}
			result.append("</tr>");
		}

		result.append("</table>");

		return result.toString();
	}

	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}
}