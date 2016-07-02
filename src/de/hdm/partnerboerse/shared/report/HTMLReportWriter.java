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

/**
 * Diese Klasse ist ein<code>ReportWriter</code>, der Reports mittels HTML
 * formatiert.
 * 
 * @author alenagerlinskaja
 */

public class HTMLReportWriter extends ReportWriter {

	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * Diese Variable wird mit dem Ergebnis einer Umwandlung belegt.
	 */
	private String reportText = "";
	// private String reportTextTwo = "";

	/**
	 * Variable <code>reportText</code> zurücksetzen.
	 */
	public void resetReportText() {
		this.reportText = "";
	}

	/**
	 * Ein <code>Paragraph</code>-Objekts in HTML umwandeln
	 * 
	 * @param p
	 *            der Paragraph
	 * @return HTML-Text
	 * @author alenagerlinskaja
	 */
	public String paragraph2HTML(Paragraph p) {
		if (p instanceof CompositeParagraph) {
			return this.paragraph2HTML((CompositeParagraph) p);
		} else {
			return this.paragraph2HTML((SimpleParagraph) p);
		}
	}

	/**
	 * Ein <code>CompositeParagraph</code>-Objekts in HTML umwandeln.
	 * 
	 * @param p
	 *            der CompositeParagraph
	 * @return HTML-Text
	 * @author alenagerlinskaja
	 */
	public String paragraph2HTML(CompositeParagraph p) {
		StringBuffer result = new StringBuffer();

		for (int i = 0; i < p.getNumParagraphs(); i++) {
			result.append("<p>" + p.getParagraphAt(i) + "</p>");
		}

		return result.toString();
	}

	/**
	 * Ein <code>SimpleParagraph</code>-Objekt in HTML umwandeln.
	 * 
	 * @param p
	 *            der SimpleParagraph
	 * @return HTML-Text
	 * @author alenagerlinskaja
	 */
	public String paragraph2HTML(SimpleParagraph p) {
		return "<p>" + p.toString() + "</p>";

	}

	/**
	 * Produzieren des HTML-Header-Texts.
	 * 
	 * @return HTML-Text
	 */
	public String getHeader() {
		StringBuffer result = new StringBuffer();

		result.append("<html><head><title></title></head><body>");
		return result.toString();
	}

	/**
	 * Produzieren des HTML-Trailer-Texts.
	 * 
	 * @return HTML-Text
	 * @author alenagerlinskaja
	 */
	public String getTrailer() {
		return "</body></html>";
	}

	/**
	 * Übergebenen Report prozessieren und im Zielformat ablegen.
	 * 
	 * @param r
	 *            der zu prozessierende Report
	 * @author alenagerlinskaja
	 */
	@Override
	public String processCompositeReport(CompositeReport r) {
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
		
		reportText = buffer.toString();
		return reportText;
	}

	@Override
	public String processSimpleReport(SimpleReport r) {
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

		reportText = result.toString();
		return reportText;
	}

	/**
	 * Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode auslesen.
	 * 
	 * @return ein String im HTML-Format
	 * @author alenagerlinskaja
	 */

	public String getReportText() {
		return this.getHeader() + this.reportText + this.getTrailer();
	}
}