package de.hdm.partnerboerse.shared;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.partnerboerse.shared.bo.*;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

/**
 * Synchrone Schnittstelle fuer eine RPC-faehige Klasse zur Verwaltung der
 * Partnerboerse.
 *
 */

@RemoteServiceRelativePath("administration")
public interface PartnerboerseAdministration extends RemoteService {

	/**
	 * Initialisierung des Objekts. Diese Methode ist ebenso enotwendig für GWT
	 * RPC, sowie der No Argument Constructor der Implementations-Klasee
	 * {@link PernerboerseImpl}
	 * 
	 * @throws IllegalArgumentException
	 */

	public void init() throws IllegalArgumentException;

	/**
	 * Ein neues Nutzer-Profil wird angelegt.
	 * 
	 * @param id Profil-Kennung
	 * @param firstName Vorname
	 * @param lastName Nachname
	 * @param dateOfBirth Geburtsdatum
	 * @param email E-Mail
	 * @param height Größe
	 * @param smoker Raucherstatus
	 * @param hairColor Haarfarbe
	 * @param confession Konfession
	 * @param gender Geschlecht
	 * @return ein fertiges Nutzer-Profil
	 */
	public Profile createProfile(int id, String firstName, String lastName,
			Date dateOfBirth, String email, int height, boolean smoker,
			HairColor hairColor, Confession confession, Gender gender);
	
	/** Ein neues Such-Profil wird angelegt.
	 * 
	 * @param id Such-Profil-Kennung
	 * @param name Name
	 * @param fromAge Altersuntergrenze
	 * @param toAge Altersobergrenze
	 * @param fromHeight minimale Körpergröße
	 * @param toHeight maximale Körpergröße
	 * @param hairColor Haarfarbe
	 * @param gender Geschlecht
	 * @param confession Konfession
	 * @param smoker Raucherstatus
	 * @return ein fertiges Such-Profil
	 */

	public SearchProfile createSearchProfile(int id, String name, int fromAge,
			int toAge, int fromHeight, int toHeight, HairColor hairColor,
			Gender gender, Confession confession, boolean smoker);

	/**Eine neue Info wird angelegt.
	 * 
	 * @param id Informations-Kennung
	 * @param informationValue Informations-Wert
	 * @return eine neue Information
	 */
	public Info createInfo(int id, String informationValue);

	/** Eine neue Auswahl-Eigenschaft wird angelegt.
	 * 
	 * @param id Auswahl-Kennung
	 * @param propertyName Eigenschaft
	 * @param textualDescriptionForProfile Beschreibung für das Profils
	 * @param textualDescriptionForSearchProfile Beschreibung für das Such-Profils
	 * @return einer neuen Auswahl-Eigenschaft
	 */
	public Selection createSelection(int id, String propertyName,
			String textualDescriptionForProfile,
			String textualDescriptionForSearchProfile);

	/** Eine neue Beschreibung wird angelegt.
	 * 
	 * @param id Beschreibungs-Kennung
	 * @param propertyName Beschreibung
	 * @param textualDescriptionForProfile textuelle Beschreeibung für das Profil
	 * @param textualDescriptionForSearchProfile textuelle Beschreeibung für das Such-Profil
	 * @return einer neuen Eigenschaft in textueller Beschreibung
	 */
	public Description createDescription(int id, String propertyName,
			String textualDescriptionForProfile,
			String textualDescriptionForSearchProfile);

	/** Ein neuer Merkzettel wird angelegt.
	 *
	 * @param fromProfile Nutzerprofil
	 * @param toProfile besuchtes Profil
	 * @return eines neu angelegten Merkzettels
	 */
	public FavoritesList createFavoritesList(Profile fromProfile,
			Profile toProfile);

	/** Eine neue Liste besuchter Profile wird angelegt.
	 * 
	 * @param id Profilbesuchs-Kennung
	 * @param fromProfile Nutzerprofil
	 * @param toProfile besuchtes Profil
	 * @return einer neuen Liste von besuchten Profilen
	 */
	public VisitList createVisitList(int id, Profile fromProfile,
			Profile toProfile);
	
	/** Ein neues Ähnlichkeitsmaß wird angelegt.
	 * 
	 * @param id Ähnlichkeitsmaß-Kennung
	 * @param fromProfile Nutzerprofil
	 * @param toProfile besuchtes Profil
	 * @param similarityValue Ähnlichkeitswert
	 * @return eines neuen Ähnlichkeitswerts
	 */
	public Similarity createSimilarity(int id, Profile fromProfile,
			Profile toProfile, double similarityValue);

