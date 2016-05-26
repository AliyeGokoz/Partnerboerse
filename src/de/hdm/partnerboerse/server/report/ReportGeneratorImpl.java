package de.hdm.partnerboerse.server.report;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.partnerboerse.client.ClientsideSettings;
import de.hdm.partnerboerse.server.LoginServiceImpl;
import de.hdm.partnerboerse.server.PartnerboerseAdministrationImpl;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.ReportGenerator;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Similarity;
import de.hdm.partnerboerse.shared.report.Column;
import de.hdm.partnerboerse.shared.report.CompositeParagraph;
import de.hdm.partnerboerse.shared.report.HTMLReportWriter;
import de.hdm.partnerboerse.shared.report.PartnerProposalsProfilesReport;
import de.hdm.partnerboerse.shared.report.Report;
import de.hdm.partnerboerse.shared.report.Row;
import de.hdm.partnerboerse.shared.report.SimpleParagraph;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private PartnerboerseAdministration administration = null;
	private LoginServiceAsync loginService = ClientsideSettings.getLoginService();


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
		r.setImprint(new SimpleParagraph("Imprint"));
	}

	@Override
	public PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport(Profile p) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		/*
		 * TODO Methode erstellen die alle ähnlichen Profile ausgibt
		 */
		// ArrayList<Profile> profiles =
		// this.administration.getMostSimilarProfiles(p);
		ArrayList<Profile> profiles = this.administration.getAllProfiles();

		return createReport(p, profiles);

	}

	@Override
	public PartnerProposalsProfilesReport createPartnerProposalsBySearchProfilesReport(Profile p, SearchProfile s) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		/*
		 * TODO Methode erstellen die alle ähnlichen Profile ausgibt
		 */
		// ArrayList<Profile> profiles =
		// this.administration.getMostSimilarProfiles(p);
		ArrayList<Profile> profiles = this.administration.getAllProfiles();

		return createReport(p, profiles);
	}

	@Override
	public PartnerProposalsProfilesReport createPartnerProposalsReport(Profile p, SearchProfile s) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		/*
		 * TODO Methode erstellen die alle ähnlichen Profile ausgibt
		 */
		// ArrayList<Profile> profiles =
		// this.administration.getMostSimilarProfiles(p);
		ArrayList<Profile> profiles = this.administration.getAllProfiles();

		return createReport(p, profiles);
	}

	private PartnerProposalsProfilesReport createReport(Profile p, ArrayList<Profile> profiles) {
		PartnerProposalsProfilesReport result = new PartnerProposalsProfilesReport();

		this.addImprint(result);

		result.setTitle("Partnervorschläge der nicht gesehenen Profile");

		result.setCreated(new Date());

		CompositeParagraph header = new CompositeParagraph();
		header.addParagraph(new SimpleParagraph(p.getLastName() + "," + p.getFirstName()));
		header.addParagraph(new SimpleParagraph(p.geteMail()));
		result.setHeaderData(header);

		Row headline = new Row();
		headline.addColumn(new Column("Profil"));
		// headline.addColumn(new Column("Nachname"));
		// headline.addColumn(new Column("Vorname"));
		// headline.addColumn(new Column("E-Mail"));
		headline.addColumn(new Column("Ähnlichkeitswert"));
		result.addRow(headline);

		for (Profile t : profiles) {
			Row profileRow = new Row();

			CompositeParagraph rowInfo = new CompositeParagraph();
			rowInfo.addParagraph(new SimpleParagraph(t.getLastName() + "," + t.getFirstName()));
			rowInfo.addParagraph(new SimpleParagraph(t.geteMail()));
			rowInfo.addParagraph(new SimpleParagraph(t.getConfession().getName()));
			// rowInfo.addParagraph(new
			// SimpleParagraph(t.getDateOfBirth().toString()));
			// rowInfo.addParagraph(new
			// SimpleParagraph(t.getHobby().getName()));
			// rowInfo.addParagraph(new SimpleParagraph(t.getFilm().getName()));
			rowInfo.addParagraph(new SimpleParagraph(t.getHairColor().getName()));
			// rowInfo.addParagraph(new
			// SimpleParagraph(t.getMusic().getName()));

			LoginServiceImpl service = new LoginServiceImpl();
			Profile currentProfile = service.getCurrentProfile();

			profileRow.addColumn(new Column(rowInfo.toString()));
			Similarity sim = this.administration.calculateSimilarity(currentProfile, t);
			profileRow.addColumn(new Column(Double.toString(sim.getSimilarityValue() * 100) + "%"));
			// profileRow.addColumn(new Column((int)
			// (t.getSimilarity().getSimilarityValue() * 100) + "%"));

			// profileRow.addColumn(new Column(t.getLastName()));
			// profileRow.addColumn(new Column(t.getFirstName()));
			// profileRow.addColumn(new Column(t.geteMail()));

			result.addRow(profileRow);
		}

		return result;
	}

	@Override
	public String renderPartnerProposalsByNotViewedProfilesReport() {
		System.out.println("Hallo");

		// LoginServiceImpl service = new LoginServiceImpl();
		// Profile currentProfile = service.getCurrentProfile();
		loginService.getCurrentProfile(new AsyncCallback<Profile>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Profile result) {
				HTMLReportWriter htmlReportWriter = new HTMLReportWriter();
				PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport = createPartnerProposalsByNotViewedProfilesReport(
						result);
				htmlReportWriter.process(createPartnerProposalsByNotViewedProfilesReport);
				//reportText = htmlReportWriter.getReportText();

			}
			
		});

	return "";

	}

}
