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

	public SearchProfile createSearchProfile(int id, int height, HairColor hairColor, Gender gender, int age,
			Confession confession, boolean smoker);

	public Info createInfo(int id, String informationValue);

	public Property createProperty(int id, String propertyName, String textualDescription);

	public Selection createSelection(int id, String propertyName, Property textualDescription);

	public Description createDescription(int id, String propertyName, Property textualDescription);

	Blocking createBlocking();

	public FavoritesList createFavoritesList(int id, Profile fromProfile, Profile toProfile);

	public VisitList createVisitList(int id, Profile fromProfile, Profile toProfile);

	public Similarity createSimilarity(int id, Profile fromProfile, Profile toProfile, double similarityValue);

	FavoritesList createFavoriteList();
	
	//save-Methoden

	public void saveProfile(Profile p) throws IllegalArgumentException;

	public void saveSearchProfile(SearchProfile s) throws IllegalArgumentException;

	public void saveInfo(Info i) throws IllegalArgumentException;

	public void saveProperty(Property p) throws IllegalArgumentException;

	public void saveDescription(Description d) throws IllegalArgumentException;

	public void saveSelection(Selection s) throws IllegalArgumentException;

	public void saveBlocking(Blocking b) throws IllegalArgumentException;

	public void saveSimilarity(Similarity s) throws IllegalArgumentException;

	public void saveVisitList(VisitList v) throws IllegalArgumentException;

	public void saveFavoritesList(FavoritesList f) throws IllegalArgumentException;

	
}