	/** Eine neue Kontaktsperre wird angelegt.
	 * 
	 * @param fromProfile Nutzerprofil
	 * @param toProfile besuchtes Profil
	 * @return einer neuen Kontaktsperre
	 */
	public Blocking createBlocking(Profile fromProfile, Profile toProfile);
	
	/** Eine neue Auswahl-Option wird angelegt.
	 * 
	 * @param id Auswahl-Options_Kennung
	 * @param option Option
	 * @return einer neuen Auswahl-Option
	 */
	public Option createOption(int id, String option);

	/**Löschen des Profils.
	 * 
	 * @param profile Profil
	 * @throws IllegalArgumentException
	 */
	public void delete(Profile profile) throws IllegalArgumentException;

	/** Löschen des Such-Profils.
	 * 
	 * @param searchProfile Such-Profile
	 * @throws IllegalArgumentException
	 */
	public void delete(SearchProfile searchProfile)
			throws IllegalArgumentException;
 
	/** Löschen der Information.
	 * 
	 * @param info Info
	 * @throws IllegalArgumentException
	 */
	public void delete(Info info) throws IllegalArgumentException;

	/** Löschen der Beschreibung.
	 * 
	 * @param description Beschreibung
	 * @throws IllegalArgumentException
	 */
	public void delete(Description description) throws IllegalArgumentException;

	/** Löschen der Auswahl.
	 * 
	 * @param selection Auswahl
	 * @throws IllegalArgumentException
	 */
	public void delete(Selection selection) throws IllegalArgumentException;

	/** Löschen der Kontaktsperre.
	 * 
	 * @param blocking Kontaktsperre
	 * @throws IllegalArgumentException
	 */
	public void delete(Blocking blocking) throws IllegalArgumentException;
	
	/** Löschen der Ähnlichkeitmaßes.
	 * 
	 * @param similarity Ähnlichkeitmaß
	 * @throws IllegalArgumentException
	 */
	public void delete(Similarity similarity) throws IllegalArgumentException;

	/** Löschen der besuchten Profile.
	 * 
	 * @param visitList besuchte Profile
	 * @throws IllegalArgumentException
	 */
	public void delete(VisitList visitList) throws IllegalArgumentException;

	/** Löschen des Merkzettels.
	 * 
	 * @param favoritesList Merkzettel
	 * @throws IllegalArgumentException
	 */
	public void delete(FavoritesList favoritesList)
			throws IllegalArgumentException;

	/** Löschen der Auswahl-Option.
	 * 
	 * @param option
	 * @throws IllegalArgumentException
	 */
	public void delete(Option option) throws IllegalArgumentException;

	/** Auslesen aller Profile.
	 * 
	 * @return aller Profile
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Profile> getAllProfiles() throws IllegalArgumentException;

	/** Auslesen des Profils anhand dessen Profil-Kennung.
	 * 
	 * @param id Profil-Kennung
	 * @return des Profils
	 * @throws IllegalArgumentException
	 */
	public Profile getProfileByKey(int id) throws IllegalArgumentException;
	
	/** Auslesen aller Such-Profile.
	 * 
	 * @return
	 * @throws IllegalArgumentException
	 */
	public ArrayList<SearchProfile> getAllSearchProfiles()
			throws IllegalArgumentException;

	/** Auslesen des Such-Profils anhand dessen Such-Profils.
	 * 
	 * @param id Such-Profil-Kennung
	 * @return des Such-Profils
	 * @throws IllegalArgumentException
	 */
	public SearchProfile getSearchProfileByKey(int id)
			throws IllegalArgumentException;

	/** Auslesen aller Infos.
	 * 
	 * @return der Informationen
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Info> getAllInfos() throws IllegalArgumentException;

	/** Auslesen der Info anhand deren Info-Kennung.
	 * 
	 * @param id Info-Kennung
	 * @return der Info
	 * @throws IllegalArgumentException
	 */
	public Info getInfoByKey(int id) throws IllegalArgumentException;

	/** Auslesen aller Beschreibungen.
	 * 
	 * @return der Beschreibung
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Description> getAllDescriptions()
			throws IllegalArgumentException;

	/** Auslesen der Beschreibung anhand deren Beschreibungs-Kennung.
	 * 
	 * @param id Beschreibungs-Kennung.
	 * @return der Beschreibung
	 * @throws IllegalArgumentException
	 */
	public Description getDescriptionByKey(int id)
			throws IllegalArgumentException;

