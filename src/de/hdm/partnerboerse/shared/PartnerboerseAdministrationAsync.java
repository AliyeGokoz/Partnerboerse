package de.hdm.partnerboerse.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Property;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Similarity;
import de.hdm.partnerboerse.shared.bo.VisitList;

public interface PartnerboerseAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createProfile(String first, String last,
			AsyncCallback<Profile> callback);

	void createSearchProfileFor(Profile p, AsyncCallback<SearchProfile> callback);

	void createBlocking(AsyncCallback<Blocking> callback);

	void createDescription(AsyncCallback<Description> callback);

	void createFavoriteList(AsyncCallback<FavoritesList> callback);

	void createProperty(AsyncCallback<Property> callback);

	void createInfo(AsyncCallback<Info> callback);

	void createSelection(AsyncCallback<Selection> callback);

	void createSimilarity(AsyncCallback<Similarity> callback);

	void createVisitList(AsyncCallback<VisitList> callback);

	void getProfile(AsyncCallback<Profile> callback);

	void getBlocking(AsyncCallback<Blocking> callback);

	void getDescription(AsyncCallback<Description> callback);

	void getFavoritesList(AsyncCallback<FavoritesList> callback);

	void getProperty(AsyncCallback<Property> callback);

	void getSelection(AsyncCallback<Selection> callback);

	void getSimilarity(AsyncCallback<Similarity> callback);

	void getInfo(AsyncCallback<Info> callback);

}
