package de.hdm.partnerboerse.shared;

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

	void createProfile(int id, String firstName, String lastName, Date dateOfBirth, String email, int height,
			boolean smoker, HairColor hairColor, Confession confession, Gender gender, AsyncCallback<Profile> callback);

	void createBlocking(AsyncCallback<Blocking> callback);

	void createDescription(int id, String propertyName, Property textualDescription,
			AsyncCallback<Description> callback);

	void createFavoriteList(AsyncCallback<FavoritesList> callback);

	void createProperty(int id, String propertyName, String textualDescription, AsyncCallback<Property> callback);

	void createInfo(int id, String informationValue, AsyncCallback<Info> callback);

	void createSelection(int id, String propertyName, Property textualDescription, AsyncCallback<Selection> callback);

	void createSimilarity(int id, Profile fromProfile, Profile toProfile, double similarityValue,
			AsyncCallback<Similarity> callback);

	void createVisitList(int id, Profile fromProfile, Profile toProfile, AsyncCallback<VisitList> callback);

	void createSearchProfile(int id, int height, HairColor hairColor, Gender gender, int age, Confession confession,
			boolean smoker, AsyncCallback<SearchProfile> callback);

	void createFavoritesList(int id, Profile fromProfile, Profile toProfile, AsyncCallback<FavoritesList> callback);

	void saveProfile(Profile p, AsyncCallback<Void> callback);

	void saveSearchProfile(SearchProfile s, AsyncCallback<Void> callback);

	void saveInfo(Info i, AsyncCallback<Void> callback);

	void saveProperty(Property p, AsyncCallback<Void> callback);

	void saveDescription(Description d, AsyncCallback<Void> callback);

	void saveSelection(Selection s, AsyncCallback<Void> callback);

	void saveBlocking(Blocking b, AsyncCallback<Void> callback);

	void saveSimilarity(Similarity s, AsyncCallback<Void> callback);

	void saveVisitList(VisitList v, AsyncCallback<Void> callback);

	void saveFavoritesList(FavoritesList f, AsyncCallback<Void> callback);

}
