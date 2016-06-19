package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>SearchProfileMapper</code> bildet <code>SearchProfile
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper, FavoritesListMapper, InfoMapper, OptionMapper,
 *      ProfileMapper, BlockingMapper, SelectionMapper, SimilarityMapper,
 *      VisitListMapper
 * @author Roxana
 */

public class SearchProfileMapper {
	
	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT searchprofiles.id AS id, fromAge, toAge, searchprofiles.hairColor AS spHairColor, searchprofiles.gender AS spGender, fromHeight, toHeight, searchprofiles.confession AS spConfession, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, profiles.confession AS pConfession, smoker, profiles.hairColor AS pHairColor, profiles.gender AS pGender FROM searchprofiles LEFT JOIN profiles ON profiles.id = searchprofiles.profileId";

	/**
	 * Die Instantiierung der Klasse SearchProfileMapper erfolgt nur einmal.
	 * Dies wird auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see searchProfileMapper()
	 */

	private static SearchProfileMapper searchProfileMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */

	protected SearchProfileMapper() {
	}

	/**
	 * Durch
	 * <code>SearchProfileMapper.searchProfileMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>SearchProfileMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des SearchProfileMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>SearchProfileMapper</code>-Objekt.
	 * 
	 * @see searchProfileMapper
	 */

	public static SearchProfileMapper searchProfileMapper() {
		if (searchProfileMapper == null) {
			searchProfileMapper = new SearchProfileMapper();
		}

		return searchProfileMapper;
	}

	/**
	 * Einfügen eines <code>SearchProfile</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param searchProfile
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene SearchProfile - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public SearchProfile insert(SearchProfile searchProfile) {

		// DB-Verbindung holen

		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();


			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM searchprofiles ");


			if (rs.next()) {

				/*
				 * searchProfile erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */
				searchProfile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				
				String sql = "INSERT INTO searchprofiles (id, fromAge, toAge, hairColor, gender, fromHeight, toHeight, confession, profileId) "

