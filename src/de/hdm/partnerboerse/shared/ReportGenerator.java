
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;

public interface ReportGenerator extends RemoteService {
	
	public void init() throws IllegalArgumentException;

	PartnerProposalsByNotViewedProfilesReport createPartnerProposalsByNotViewedProfilesReport(Profile p);

	PartnerProposalsBySearchProfileReport createPartnerProposalsBySearchProfilesReport(SearchProfile s);

}

