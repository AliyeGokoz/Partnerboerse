package de.hdm.partnerboerse.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Property;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Similarity;
import de.hdm.partnerboerse.shared.bo.VisitList;

public interface PartnerboerseAdministrationAsync {

	void init(AsyncCallback<Void> callback);

	void createProfile(int id, String firstName, String lastName,
			Date dateOfBirth, String email, int height, boolean smoker,
			HairColor hairColor, Confession confession, Gender gender,
			AsyncCallback<Profile> callback);

	void createBlocking(int id, Profile fromProfile, Profile toProfile,
			AsyncCallback<Blocking> callback);

	void createDescription(int id, String propertyName,
			Property textualDescription, AsyncCallback<Description> callback);

	void createProperty(int id, String propertyName, String textualDescription,
			AsyncCallback<Property> callback);

	void createInfo(int id, String informationValue,
			AsyncCallback<Info> callback);

	void createSelection(int id, String propertyName,
			Property textualDescription, AsyncCallback<Selection> callback);

	void createSimilarity(int id, Profile fromProfile, Profile toProfile,
			double similarityValue, AsyncCallback<Similarity> callback);

	void createVisitList(int id, Profile fromProfile, Profile toProfile,
			AsyncCallback<VisitList> callback);

	void getAllProfiles(AsyncCallback<ArrayList<Profile>> callback);

	void createSearchProfile(int id, int height, HairColor hairColor,
			Gender gender, int age, Confession confession, boolean smoker,
			AsyncCallback<SearchProfile> callback);

	void getProfileByKey(int id, AsyncCallback<Profile> callback);

	void createFavoritesList(int id, Profile fromProfile, Profile toProfile,
			AsyncCallback<FavoritesList> callback);

	void getAllSearchProfiles(AsyncCallback<ArrayList<SearchProfile>> callback);

	void saveProfile(Profile p, AsyncCallback<Void> callback);

	void getSearchProfileByKey(int id, AsyncCallback<SearchProfile> callback);

	void saveSearchProfile(SearchProfile s, AsyncCallback<Void> callback);

	void getAllInfos(AsyncCallback<ArrayList<Info>> callback);

	void saveInfo(Info i, AsyncCallback<Void> callback);

	void getInfoByKey(int id, AsyncCallback<Info> callback);

	void saveProperty(Property p, AsyncCallback<Void> callback);

	void getAllDescriptions(AsyncCallback<ArrayList<Description>> callback);

	void saveDescription(Description d, AsyncCallback<Void> callback);

	void getDescriptionByKey(int id, AsyncCallback<Description> callback);

	void saveSelection(Selection s, AsyncCallback<Void> callback);

	void saveBlocking(Blocking b, AsyncCallback<Void> callback);

	void saveSimilarity(Similarity s, AsyncCallback<Void> callback);

	void saveVisitList(VisitList v, AsyncCallback<Void> callback);

	void saveFavoritesList(FavoritesList f, AsyncCallback<Void> callback);

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
