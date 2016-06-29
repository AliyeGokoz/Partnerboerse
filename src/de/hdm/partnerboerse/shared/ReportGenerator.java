
package de.hdm.partnerboerse.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.SearchProfile;

@RemoteServiceRelativePath("report")
public interface ReportGenerator extends RemoteService {

	public void init() throws IllegalArgumentException;

	String renderPartnerProposalsByNotViewedProfilesReport();

	String renderPartnerProposalsBySearchProfilesReport(ArrayList<SearchProfile> searchProfiles);

}
