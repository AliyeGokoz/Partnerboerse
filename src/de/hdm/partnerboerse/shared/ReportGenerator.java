
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.report.PartnerProposalsProfilesReport;

@RemoteServiceRelativePath("report")
public interface ReportGenerator extends RemoteService {
	
	public void init() throws IllegalArgumentException;

	PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport(Profile p);

	PartnerProposalsProfilesReport createPartnerProposalsBySearchProfilesReport(Profile p, SearchProfile s);

	PartnerProposalsProfilesReport createPartnerProposalsReport(Profile p, SearchProfile s);

	String renderPartnerProposalsByNotViewedProfilesReport();

}

