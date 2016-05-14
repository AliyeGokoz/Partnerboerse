package de.hdm.partnerboerse.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.partnerboerse.server.PartnerboerseAdministrationImpl;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.ReportGenerator;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.report.Column;
import de.hdm.partnerboerse.shared.report.CompositeParagraph;
import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;
import de.hdm.partnerboerse.shared.report.Report;
import de.hdm.partnerboerse.shared.report.Row;
import de.hdm.partnerboerse.shared.report.SimpleParagraph;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private PartnerboerseAdministration administration = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}
	// TODO init methode einfügen

	public void init() throws IllegalArgumentException {
		PartnerboerseAdministrationImpl a = new PartnerboerseAdministrationImpl();
		a.init();
		this.administration = a;

	}

	protected PartnerboerseAdministration getPartnerboerseVerwaltung() {
		return this.administration;
	}

	protected void addImprint(Report r) {

	}

	@Override
	public PartnerProposalsByNotViewedProfilesReport createPartnerProposalsByNotViewedProfilesReport(Profile p) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		PartnerProposalsByNotViewedProfilesReport result = new PartnerProposalsByNotViewedProfilesReport();

		this.addImprint(result);

		result.setTitle("Partnervorschläge der nicht gesehenen Profile");

		result.setCreated(new Date());

		CompositeParagraph header = new CompositeParagraph();
		header.addParagraph(new SimpleParagraph(p.getLastName() + "," + p.getFirstName()));
		header.addParagraph(new SimpleParagraph(p.geteMail()));
		result.setHeaderData(header);

		Row headline = new Row();

		headline.addColumn(new Column("Nachname"));
		headline.addColumn(new Column("Vorname"));
		headline.addColumn(new Column("E-Mail"));
		headline.addColumn(new Column("Ähnlichkeitswert"));
		result.addRow(headline);

		/*
		 * TODO Methode erstellen die alle ähnlichen Profile ausgibt
		 */
		ArrayList<Profile> profiles = this.administration.getAllProfiles();

		for (Profile t : profiles) {
			Row profileRow = new Row();

			profileRow.addColumn(new Column(t.getLastName()));
			profileRow.addColumn(new Column(t.getLastName()));
			profileRow.addColumn(new Column(t.geteMail()));
			/*
			 * TODO similiarity wert errechnen
			 */
			// profileRow.addColumn(new
			// Column(this.administration.getSimilarityByKey(t.getId())));

			result.addRow(profileRow);
		}

		return result;

	}

	@Override
	public PartnerProposalsBySearchProfileReport createPartnerProposalsBySearchProfilesReport(SearchProfile s) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		PartnerProposalsBySearchProfileReport result = new PartnerProposalsBySearchProfileReport();

		result.setTitle("Partnervorschläge anhand des Suchprofils");
		this.addImprint(result);
		
		result.setCreated(new Date());
		
		CompositeParagraph header = new CompositeParagraph();
		header.addParagraph(new SimpleParagraph());
		header.addParagraph(new SimpleParagraph());
		result.setHeaderData(header);

		Row headline = new Row();

		headline.addColumn(new Column("Nachname"));
		headline.addColumn(new Column("Vorname"));
		headline.addColumn(new Column("E-Mail"));
		headline.addColumn(new Column("Ähnlichkeitswert"));
		result.addRow(headline);

		/*
		 * TODO Methode erstellen die alle ähnlichen Profile ausgibt
		 */
		ArrayList<Profile> profiles = this.administration.getAllProfiles();

		for (Profile t : profiles) {
			Row profileRow = new Row();

			profileRow.addColumn(new Column(t.getLastName()));
			profileRow.addColumn(new Column(t.getLastName()));
			profileRow.addColumn(new Column(t.geteMail()));
			/*
			 * TODO similiarity wert errechnen
			 */
			// profileRow.addColumn(new
			// Column(this.administration.getSimilarityByKey(t.getId())));

			result.addRow(profileRow);
		}
		
		return result;
	}

}
