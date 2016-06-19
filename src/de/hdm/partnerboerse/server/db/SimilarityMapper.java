package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>SimilarityMapper</code> bildet <code>Similarity
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper, FavoritesListMapper, InfoMapper, OptionMapper,
 *      ProfileMapper, SearchProfileMapper, SelectionMapper, BlockingMapper,
 *      VisitListMapper
 * @author Claudia
 */
public class SimilarityMapper {

	private static final String BASE_SELECT = "SELECT similarities.id AS sid, similarityValue, "
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender,"
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM similarities LEFT JOIN profiles AS fromProfile ON fromProfile.id = similarities.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = similarities.toProfile";

	/**
	 * Die Instantiierung der Klasse SimilarityMapper erfolgt nur einmal. Dies
	 * wird auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see similarityMapper()
	 */
	private static SimilarityMapper similarityMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */
	protected SimilarityMapper() {

	}

	/**
	 * Durch
	 * <code>SimilarityMapper.similarityMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>SimilarityMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des SimilarityMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>SimilarityMapper</code>-Objekt.
	 * 
	 * @see similarityMapper
	 */

	public static SimilarityMapper similarityMapper() {
		if (similarityMapper == null) {
			similarityMapper = new SimilarityMapper();
		}
		return similarityMapper;
	}

	/**
	 * Einfügen eines <code>Similarity</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param similarity
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Similarity - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public Similarity insert(Similarity similarity) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM similarities");

			if (rs.next()) {

				/*
				 * similarity erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */

				similarity.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO similarities (id, fromProfile, toProfile, similarityValue) "
						+ "VALUES (" + similarity.getId() + "," + similarity.getFromProfile().getId() + ","
						+ similarity.getToProfile().getId() + "," + similarity.getSimilarityValue() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Similarity.
		return similarity;
	}

	/**
	 * Wiederholtes Schreiben eines Similarity-Objekts in die Datenbank.
	 * 
	 * @param similarity,
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Similarity update(Similarity similarity) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE similarities " + "SET fromProfile=\"" + similarity.getFromProfile().getId()
					+ "\", " + "toProfile=\"" + similarity.getToProfile().getId() + "\", " + "similarityValue=\""
					+ similarity.getSimilarityValue() + "\" " + "WHERE id=" + similarity.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Similarity.
		return similarity;
	}

	/**
	 * Löschen der Daten eines <code>Similarity</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param similarity
	 *            das aus der DB zu löschende Objekt
	 */
	public void delete(Similarity similarity) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM similarities " + "WHERE id=" + similarity.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Ähnlichkeiten.
	 * 
	 * @return Eine ArrayList mit Similarity-Objekten, die sämtliche
	 *         Ähnlichkeiten repräsentieren. Bei evtl. Exceptions wird eine
	 *         partiell gefüllte oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<Similarity> findAll() {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Similarity> result = new ArrayList<Similarity>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT);

			// Für jeden Eintrag im Suchergebnis wird nun ein Similarity-Objekt
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
	 * Suchen einer Ähnlichkeit mit vorgegebener ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return Similarity-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */
	public Similarity findByKey(int id) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE similarities.id=" + id + " ORDER BY fromProfile");

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
	 * Auslesen aller Ähnlichkeiten eines bestimmten Profils mit Hilfe der
	 * Profil-ID. Da ein Profil mehrere Ähnlichkeiten haben kann, können mehrere
	 * Similarity-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profileId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mitSimilarity-Objekten, die sämtliche
	 *         Ähnlichkeiten des vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<Similarity> findByProfile(int profileId) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Similarity> result = new ArrayList<Similarity>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE fromProfile=" + profileId);

			// Für jeden Eintrag im Suchergebnis wird nun ein Similarity-Objekt
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
	 * Auslesen aller Ähnlichkeiten eines bestimmten Profils mit Hilfe eines
	 * Profil-Objekts. Da ein Profil mehrere Ähnlichkeiten haben kann, können
	 * mehrere Similarity-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param profile,
	 *            das Profil dessen Ähnlichkeiten ausgelesen werden sollen
	 * @return Eine ArrayList mit Similarity-Objekten, die sämtliche
	 *         Ähnlichkeiten des vorgegebenen Profils repräsentieren.
	 */
	public ArrayList<Similarity> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	public Similarity findByFromAndTo(Profile from, Profile to) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					BASE_SELECT + " WHERE fromProfile=" + from.getId() + " AND toProfile=" + to.getId() + " LIMIT 1");

			while (rs.next()) {

				return map(rs);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Similarity> findWith(Profile with) {
		Connection con = DBConnection.connection();
		ArrayList<Similarity> result = new ArrayList<Similarity>();
		
		try {
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

	private Similarity map(ResultSet rs) throws SQLException {
		Similarity similarity = new Similarity();
		similarity.setId(rs.getInt("sid"));
		similarity.setSimilarityValue(rs.getDouble("similarityValue"));

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

		similarity.setFromProfile(profileFrom);
		similarity.setToProfile(profileTo);

		return similarity;

	}

	public static void main(String[] args) {
		SimilarityMapper similarityMapper = new SimilarityMapper();
		similarityMapper.findByKey(1);
		similarityMapper.findByProfile(1);
		similarityMapper.findAll();
	}

}
