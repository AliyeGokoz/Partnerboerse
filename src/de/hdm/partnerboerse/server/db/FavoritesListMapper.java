package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>FavoritesListMapper</code> bildet <code>FavoritesList
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper, BlockingMapper, InfoMapper, OptionMapper,
 *      ProfileMapper, SearchProfileMapper, SelectionMapper, SimilarityMapper,
 *      VisitListMapper
 * @author Claudia
 */

public class FavoritesListMapper {

	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT favorites.id AS fid,"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender, "
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM favorites LEFT JOIN profiles AS fromProfile ON fromProfile.id = favorites.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = favorites.toProfile";

	/**
	 * Die Instantiierung der Klasse FavoritesListMapper erfolgt nur einmal.
	 * Dies wird auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see favoritesListMapper()
	 */
	private static FavoritesListMapper favoritesListMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */
	protected FavoritesListMapper() {

	}

	/**
	 * Durch
	 * <code>FavoritesListMapper.favoritesListMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>FavoritesListMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des FavoritesListMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>FavoritesListMapper</code>-Objekt.
	 * 
	 * @see favoritesListMapper
	 */
	public static FavoritesListMapper favoritesListMapper() {
		if (favoritesListMapper == null) {
			favoritesListMapper = new FavoritesListMapper();
		}
		return favoritesListMapper;
	}

	/**
	 * Einfügen eines <code>FavoritesList</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param favoritesList
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene FavoritesList - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public FavoritesList insert(FavoritesList favoritesList) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM favorites ");

			if (rs.next()) {

				/*
				 * favoritesList erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */
				favoritesList.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO favorites (id, fromProfile, toProfile) " + "VALUES ("
						+ favoritesList.getId() + ",'" + favoritesList.getFromProfile().getId() + "','"
						+ favoritesList.getToProfile().getId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Rückgabe, des evtl. korrigierten FavoritesList.
		return favoritesList;
	}

	/**
	 * Wiederholtes Schreiben eines FavoritesList-Objekts in die Datenbank.
	 * 
	 * @param favoritesList,
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public FavoritesList update(FavoritesList favoritesList) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE favorites " + "SET fromProfile=\"" + favoritesList.getFromProfile() + "\", "
					+ "toProfile=\"" + favoritesList.getToProfile() + "\" " + "WHERE id=" + favoritesList.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Rückgabe, des evtl. korrigierten Blockings.
		return favoritesList;
	}

	/**
	 * Löschen der Daten eines <code>FavoritesList</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param favoritesList
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(FavoritesList favoritesList) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM favorites " + "WHERE id=" + favoritesList.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Favoriten.
	 * 
	 * @return Eine ArrayList mit FavoritesList-Objekten, die sämtliche
	 *         Favoriten repräsentieren. Bei evtl. Exceptions wird eine partiell
	 *         gefüllte oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<FavoritesList> findAll() {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<FavoritesList> result = new ArrayList<FavoritesList>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT);

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// FavoritesList-Objekt
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
	 * Suchen eines Favoriten mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return FavoritesList-Objekt, das dem übergebenen Schlüssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public FavoritesList findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + "WHERE id=" + id + " ORDER BY fromProfile");

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
	 * Auslesen aller Favoriten eines bestimmten Profils mit Hilfe der
	 * Profil-ID. Da ein Profil mehrere Favoriten haben kann, können mehrere
	 * FavoritesList-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit FavoritesList-Objekten, die sämtliche
	 *         Favoriten des vorgegebenen Profils repräsentieren.
	 */

	public ArrayList<FavoritesList> findByProfile(int profileId) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<FavoritesList> result = new ArrayList<FavoritesList>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE fromProfile=" + profileId);

			// Für jeden Eintrag im Suchergebnis wird nun ein
			// FavoritesList-Objekt
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
	 * Auslesen ob eine Favoritisierung zwischen zwei gegebenen Profilen existiert.
	 * 
	 *  
	 * @param fromProfile und toProfile, die beiden Profile zwischen denen eine Favoritisierung geprüft werden soll
	 *            
	 * @return true oder false, je nach dem ob eine Favoritisierung existiert oder nicht
	 */
	public boolean doFavoritesListEntryExist(Profile fromProfile, Profile toProfile) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT fromProfile FROM favorites WHERE fromProfile="
					+ fromProfile.getId() + " AND toProfile=" + toProfile.getId());
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return false;
	}

	/**
	 * Auslesen aller Favoriten eines bestimmten Profils mit Hilfe eines
	 * Profil-Objekts. Da ein Profil mehrere Favoriten haben kann, können
	 * mehrere FavoritesList-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profile,
	 *            das Profil dessen Favoriten ausgelesen werden sollen
	 * @return Eine ArrayList mit FavoritesList-Objekten, die sämtliche
	 *         Favoriten des vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<FavoritesList> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	/**
	 * Diese Methode bildet das Resultset auf ein Java - Objekt ab.
	 * 
	 * @param rs,
	 *            das Resultset das auf ein Java-Objekt abgebildet werden soll
	 * @return FavoritesList-Objekt
	 */

	private FavoritesList map(ResultSet rs) throws SQLException {
		FavoritesList favoritesList = new FavoritesList();
		favoritesList.setId(rs.getInt("fid"));

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

		favoritesList.setFromProfile(profileFrom);
		favoritesList.setToProfile(profileTo);

		return favoritesList;

	}

}
