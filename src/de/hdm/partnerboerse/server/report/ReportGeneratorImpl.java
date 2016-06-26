package de.hdm.partnerboerse.server.report;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.partnerboerse.client.ClientsideSettings;
import de.hdm.partnerboerse.client.Partnerboerse;
import de.hdm.partnerboerse.server.LoginServiceImpl;
import de.hdm.partnerboerse.server.PartnerboerseAdministrationImpl;
import de.hdm.partnerboerse.shared.LoginService;
import de.hdm.partnerboerse.shared.LoginServiceAsync;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.ReportGenerator;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Similarity;
import de.hdm.partnerboerse.shared.report.Column;
import de.hdm.partnerboerse.shared.report.CompositeParagraph;
import de.hdm.partnerboerse.shared.report.CompositeReport;
import de.hdm.partnerboerse.shared.report.HTMLReportWriter;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsProfilesReport;
import de.hdm.partnerboerse.shared.report.Report;
import de.hdm.partnerboerse.shared.report.Row;
import de.hdm.partnerboerse.shared.report.SimpleParagraph;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

	private PartnerboerseAdministration administration = null;
	private LoginService loginService;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}

	// TODO init methode einfügen
	@Override
	public void init() throws IllegalArgumentException {
		PartnerboerseAdministrationImpl a = new PartnerboerseAdministrationImpl();
		a.init();
		this.administration = a;
		this.loginService = new LoginServiceImpl();
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
	public PartnerProposalsBySearchProfileReport createPartnerProposalsBySearchProfilesReport(Profile p) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		/*
		 * TODO Methode erstellen die alle ähnlichen Profile ausgibt
		 */

		PartnerProposalsBySearchProfileReport compositeReport = new PartnerProposalsBySearchProfileReport();
		ArrayList<SearchProfile> searchProfiles = this.administration.getSearchProfileOf(p);
		for (SearchProfile sp : searchProfiles) {
			ArrayList<Profile> profiles = this.administration.getProfilesOf(sp);

			PartnerProposalsProfilesReport createReport = createReport(p, profiles);

			compositeReport.addSubReport(createReport);
		}
		return compositeReport;
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
		headline.addColumn(new Column(new SimpleParagraph("Profil")));
		headline.addColumn(new Column(new SimpleParagraph("Ähnlichkeitswert")));
		result.addRow(headline);

		for (Profile t : profiles) {
			Row profileRow = new Row();

			CompositeParagraph rowInfo = new CompositeParagraph();
			rowInfo.addParagraph(new SimpleParagraph("Nach-/Vorname:" + " " + t.getLastName() + "," + t.getFirstName()));
			rowInfo.addParagraph(new SimpleParagraph("Email:" +" "+ t.geteMail()));
			rowInfo.addParagraph(new SimpleParagraph("Religion:"+" "+ t.getConfession().getName()));
			rowInfo.addParagraph(new SimpleParagraph("Geburtsdatum:" + " " + t.getDateOfBirth().toString()));

			ArrayList<Info> infos = this.administration.getInfoOf(t);
			for (Info i : infos) {
				String info = i.getInformationValue();
				
				if (i.getDescription() != null) {
					String descriptionValue = i.getDescription().getTextualDescription();
					rowInfo.addParagraph(new SimpleParagraph(descriptionValue.toString() + " " + info));
					
				} else if (i.getDescription() == null) {
					String selectionValue = i.getSelection().getTextualDescription();
					rowInfo.addParagraph(new SimpleParagraph(selectionValue.toString() + " " + info));

				}

			}

			LoginServiceImpl service = new LoginServiceImpl();
			Profile currentProfile = service.getCurrentProfile();

			profileRow.addColumn(new Column(rowInfo));
			Similarity sim = this.administration.calculateSimilarity(currentProfile, t);
			profileRow
					.addColumn(new Column(new SimpleParagraph(Double.toString(sim.getSimilarityValue() * 100) + "%")));

			result.addRow(profileRow);
		}

		return result;
	}

	@Override
	public String renderPartnerProposalsByNotViewedProfilesReport() {
		System.out.println("Hallo");

		LoginServiceImpl service = new LoginServiceImpl();
		Profile currentProfile = service.getCurrentProfile();

		HTMLReportWriter htmlReportWriter = new HTMLReportWriter();
		PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport = createPartnerProposalsByNotViewedProfilesReport(
				currentProfile);
		htmlReportWriter.process(createPartnerProposalsByNotViewedProfilesReport);
		String reportText = htmlReportWriter.getReportText();

		return reportText;

	}

	@Override
	public String renderPartnerProposalsBySearchProfilesReport() {
		System.out.println("Hallo");

		LoginServiceImpl service = new LoginServiceImpl();
		Profile currentProfile = service.getCurrentProfile();

		HTMLReportWriter htmlReportWriter = new HTMLReportWriter();
		PartnerProposalsBySearchProfileReport createPartnerProposalsByNotViewedProfilesReport = createPartnerProposalsBySearchProfilesReport(
				currentProfile);
		htmlReportWriter.process(createPartnerProposalsByNotViewedProfilesReport);
		String reportText = htmlReportWriter.getReportText();

		return reportText;

	}

}
