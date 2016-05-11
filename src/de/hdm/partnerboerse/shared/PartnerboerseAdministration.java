package de.hdm.partnerboerse.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.*;


public interface PartnerboerseAdministration extends RemoteService {

	public void init() throws IllegalArgumentException;
	
	public Profile createProfile(String first, String last)
		      throws IllegalArgumentException;
	
	
	SearchProfile createSearchProfileFor(Profile p);
	
	public Blocking createBlocking() throws IllegalArgumentException;
	
	public Description createDescription() throws IllegalArgumentException;
	
	public FavoritesList createFavoriteList() throws IllegalArgumentException;
	
	public Property createProperty() throws IllegalArgumentException;
	
	public Info createInfo() throws IllegalArgumentException;
	
	public Selection createSelection() throws IllegalArgumentException;
	
	public Similarity createSimilarity() throws IllegalArgumentException;
	
	public VisitList createVisitList() throws IllegalArgumentException;
	
	
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


