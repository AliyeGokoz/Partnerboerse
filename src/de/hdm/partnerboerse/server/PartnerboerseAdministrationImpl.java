package de.hdm.partnerboerse.server;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.partnerboerse.server.db.*;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.bo.*;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

public class PartnerboerseAdministrationImpl extends RemoteServiceServlet implements PartnerboerseAdministration {

	// Referenz auf die jeweiligen Mapper-Klassen

	private static final long serialVersionUID = 1L;
	private BlockingMapper blockingMapper = null;
	private DescriptionMapper descriptionMapper = null;
	private FavoritesListMapper favoritesListMapper = null;
	private InfoMapper infoMapper = null;
	private ProfileMapper profileMapper = null;
	private SearchProfileMapper searchProfileMapper = null;
	private SelectionMapper selectionMapper = null;
	private SimilarityMapper similarityMapper = null;
	private VisitListMapper visitListMapper = null;

	// No-Argument Constructor
	
	public PartnerboerseAdministrationImpl() throws IllegalArgumentException {

	}

	public void init() throws IllegalArgumentException {
		this.blockingMapper = BlockingMapper.blockingMapper();
		this.descriptionMapper = DescriptionMapper.descriptionMapper();
		this.favoritesListMapper = FavoritesListMapper.favoritesListMapper();
		this.infoMapper = InfoMapper.infoMapper();
		this.profileMapper = ProfileMapper.profileMapper();
		this.searchProfileMapper = SearchProfileMapper.searchProfileMapper();
		this.selectionMapper = SelectionMapper.selectionMapper();
		this.similarityMapper = SimilarityMapper.similarityMapper();
		this.visitListMapper = VisitListMapper.visitListMapper();

	}

	// Create-Methoden
	@Override
	public Profile createProfile(int id, String firstName, String lastName, Date dateOfBirth, String email, int height,
			boolean smoker, HairColor hairColor, Confession confession, Gender gender) {
		Profile p = new Profile();
		p.setId(id);
		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setDateOfBirth(dateOfBirth);
		p.seteMail(email);
		p.setHeight(height);
		p.isSmoker();
		/*
		 * HairColor, Confession, Gender fehlen noch. (enum)
		 */
		p.setId(1);
		
		return this.profileMapper.insert(p);
	}

	@Override
	public SearchProfile createSearchProfile(int id, int height, HairColor hairColor, Gender gender, int age,
			Confession confession, boolean smoker) {
		SearchProfile sp = new SearchProfile();
		
		sp.setId(id);
		
		/*
		 * FromAge, toAge etc. ist noch abzuklären.
		 * HairColor, Confession, Gender fehlen noch. (enum)
		 */
		
		return this.searchProfileMapper.insert(sp);
	}

	
	@Override
	public Info createInfo(int id, String informationValue) {
		Info i = new Info();
		
		i.setId(id);
		i.setInformationValue(informationValue);
		
		return this.infoMapper.insert(i);
	}

	@Override
	public Property createProperty(int id, String propertyName, String textualDescription) {
		return null;
	}

	@Override
	public Selection createSelection(int id, String propertyName, Property textualDescription) {
		return null;
	}

	@Override
	public Description createDescription(int id, String propertyName, Property textualDescription) {

		return null;
	}

	@Override
	public FavoritesList createFavoritesList(int id, Profile fromProfile, Profile toProfile) {

		return null;
	}

	@Override
	public VisitList createVisitList(int id, Profile fromProfile, Profile toProfile) {

		return null;
	}

	@Override
	public Similarity createSimilarity(int id, Profile fromProfile, Profile toProfile, double similarityValue) {

		return null;
	}

	@Override
	public Blocking createBlocking(int id, Profile fromProfile, Profile toProfile) {

		return null;
	}

	// Delete-Methoden
	@Override
	public void delete(Profile profile) throws IllegalArgumentException {

	}

	@Override
	public void delete(SearchProfile searchProfile) throws IllegalArgumentException {

	}

	@Override
	public void delete(Info info) throws IllegalArgumentException {

	}

	@Override
	public void delete(Property property) throws IllegalArgumentException {

	}

	@Override
	public void delete(Description description) throws IllegalArgumentException {

	}

	@Override
	public void delete(Selection selection) throws IllegalArgumentException {

	}

	@Override
	public void delete(Blocking blocking) throws IllegalArgumentException {

	}

	@Override
	public void delete(Similarity similarity) throws IllegalArgumentException {

	}

	@Override
	public void delete(VisitList visitList) throws IllegalArgumentException {

	}

	@Override
	public void delete(FavoritesList favoritesList) throws IllegalArgumentException {

	}

	// Get-Methoden
	@Override
	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Profile getProfileByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<SearchProfile> getAllSearchProfiles() throws IllegalArgumentException {
		return null;
	}

	@Override
	public SearchProfile getSearchProfileByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Info> getAllInfos() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Info getInfoByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Description> getAllDescriptions() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Description getDescriptionByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Property> getAllProperties() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Property getPropertyByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Selection> getAllSelections() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Selection getSelectionByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Similarity> getAllSimilarities() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Similarity getSimilarityByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<VisitList> getAllVisitLists() throws IllegalArgumentException {
		return null;
	}

	@Override
	public VisitList getVisitListByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Blocking> getAllBlockings() throws IllegalArgumentException {
		return null;
	}

	@Override
	public Blocking getBlockingByKey(int id) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<FavoritesList> getAllFavoritesLists() throws IllegalArgumentException {
		return null;
	}

	@Override
	public FavoritesList getFavoritesListByKey(int id) throws IllegalArgumentException {
		return null;
	}

	//Save-Methoden
	@Override
	public void save(Profile profile) throws IllegalArgumentException {

	}

	@Override
	public void save(SearchProfile searchProfile) throws IllegalArgumentException {

	}

	@Override
	public void save(Info info) throws IllegalArgumentException {

	}

	@Override
	public void save(Property property) throws IllegalArgumentException {

	}

	@Override
	public void save(Description description) throws IllegalArgumentException {

	}

	@Override
	public void save(Selection selection) throws IllegalArgumentException {

	}

	@Override
	public void save(Blocking blocking) throws IllegalArgumentException {

	}

	@Override
	public void save(Similarity similarity) throws IllegalArgumentException {

	}

	@Override
	public void save(VisitList visitList) throws IllegalArgumentException {

	}

	@Override
	public void save(FavoritesList favoritesList) throws IllegalArgumentException {

	}

}
