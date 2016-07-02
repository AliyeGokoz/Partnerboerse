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
import de.hdm.partnerboerse.shared.bo.Profile.Orientation;
import de.hdm.partnerboerse.shared.bo.SearchProfile;
import de.hdm.partnerboerse.shared.bo.Selection;
import de.hdm.partnerboerse.shared.bo.Similarity;
import de.hdm.partnerboerse.shared.bo.VisitList;

public class PartnerboerseAdministrationImpl extends RemoteServiceServlet
		implements PartnerboerseAdministration {

	/**
	 * Dies ist die Implementierungsklasse des Interfaces
	 * <code>PartnerboerseAdministration</code>.
	 * 
	 * 
	 * @ author Carolin Elsner
	 * @ author Jana Kuch
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
	 * Referenz auf den FavoritesListMapper, dieser ermöglicht den
	 * Datenbankzugriff auf die FavoritesList-Tabelle
	 */
	private FavoritesListMapper favoritesListMapper = null;

	/**
	 * Referenz auf den InfoMapper, dieser ermöglicht den Datenbankzugriff auf
	 * die Info-Tabelle.
	 */
	private InfoMapper infoMapper = null;

	/**
	 * Referenz auf den ProfileMapper, dieser ermöglicht den Datenbankzugriff
	 * auf die Profile-Tabelle.
	 */
	private ProfileMapper profileMapper = null;

	/**
	 * Referenz auf den SearchProfileMapper, dieser ermöglicht den
	 * Datenbankzugriff auf die SearchProfile-Tabelle.
	 */
	private SearchProfileMapper searchProfileMapper = null;

	/**
	 * Referenz auf den SelectionMapper, dieser ermöglicht den Datenbankzugriff
	 * auf die Selection-Tabelle.
	 */
	private SelectionMapper selectionMapper = null;

	/**
	 * Referenz auf den SimilarityMapper, dieser ermöglicht den Datenbankzugriff
	 * auf die Similarity-Tabelle.
	 */
	private SimilarityMapper similarityMapper = null;

	/**
	 * Referenz auf den VisitListMapper, dieser ermöglicht den Datenbankzugriff
	 * auf die VisitList-Tabelle.
	 */
	private VisitListMapper visitListMapper = null;

	/**
	 * Referenz auf den OptionMapper, dieser ermöglicht den Datenbankzugriff auf
	 * die Option-Tabelle.
	 */
	private OptionMapper optionMapper = null;

	/**
	 * No-Argument Constructor muss vorhanden sein.
	 * 
	 * @throws IllegalArgumentException
	 */

	public PartnerboerseAdministrationImpl() throws IllegalArgumentException {

	}

	/**
	 * Instanzenmethode zur Initialisierung der jeweiligen Instanzen. Für jede
	 * Instanz der <code>PartnerboerseAdministrationImpl</code> muss diese
	 * Methode aufgerufen werden.
	 * 
	 */
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

	/**
	 * Ein neues Nutzer-Profil wird angelegt, dies führt zu einer Speicherung
	 * des neuen Profils in der Datenbank.
	 */
	@Override
	public Profile createProfile(int id, String firstName, String lastName,
			Date dateOfBirth, String email, int height, boolean smoker,
			HairColor hairColor, Confession confession, Gender gender,
			Orientation orientation) {
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
		p.setOrientation(orientation);

		return this.profileMapper.insert(p);
	}

	/**
	 * Ein neues Such-Profil wird angelegt, dies führt zu einer Speicherung des
	 * neuen Such-Profils in der Datenbank.
	 */
	@Override
	public SearchProfile createSearchProfile(int id, String name, int fromAge,
			int toAge, int fromHeight, int toHeight, HairColor hairColor,
			Gender gender, Confession confession, boolean smoker) {
		SearchProfile sp = new SearchProfile();

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

	/**
	 * Eine neue Info wird angelegt, dies führt zu einer Speicherung der neuen
	 * Info in der Datenbank.
	 */
	@Override
	public Info createInfo(int id, String informationValue) {
		Info i = new Info();

		i.setId(id);
		i.setInformationValue(informationValue);

		return this.infoMapper.insert(i);
	}

	/**
	 * Eine neue Auswahl wird angelegt, dies führt zu einer Speicherung der
	 * neuen Auswahl in der Datenbank.
	 */
	@Override
	public Selection createSelection(int id, String propertyName,
			String textualDescriptionForProfile,
			String textualDescriptionForSearchProfile) {
		Selection s = new Selection();

		s.setId(id);
		s.setPropertyName(propertyName);
		s.setTextualDescriptionForProfile(textualDescriptionForProfile);
		s.setTextualDescriptionForSearchProfile(textualDescriptionForSearchProfile);

		return this.selectionMapper.insert(s);
	}

	/**
	 * Eine neue Beschreibung wird angelegt, dies führt zu einer Speicherung der
	 * neuen Beschreibung in der Datenbank.
	 */
	@Override
	public Description createDescription(int id, String propertyName,
			String textualDescriptionForProfile,
			String textualDescriptionForSearchProfile) {
		Description d = new Description();

		d.setId(id);
		d.setPropertyName(propertyName);
		d.setTextualDescriptionForProfile(textualDescriptionForProfile);
		d.setTextualDescriptionForSearchProfile(textualDescriptionForSearchProfile);

		return this.descriptionMapper.insert(d);
	}

	/**
	 * Ein neuer Merkzettel wird angelegt, dies führt zu einer Speicherung des
	 * neuen Merkzettels in der Datenbank.
	 */
	@Override
	public FavoritesList createFavoritesList(Profile fromProfile,
			Profile toProfile) {
		FavoritesList fl = new FavoritesList();

		fl.setFromProfile(fromProfile);
		fl.setToProfile(toProfile);

		return this.save(fl);
	}

	/**
	 * Eine neue Besuchsliste wird angelegt, dies führt zu einer Speicherung der
	 * neuen Besuchsliste in der Datenbank.
	 */

	@Override
	public VisitList createVisitList(int id, Profile fromProfile,
			Profile toProfile) {
		VisitList vl = new VisitList();

		vl.setId(id);
		vl.setFromProfile(fromProfile);
		vl.setToProfile(toProfile);

		return this.visitListMapper.insert(vl);
	}

	/**
	 * Ein neues Ähnlichkeitsmaß wird angelegt. Dies führt zu einer Speicherung
	 * in der Datenbank.
	 */
	@Override
	public Similarity createSimilarity(int id, Profile fromProfile,
			Profile toProfile, double similarityValue) {
		Similarity si = new Similarity();

		si.setId(id);
		si.setFromProfile(fromProfile);
		si.setToProfile(toProfile);
		si.setSimilarityValue(similarityValue);

		return this.similarityMapper.insert(si);
	}

	/**
	 * Eine neue Kontaktsperre wird angelegt, dies führt zu einer Speicherung
	 * der neuen Kontaktsperre in der Datenbank.
	 */
	@Override
	public Blocking createBlocking(Profile fromProfile, Profile toProfile) {
		Blocking b = new Blocking();

		b.setFromProfile(fromProfile);
		b.setToProfile(toProfile);

		return this.blockingMapper.insert(b);
	}

	/**
	 * Ein Profil + sämtliche Attribute werden aus der Datenbank gelöscht. Zudem
	 * werden abhängige Objekte vom Typ {@link SearchProfile} gelöscht.
	 */
	@Override
	public void delete(Profile profile) throws IllegalArgumentException {

		ArrayList<FavoritesList> favoritesLists = this
				.getFavoritesListsOf(profile);
		ArrayList<SearchProfile> searchProfiles = this
				.getSearchProfileOf(profile);
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

	/**
	 * Bewirkt das Löschen des {@link SearchProfile} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(SearchProfile searchProfile)
			throws IllegalArgumentException {

		ArrayList<Info> infos = infoMapper.findBySearchProfile(searchProfile);
		for (Info info : infos) {
			this.infoMapper.delete(info);
		}

		this.searchProfileMapper.delete(searchProfile);
	}

	/**
	 * Bewirkt das Löschen einer {@link Info} aus der Datenbank
	 * 
	 */
	@Override
	public void delete(Info info) throws IllegalArgumentException {

		this.infoMapper.delete(info);
	}

	/**
	 * Bewirkt das Löschen einer {@link Description} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(Description description) throws IllegalArgumentException {
		ArrayList<Info> infos = infoMapper.findByDescription(description);
		for (Info info : infos) {
			this.infoMapper.delete(info);
		}

		this.descriptionMapper.delete(description);

	}

	/**
	 * Bewirkt das Löschen einer {@link Selection} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(Selection selection) throws IllegalArgumentException {
		ArrayList<Info> infos = infoMapper.findBySelection(selection);
		for (Info info : infos) {
			this.infoMapper.delete(info);
		}

		this.selectionMapper.delete(selection);
	}

	/**
	 * Bewirkt das Löschen einer {@link Blocking} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(Blocking blocking) throws IllegalArgumentException {

		this.blockingMapper.delete(blocking);
	}

	/**
	 * Bewirkt das Löschen eines {@link Similarity} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(Similarity similarity) throws IllegalArgumentException {

		this.similarityMapper.delete(similarity);
	}

	/**
	 * Bewirkt das Löschen einer {@link VisitList} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(VisitList visitList) throws IllegalArgumentException {

		this.visitListMapper.delete(visitList);
	}

	/**
	 * Bewirkt das Löschen einer {@link FavoritsList} aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(FavoritesList favoritesList)
			throws IllegalArgumentException {

		this.favoritesListMapper.delete(favoritesList);
	}

	/**
	 * Auslesen aller Profile aus der Datenbank.
	 */
	@Override
	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException {
		return this.profileMapper.findAll();
	}

	@Override
	public ArrayList<Profile> getAllProfilesFiltered() {
		Profile currentProfile = LoginServiceImpl.loginService()
				.getCurrentProfile();
		SearchProfile searchProfile = new SearchProfile();

		/*
		 * Sorgt dafür, dass sowohl Männer als auch Frauen aus der DB gelesen
		 * werden
		 */
		currentProfile.setOrientation(Orientation.BI);

		searchProfile.setProfile(currentProfile);
		return profileMapper.findBySearchProfile(searchProfile);
	}

	/**
	 * Auslesen eines Profils aus der Datenbank anhand dessen Profil-Kennung.
	 */
	@Override
	public Profile getProfileByKey(int id) throws IllegalArgumentException {
		return this.profileMapper.findByKey(id);
	}

	/**
	 * Auslesen aller Such-Profile aus der Datenbank.
	 */
	@Override
	public ArrayList<SearchProfile> getAllSearchProfiles()
			throws IllegalArgumentException {
		return this.searchProfileMapper.findAll();
	}

	/**
	 * Auslesen eines Such-Profils aus der Datenbank anhand dessen
	 * Such-Profil-Kennung.
	 */
	@Override
	public SearchProfile getSearchProfileByKey(int id)
			throws IllegalArgumentException {
		return this.searchProfileMapper.findByKey(id);
	}

	/**
	 * Auslesen sämtlicher Infos aus der Datenbank.
	 */
	@Override
	public ArrayList<Info> getAllInfos() throws IllegalArgumentException {
		return this.infoMapper.findAll();
	}

	/**
	 * Auslesen einer Info aus der Datenbank anhand deren Info-Kennung.
	 */
	@Override
	public Info getInfoByKey(int id) throws IllegalArgumentException {
		return this.infoMapper.findByKey(id);
	}

	/**
	 * Auslesen sämtlicher Beschreibungen aus der Datenbank.
	 */
	@Override
	public ArrayList<Description> getAllDescriptions()
			throws IllegalArgumentException {
		return this.descriptionMapper.findAll();
	}

	/**
	 * Auslesen einer Beschreibung aus der Datenbank anhand deren
	 * Beschreibungs-Kennung.
	 */
	@Override
	public Description getDescriptionByKey(int id)
			throws IllegalArgumentException {
		return this.descriptionMapper.findByKey(id);
	}

	/**
	 * Auslesen sämtlicher Auswahlen aus der Datenbank.
	 */
	@Override
	public ArrayList<Selection> getAllSelections()
			throws IllegalArgumentException {
		return this.selectionMapper.findAll();
	}

	/**
	 * Auslesen einer Auswahl aus der Datenbank anhand deren Auswahl-Kennung.
	 */
	@Override
	public Selection getSelectionByKey(int id) throws IllegalArgumentException {
		return this.selectionMapper.findByKey(id);
	}

	/**
	 * Auslesen sämtlicher Ähnlichkeitsmaße aus der Datenbank.
	 */
	@Override
	public ArrayList<Similarity> getAllSimilarities()
			throws IllegalArgumentException {
		return this.similarityMapper.findAll();

	}

	/**
	 * Auslesen eines Ähnlichkeitsmaßes aus der Datenbank anhand deren
	 * Ähnlichkeits-Kennung.
	 */
	@Override
	public Similarity getSimilarityByKey(int id)
			throws IllegalArgumentException {
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

		if (two.getHeight() > one.getHeight() - 10
				&& two.getHeight() < one.getHeight() + 10) {
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
				if (infoOfOne.getDescription() != null
						&& infoOfTwo.getDescription() != null) {
					if (infoOfOne.getDescription().getId() == infoOfTwo
							.getDescription().getId()) {
						attributeCount++;
						if (infoOfOne.getInformationValue().equals(
								infoOfTwo.getInformationValue())) {
							matches++;
						}
					}
				}
				if (infoOfOne.getSelection() != null
						&& infoOfTwo.getSelection() != null) {
					if (infoOfOne.getSelection().getId() == infoOfTwo
							.getSelection().getId()) {
						attributeCount++;
						if (infoOfOne.getInformationValue().equals(
								infoOfTwo.getInformationValue())) {
							matches++;
						}
					}
				}
			}
		}

		double similarityValue = matches / (double) attributeCount;

		Similarity similarityFromDB = similarityMapper
				.findByFromAndTo(one, two);

		Similarity similarity = similarityFromDB == null ? new Similarity()
				: similarityFromDB;
		similarity.setFromProfile(one);
		similarity.setToProfile(two);
		similarity.setSimilarityValue(similarityValue);
		return similarity;
	}

	public void updateSimilarityForProfile(Profile profile) {
		ArrayList<Profile> allProfiles = getAllProfiles();
		for (Profile otherProfile : allProfiles) {
			Similarity similarityFrom = calculateSimilarity(profile,
					otherProfile);
			Similarity similarityTo = calculateSimilarity(otherProfile, profile);
			save(similarityFrom);
			save(similarityTo);
		}
	}

	/**
	 * Ende Ähnlichkeitsberechnung
	 */

	/**
	 * Auslesen aller {@link VisitList} aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<VisitList> getAllVisitLists()
			throws IllegalArgumentException {
		return this.visitListMapper.findAll();
	}

	/**
	 * Auslesen der {@link VisitList} anhand dessen Kennung aus der Datenbank.
	 * 
	 */
	@Override
	public VisitList getVisitListByKey(int id) throws IllegalArgumentException {
		return this.visitListMapper.findByKey(id);
	}

	/**
	 * Auslesen aller {@link Blocking} aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<Blocking> getAllBlockings()
			throws IllegalArgumentException {
		return this.blockingMapper.findAll();
	}

	/**
	 * Auslesen der {@link Blocking} anhand deren Kennung aus der Datenbank.
	 * 
	 */
	@Override
	public Blocking getBlockingByKey(int id) throws IllegalArgumentException {
		return this.blockingMapper.findByKey(id);
	}

	/**
	 * Auslesen aller {@link FavoritesList} aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<FavoritesList> getAllFavoritesLists()
			throws IllegalArgumentException {
		return this.favoritesListMapper.findAll();
	}

	/**
	 * Auslesen des {@link FavoritesList} anhand dessen Kennung aus der
	 * Datenbank.
	 * 
	 */
	@Override
	public FavoritesList getFavoritesListByKey(int id)
			throws IllegalArgumentException {
		return this.favoritesListMapper.findByKey(id);
	}

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

	/**
	 * Speichern eines Such-Profils in der Datenbank.
	 */
	@Override
	public SearchProfile save(SearchProfile searchProfile)
			throws IllegalArgumentException {
		if (searchProfile.getId() != 0) {
			return searchProfileMapper.update(searchProfile);
		} else {
			return searchProfileMapper.insert(searchProfile);
		}
	}

	/**
	 * Speichern einer Information in der Datenbank.
	 */
	@Override
	public Info save(Info info) throws IllegalArgumentException {
		Info savedInfo = null;
		if (info.getId() != 0) {
			savedInfo = infoMapper.update(info);
		} else {
			if (!infoMapper.doInformationExist(info)) {
				savedInfo = infoMapper.insert(info);
			}
		}

		if (info.getProfile() != null) {
			updateSimilarityForProfile(info.getProfile());
		} else {
			updateSimilarityForProfile(info.getSearchProfile().getProfile());
		}

		return savedInfo;
	}

	/**
	 * Speichern einer Beschreibung in der Datenbank.
	 */
	@Override
	public void save(Description description) throws IllegalArgumentException {
		if (description.getId() != 0) {
			descriptionMapper.update(description);
		} else {
			descriptionMapper.insert(description);
		}

	}

	/**
	 * Speichern einer Auswahl in der Datenbank.
	 */
	@Override
	public void save(Selection selection) throws IllegalArgumentException {
		if (selection.getId() != 0) {
			selectionMapper.update(selection);
		} else {
			selectionMapper.insert(selection);
		}
	}

	/**
	 * Speichern einer Kontaktsperre in der Datenbank.
	 */
	@Override
	public void save(Blocking blocking) throws IllegalArgumentException {
		if (blocking.getId() != 0) {
			blockingMapper.update(blocking);
		} else {
			blockingMapper.insert(blocking);
		}
	}

	/**
	 * Speichern eines Ähnlickeitsmaßes in der Datenbank.
	 */
	@Override
	public void save(Similarity similarity) throws IllegalArgumentException {
		if (similarity.getId() != 0) {
			similarityMapper.update(similarity);
		} else {
			similarityMapper.insert(similarity);
		}
	}

	/**
	 * Speichern einer Liste von besuchten Profilen in der Datenbank.
	 * 
	 */
	@Override
	public void save(VisitList visitList) throws IllegalArgumentException {
		if (visitList.getId() != 0) {
			visitListMapper.update(visitList);
		} else {
			visitListMapper.insert(visitList);
		}
	}

	/**
	 * Speichern eines Merkzettels in der Datenbank.
	 */
	@Override
	public FavoritesList save(FavoritesList favoritesList)
			throws IllegalArgumentException {
		if (favoritesList.getId() != 0) {
			return favoritesListMapper.update(favoritesList);
		} else {
			if (!favoritesListMapper.doFavoritesListEntryExist(
					favoritesList.getFromProfile(),
					favoritesList.getToProfile())) {
				return favoritesListMapper.insert(favoritesList);
			}
		}
		return favoritesList;
	}

	/**
	 * Auslesen eines {@link SearchProfile} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<SearchProfile> getSearchProfileOf(Profile profile) {

		return searchProfileMapper.findByProfile(profile);
	}

	/**
	 * Auslesen einer {@link Info} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<Info> getInfoOf(Profile profile) {

		return infoMapper.findByProfile(profile);
	}

	/**
	 * Auslesen einer {@link Selection} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<Info> getInfoOf(Selection selection) {

		return infoMapper.findBySelection(selection);
	}

	/**
	 * Auslesen eines zugehörigen Profils von einem Such-Profils.
	 * 
	 */
	@Override
	public ArrayList<Profile> getProfilesOf(SearchProfile searchProfile)
			throws IllegalArgumentException {
		return this.profileMapper.findBySearchProfile(searchProfile);
	}

	/**
	 * Auslesen eines {@link Blocking} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<Blocking> getBlockingsOf(Profile profile)
			throws IllegalArgumentException {
		return this.blockingMapper.findByProfile(profile);
	}

	/**
	 * Auslesen einer {@link FavoritesList} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<FavoritesList> getFavoritesListsOf(Profile profile)
			throws IllegalArgumentException {
		return this.favoritesListMapper.findByProfile(profile);
	}

	/**
	 * Auslesen einer {@link VisistList} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<VisitList> getVisitListsOf(Profile profile)
			throws IllegalArgumentException {
		return this.visitListMapper.findByProfile(profile);
	}

	/**
	 * Auslesen eines {@link Similarity} eines Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<Similarity> getSimilaritiesOf(Profile profile)
			throws IllegalArgumentException {
		return this.similarityMapper.findByProfile(profile);
	}

	/**
	 * Auslesen des Profils anhand des Such-Profils.
	 * 
	 */
	@Override
	public ArrayList<Profile> getBySearchProfile(SearchProfile searchProfile) {
		return this.profileMapper.findBySearchProfile(searchProfile);
	}

	/**
	 * Eine neue Auswahl-Option wird angelegt. Dies führt zu einer Speicherung
	 * einer Auswahl-Option in der Datenbank.
	 * 
	 */
	public Option createOption(int id, String option) {

		Option o = new Option();

		o.setId(id);
		o.setOption(option);

		return this.optionMapper.insert(o);
	}

	/**
	 * Bewirkt das Löschen einer Auswahl-Option aus der Datenbank.
	 * 
	 */
	@Override
	public void delete(Option option) throws IllegalArgumentException {

		this.optionMapper.delete(option);

	}

	/**
	 * Speichern einer Auswahl-Option in der Datenbank.
	 * 
	 */
	@Override
	public void save(Option option) throws IllegalArgumentException {

		if (option.getId() != 0) {
			optionMapper.update(option);
		} else {
			optionMapper.insert(option);
		}

	}

	/**
	 * Auslesen einer Auswahl-Möglichkeit von einem Auswahl-Objekt.
	 * 
	 */
	@Override
	public ArrayList<Option> getOptionsOf(Selection selection) {

		return this.optionMapper.findBySelection(selection);

	}

	/**
	 * Prüft ob sich ein Profil auf einem Merkzettel befindet.
	 * 
	 * @param profile
	 *            Nutzerprofil
	 * @return Merkzettel
	 */
	public boolean isOnFavoritesList(Profile profile) {
		Profile currentProfile = LoginServiceImpl.loginService()
				.getCurrentProfile();
		return favoritesListMapper.doFavoritesListEntryExist(currentProfile,
				profile);
	}

	/**
	 * Prüft ob bereits eine Kontaksperre vorhanden ist.
	 * 
	 * @param profile
	 *            Nutzerprofil
	 * @return Kontaktsperre
	 */
	public boolean isBlocked(Profile profile) {
		Profile currentProfile = LoginServiceImpl.loginService()
				.getCurrentProfile();
		return blockingMapper.doBlockingExist(currentProfile, profile);
	}

	/**
	 * Auslesen des ähnlichsten Profils des gegebenen Profils.
	 * 
	 */
	@Override
	public ArrayList<Profile> getMostSimilarProfiles(Profile fromProfile)
			throws IllegalArgumentException {
		return this.profileMapper.findMostSimilarProfiles(fromProfile);
	}

	/**
	 * Gibt alle Merkzettel-Einträge auf denen das gegebene Profil beteiligt ist
	 * zurück.
	 * 
	 */
	@Override
	public ArrayList<FavoritesList> getWithInFavoritesList(Profile with)
			throws IllegalArgumentException {
		return this.favoritesListMapper.findWith(with);
	}

	/**
	 * Gibt alle Profil-Besuche bei denen das gegebene Profil beteiligt ist
	 * zurück.
	 * 
	 */
	@Override
	public ArrayList<VisitList> getWithInVisitList(Profile with)
			throws IllegalArgumentException {
		return this.visitListMapper.findWith(with);
	}

	/**
	 * Gibt alle Kontaktsperren auf denen das gegebene Profil beteiligt ist
	 * zurück.
	 * 
	 */
	@Override
	public ArrayList<Blocking> getWithInBlocking(Profile with)
			throws IllegalArgumentException {
		return this.blockingMapper.findWith(with);
	}

	/**
	 * Gibt alle Ähnlichkeitswerte auf denen das gegebene Profil beteiligt ist
	 * zurück.
	 * 
	 */
	@Override
	public ArrayList<Similarity> getWithInSimilarity(Profile with)
			throws IllegalArgumentException {
		return this.similarityMapper.findWith(with);
	}

	/**
	 * Speichert das ein Profil besucht wurde.
	 * 
	 */
	@Override
	public void visit(Profile profile) {
		Profile currentProfile = LoginServiceImpl.loginService()
				.getCurrentProfile();

		if (!visitListMapper.doVisitListExist(currentProfile, profile)) {
			VisitList visitList = new VisitList();
			visitList.setFromProfile(currentProfile);
			visitList.setToProfile(profile);
			visitListMapper.insert(visitList);
		}
	}

	/**
	 * Auslesen einer Info eines Such-Profils anhand deren Kennung aus der
	 * Datenbank.
	 * 
	 */
	public ArrayList<Info> getInfosOf(int searchProfileId)
			throws IllegalArgumentException {

		return this.infoMapper.findBySearchProfile(searchProfileId);
	}

	/**
	 * Auslesen einer Info eines Such-Profils aus der Datenbank.
	 * 
	 */
	@Override
	public ArrayList<Info> getInfosOf(SearchProfile searchProfile)
			throws IllegalArgumentException {

		return this.infoMapper.findBySearchProfile(searchProfile);
	}

}
