package de.hdm.partnerboerse.shared;


import java.util.ArrayList;
import java.util.Date;

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


public interface PartnerboerseAdministration {
	
	public void init() throws IllegalArgumentException;
	
	/*
	 * create Methoden
	 */
	
	public Profile createProfile(int id, String firstName, String lastName, 
			Date dateOfBirth, String email, int height, boolean smoker, HairColor hairColor, 
			Confession confession, Gender gender);
	
	public SearchProfile createSearchProfile(int id, int height, HairColor hairColor, Gender gender,
			int age, Confession confession, boolean smoker);
	
	public Info createInfo(int id, String informationValue);
	
	
	public Property createProperty(int id, String propertyName, String textualDescription);
	
	
	public Selection createSelection(int id, String propertyName, Property textualDescription);
	
	public Description createDescription(int id, String propertyName, Property textualDescription);
		
	public Blocking createBlocking(int id, Profile fromProfile, Profile toProfile);
	
	public FavoritesList createFavoritesList(int id, Profile fromProfile, Profile toProfile);
	
	public VisitList createVisitList(int id, Profile fromProfile, Profile toProfile);
	
	public Similarity createSimilarity(int id, Profile fromProfile, Profile toProfile, double similarityValue);
	
	
	/*
	 * save Methoden
	 */
	
	public void save(Profile p) throws IllegalArgumentException ;
	
	public void save(SearchProfile s) throws IllegalArgumentException;
	
	public void save(Info i) throws IllegalArgumentException;
	
	public void save(Property p) throws IllegalArgumentException;
	
	public void save(Description d) throws IllegalArgumentException;
	
	public void save(Selection s) throws IllegalArgumentException;
	
	public void save(Blocking b) throws IllegalArgumentException;
	
	public void save(Similarity s) throws IllegalArgumentException;
	
	public void save(VisitList v) throws IllegalArgumentException;
	
	public void save(FavoritesList f) throws IllegalArgumentException;
	
	/*
	 * delete Methoden
	 */
	
	public void deleteProfile(Profile p) throws IllegalArgumentException;
	
	public void deleteSearchProfile(SearchProfile s) throws IllegalArgumentException;
	
	public void deleteInfo(Info i) throws IllegalArgumentException;
	
	public void deleteProperty(Property p) throws IllegalArgumentException;
	
	public void deleteDescription(Description d) throws IllegalArgumentException;
	
	public void deleteSelection(Selection s) throws IllegalArgumentException;
	
	public void deleteBlocking(Blocking b) throws IllegalArgumentException;
	
	public void deleteSimilarity(Similarity s) throws IllegalArgumentException;
	
	public void deleteVisitList(VisitList v) throws IllegalArgumentException;
	
	public void deleteFavoritesList(FavoritesList f) throws IllegalArgumentException;
	
	/*
	 * 1) Synchronization: ArrayList is non-synchronized which means multiple threads
	 *  can work on ArrayList at the same time. For e.g. if one thread is performing an 
	 *  add operation on ArrayList, there can be an another thread performing remove 
	 *  operation on ArrayList at the same time in a multithreaded environment
	 *  while Vector is synchronized. This means if one thread is working on Vector, 
	 *  no other thread can get a hold of it. Unlike ArrayList, only one thread can 
	 *  perform an operation on vector at a time.
	 *  
	 *  get Methoden
	 */
	
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
	public ArrayList<Property> getAllProperties() throws IllegalArgumentException;
	
	public Property getPropertyByKey(int id) throws IllegalArgumentException;
	
	
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
	
	
	
 }
