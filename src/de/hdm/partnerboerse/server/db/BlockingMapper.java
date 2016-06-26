package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>BlockingMapper</code> bildet <code>Blocking
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper, FavoritesListMapper, InfoMapper, OptionMapper,
 *      ProfileMapper, SearchProfileMapper, SelectionMapper, SimilarityMapper,
 *      VisitListMapper
 * @author Claudia
 */

public class BlockingMapper {

	//Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT blockings.id AS bid,"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender, "
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM blockings LEFT JOIN profiles AS fromProfile ON fromProfile.id = blockings.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = blockings.toProfile";

	/**
	 * Die Instantiierung der Klasse BlockingMapper erfolgt nur einmal. Dies
	 * wird auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see blockingMapper()
	 */

	private static BlockingMapper blockingMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */

	protected BlockingMapper() {

	}

	/**
	 * Durch
	 * <code>BlockingMapper.blockingMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>BlockingMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des BlockingMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>BlockingMapper</code>-Objekt.
	 * 
	 * @see blockingMapper
	 */

	public static BlockingMapper blockingMapper() {
		if (blockingMapper == null) {
			blockingMapper = new BlockingMapper();
		}
		return blockingMapper;
	}

	/**
	 * Einfügen eines <code>Blocking</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param blocking
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Blocking - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public Blocking insert(Blocking blocking) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM blockings ");

			if (rs.next()) {

				/*
				 * blocking erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */

				blocking.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO blockings (id, fromProfile, toProfile) VALUES (" + blocking.getId()
						+ ",'" + blocking.getFromProfile().getId() + "','" + blocking.getToProfile().getId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Rückgabe, des evtl. korrigierten Blockings.
		return blocking;
	}

	/**
	 * Wiederholtes Schreiben eines Blocking-Objekts in die Datenbank.
	 * 
	 * @param blocking,
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Blocking update(Blocking blocking) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE blockings " + "SET fromProfile=\"" + blocking.getFromProfile() + "\", "
					+ "toProfile=\"" + blocking.getToProfile() + "\" " + "WHERE id=" + blocking.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Rückgabe, des evtl. korrigierten Blockings.
		return blocking;
	}

	/**
	 * Löschen der Daten eines <code>Blocking</code>-Objekts aus der Datenbank.
	 * 
	 * @param blocking
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(Blocking blocking) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM blockings " + "WHERE id=" + blocking.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Kontaktsperren.
	 * 
	 * @return Eine ArrayList mit Blocking-Objekten, die sämtliche
	 *         Kontaktsperren repräsentieren. Bei evtl. Exceptions wird eine
	 *         partiell gefüllte oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<Blocking> findAll() {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Blocking> result = new ArrayList<Blocking>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT);

			// Für jeden Eintrag im Suchergebnis wird nun ein Blocking-Objekt
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
	 * Suchen einer Kontaktsperre mit vorgegebener ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return Blocking-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public Blocking findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE blockings.id=" + id + " ORDER BY fromProfile");

			/*
			 * Da id der Primärschlüssel ist, kann maximal nur ein Tupel
			 * zurückgegeben werden. Prüfung, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Umwandlung des Ergebnis-Tupel in ein Objekt und Ausgabe des
				// Ergebnis-Objekts
				return map(rs);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	/**
	 * Auslesen aller Kontaktsperren eines bestimmten Profils mit Hilfe der
	 * Profil-ID. Da ein Profil mehrere Kontaktsperren erheben kann, können
	 * mehrere Blocking-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Blocking-Objekten, die sämtliche
	 *         Kontaktsperren des vorgegebenen Profils repräsentieren.
	 */

	public ArrayList<Blocking> findByProfile(int profileId) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Blocking> result = new ArrayList<Blocking>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE fromProfile=" + profileId);

			// Für jeden Eintrag im Suchergebnis wird nun ein Blocking-Objekt
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
	 * Auslesen aller Kontaktsperren eines bestimmten Profils mit Hilfe eines
	 * Profil-Objekts. Da ein Profil mehrere Kontaktsperren erheben kann, können
	 * mehrere Blocking-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profile,
	 *            das Profil dessen Kontaktsperren ausgelesen werden sollen
	 * @return Eine ArrayList mit Blocking-Objekten, die sämtliche
	 *         Kontaktsperren des vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<Blocking> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	/**
	 * Diese Methode bildet das Resultset auf ein Java - Objekt ab.
	 * 
	 * @param rs,
	 *            das Resultset das auf ein Java-Objekt abgebildet werden soll
	 * @return Blocking-Objekt
	 */
	private Blocking map(ResultSet rs) throws SQLException {
		Blocking blocking = new Blocking();
		blocking.setId(rs.getInt("bid"));

		Profile profileFrom = new Profile();
		profileFrom.setId(rs.getInt("tpId"));
		profileFrom.setFirstName(rs.getString("tpFirstName"));
		profileFrom.setLastName(rs.getString("tpLastName"));
		profileFrom.setDateOfBirth(rs.getDate("tpDateOfBirth"));
		profileFrom.seteMail(rs.getString("tpEmail"));
		profileFrom.setHeight(rs.getInt("tpHeight"));
		profileFrom.setConfession(Profile.Confession.valueOf(rs.getString("tpConfession")));
		profileFrom.setSmoker(rs.getBoolean("tpSmoker"));
		profileFrom.setHairColor(Profile.HairColor.valueOf(rs.getString("tpHairColor")));
		profileFrom.setGender(Profile.Gender.valueOf(rs.getString("tpGender")));

		Profile profileTo = new Profile();
		profileTo.setId(rs.getInt("tpId"));
		profileTo.setFirstName(rs.getString("tpFirstName"));
		profileTo.setLastName(rs.getString("tpLastName"));
		profileTo.setDateOfBirth(rs.getDate("tpDateOfBirth"));
		profileTo.seteMail(rs.getString("tpEmail"));
		profileTo.setHeight(rs.getInt("tpHeight"));
		profileTo.setConfession(Profile.Confession.valueOf(rs.getString("tpConfession")));
		profileTo.setSmoker(rs.getBoolean("tpSmoker"));
		profileTo.setHairColor(Profile.HairColor.valueOf(rs.getString("tpHairColor")));
		profileTo.setGender(Profile.Gender.valueOf(rs.getString("tpGender")));

		blocking.setFromProfile(profileFrom);
		blocking.setToProfile(profileTo);

		return blocking;
	}

	/**
	 * Auslesen ob eine Kontaktsperre zwischen zwei gegebenen Profilen existiert.
	 * 
	 *  
	 * @param fromProfile und toProfile, die beiden Profile zwischen denen eine Kontaktsperre geprüft werden soll
	 *            
	 * @return true oder false, je nach dem ob eine Kontaktsperre existiert oder nicht
	 */
	
	public boolean doBlockingExist(Profile fromProfile, Profile toProfile) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT fromProfile FROM blockings WHERE fromProfile="
					+ fromProfile.getId() + " AND toProfile=" + toProfile.getId());

			while (rs.next()) {
				return true;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return false;
	}

	public ArrayList<Blocking> findWith(Profile with) {
		
		//DB-Verbindung holen
		Connection con = DBConnection.connection();
		
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Blocking> result = new ArrayList<Blocking>();
		
		try {
			
			//Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					BASE_SELECT + " WHERE fromProfile=" + with.getId() + " OR toProfile=" + with.getId());

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	
}
