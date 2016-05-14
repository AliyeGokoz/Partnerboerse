
package de.hdm.partnerboerse.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.*;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public interface PartnerboerseAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;

	// Create Methoden:

	public Profile createProfile(int id, String firstName, String lastName, Date dateOfBirth, String email, int height,
			boolean smoker, HairColor hairColor, Confession confession, Gender gender);

	public SearchProfile createSearchProfile(int id, int fromAge, int toAge, int fromHeight, int toHeight,
			HairColor hairColor, Gender gender, Confession confession, boolean smoker);

	public Info createInfo(int id, String informationValue);

	//public Property createProperty(int id, String propertyName, String textualDescription);

	public Selection createSelection(int id, String propertyName, String textualDescription);

	public Description createDescription(int id, String propertyName, String textualDescription);

	public FavoritesList createFavoritesList(int id, Profile fromProfile, Profile toProfile);

	public VisitList createVisitList(int id, Profile fromProfile, Profile toProfile);

	public Similarity createSimilarity(int id, Profile fromProfile, Profile toProfile, double similarityValue);

	public Blocking createBlocking(int id, Profile fromProfile, Profile toProfile);

	/*
	 * delete Methoden
	 */

	public void delete(Profile profile) throws IllegalArgumentException;

	public void delete(SearchProfile searchProfile) throws IllegalArgumentException;

	public void delete(Info info) throws IllegalArgumentException;

	//public void delete(Property property) throws IllegalArgumentException;

	public void delete(Description description) throws IllegalArgumentException;

	public void delete(Selection selection) throws IllegalArgumentException;

	public void delete(Blocking blocking) throws IllegalArgumentException;

	public void delete(Similarity similarity) throws IllegalArgumentException;

	public void delete(VisitList visitList) throws IllegalArgumentException;

	public void delete(FavoritesList favoritesList) throws IllegalArgumentException;

	/*
	 * Profile
	 */

	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException;

	public Profile getProfileByKey(int id) throws IllegalArgumentException;

	/*
	 * SearchProfile
	 */

	public ArrayList<SearchProfile> getAllSearchProfiles() throws IllegalArgumentException;

	public SearchProfile getSearchProfileByKey(int id) throws IllegalArgumentException;

	/*
	 * Infos
	 */

	public ArrayList<Info> getAllInfos() throws IllegalArgumentException;

	public Info getInfoByKey(int id) throws IllegalArgumentException;

	/*
	 * Description
	 */

	public ArrayList<Description> getAllDescriptions() throws IllegalArgumentException;

	public Description getDescriptionByKey(int id) throws IllegalArgumentException;

	/*
	 * Property
	 */

	//public ArrayList<Property> getAllProperties() throws IllegalArgumentException;

	//public Property getPropertyByKey(int id) throws IllegalArgumentException;

	/*
	 * Selection
	 */
	public ArrayList<Selection> getAllSelections() throws IllegalArgumentException;

	public Selection getSelectionByKey(int id) throws IllegalArgumentException;

	/*
	 * Similarity
	 */
	public ArrayList<Similarity> getAllSimilarities() throws IllegalArgumentException;

	public Similarity getSimilarityByKey(int id) throws IllegalArgumentException;

	/*
	 * VisitList
	 */
	public ArrayList<VisitList> getAllVisitLists() throws IllegalArgumentException;

	public VisitList getVisitListByKey(int id) throws IllegalArgumentException;

	/*
	 * Blocking
	 */

	public ArrayList<Blocking> getAllBlockings() throws IllegalArgumentException;

	public Blocking getBlockingByKey(int id) throws IllegalArgumentException;

	/*
	 * FavoritesList
	 */
	public ArrayList<FavoritesList> getAllFavoritesLists() throws IllegalArgumentException;

	public FavoritesList getFavoritesListByKey(int id) throws IllegalArgumentException;

	// save-Methoden

	public void save(Profile profile) throws IllegalArgumentException;

	public void save(SearchProfile searchProfile) throws IllegalArgumentException;

	public void save(Info info) throws IllegalArgumentException;

	//public void save(Property property) throws IllegalArgumentException;

	public void save(Description description) throws IllegalArgumentException;

	public void save(Selection selection) throws IllegalArgumentException;

	public void save(Blocking blocking) throws IllegalArgumentException;

	public void save(Similarity similarity) throws IllegalArgumentException;

	public void save(VisitList visitList) throws IllegalArgumentException;

	public void save(FavoritesList favoritesList) throws IllegalArgumentException;

}
