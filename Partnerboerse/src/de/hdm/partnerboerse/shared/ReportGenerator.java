package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;

public interface ReportGenerator extends RemoteService {
	
	public void init();

	PartnerProposalsByNotViewedProfilesReport createPartnerProposalsByNotViewedProfilesReport();

	PartnerProposalsByNotViewedProfilesReport createPartnerProposalsBySearchProfilesReport();

}
