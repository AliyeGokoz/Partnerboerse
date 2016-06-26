package de.hdm.partnerboerse.server;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.partnerboerse.server.db.BlockingMapper;
import de.hdm.partnerboerse.server.db.DescriptionMapper;
import de.hdm.partnerboerse.server.db.FavoritesListMapper;
import de.hdm.partnerboerse.server.db.InfoMapper;
import de.hdm.partnerboerse.server.db.OptionMapper;
import de.hdm.partnerboerse.server.db.ProfileMapper;
import de.hdm.partnerboerse.server.db.SearchProfileMapper;
import de.hdm.partnerboerse.server.db.SelectionMapper;
import de.hdm.partnerboerse.server.db.SimilarityMapper;
import de.hdm.partnerboerse.server.db.VisitListMapper;
import de.hdm.partnerboerse.shared.PartnerboerseAdministration;
import de.hdm.partnerboerse.shared.bo.Blocking;
import de.hdm.partnerboerse.shared.bo.Description;
import de.hdm.partnerboerse.shared.bo.FavoritesList;
import de.hdm.partnerboerse.shared.bo.Info;
import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Profile;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Similarity;
import de.hdm.partnerboerse.shared.bo.VisitList;

public class PartnerboerseAdministrationImpl extends RemoteServiceServlet implements PartnerboerseAdministration {

