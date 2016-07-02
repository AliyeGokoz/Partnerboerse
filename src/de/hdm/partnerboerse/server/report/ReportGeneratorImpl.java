package de.hdm.partnerboerse.server.report;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.partnerboerse.server.LoginServiceImpl;
import de.hdm.partnerboerse.server.PartnerboerseAdministrationImpl;
import de.hdm.partnerboerse.shared.LoginService;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.ReportGenerator;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.report.Column;
import de.hdm.partnerboerse.shared.report.CompositeParagraph;
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

	/**
	 * Initialisierung notwendiger Dienste
	 * 
	 * @author alenagerlinskaja
	 */
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

	/**
	 * Hinzufügen eines Impressums
	 * 
	 * @param r
	 * @param text
	 * @author alenagerlinskaja
	 */
	protected void addImprint(Report r, String text) {
		r.setImprint(new SimpleParagraph(text));
	}

	/**
	 * Umwandlung der von dem gegebenen Profil nicht besuchten Profile in einen
	 * SimpleReport
	 * 
	 * @param p
	 * @return Erstelltes Report Objekt
	 * @author alenagerlinskaja
	 */
	public PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport(Profile p) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		SearchProfile searchProfile = new SearchProfile();
		searchProfile.setName("Nicht besuchte Profile");
		searchProfile.setNoVisited(true);
		searchProfile.setProfile(p);

		ArrayList<Profile> profiles = this.administration.getBySearchProfile(searchProfile);

		return createReport(p, profiles, searchProfile.getName());
	}

	/**
	 * Umwandlung der Daten in CompositeReport Struktur
	 * 
	 * @param p
	 * @param searchProfiles
	 * @return Erstelltes Report Objekt
	 * @author alenagerlinskaja
	 */
	public PartnerProposalsBySearchProfileReport createPartnerProposalsBySearchProfilesReport(Profile p,
			ArrayList<SearchProfile> searchProfiles) {

		if (this.getPartnerboerseVerwaltung() == null) {
			return null;
		}

		PartnerProposalsBySearchProfileReport compositeReport = new PartnerProposalsBySearchProfileReport();
		if (searchProfiles == null || searchProfiles.isEmpty()) {
			searchProfiles = this.administration.getSearchProfileOf(p);
		}
		for (SearchProfile sp : searchProfiles) {
			ArrayList<Profile> profiles = this.administration.getProfilesOf(sp);

			PartnerProposalsProfilesReport createReport = createReport(p, profiles, sp.getName());

			compositeReport.addSubReport(createReport);
		}
		return compositeReport;
	}

	/**
	 * Umwandlung der Daten in SimpleReport Struktur
	 * 
	 * @param p
	 * @param profiles
	 * @param name
	 * @return Erstelltes Report Objekt
	 */
	private PartnerProposalsProfilesReport createReport(Profile p, ArrayList<Profile> profiles, String name) {
		PartnerProposalsProfilesReport result = new PartnerProposalsProfilesReport();

		this.addImprint(result, name);

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
			rowInfo.addParagraph(
					new SimpleParagraph("Nach-/Vorname:" + " " + t.getLastName() + "," + t.getFirstName()));
			rowInfo.addParagraph(new SimpleParagraph("Email:" + " " + t.geteMail()));
			rowInfo.addParagraph(new SimpleParagraph("Religion:" + " " + t.getConfession().getName()));
			rowInfo.addParagraph(new SimpleParagraph("Geburtsdatum:" + " " + sdf.format(t.getDateOfBirth())));

			ArrayList<Info> infos = this.administration.getInfoOf(t);
			for (Info i : infos) {
				String info = i.getInformationValue();

				if (i.getDescription() != null) {
					String descriptionValue = i.getDescription().getTextualDescriptionForProfile();
					rowInfo.addParagraph(new SimpleParagraph(descriptionValue.toString() + " " + info));

				} else if (i.getDescription() == null) {
					String selectionValue = i.getSelection().getTextualDescriptionForProfile();
					rowInfo.addParagraph(new SimpleParagraph(selectionValue.toString() + " " + info));

				}

			}

			profileRow.addColumn(new Column(rowInfo));
			profileRow.addColumn(new Column(
					new SimpleParagraph(new DecimalFormat("#.##").format(t.getSimilarityValue() * 100) + "%")));

			result.addRow(profileRow);
		}

		return result;
	}

	/**
	 * Erstellung und Generierung eines HTML Reports der nicht besuchten Profile
	 * 
	 * @author alenagerlinskaja 
	 * @return HTML-Code des generierten Reports als String
	 */
	@Override
	public String renderPartnerProposalsByNotViewedProfilesReport() {

		LoginServiceImpl service = new LoginServiceImpl();
		Profile currentProfile = service.getCurrentProfile();

		HTMLReportWriter htmlReportWriter = new HTMLReportWriter();
		PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport = createPartnerProposalsByNotViewedProfilesReport(
				currentProfile);
		htmlReportWriter.processSimpleReport(createPartnerProposalsByNotViewedProfilesReport);
		String reportText = htmlReportWriter.getReportText();

		return reportText;

	}

	/**
	 * Erstellung und Generierung eines HTML Reports anhand der gegebenen
	 * Suchprofile
	 * 
	 * @author alenagerlinskaja 
	 * @return HTML-Code des generierten Reports als String
	 */
	@Override
	public String renderPartnerProposalsBySearchProfilesReport(ArrayList<SearchProfile> searchProfiles) {

		LoginServiceImpl service = new LoginServiceImpl();
		Profile currentProfile = service.getCurrentProfile();

		HTMLReportWriter htmlReportWriter = new HTMLReportWriter();
		PartnerProposalsBySearchProfileReport createPartnerProposalsBySearchProfileReport = createPartnerProposalsBySearchProfilesReport(
				currentProfile, searchProfiles);
		htmlReportWriter.processCompositeReport(createPartnerProposalsBySearchProfileReport);
		String reportText = htmlReportWriter.getReportText();

		return reportText;

	}

}
