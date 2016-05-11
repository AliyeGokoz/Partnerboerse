package de.hdm.partnerboerse.shared;

import java.util.ArrayList;

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

	void getAllProfiles(AsyncCallback<ArrayList<Profile>> callback);

	void getProfileByKey(int id, AsyncCallback<Profile> callback);

	void getAllSearchProfiles(AsyncCallback<ArrayList<SearchProfile>> callback);

	void getSearchProfileByKey(int id, AsyncCallback<SearchProfile> callback);

	void getAllInfos(AsyncCallback<ArrayList<Info>> callback);

	void getInfoByKey(int id, AsyncCallback<Info> callback);

	void getAllDescriptions(AsyncCallback<ArrayList<Description>> callback);

	void getDescriptionByKey(int id, AsyncCallback<Description> callback);

	void getAllProperties(AsyncCallback<ArrayList<Property>> callback);

	void getPropertyByKey(int id, AsyncCallback<Property> callback);

	void getAllSelections(AsyncCallback<ArrayList<Selection>> callback);

	void getSelectionByKey(int id, AsyncCallback<Selection> callback);

	void getAllSimilarities(AsyncCallback<ArrayList<Similarity>> callback);

	void getSimilarityByKey(int id, AsyncCallback<Similarity> callback);

	void getAllVisitLists(AsyncCallback<ArrayList<VisitList>> callback);

	void getVisitListByKey(int id, AsyncCallback<VisitList> callback);

	void getAllBlockings(AsyncCallback<ArrayList<Blocking>> callback);

	void getBlockingByKey(int id, AsyncCallback<Blocking> callback);

	void getAllFavoritesLists(AsyncCallback<ArrayList<FavoritesList>> callback);

	void getFavoritesListByKey(int id, AsyncCallback<FavoritesList> callback);

	void deleteProfile(Profile p, AsyncCallback<Void> callback);

	void deleteSearchProfile(SearchProfile s, AsyncCallback<Void> callback);

	void deleteInfo(Info i, AsyncCallback<Void> callback);

	void deleteProperty(Property p, AsyncCallback<Void> callback);

	void deleteDescription(Description d, AsyncCallback<Void> callback);

	void deleteSelection(Selection s, AsyncCallback<Void> callback);

	void deleteBlocking(Blocking b, AsyncCallback<Void> callback);

	void deleteSimilarity(Similarity s, AsyncCallback<Void> callback);

	void deleteVisitList(VisitList v, AsyncCallback<Void> callback);

	void deleteFavoritesList(FavoritesList f, AsyncCallback<Void> callback);

	
}