	// Referenz auf die jeweiligen Mapper-Klassen

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Referenz auf den BlockingMapper, dieser ermöglicht den Datenbankzugriff
	 * auf die Blocking-Tabelle.
	 */
	private BlockingMapper blockingMapper = null;
	/**
	 * Referenz auf den DescriptionMapper, dieser ermöglicht den
	 * Datenbankzugriff auf die Description-Tabelle.
	 */
	private DescriptionMapper descriptionMapper = null;
	/**
	 * Referenz auf den FavoritesListMapper,
	 */
	private FavoritesListMapper favoritesListMapper = null;
	private InfoMapper infoMapper = null;
	private ProfileMapper profileMapper = null;
	private SearchProfileMapper searchProfileMapper = null;
	private SelectionMapper selectionMapper = null;
	private SimilarityMapper similarityMapper = null;
	private VisitListMapper visitListMapper = null;
	private OptionMapper optionMapper = null;

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
		this.optionMapper = OptionMapper.optionMapper();

	}

	// Create-Methoden

	/**
	 * Ein neues Nutzer-Profil wird angelegt, dies führt zu einer Speicherung
	 * des neuen Profils in der Datenbank.
	 */
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
		p.setHairColor(hairColor);
		p.setConfession(confession);
		p.setGender(gender);

		return this.profileMapper.insert(p);
	}

	@Override
	public SearchProfile createSearchProfile(int id, String name, int fromAge, int toAge, int fromHeight, int toHeight,
			HairColor hairColor, Gender gender, Confession confession, boolean smoker) {
		SearchProfile sp = new SearchProfile();
		// TODO Auto-generated method stub

		sp.setId(id);
		sp.setName(name);
		sp.setFromAge(fromAge);
		sp.setToAge(toAge);
		sp.setFromHeight(fromHeight);
		sp.setToHeight(toHeight);
		sp.setHairColor(hairColor);
		sp.setConfession(confession);
		sp.setGender(gender);

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
	public Selection createSelection(int id, String propertyName, String textualDescriptionForProfile,
			String textualDescriptionForSearchProfile) {
		Selection s = new Selection();

		s.setId(id);
		s.setPropertyName(propertyName);
		s.setTextualDescriptionForProfile(textualDescriptionForProfile);
		s.setTextualDescriptionForSearchProfile(textualDescriptionForSearchProfile);

		return this.selectionMapper.insert(s);
	}

	@Override
	public Description createDescription(int id, String propertyName, String textualDescriptionForProfile,
			String textualDescriptionForSearchProfile) {
		Description d = new Description();

		d.setId(id);
		d.setPropertyName(propertyName);
		d.setTextualDescriptionForProfile(textualDescriptionForProfile);
		d.setTextualDescriptionForSearchProfile(textualDescriptionForSearchProfile);

		return this.descriptionMapper.insert(d);
	}

	@Override
	public FavoritesList createFavoritesList(Profile fromProfile, Profile toProfile) {
		FavoritesList fl = new FavoritesList();

		fl.setFromProfile(fromProfile);
		fl.setToProfile(toProfile);

		return this.save(fl);
	}

	@Override
	public VisitList createVisitList(int id, Profile fromProfile, Profile toProfile) {
		VisitList vl = new VisitList();

		vl.setId(id);
		vl.setFromProfile(fromProfile);
		vl.setToProfile(toProfile);

		return this.visitListMapper.insert(vl);
	}

	@Override
	public Similarity createSimilarity(int id, Profile fromProfile, Profile toProfile, double similarityValue) {
		Similarity si = new Similarity();

		si.setId(id);
		si.setFromProfile(fromProfile);
		si.setToProfile(toProfile);
		si.setSimilarityValue(similarityValue);

		return this.similarityMapper.insert(si);
	}

	@Override
	public Blocking createBlocking(Profile fromProfile, Profile toProfile) {
		Blocking b = new Blocking();

		b.setFromProfile(fromProfile);
		b.setToProfile(toProfile);

		return this.blockingMapper.insert(b);
	}

	// Delete-Methoden
	/**
	 * Ein Profil + sämtliche Attribute werden aus der Datenbank gelöscht. Zudem
	 * werden abhängige Objekte vom Typ {@link SearchProfile}
	 */
	@Override
	public void delete(Profile profile) throws IllegalArgumentException {

		ArrayList<FavoritesList> favoritesLists = this.getFavoritesListsOf(profile);
		ArrayList<SearchProfile> searchProfiles = this.getSearchProfileOf(profile);
		ArrayList<Info> infos = this.getInfoOf(profile);
		ArrayList<Blocking> blockings = this.getBlockingsOf(profile);
		ArrayList<VisitList> visitLists = this.getVisitListsOf(profile);
		ArrayList<Similarity> similarities = similarityMapper.findWith(profile);

		if (favoritesLists != null) {
			for (FavoritesList favoritesList : favoritesLists) {
				this.delete(favoritesList);
			}
		}

		if (searchProfiles != null) {
			for (SearchProfile searchProfile : searchProfiles) {
				this.delete(searchProfile);
			}
		}
		if (infos != null) {
			for (Info info : infos) {
				this.delete(info);
			}
		}

		if (blockings != null) {
			for (Blocking blocking : blockings) {
				this.delete(blocking);
			}
		}
		if (visitLists != null) {
			for (VisitList visitList : visitLists) {
				this.delete(visitList);
			}
		}
		if (similarities != null) {
			for (Similarity similarity : similarities) {
				this.delete(similarity);
			}
		}

		this.profileMapper.delete(profile);
	}

	@Override
	public void delete(SearchProfile searchProfile) throws IllegalArgumentException {

		ArrayList<Info> infos = infoMapper.findBySearchProfile(searchProfile);
		for (Info info : infos) {
			this.infoMapper.delete(info);
		}

		this.searchProfileMapper.delete(searchProfile);
	}

	@Override
	public void delete(Info info) throws IllegalArgumentException {

		this.infoMapper.delete(info);
	}

	@Override
	public void delete(Description description) throws IllegalArgumentException {
		ArrayList<Info> infos = infoMapper.findByDescription(description);
		for (Info info : infos) {
			this.infoMapper.delete(info);
		}

		this.descriptionMapper.delete(description);

	}

	@Override
	public void delete(Selection selection) throws IllegalArgumentException {
		ArrayList<Info> infos = infoMapper.findBySelection(selection);
		for (Info info : infos) {
			this.infoMapper.delete(info);
		}

		this.selectionMapper.delete(selection);
	}

	@Override
	public void delete(Blocking blocking) throws IllegalArgumentException {

		this.blockingMapper.delete(blocking);
	}

	@Override
	public void delete(Similarity similarity) throws IllegalArgumentException {

		this.similarityMapper.delete(similarity);
	}

	@Override
	public void delete(VisitList visitList) throws IllegalArgumentException {

		this.visitListMapper.delete(visitList);
	}

	@Override
	public void delete(FavoritesList favoritesList) throws IllegalArgumentException {

		this.favoritesListMapper.delete(favoritesList);
	}

	// Get-Methoden
	/**
	 * Auslesen aller Profile aus der Datenbank.
	 */
	@Override
	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException {
		return this.profileMapper.findAll();
	}

	public ArrayList<Profile> getAllProfilesFiltered() {
		ArrayList<Profile> allProfiles = getAllProfiles();
		Profile currentProfile = LoginServiceImpl.loginService().getCurrentProfile();
		allProfiles.remove(currentProfile);
		ArrayList<Blocking> blockings = blockingMapper.findByProfile(currentProfile);
		for (Blocking blocking : blockings) {
			allProfiles.remove(blocking.getToProfile());
		}
		return allProfiles;
	}

	/**
	 * Auslesen eines Profils aus der Datenbank anhand dessen Profil-Kennung.
	 */
	@Override
	public Profile getProfileByKey(int id) throws IllegalArgumentException {
		return this.profileMapper.findByKey(id);
	}

	@Override
	public ArrayList<SearchProfile> getAllSearchProfiles() throws IllegalArgumentException {
		return this.searchProfileMapper.findAll();
	}

	@Override
	public SearchProfile getSearchProfileByKey(int id) throws IllegalArgumentException {
		return this.searchProfileMapper.findByKey(id);
	}

	/**
	 * Auslesen sämtlicher Infos aus der Datenbank.
	 */
	@Override
	public ArrayList<Info> getAllInfos() throws IllegalArgumentException {
		return this.infoMapper.findAll();
	}

	@Override
	public Info getInfoByKey(int id) throws IllegalArgumentException {
		return this.infoMapper.findByKey(id);
	}

	@Override
	public ArrayList<Description> getAllDescriptions() throws IllegalArgumentException {
		return this.descriptionMapper.findAll();
	}

	@Override
	public Description getDescriptionByKey(int id) throws IllegalArgumentException {
		return this.descriptionMapper.findByKey(id);
	}

	@Override
	public ArrayList<Selection> getAllSelections() throws IllegalArgumentException {
		return this.selectionMapper.findAll();
	}

	@Override
	public Selection getSelectionByKey(int id) throws IllegalArgumentException {
		return this.selectionMapper.findByKey(id);
	}

	@Override
	public ArrayList<Similarity> getAllSimilarities() throws IllegalArgumentException {
		return this.similarityMapper.findAll();

	}

	@Override
	public Similarity getSimilarityByKey(int id) throws IllegalArgumentException {
		return this.similarityMapper.findByKey(id);
	}

	/**
	 * Berechnung aller Ähnlichkeiten zwischen den entsprechenden Profilen.
	 */
	public void calculateAllSimilarities() {
		ArrayList<Profile> allProfiles = getAllProfiles();
		for (Profile one : allProfiles) {
			for (Profile two : allProfiles) {
				if (!one.equals(two)) {
					Similarity similarity = calculateSimilarity(one, two);
					save(similarity);
				}
			}
		}
	}

	public static void main(String[] args) {
		PartnerboerseAdministrationImpl partnerboerseAdministrationImpl = new PartnerboerseAdministrationImpl();
		partnerboerseAdministrationImpl.init();
		partnerboerseAdministrationImpl.calculateAllSimilarities();
	}

	/**
	 * Berechnung der Ähnlichkeit zwischen zwei Profilen.
	 */
	public Similarity calculateSimilarity(Profile one, Profile two) {
		int attributeCount = 10;
		int matches = 0;

		if (one.isSmoker() == two.isSmoker()) {
			matches++;
		}

		if (one.getConfession() == two.getConfession()) {
			matches++;
		}

		if (two.getAge() > one.getAge() - 3 && two.getAge() < one.getAge() + 3) {
			matches++;
		}

		if (one.getHairColor() == two.getHairColor()) {
			matches++;
		}

		if (two.getHeight() > one.getHeight() - 10 && two.getHeight() < one.getHeight() + 10) {
			matches++;
		}

		if ((one.getGender() == Gender.MALE && two.getGender() == Gender.FEMALE)
				|| (one.getGender() == Gender.FEMALE && two.getGender() == Gender.MALE)
				|| (one.getGender() == Gender.OTHERS && two.getGender() == Gender.OTHERS)) {
			matches++;
		}

		ArrayList<Info> infosOfOne = getInfoOf(one);
		ArrayList<Info> infosOfTwo = getInfoOf(two);
		for (Info infoOfOne : infosOfOne) {
			for (Info infoOfTwo : infosOfTwo) {
				if (infoOfOne.getDescription() != null && infoOfTwo.getDescription() != null) {
					if (infoOfOne.getDescription().getId() == infoOfTwo.getDescription().getId()) {
						attributeCount++;
						if (infoOfOne.getInformationValue().equals(infoOfTwo.getInformationValue())) {
							matches++;
						}
					}
				}
				if (infoOfOne.getSelection() != null && infoOfTwo.getSelection() != null) {
					if (infoOfOne.getSelection().getId() == infoOfTwo.getSelection().getId()) {
						attributeCount++;
						if (infoOfOne.getInformationValue().equals(infoOfTwo.getInformationValue())) {
							matches++;
						}
					}
				}
			}
		}

		double similarityValue = matches / (double) attributeCount;

		Similarity similarityFromDB = similarityMapper.findByFromAndTo(one, two);

		Similarity similarity = similarityFromDB == null ? new Similarity() : similarityFromDB;
		similarity.setFromProfile(one);
		similarity.setToProfile(two);
		similarity.setSimilarityValue(similarityValue);
		return similarity;
	}

	public void updateSimilarityForProfile(Profile profile) {
		ArrayList<Profile> allProfiles = getAllProfiles();
		for (Profile otherProfile : allProfiles) {
			Similarity similarityFrom = calculateSimilarity(profile, otherProfile);
			Similarity similarityTo = calculateSimilarity(otherProfile, profile);
			save(similarityFrom);
			save(similarityTo);
		}
	}

	/**
	 * Ende Ähnlichkeitsberechnung
	 */
	@Override
	public ArrayList<VisitList> getAllVisitLists() throws IllegalArgumentException {
		return this.visitListMapper.findAll();
	}

	@Override
	public VisitList getVisitListByKey(int id) throws IllegalArgumentException {
		return this.visitListMapper.findByKey(id);
	}

	@Override
	public ArrayList<Blocking> getAllBlockings() throws IllegalArgumentException {
		return this.blockingMapper.findAll();
	}

	@Override
	public Blocking getBlockingByKey(int id) throws IllegalArgumentException {
		return this.blockingMapper.findByKey(id);
	}

	@Override
	public ArrayList<FavoritesList> getAllFavoritesLists() throws IllegalArgumentException {
		return this.favoritesListMapper.findAll();
	}

	@Override
	public FavoritesList getFavoritesListByKey(int id) throws IllegalArgumentException {
		return this.favoritesListMapper.findByKey(id);
	}

	// Save-Methoden
	/**
	 * Speichern eines Profils in der Datenbank.
	 */
	@Override
	public void save(Profile profile) throws IllegalArgumentException {
		if (profile.getId() != 0) {
			profileMapper.update(profile);
		} else {
			profileMapper.insert(profile);
		}

		updateSimilarityForProfile(profile);
	}

	@Override
	public SearchProfile save(SearchProfile searchProfile) throws IllegalArgumentException {
		if (searchProfile.getId() != 0) {
			return searchProfileMapper.update(searchProfile);
		} else {
			return searchProfileMapper.insert(searchProfile);
		}
	}

	@Override
	public Info save(Info info) throws IllegalArgumentException {
		Info savedInfo;
		if (info.getId() != 0) {
			savedInfo = infoMapper.update(info);
		} else {
			savedInfo = infoMapper.insert(info);
		}

		if(info.getProfile() != null){
			updateSimilarityForProfile(info.getProfile());
		} else {
			updateSimilarityForProfile(info.getSearchProfile().getProfile());
		}

		return savedInfo;
	}

	@Override
	public void save(Description description) throws IllegalArgumentException {
		if (description.getId() != 0) {
			descriptionMapper.update(description);
		} else {
			descriptionMapper.insert(description);
		}

	}

	@Override
	public void save(Selection selection) throws IllegalArgumentException {
		if (selection.getId() != 0) {
			selectionMapper.update(selection);
		} else {
			selectionMapper.insert(selection);
		}
	}

	@Override
	public void save(Blocking blocking) throws IllegalArgumentException {
		if (blocking.getId() != 0) {
			blockingMapper.update(blocking);
		} else {
			blockingMapper.insert(blocking);
		}
	}

	@Override
	public void save(Similarity similarity) throws IllegalArgumentException {
		if (similarity.getId() != 0) {
			similarityMapper.update(similarity);
		} else {
			similarityMapper.insert(similarity);
		}
	}

	@Override
	public void save(VisitList visitList) throws IllegalArgumentException {
		if (visitList.getId() != 0) {
			visitListMapper.update(visitList);
		} else {
			visitListMapper.insert(visitList);
		}
	}

	@Override
	public FavoritesList save(FavoritesList favoritesList) throws IllegalArgumentException {
		if (favoritesList.getId() != 0) {
			return favoritesListMapper.update(favoritesList);
		} else {
			if (!favoritesListMapper.doFavoritesListEntryExist(favoritesList.getFromProfile(),
					favoritesList.getToProfile())) {
				return favoritesListMapper.insert(favoritesList);
			}
		}
		return favoritesList;
	}

	// Get-Methoden
	@Override
	public ArrayList<SearchProfile> getSearchProfileOf(Profile profile) {

		return searchProfileMapper.findByProfile(profile);
	}

	@Override
	public ArrayList<Info> getInfoOf(Profile profile) {

		return infoMapper.findByProfile(profile);
	}

	@Override
	public ArrayList<Info> getInfoOf(Selection selection) {

		return infoMapper.findBySelection(selection);
	}

	@Override
	public ArrayList<Profile> getProfilesOf(SearchProfile searchProfile) throws IllegalArgumentException {
		return this.profileMapper.findBySearchProfile(searchProfile);
	}

	@Override
	public ArrayList<Blocking> getBlockingsOf(Profile profile) throws IllegalArgumentException {
		return this.blockingMapper.findByProfile(profile);
	}

	@Override
	public ArrayList<FavoritesList> getFavoritesListsOf(Profile profile) throws IllegalArgumentException {
		return this.favoritesListMapper.findByProfile(profile);
	}

	@Override
	public ArrayList<VisitList> getVisitListsOf(Profile profile) throws IllegalArgumentException {
		return this.visitListMapper.findByProfile(profile);
	}

	@Override
	public ArrayList<Similarity> getSimilaritiesOf(Profile profile) throws IllegalArgumentException {
		return this.similarityMapper.findByProfile(profile);
	}

	@Override
	public ArrayList<Profile> getNotViewedProfiles(Profile vistingProfile) {
		return this.profileMapper.findNotViewedProfiles(vistingProfile);
	}

	@Override
	public ArrayList<Profile> getBySearchProfile(SearchProfile searchProfile) {
		return this.profileMapper.findBySearchProfile(searchProfile);
	}

	public Option createOption(int id, String option) {

		Option o = new Option();

		o.setId(id);
		o.setOption(option);

		return this.optionMapper.insert(o);
	}

	@Override
	public void delete(Option option) throws IllegalArgumentException {

		this.optionMapper.delete(option);

	}

	@Override
	public void save(Option option) throws IllegalArgumentException {

		if (option.getId() != 0) {
			optionMapper.update(option);
		} else {
			optionMapper.insert(option);
		}

	}

	@Override
	public ArrayList<Option> getOptionsOf(Selection selection) {

		return this.optionMapper.findBySelection(selection);

	}

	public boolean isOnFavoritesList(Profile profile) {
		Profile currentProfile = LoginServiceImpl.loginService().getCurrentProfile();
		return favoritesListMapper.doFavoritesListEntryExist(currentProfile, profile);
	}

	public boolean isBlocked(Profile profile) {
		Profile currentProfile = LoginServiceImpl.loginService().getCurrentProfile();
		return blockingMapper.doBlockingExist(currentProfile, profile);
	}

	@Override
	public ArrayList<Profile> getMostSimilarProfiles(Profile fromProfile) throws IllegalArgumentException {
		return this.profileMapper.findMostSimilarProfiles(fromProfile);
	}

	@Override
	public ArrayList<FavoritesList> getWithInFavoritesList(Profile with) throws IllegalArgumentException {
		return this.favoritesListMapper.findWith(with);
	}

	@Override
	public ArrayList<VisitList> getWithInVisitList(Profile with) throws IllegalArgumentException {
		return this.visitListMapper.findWith(with);
	}

	@Override
	public ArrayList<Blocking> getWithInBlocking(Profile with) throws IllegalArgumentException {
		return this.blockingMapper.findWith(with);
	}

	@Override
	public ArrayList<Similarity> getWithInSimilarity(Profile with)
			throws IllegalArgumentException {
		return this.similarityMapper.findWith(with);
	}

	@Override
	public void visit(Profile profile) {
		Profile currentProfile = LoginServiceImpl.loginService().getCurrentProfile();

		if (!visitListMapper.doVisitListExist(currentProfile, profile)) {
			VisitList visitList = new VisitList();
			visitList.setFromProfile(currentProfile);
			visitList.setToProfile(profile);
			visitListMapper.insert(visitList);
		}
	}

	public ArrayList<Info> getInfosOf(int searchProfileId) throws IllegalArgumentException {

		return this.infoMapper.findBySearchProfile(searchProfileId);
	}

	@Override
	public ArrayList<Info> getInfosOf(SearchProfile searchProfile)
			throws IllegalArgumentException {

		return this.infoMapper.findBySearchProfile(searchProfile);
	}

}
