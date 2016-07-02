
package de.hdm.partnerboerse.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.SearchProfile;

/**
 * Asynchrones Interface zum Aufruf im 
 * clientseitigen Code 
 * @author alenagerlinskaja
 *
 */
public interface ReportGeneratorAsync {

	void init(AsyncCallback<Void> callback);

	void renderPartnerProposalsByNotViewedProfilesReport(AsyncCallback<String> callback);

	void renderPartnerProposalsBySearchProfilesReport(ArrayList<SearchProfile> searchProfiles,
			AsyncCallback<String> callback);

}
