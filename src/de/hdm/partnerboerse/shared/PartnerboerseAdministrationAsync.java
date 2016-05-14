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
	
	void createSearchProfile(int id, int fromAge, int toAge, int fromHeight, int toHeight, HairColor hairColor,
			Gender gender, Confession confession, boolean smoker, AsyncCallback<SearchProfile> callback);

	void createBlocking(int id, Profile fromProfile, Profile toProfile,
			AsyncCallback<Blocking> callback);

	void createDescription(int id, String propertyName, String textualDescription, AsyncCallback<Description> callback);

	void createProperty(int id, String propertyName, String textualDescription,
			AsyncCallback<Property> callback);

	void createInfo(int id, String informationValue,
			AsyncCallback<Info> callback);

	void createSelection(int id, String propertyName, String textualDescription, AsyncCallback<Selection> callback);

	void createSimilarity(int id, Profile fromProfile, Profile toProfile,
			double similarityValue, AsyncCallback<Similarity> callback);

	void createVisitList(int id, Profile fromProfile, Profile toProfile,
			AsyncCallback<VisitList> callback);

	void createFavoritesList(int id, Profile fromProfile, Profile toProfile, AsyncCallback<FavoritesList> callback);

	/*
	 * save
	 */
	void save(Profile profile, AsyncCallback<Void> callback);

	void save(SearchProfile searchProfile, AsyncCallback<Void> callback);

	void save(Info info, AsyncCallback<Void> callback);

	void save(Property property, AsyncCallback<Void> callback);

	void save(Description description, AsyncCallback<Void> callback);

	void save(Selection selection, AsyncCallback<Void> callback);

	void save(Blocking blocking, AsyncCallback<Void> callback);

	void save(Similarity similarity, AsyncCallback<Void> callback);

	void save(VisitList visitList, AsyncCallback<Void> callback);

	void save(FavoritesList favoritesList, AsyncCallback<Void> callback);

	/*
	 * get
	 */
	void getAllProfiles(AsyncCallback<ArrayList<Profile>> callback);

	void getProfileByKey(int id, AsyncCallback<Profile> callback);

	void getAllSearchProfiles(AsyncCallback<ArrayList<SearchProfile>> callback);

	void getSearchProfileByKey(int id, AsyncCallback<SearchProfile> callback);

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

	void getAllDescriptions(AsyncCallback<ArrayList<Description>> callback);

	void getDescriptionByKey(int id, AsyncCallback<Description> callback);

	void getAllInfos(AsyncCallback<ArrayList<Info>> callback);

	void getInfoByKey(int id, AsyncCallback<Info> callback);

	/*
	 * delete
	 */

	void delete(Profile profile, AsyncCallback<Void> callback);

	void delete(SearchProfile searchProfile, AsyncCallback<Void> callback);

	void delete(Info info, AsyncCallback<Void> callback);

	void delete(Property property, AsyncCallback<Void> callback);

	void delete(Description description, AsyncCallback<Void> callback);

	void delete(Selection selection, AsyncCallback<Void> callback);

	void delete(Blocking blocking, AsyncCallback<Void> callback);

	void delete(Similarity similarity, AsyncCallback<Void> callback);

	void delete(VisitList visitList, AsyncCallback<Void> callback);

	void delete(FavoritesList favoritesList, AsyncCallback<Void> callback);

	
	
}
