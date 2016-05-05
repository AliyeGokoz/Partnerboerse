package de.hdm.partnerboerse.server.report;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import de.hdm.partnerboerse.server.PartnerboerseAdministrationImpl;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.ReportGenerator;
import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	private PartnerboerseAdministration administration = null;

	public ReportGeneratorImpl() throws IllegalArgumentException {

	}
	// TODO init methode einfügen
	
	public void init() throws IllegalArgumentException{
		PartnerboerseAdministrationImpl a = new PartnerboerseAdministrationImpl();
	}

	@Override
	public PartnerProposalsByNotViewedProfilesReport createPartnerProposalsByNotViewedProfilesReport() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PartnerProposalsByNotViewedProfilesReport createPartnerProposalsBySearchProfilesReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
