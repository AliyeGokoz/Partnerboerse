package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>VisitListMapper</code> bildet <code>VisitList
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper, FavoritesListMapper, InfoMapper, OptionMapper,
 *      ProfileMapper, SearchProfileMapper, SelectionMapper, SimilarityMapper,
 *      BlockingMapper
 * @author Claudia
 */
public class VisitListMapper {

	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT visits.id AS vid,"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender, fromProfile.orientation AS fpOrientation,"
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender, toProfile.orientation AS tpOrientation FROM visits LEFT JOIN profiles AS fromProfile ON fromProfile.id = visits.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = visits.toProfile";

	/**
	 * Die Instantiierung der Klasse VisitListMapper erfolgt nur einmal. Dies
	 * wird auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see visitListMapper()
	 */
	private static VisitListMapper visitListMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */
	protected VisitListMapper() {

	}

	/**
	 * Durch
	 * <code>VisitListMapper.visitListMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>VisitListMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des VisitListMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>VisitListMapper</code>-Objekt.
	 * 
	 * @see visitListMapper
	 */
	public static VisitListMapper visitListMapper() {
		if (visitListMapper == null) {
			visitListMapper = new VisitListMapper();
		}
		return visitListMapper;
	}

	/**
	 * Einfügen eines <code>VisitList</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param visitList
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene VisitList - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */
	public VisitList insert(VisitList visitList) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM visits ");

			if (rs.next()) {
				/*
				 * visitList erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */
				visitList.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO visits (id, fromProfile, toProfile) " + "VALUES (" + visitList.getId()
						+ ",'" + visitList.getFromProfile().getId() + "','" + visitList.getToProfile().getId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Rückgabe, der evtl. korrigierten VisitList.
		return visitList;
	}

	/**
	 * Auslesen ob ein Besuch zwischen zwei gegebenen Profilen existiert.
	 * 
	 *  
	 * @param fromProfile und toProfile, die beiden Profile zwischen denen ein Besuch geprüft werden soll
	 *            
	 * @return true oder false, je nach dem ob ein Besuch existiert oder nicht
	 */
	
	public boolean doVisitListExist(Profile fromProfile, Profile toProfile) {
		try {
			Statement statement = DBConnection.connection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM visits WHERE fromProfile = "
					+ fromProfile.getId() + " AND toProfile = " + toProfile.getId());
			resultSet.next();
			return resultSet.getInt("count") > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Wiederholtes Schreiben eines VisitList-Objekts in die Datenbank.
	 * 
	 * @param visitList,
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */
	public VisitList update(VisitList visitList) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE visits " + "SET fromProfile=\"" + visitList.getFromProfile() + "\", "
					+ "toProfile=\"" + visitList.getToProfile() + "\" " + "WHERE id=" + visitList.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Rückgabe, der evtl. korrigierten VisitList.
		return visitList;
	}

	/**
	 * Löschen der Daten eines <code>VisitList</code>-Objekts aus der Datenbank.
	 * 
	 * @param visitList
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(VisitList visitList) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM visits " + "WHERE id=" + visitList.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Besuche.
	 * 
	 * @return Eine ArrayList mit VisitList-Objekten, die sämtliche Besuche
	 *         repräsentieren. Bei evtl. Exceptions wird eine partiell gefüllte
	 *         oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<VisitList> findAll() {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<VisitList> result = new ArrayList<VisitList>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT);

			// Für jeden Eintrag im Suchergebnis wird nun ein VisitList-Objekt
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
	 * Suchen eines Besuchs mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return VisitList-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public VisitList findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE visits.id=" + id);

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
	 * Auslesen aller Besuche eines bestimmten Profils mit Hilfe der Profil-ID.
	 * Da ein Profil mehrere Besuche haben kann, können mehrere
	 * VisitList-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit VisitList-Objekten, die sämtliche Besuche des
	 *         vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<VisitList> findByProfile(int profileId) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<VisitList> result = new ArrayList<VisitList>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE fromProfile=" + profileId);

			// Für jeden Eintrag im Suchergebnis wird nun ein VisitList-Objekt
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
	 * Auslesen aller Besuche eines bestimmten Profils mit Hilfe des
	 * Profil-Objekts. Da ein Profil mehrere Besuche haben kann, können mehrere
	 * VisitList-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param Profil-Objekt
	 * @return Eine ArrayList mit VisitList-Objekten, die sämtliche Besuche des
	 *         vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<VisitList> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	/**
	 * Diese Methode bildet das Resultset auf ein Java - Objekt ab.
	 * 
	 * @param rs,
	 *            das Resultset das auf ein Java-Objekt abgebildet werden soll
	 * @return VisitList-Objekt
	 */
	private VisitList map(ResultSet rs) throws SQLException {
		VisitList visitList = new VisitList();
		visitList.setId(rs.getInt("vid"));

		Profile profileFrom = new Profile();
		profileFrom.setId(rs.getInt("fpId"));
		profileFrom.setFirstName(rs.getString("fpFirstName"));
		profileFrom.setLastName(rs.getString("fpLastName"));
		profileFrom.setDateOfBirth(rs.getDate("fpDateOfBirth"));
		profileFrom.seteMail(rs.getString("fpEmail"));
		profileFrom.setHeight(rs.getInt("fpHeight"));
		profileFrom.setConfession(Profile.Confession.valueOf(rs.getString("fpConfession")));
		profileFrom.setSmoker(rs.getBoolean("fpSmoker"));
		profileFrom.setHairColor(Profile.HairColor.valueOf(rs.getString("fpHairColor")));
		profileFrom.setGender(Profile.Gender.valueOf(rs.getString("fpGender")));
		profileFrom.setOrientation(Profile.Orientation.valueOf(rs.getString("fpOrientation")));

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
		profileTo.setOrientation(Profile.Orientation.valueOf(rs.getString("tpOrientation")));
		
		visitList.setFromProfile(profileFrom);
		visitList.setToProfile(profileTo);

		return visitList;

	}

	/**
	 * Auslesen von Besuchen, in denen das gegebene Profil beteiligt ist.
	 * @param with 
	 *             ist das Profil, welches beteiligt sein muss.
	 * @return
	 *             ArrayList mit allen gefundenen Besuchen.
	 */
	
	public ArrayList<VisitList> findWith(Profile with) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<VisitList> result = new ArrayList<VisitList>();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery(BASE_SELECT + " WHERE fromProfile=" + with.getId() + " OR toProfile=" + with.getId());

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

}
