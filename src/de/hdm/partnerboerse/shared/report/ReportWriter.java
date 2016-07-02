package de.hdm.partnerboerse.shared.report;

/**
 * <p>
 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu überführen.
 * </p>
 * 
 */
public abstract class ReportWriter {

    abstract String processSimpleReport(SimpleReport r);

	abstract String processCompositeReport(CompositeReport r);

}
