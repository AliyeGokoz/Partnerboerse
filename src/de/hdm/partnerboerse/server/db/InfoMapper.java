package de.hdm.partnerboerse.server.db;

import java.sql.*;

import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>InfoMapper</code> bildet <code>Info
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper
 * @see FavoritesListMapper
 * @see BlockingMapper
 * @see OptionMapper
 * @see ProfileMapper 
 * @see SearchProfileMapper 
 * @see SelectionMapper 
 * @see SimilarityMapper
 * @see VisitListMapper
 * @author Roxana
 */

public class InfoMapper {

	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT infos.id AS id, informationValue,selections.id AS sid, selections.textualDescriptionForProfile AS stdfp,selections.textualDescriptionForSearchProfile AS stdfsp, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescriptionForProfile AS dtdfp,descriptions.textualDescriptionForSearchProfile AS dtdfsp, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, profiles.confession AS pc, smoker, profiles.hairColor AS ph, profiles.gender AS pg, profiles.orientation AS po, searchprofiles.id AS spid, searchprofiles.name AS spn, searchprofiles.fromAge AS spfa, searchprofiles.toAge AS spta, searchprofiles.fromHeight AS spfh, searchprofiles.toHeight AS spth, searchprofiles.hairColor AS sphc, searchprofiles.gender AS spg, searchprofiles.confession AS spc FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId LEFT JOIN searchprofiles ON searchprofiles.id = infos.searchprofileId ";

	/**
	 * Die Instantiierung der Klasse InfoMapper erfolgt nur einmal. Dies wird
	 * auch als <b>Singleton</b> bezeichnet.
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see infoMapper()
	 */

	private static InfoMapper infoMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new</code>.
	 */
	protected InfoMapper() {
	}

	/**
	 * Durch
	 * <code>InfoMapper.infoMapper()</code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>InfoMapper</code> existiert.
	 * Die Instantiierung des InfoMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>InfoMapper</code>-Objekt.
	 * 
	 * @see InfoMapper#infoMapper()
	 */

	public static InfoMapper infoMapper() {
		if (infoMapper == null) {
			infoMapper = new InfoMapper();
		}

		return infoMapper;
	}

	/**
	 * Einfügen eines <code>Info</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param info
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Info - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public Info insert(Info info) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM infos ");

			if (rs.next()) {

				/*
				 * info erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */

