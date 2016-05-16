
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.report.PartnerProposalsProfilesReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;

public interface ReportGeneratorAsync {


	void init(AsyncCallback<Void> callback);

	void createPartnerProposalsBySearchProfilesReport(Profile p, SearchProfile s,
			AsyncCallback<PartnerProposalsProfilesReport> callback);

	void createPartnerProposalsByNotViewedProfilesReport(Profile p,
			AsyncCallback<PartnerProposalsProfilesReport> callback);

	void createPartnerProposalsReport(Profile p, SearchProfile s,
			AsyncCallback<PartnerProposalsProfilesReport> callback);

}