	/** Auslesen aller Auswahlen.
	 * 
	 * @return der Auswahl
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Selection> getAllSelections()
			throws IllegalArgumentException;

	/** Auslesen der Auswahl anhand deren Auswahl-Kennung.
	 * 
	 * @param id Auswahl-Kennung
	 * @return der Auswahl
	 * @throws IllegalArgumentException
	 */
	public Selection getSelectionByKey(int id) throws IllegalArgumentException;

	
	/** Auslesen aller Ähnlichkeitsmaßes.
	 * 
	 * @return der Ähnlichkeitswerte
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Similarity> getAllSimilarities()
			throws IllegalArgumentException;

	/** Auslesen des Ähnlichkeitsmaßes anhand dessen Ähnlichkeits-Kennung.
	 * 
	 * @param id Ähnlichkeits-Kennung
	 * @return des Ähnlichkeitswertes
	 * @throws IllegalArgumentException
	 */
	public Similarity getSimilarityByKey(int id)
			throws IllegalArgumentException;

	/** Auslesen aller besuchten Proflie.
	 * 
	 * @return der besuchten Profile
	 * @throws IllegalArgumentException
	 */
	public ArrayList<VisitList> getAllVisitLists()
			throws IllegalArgumentException;
	
	/** Auslesen des besuchten Profils anahand dessen Profil-Kennung.
	 * 
	 * @param id Profil-Kennung des besuchten Profils
	 * @return des des besuchten Profils
	 * @throws IllegalArgumentException
	 */

	public VisitList getVisitListByKey(int id) throws IllegalArgumentException;

	/** Auslesen sämtlicher Kontaktsperren.
	 * 
	 * @return der Kontaktsperren
	 * @throws IllegalArgumentException
	 */
	public ArrayList<Blocking> getAllBlockings()
			throws IllegalArgumentException;

	/** Auslesen der Kontaktsperre anhand deren Kontaktsperre-Kennung.
	 * 
	 * @param id Kontaktsperre-Kennung
	 * @return der Kontaktsperre
	 * @throws IllegalArgumentException
	 */
	public Blocking getBlockingByKey(int id) throws IllegalArgumentException;

	/** Auslesen sämtlicher Merkzettel.
	 * 
	 * @return der Merkzettel
	 * @throws IllegalArgumentException
	 */
	public ArrayList<FavoritesList> getAllFavoritesLists()
			throws IllegalArgumentException;

	/** Auslesen des Merkzettels anhand dessen Merkzettel-Kennung.
	 *  
	 * @param id Merkzettel-Kennung
	 * @return Merkzettel
	 * @throws IllegalArgumentException
	 */
	public FavoritesList getFavoritesListByKey(int id)
			throws IllegalArgumentException;

	/** Speichern eines Profils in der Datenbank
	 * 
	 * @param profile zu speicherndes Profil
	 * @throws IllegalArgumentException
	 */
	public void save(Profile profile) throws IllegalArgumentException;

	/** Speichern eines Such-Profils in der Datenbank.
	 * 
	 * @param searchProfile zu speicherndes Such-Profil
	 * @return Such-Profil
	 * @throws IllegalArgumentException
	 */
	public SearchProfile save(SearchProfile searchProfile)
			throws IllegalArgumentException;

	/** Speichern einer Info in der Datenbank.
	 * 
	 * @param info zu speichernde Info
	 * @return Info
	 * @throws IllegalArgumentException
	 */
	public Info save(Info info) throws IllegalArgumentException;

	/** Speichern der Beschreibung in der Datenbank.
	 * 
	 * @param description zu speichernde Beschreibung
	 * @throws IllegalArgumentException
	 */
	public void save(Description description) throws IllegalArgumentException;

	/** Speichern der Auswahl in der Datenbank
	 * 
	 * @param selection zu speichernde Auswahl
	 * @throws IllegalArgumentException
	 */
	public void save(Selection selection) throws IllegalArgumentException;
	
	/** Speichern der Kontaktsperre in der Datenbank.
	 * 
	 * @param blocking zu speichernde Kontaktsperre
	 * @throws IllegalArgumentException
	 */
	public void save(Blocking blocking) throws IllegalArgumentException;

	/** Speichern des Ähnlichkeitsmaßes in der Datenbank.
	 * 
	 * @param similarity zu speicherndes Ähnlichkeitsmaß
	 * @throws IllegalArgumentException
	 */
	public void save(Similarity similarity) throws IllegalArgumentException;

	/** Speichern der Liste der besuchten Profile in der Datenbank.
	 * 
	 * @param visitList zu speichernde Liste der besuchten Profile
	 * @throws IllegalArgumentException
	 */
	public void save(VisitList visitList) throws IllegalArgumentException;

	/** Speichern des Merkzettels in der Datenbank.
	 * 
	 * @param favoritesList zu speichernder Merkzettel
	 * @return Merkzettel
	 * @throws IllegalArgumentException
	 */
	public FavoritesList save(FavoritesList favoritesList)
			throws IllegalArgumentException;