				info.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO infos (id, informationValue, profileId, searchprofileId, selectionId, descriptionId) "
								+ "VALUES (" + info.getId() + ",'" + info.getInformationValue() + "', "
								+ (info.getProfile() != null ? info.getProfile().getId() : "NULL") + ", "
								+ (info.getSearchProfile() != null ? info.getSearchProfile().getId() : "NULL") + ", "
								+ (info.getSelection() != null ? info.getSelection().getId() : "NULL") + ", "
								+ (info.getDescription() != null ? info.getDescription().getId() : "NULL") + ")");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Rückgabe, der evtl. korrigierten Info.
		return info;
	}

	/**
	 * Wiederholtes Schreiben eines Info-Objekts in die Datenbank.
	 * 
	 * @param info
	 *            , das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Info update(Info info) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE infos " + "SET informationValue=\"" + info.getInformationValue() + "WHERE id="
					+ info.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Info.
		return info;
	}

	/**
	 * Löschen der Daten eines <code>Info</code>-Objekts aus der Datenbank.
	 * 
	 * @param info
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(Info info) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM infos " + "WHERE id=" + info.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen einer Info mit vorgegebener ID. Da diese eindeutig ist, wird genau
	 * ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return Info-Objekt, das dem übergebenen Schlüssel entspricht, null bei
	 *         nicht vorhandenem DB-Tupel.
	 */

	public Info findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			String sql = BASE_SELECT + " WHERE infos.id=" + id;

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
	 * Auslesen aller Infos.
	 * 
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos
	 *         repräsentieren. Bei evtl. Exceptions wird eine partiell gefüllte
	 *         oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<Info> findAll() {

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			// Für jeden Eintrag im Suchergebnis wird nun ein Info-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.

			String sql = BASE_SELECT;

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Infos eines bestimmten Profils mit Hilfe der Profil-ID. Da
	 * ein Profil mehrere Infos erheben kann, können mehrere Info-Objekte in
	 * einer ArrayList ausgegeben werden.
	 * 
	 * @param profileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos des
	 *         vorgegebenen Profils repräsentieren.
	 */

	public ArrayList<Info> findByProfile(int profileId) {

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			String sql = BASE_SELECT + " WHERE infos.profileId=" + profileId + " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			// Für jeden Eintrag im Suchergebnis wird nun ein Info-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {

				Info info = map(rs);

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Infos eines bestimmten Profils mit Hilfe eines
	 * Profil-Objekts. Da ein Profil mehrere Infos hat, können mehrere
	 * Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profile Profil Objekt
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos des
	 *         vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<Info> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	/**
	 * Auslesen aller Infos einer bestimmten Auwahl mit Hilfe der Auswahl-ID. Da
	 * eine Auswahl mehrere Infos haben kann, können mehrere Info-Objekte in
	 * einer ArrayList ausgegeben werden.
	 * 
	 * @param selectionId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Auswahl repräsentieren.
	 */

	public ArrayList<Info> findBySelection(int selectionId) {

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Für jeden Eintrag im Suchergebnis wird nun ein Info-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.

			String sql = BASE_SELECT + " WHERE selectionId=" + selectionId + " ORDER BY id";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Infos einer bestimmten Auswahl mit Hilfe eines
	 * Selection-Objekts. Da ein Auswahl mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param selection Selection Objekt
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Selection repräsentieren.
	 */
	public ArrayList<Info> findBySelection(Selection selection) {

		return findBySelection(selection.getId());
	}

	/**
	 * Auslesen aller Infos einer bestimmten Beschreibung mit Hilfe der
	 * Beschreibungs-ID. Da eine Beschreibung mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param descriptionId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Description repräsentieren.
	 */
	public ArrayList<Info> findByDescription(int descriptionId) {

		// Vorbereiten der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Für jeden Eintrag im Suchergebnis wird nun ein Info-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.

			String sql = BASE_SELECT + " WHERE descriptions.id=" + descriptionId + " ORDER BY id";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Ergebnis-ArrayList zurückgeben
		return result;

	}

	/**
	 * Auslesen aller Infos einer bestimmten Beschreibung mit Hilfe eines
	 * Description-Objekts. Da ein Beschreibung mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param description Description Objekt
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Description repräsentieren.
	 */
	public ArrayList<Info> findByDescription(Description description) {
		return findByDescription(description.getId());
	}

	/**
	 * Auslesen aller Infos eines bestimmten Suchprofils mit Hilfe der
	 * Suchprofil-ID. Da ein Suchprofil mehrere Infos haben kann, können mehrere
	 * Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param searchProfileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos des
	 *         vorgegebenen Suchprofils repräsentieren.
	 */

	public ArrayList<Info> findBySearchProfile(int searchProfileId) {

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Info> result = new ArrayList<Info>();

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			String sql = BASE_SELECT + " WHERE searchprofileId=" + searchProfileId + " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			// Für jeden Eintrag im Suchergebnis wird nun ein Info-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {

				Info info = map(rs);

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Infos eines bestimmten Suchprofils mit Hilfe eines
	 * SearchProfile-Objekts. Da ein Suchprofil mehrere Infos haben kann, können
	 * mehrere Info-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param searchProfile SearchProfile Objekt 
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos des
	 *         vorgegebenen SearchProfile repräsentieren.
	 */
	public ArrayList<Info> findBySearchProfile(SearchProfile searchProfile) {
		return findBySearchProfile(searchProfile.getId());
	}

	/**
	 * Prüft ob bereits eine Information in der Datenbank existiert.
	 * 
	 * @param info
	 *            Das Informations-Objekt welches inhaltlich auf die Existenz in
	 *            der Datenbank geprüft werden soll.
	 * @return true wenn bereits eine Information mit den gegebenen Daten in der
	 *         Datenbank existiert.
	 */
	public boolean doInformationExist(Info info) {

		Connection con = DBConnection.connection();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT id FROM infos WHERE informationValue = '" + info.getInformationValue() + "' ";
			if (info.getSelection() != null) {
				sql += " AND selectionId = " + info.getSelection().getId();
			}
			if (info.getDescription() != null) {
				sql += " AND descriptionId = " + info.getDescription().getId();
			}
			if (info.getProfile() != null) {
				sql += " AND profileId = " + info.getProfile().getId();
			}
			if (info.getSearchProfile() != null) {
				sql += " AND searchprofileId = " + info.getSearchProfile().getId();
			}
			sql += " LIMIT 1";

			ResultSet rs = stmt.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * Diese Methode bildet das ResultSet auf ein Java-Objekt ab.
	 *
	 * @param rs
	 *            , das ResultSet, dass auf ein Java-Objekt abgebildet werden
	 *            soll
	 * @return Info-Objekt
	 */

	private Info map(ResultSet rs) throws SQLException {
		Info info = new Info();
		info.setId(rs.getInt("id"));
		info.setInformationValue(rs.getString("informationValue"));

		if (rs.getInt("pid") != 0) {

			Profile profile = new Profile();
			profile.setId(rs.getInt("pid"));
			profile.setFirstName(rs.getString("firstName"));
			profile.setLastName(rs.getString("lastName"));
			profile.setDateOfBirth(rs.getDate("dateOfBirth"));
			profile.seteMail(rs.getString("email"));
			profile.setHeight(rs.getInt("height"));
			profile.setConfession(Profile.Confession.valueOf(rs.getString("pc")));
			profile.setSmoker(rs.getBoolean("smoker"));
			profile.setHairColor(Profile.HairColor.valueOf(rs.getString("ph")));
			profile.setGender(Profile.Gender.valueOf(rs.getString("pg")));
			profile.setOrientation(Profile.Orientation.valueOf(rs.getString("po")));

			info.setProfile(profile);
		}

		if (rs.getInt("spid") != 0) {

			SearchProfile searchProfile = new SearchProfile();
			searchProfile.setId(rs.getInt("spid"));
			searchProfile.setName(rs.getString("spn"));
			searchProfile.setFromAge(rs.getInt("spfa"));
			searchProfile.setToAge(rs.getInt("spta"));
			searchProfile.setFromHeight(rs.getInt("spfh"));
			searchProfile.setToHeight(rs.getInt("spth"));
			String hairColor = rs.getString("sphc");
			if (hairColor != null) {
				searchProfile.setHairColor(Profile.HairColor.valueOf(hairColor));
			}
			String gender = rs.getString("spg");
			if (gender != null) {
				searchProfile.setGender(Profile.Gender.valueOf(gender));
			}
			String confession = rs.getString("spc");
			if (confession != null) {
				searchProfile.setConfession(Profile.Confession.valueOf(confession));
			}

			info.setSearchProfile(searchProfile);
		}

		Description description = new Description();
		description.setId(rs.getInt("did"));
		description.setTextualDescriptionForProfile(rs.getString("dtdfp"));
		description.setTextualDescriptionForSearchProfile(rs.getString("dtdfsp"));
		description.setPropertyName(rs.getString("dpn"));

		if (description.getId() != 0) {
			info.setDescription(description);
		}

		Selection selection = new Selection();
		selection.setId(rs.getInt("sid"));
		selection.setTextualDescriptionForProfile(rs.getString("stdfp"));
		selection.setTextualDescriptionForSearchProfile(rs.getString("stdfsp"));
		selection.setPropertyName(rs.getString("spn"));

		if (selection.getId() != 0) {
			info.setSelection(selection);
		}
		return info;
	}

}


