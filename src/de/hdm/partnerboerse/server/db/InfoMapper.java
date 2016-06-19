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
 * @see DescriptionMapper, FavoritesListMapper, BlockingMapper, OptionMapper,
 *      ProfileMapper, SearchProfileMapper, SelectionMapper, SimilarityMapper,
 *      VisitListMapper
 * @author Roxana
 */

public class InfoMapper {

	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT infos.id AS id, informationValue, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescription AS dtd, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId";

	/**
	 * Die Instantiierung der Klasse InfoMapper erfolgt nur einmal. Dies wird
	 * auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see infoMapper()
	 */

	private static InfoMapper infoMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */

	protected InfoMapper() {
	}

	/**
	 * Durch
	 * <code>InfoMapper.infoMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>InfoMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des InfoMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>InfoMapper</code>-Objekt.
	 * 
	 * @see infoMapper
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
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM infos ");

			if (rs.next()) {

				/*
				 * info erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */

				info.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO infos (id, informationValue, profileId, selectionId, descriptionId) "
						+ "VALUES ("
						+ info.getId()
						+ ",'"
						+ info.getInformationValue()
						+ "', "
						+ info.getProfile().getId()
						+ ", "
						+ (info.getSelection() != null ? info.getSelection()
								.getId() : "NULL")
						+ ","
						+ (info.getDescription() != null ? info
								.getDescription().getId() : "NULL") + ")");

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

			stmt.executeUpdate("UPDATE infos " + "SET informationValue=\""
					+ info.getInformationValue() + "WHERE id=" + info.getId());

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

			stmt.executeUpdate("DELETE FROM infos " + "WHERE id="
					+ info.getId());
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

			String sql = BASE_SELECT + " WHERE profileId=" + profileId
					+ " ORDER BY id";

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
	 * @param Profil
	 *            -Objekt
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

			String sql = BASE_SELECT + " WHERE selectionId=" + selectionId
					+ " ORDER BY id";

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
	 * @param Selection
	 *            -Objekt
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

			String sql = BASE_SELECT + " WHERE descriptions.id="
					+ descriptionId + " ORDER BY id";

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
	 * @param Description
	 *            -Objekt
	 * @return Eine ArrayList mit Info-Objekten, die sämtliche Infos der
	 *         vorgegebenen Description repräsentieren.
	 */

	public ArrayList<Info> findByDescription(Description description) {
		return findByDescription(description.getId());
	}

	/**
	 * Diese Methode bildet das ResultSet auf ein Java-Objekt ab.
	 *
	 * @param rs
	 *            , das ResultSet, dass auf ein Java-Objekt abgebildet werden soll
	 * @return Info-Objekt
	 */

	private Info map(ResultSet rs) throws SQLException {
		Info info = new Info();
		info.setId(rs.getInt("id"));
		info.setInformationValue(rs.getString("informationValue"));

		Profile profile = new Profile();
		profile.setId(rs.getInt("pid"));
		profile.setFirstName(rs.getString("firstName"));
		profile.setLastName(rs.getString("lastName"));
		profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
		profile.setConfession(Profile.Confession.valueOf(rs
				.getString("confession")));
		profile.setSmoker(rs.getBoolean("smoker"));
		profile.setHairColor(Profile.HairColor.valueOf(rs
				.getString("hairColor")));
		profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

		info.setProfile(profile);

		Description description = new Description();
		description.setId(rs.getInt("did"));
		description.setTextualDescription(rs.getString("dtd"));
		description.setPropertyName(rs.getString("dpn"));

		if (description.getId() != 0) {
			info.setDescription(description);
		}

		Selection selection = new Selection();
		selection.setId(rs.getInt("sid"));
		selection.setTextualDescription(rs.getString("std"));
		selection.setPropertyName(rs.getString("spn"));

		if (selection.getId() != 0) {
			info.setSelection(selection);
		}
		return info;
	}

}
