
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.report.PartnerProposalsBySearchProfileReport;
import de.hdm.partnerboerse.shared.report.PartnerProposalsProfilesReport;

public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void createPartnerProposalsBySearchProfilesReport(Profile p,
			AsyncCallback<PartnerProposalsBySearchProfileReport> callback);

	void createPartnerProposalsByNotViewedProfilesReport(Profile p,
			AsyncCallback<PartnerProposalsProfilesReport> callback);

	void renderPartnerProposalsByNotViewedProfilesReport(AsyncCallback<String> callback);

	void renderPartnerProposalsBySearchProfilesReport(AsyncCallback<String> callback);

}
