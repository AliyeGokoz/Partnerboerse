package de.hdm.partnerboerse.server.report;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.partnerboerse.shared.ReportGenerator;
import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;

@SuppressWarnings("serial")
public class ReportGeneratorImpl extends RemoteServiceServlet implements ReportGenerator {

	public ReportGeneratorImpl() {

	}
	//TODO init methode einfügen
	


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
