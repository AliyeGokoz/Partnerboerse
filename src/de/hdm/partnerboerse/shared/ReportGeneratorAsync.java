
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;

public interface ReportGeneratorAsync {

	void createPartnerProposalsByNotViewedProfilesReport(Profile p,
			AsyncCallback<PartnerProposalsByNotViewedProfilesReport> callback);

	void createPartnerProposalsBySearchProfilesReport(SearchProfile s,
			AsyncCallback<PartnerProposalsBySearchProfileReport> callback);

	void init(AsyncCallback<Void> callback);

}
