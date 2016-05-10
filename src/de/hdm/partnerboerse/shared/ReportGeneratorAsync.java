
package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.report.PartnerProposalsByNotViewedProfilesReport;

public interface ReportGeneratorAsync {

	void createPartnerProposalsByNotViewedProfilesReport(
			AsyncCallback<PartnerProposalsByNotViewedProfilesReport> callback);

	void createPartnerProposalsBySearchProfilesReport(
			AsyncCallback<PartnerProposalsByNotViewedProfilesReport> callback);

	void init(AsyncCallback<Void> callback);

}
