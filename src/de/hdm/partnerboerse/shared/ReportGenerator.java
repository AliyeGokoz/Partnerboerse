
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsProfilesReport;

@RemoteServiceRelativePath("report")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;

	PartnerProposalsProfilesReport createPartnerProposalsByNotViewedProfilesReport(Profile p);

	PartnerProposalsBySearchProfileReport createPartnerProposalsBySearchProfilesReport(Profile p);

	String renderPartnerProposalsByNotViewedProfilesReport();

	String renderPartnerProposalsBySearchProfilesReport();

}