						+ "VALUES ("
						+ searchProfile.getId()
						+ ","
						+ searchProfile.getFromAge()
						+ ","
						+ searchProfile.getToAge()
						+ ",'"
						+ searchProfile.getHairColor()
						+ "',"
						+ (searchProfile.getGender() != null ? "'" +searchProfile.getGender()+"'" : null)
						+ ","
						+ searchProfile.getFromHeight()
						+ ","
						+ searchProfile.getToHeight()
						+ ",'"
						+ searchProfile.getConfession() + "',"+searchProfile.getProfile().getId()+")";
				System.out.println(sql);
				stmt.executeUpdate(sql);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, des evtl. korrigierten SearchProfiles.
		return searchProfile;
	}

	/**
	 * Wiederholtes Schreiben eines SearchProfile-Objekts in die Datenbank.
	 * 
	 * @param searchProfile
	 *            , das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public SearchProfile update(SearchProfile searchProfile) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE searchprofiles " + "SET fromAge=\"" + searchProfile.getFromAge() + "\", "
					+ "toAge=\"" + searchProfile.getToAge() + "\", " + "hairColor=\"" + searchProfile.getHairColor()
					+ "\", " + "gender=" + (searchProfile.getGender() != null ? "\""+searchProfile.getGender()+"\"" : null ) + ", " + "fromHeight=\""
					+ searchProfile.getFromHeight() + "\", " + "toHeight=\"" + searchProfile.getToHeight() + "\", "
					+ "confession=\"" + searchProfile.getConfession() + "\" " + "WHERE id=" + searchProfile.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, des evtl. korrigierten SearchProfiles.
		return searchProfile;
	}

	/**
	 * Löschen der Daten eines <code>SearchProfile</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param searchProfile
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(SearchProfile searchProfile) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM searchprofiles " + "WHERE id=" + searchProfile.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen eines Suchprofils mit vorgegebener ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return SearchProfile-Objekt, das dem übergebenen Schlüssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public SearchProfile findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			String sql = BASE_SELECT + " WHERE searchprofiles.id=" + id;

			ResultSet rs = stmt.executeQuery(sql);

			/*
			 * Da id der Primärschlüssel ist, kann maximal nur ein Tupel
			 * zurückgegeben werden. Prüfung, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {

				// Umwandlung des Ergebnis-Tupel in ein Objekt und Ausgabe des
				// Ergebnis-Objekts.
				return map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Suchprofile.
	 * 
	 * @return Eine ArrayList mit SearchProfile-Objekten, die sämtliche
	 *         Suchprofile repräsentieren. Bei evtl. Exceptions wird eine
	 *         partiell gefüllte oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<SearchProfile> findAll() {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT);

			while (rs.next()) {

				SearchProfile searchProfile = map(rs);

				// Für jeden Eintrag im Suchergebnis wird nun ein
				// SearchProfile-Objekt
				// erstellt und zur Ergebnis-ArrayList hinzugefügt.
				result.add(searchProfile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Suchprofile eines bestimmten Profils mit Hilfe der
	 * Profil-ID. Da ein Profil mehrere Suchprofile haben kann, können mehrere
	 * SearchProfile-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit SearchProfile-Objekten, die sämtliche
	 *         Suchprofile des vorgegebenen Profils repräsentieren.
	 */

	public ArrayList<SearchProfile> findByProfile(int profileId) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();
			String sql = BASE_SELECT + " WHERE profileId=" + profileId + " ORDER BY id";

			ResultSet rs = stmt.executeQuery(sql);

			// Für jeden Eintrag im Suchergebnis wird nun ein SearchProfile-Objekt
		    // erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}
	
	 /**
	   * Auslesen aller Suchprofile eines bestimmten Profils mit Hilfe eines Profil-Objekts. 
	   * Da ein Profil mehrere Suchprofile haben kann, können mehrere SearchProfile-Objekte in 
	   * einer ArrayList ausgegeben werden.
	   * 
	   * @param Profil-Objekt
	   * @return Eine ArrayList mit SearchProfile-Objekten, die sämtliche
	   *         Suchprofile des vorgegebenen Profils repräsentieren. 
	   */

	public ArrayList<SearchProfile> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}
	
	/**
	 * Diese Methode bildet das ResultSet auf ein Java-Objekt ab.
	 *
	 * @param rs
	 *            , das ResultSet, dass auf ein Java-Objekt abgebildet werden soll
	 * @return SearchProfile-Objekt
	 */
	
	private SearchProfile map(ResultSet rs) throws SQLException {
		SearchProfile searchProfile = new SearchProfile();
		searchProfile.setId(rs.getInt("id"));
		searchProfile.setFromAge(rs.getInt("fromAge"));
		searchProfile.setToAge(rs.getInt("toAge"));

		String hairColorString = rs.getString("spHairColor");
		searchProfile
				.setHairColor(hairColorString != null ? Profile.HairColor.valueOf(rs.getString("spHairColor")) : null);
		String genderString = rs.getString("spGender");
		searchProfile.setGender(genderString != null ? Profile.Gender.valueOf(genderString) : null);
		searchProfile.setFromHeight(rs.getInt("fromHeight"));
		searchProfile.setToHeight(rs.getInt("ToHeight"));
		String confessionString = rs.getString("spConfession");
		searchProfile.setConfession(confessionString != null ? Profile.Confession.valueOf(confessionString) : null);

		Profile profile = new Profile();
		profile.setId(rs.getInt("pid"));
		profile.setFirstName(rs.getString("firstName"));
		profile.setLastName(rs.getString("lastName"));
		profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
		profile.setConfession(Profile.Confession.valueOf(rs.getString("pConfession")));
		profile.setSmoker(rs.getBoolean("smoker"));
		profile.setHairColor(Profile.HairColor.valueOf(rs.getString("pHairColor")));
		profile.setGender(Profile.Gender.valueOf(rs.getString("pGender")));

		searchProfile.setProfile(profile);
		return searchProfile;
	}


}