	/** Speichern der Auswahl-Option in der Datenbank.
	 * 
	 * @param option zu speichernde Auswahl-Option
	 * @throws IllegalArgumentException
	 */
	public void save(Option option) throws IllegalArgumentException;

	/** Auslesen eines Suchprofils eines Profils {@link Profile}.
	 * 
	 * @param profile Profil
	 * @return Such_profil
	 */
	ArrayList<SearchProfile> getSearchProfileOf(Profile profile);

	/** Auslesen einer Info eines Profils {@link Profile}.
	 * 
	 * @param profile Profil
	 * @return Info
	 */
	ArrayList<Info> getInfoOf(Profile profile);

	/** Auslesen der Auswahl einer Info{@link Info}.
	 * 
	 * @param selection Auswahl
	 * @return Info
	 */
	ArrayList<Info> getInfoOf(Selection selection);

	/** Auslesen eines Profils eines Such-Profils {@link SearchProfile}.
	 * 
	 * @param searchProfile Such-Profil
	 * @return Profil
	 * @throws IllegalArgumentException
	 */
	ArrayList<Profile> getProfilesOf(SearchProfile searchProfile)
			throws IllegalArgumentException;

	/** Auslesen einer Kontaktsperre eines Profils {@link Profile}.
	 * 
	 * @param profile Profil
	 * @return Kontaktsperre
	 * @throws IllegalArgumentException
	 */
	ArrayList<Blocking> getBlockingsOf(Profile profile)
			throws IllegalArgumentException;

	/** Auslesen eines Merkzettels eines Profils {@link Profile}.
	 * 
	 * @param profile Profil
	 * @return Merkzettel
	 * @throws IllegalArgumentException
	 */
	ArrayList<FavoritesList> getFavoritesListsOf(Profile profile)
			throws IllegalArgumentException;

	/** Auslesen einer Liste besuchter Profile eines Profils {@link Profile}.
	 * 
	 * @param profile Profil
	 * @return Liste besuchter Profile
	 * @throws IllegalArgumentException
	 */
	ArrayList<VisitList> getVisitListsOf(Profile profile)
			throws IllegalArgumentException;

	/** Auslesen eines Ähnlichkeitsmaßes eines Profils {@link Profile}.
	 * 
	 * @param profile Profil
	 * @return Ähnlichkeitsmaß
	 * @throws IllegalArgumentException
	 */
	ArrayList<Similarity> getSimilaritiesOf(Profile profile)
			throws IllegalArgumentException;

	/** Berechnung der Ähnlichkeit zwischen zwei Profilen.
	 * 
	 * @param one Eins
	 * @param two Zwei
	 * @return Ähnlichkeitswert
	 */
	Similarity calculateSimilarity(Profile one, Profile two);

	/** Auslesen eines Profils anhande eines Such-Profils {@link SearchProfile}.
	 * 
	 * @param searchProfile Such-Profil
	 * @return Profil
	 */
	ArrayList<Profile> getBySearchProfile(SearchProfile searchProfile);

	/** Auslesen einer Auswahl-Option {@link Selection}
	 * 
	 * @param selection Auswahl
	 * @return Auswahl-Option
	 * @throws IllegalArgumentException
	 */
	ArrayList<Option> getOptionsOf(Selection selection)
			throws IllegalArgumentException;

	/** Auslesen der ähnlichsten Profile von einem Profil {@link Profile}
	 * 
	 * @param fromProfile Nutzerprofil
	 * @return ähnlichste Profile
	 * @throws IllegalArgumentException
	 */
	ArrayList<Profile> getMostSimilarProfiles(Profile fromProfile)
			throws IllegalArgumentException;

	ArrayList<FavoritesList> getWithInFavoritesList(Profile with)
			throws IllegalArgumentException;

	ArrayList<VisitList> getWithInVisitList(Profile with)
			throws IllegalArgumentException;

	ArrayList<Blocking> getWithInBlocking(Profile with)
			throws IllegalArgumentException;

	void visit(Profile profile);

	ArrayList<Profile> getAllProfilesFiltered();

	ArrayList<Similarity> getWithInSimilarity(Profile with)
			throws IllegalArgumentException;

	/** Auslesen einer Info anhand der Such-Profils-Kennung {@link SearchProfile}
	 * 
	 * @param searchProfileId Such-Profil-Kennung
	 * @return Info
	 * @throws IllegalArgumentException
	 */
	ArrayList<Info> getInfosOf(int searchProfileId)
			throws IllegalArgumentException;

	/** Auslesen der Infos eines Such-Profils {@link SearchProfile}.
	 * 
	 * @param searchProfile Such-Profil
	 * @return Info
	 * @throws IllegalArgumentException
	 */
	ArrayList<Info> getInfosOf(SearchProfile searchProfile)
			throws IllegalArgumentException;

}
