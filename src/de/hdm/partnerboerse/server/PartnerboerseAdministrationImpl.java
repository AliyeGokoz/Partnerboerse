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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BlockingMapper blockingMapper = null;
	private DescriptionMapper descriptionMapper = null;
	private FavoritesListMapper favoritesListMapper = null;
	private InfoMapper infoMapper = null;
	// private PropertyMapper propertyMapper = null;
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
		p.setHairColor(hairColor);
		p.setConfession(confession);
		p.setGender(gender);

		return this.profileMapper.insert(p);
	}

	@Override
	public SearchProfile createSearchProfile(int id, int fromAge, int toAge, int fromHeight, int toHeight,
			HairColor hairColor, Gender gender, Confession confession, boolean smoker) {
		SearchProfile sp = new SearchProfile();
		// TODO Auto-generated method stub

		sp.setId(id);
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

	/*
	 * @Override public Property createProperty(int id, String
	 * propertyName,String textualDescription) { Property p = new Property();
	 * 
	 * 
	 * return this.propertyMapper.insert(p); }
	 */

	@Override
	public Selection createSelection(int id, String propertyName, String textualDescription) {
		Selection s = new Selection();

		s.setId(id);
		s.setPropertyName(propertyName);
		s.setTextualDescription(textualDescription);

		return this.selectionMapper.insert(s);
	}

	@Override
	public Description createDescription(int id, String propertyName, String textualDescription) {
		Description d = new Description();

		d.setId(id);
		d.setPropertyName(propertyName);
		d.setTextualDescription(textualDescription);

		return this.descriptionMapper.insert(d);
	}

	@Override
	public FavoritesList createFavoritesList(int id, Profile fromProfile, Profile toProfile) {
		FavoritesList fl = new FavoritesList();

		fl.setId(id);
		fl.setFromProfile(fromProfile);
		fl.setToProfile(toProfile);

		return this.favoritesListMapper.insert(fl);
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
	public Blocking createBlocking(int id, Profile fromProfile, Profile toProfile) {
		Blocking b = new Blocking();

		b.setId(id);
		b.setFromProfile(fromProfile);
		b.setToProfile(toProfile);

		return this.blockingMapper.insert(b);
	}

	// Delete-Methoden
	@Override
	public void delete(Profile profile) throws IllegalArgumentException {
		
		ArrayList<FavoritesList> favoritesLists = this.getFavoritesListsOf(profile);
	    ArrayList<SearchProfile> searchProfiles = this.getSearchprofilesOf(profile);
	    ArrayList<Info> infos = this.getInfosOf(profile);
	    ArrayList<Blocking> blockings= this.getBlockingsOf(profile);
	    ArrayList<VisitList> visitLists= this.getVisitListsOf(profile);
	    ArrayList<Similarity> similarities= this.getSimilaritiesOf(profile);


	    if (favoritesLists!= null) {
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
	      for (Similarity similarity: similarities) {
	        this.delete(similarity);
	      }
	    }

		this.profileMapper.delete(profile);
	}

	@Override
	public void delete(SearchProfile searchProfile) throws IllegalArgumentException {
		// ...
		this.searchProfileMapper.delete(searchProfile);
	}

	@Override
	public void delete(Info info) throws IllegalArgumentException {
		// ...
		this.infoMapper.delete(info);
	}

	/*
	 * @Override public void delete(Property property) throws
	 * IllegalArgumentException {
	 * 
	 * }
	 */

	@Override
	public void delete(Description description) throws IllegalArgumentException {
		// ...
		this.descriptionMapper.delete(description);

	}

	@Override
	public void delete(Selection selection) throws IllegalArgumentException {
		// ...
		this.selectionMapper.delete(selection);
	}

	@Override
	public void delete(Blocking blocking) throws IllegalArgumentException {
		// ...
		this.blockingMapper.delete(blocking);
	}

	@Override
	public void delete(Similarity similarity) throws IllegalArgumentException {
		// ...
		this.similarityMapper.delete(similarity);
	}

	@Override
	public void delete(VisitList visitList) throws IllegalArgumentException {
		// ...
		this.visitListMapper.delete(visitList);
	}

	@Override
	public void delete(FavoritesList favoritesList) throws IllegalArgumentException {
		// ...
		this.favoritesListMapper.delete(favoritesList);
	}

	// Get-Methoden
	@Override
	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException {
		return this.profileMapper.findAll();
	}

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

	/*
	 * @Override public ArrayList<Property> getAllProperties() throws
	 * IllegalArgumentException { return this.propertyMapper.getAllProperties();
	 * }
	 * 
	 * @Override public Property getPropertyByKey(int id) throws
	 * IllegalArgumentException { return
	 * this.propertyMapper.getPropertyByKey(id); }
	 */

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

	// Methode zur Berechnung der Ähnlichkeit (Beispiel smoker)
	public Similarity calculateSimilarity(Profile one, Profile two) {
		int attributeCount = 3;
		int matches = 0;

		if (one.isSmoker() == two.isSmoker()) {
			matches++;
		}

		double similarityValue = matches / attributeCount;

		Similarity similarity = new Similarity();
		similarity.setFromProfile(one);
		similarity.setToProfile(two);
		similarity.setSimilarityValue(similarityValue);
		return similarity;
	}

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
	@Override
	public void save(Profile profile) throws IllegalArgumentException {
		profileMapper.update(profile);
	}

	@Override
	public void save(SearchProfile searchProfile) throws IllegalArgumentException {
		searchProfileMapper.update(searchProfile);
	}

	@Override
	public void save(Info info) throws IllegalArgumentException {
		infoMapper.update(info);
	}

	/*
	 * @Override
	 *
	 * public void save(Property property) throws IllegalArgumentException {
	 * propertyMapper.update(property); }
	 */

	@Override
	public void save(Description description) throws IllegalArgumentException {
		descriptionMapper.update(description);

	}

	@Override
	public void save(Selection selection) throws IllegalArgumentException {
		selectionMapper.update(selection);
	}

	@Override
	public void save(Blocking blocking) throws IllegalArgumentException {
		blockingMapper.update(blocking);
	}

	@Override
	public void save(Similarity similarity) throws IllegalArgumentException {
		similarityMapper.update(similarity);
	}

	@Override
	public void save(VisitList visitList) throws IllegalArgumentException {
		visitListMapper.update(visitList);
	}

	@Override
	public void save(FavoritesList favoritesList) throws IllegalArgumentException {
		favoritesListMapper.update(favoritesList);
	}

}
