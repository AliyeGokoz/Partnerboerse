package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdm.partnerboerse.shared.bo.*;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.Gender;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;
import de.hdm.partnerboerse.shared.bo.Profile.Orientation;

/**
 * Die Mapper-Klasse <code>ProfileMapper</code> bildet <code>Profile
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see DescriptionMapper, FavoritesListMapper, InfoMapper, OptionMapper,
 *      BlockingMapper, SearchProfileMapper, SelectionMapper, SimilarityMapper,
 *      VisitListMapper
 * @author Claudia
 */

public class ProfileMapper {

	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender, orientation FROM profiles ";

	/**
	 * Die Instantiierung der Klasse ProfileMapper erfolgt nur einmal. Dies wird
	 * auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see blockingMapper()
	 */
	private static ProfileMapper profileMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */

	protected ProfileMapper() {

	}

	/**
	 * Durch
	 * <code>ProfileMapper.profileMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>ProfileMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des ProfileMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>ProfileMapper</code>-Objekt.
	 * 
	 * @see profileMapper
	 */

	public static ProfileMapper profileMapper() {
		if (profileMapper == null) {
			profileMapper = new ProfileMapper();
		}
		return profileMapper;
	}

	/**
	 * Einfügen eines <code>Profile</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param profile
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Profile - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */
	public Profile insert(Profile profile) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM profiles ");

			if (rs.next()) {
				/*
				 * profile erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				profile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				// Einfügeoperation erfolgt
				String sql = "INSERT INTO profiles (id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender, orientation) "
						+ "VALUES (" + profile.getId() + ",'" + profile.getFirstName() + "','" + profile.getLastName()
						+ "','" + simpleDateFormat.format(profile.getDateOfBirth()) + "','" + profile.geteMail()

						+ "'," + profile.getHeight() + ",'" + profile.getConfession() + "','"
						+ (profile.isSmoker() ? 1 : 0) + "','" + profile.getHairColor() + "','" + profile.getGender()
						+ "', '" + profile.getOrientation() + "')";
				System.out.println(sql);
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Rückgabe, des evtl. korrigierten Profiles.
		return profile;
	}

	/**
	 * Wiederholtes Schreiben eines Profile-Objekts in die Datenbank.
	 * 
	 * @param profile,
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Profile update(Profile profile) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			stmt.executeUpdate("UPDATE profiles " + "SET firstName=\"" + profile.getFirstName() + "\", " + "lastName=\""
					+ profile.getLastName() + "\", " + "dateOfBirth=\""
					+ simpleDateFormat.format(profile.getDateOfBirth()) + "\", " + "email=\"" + profile.geteMail()
					+ "\", " + "height=\"" + profile.getHeight() + "\", " + "confession=\""

					+ profile.getConfession() + "\", " + "smoker=\"" + (profile.isSmoker() ? 1 : 0) + "\", "
					+ "hairColor=\"" + profile.getHairColor() + "\", " + "gender=\"" + profile.getGender()
					+ "\", orientation=\"" + profile.getOrientation() + "\"" + "WHERE id=" + profile.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		// Rückgabe, des evtl. korrigierten Profiles.
		return profile;
	}

	/**
	 * Löschen der Daten eines <code>Profile</code>-Objekts aus der Datenbank.
	 * 
	 * @param profile
	 *            das aus der DB zu löschende Objekt
	 */
	public void delete(Profile profile) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM profiles " + "WHERE id=" + profile.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Profile.
	 * 
	 * @return Eine ArrayList mit Profile-Objekten, die sämtliche Profile
	 *         repräsentieren. Bei evtl. Exceptions wird eine partiell gefüllte
	 *         oder ggf. auch leere ArrayList zurückgeliefert.
	 */
	public ArrayList<Profile> findAll() {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + "ORDER BY lastName");

			// Für jeden Eintrag im Suchergebnis wird nun ein Profile-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {
				Profile profile = map(rs);

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Suchen eines Profils mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return Profile-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */
	public Profile findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + "WHERE id=" + id + " ORDER BY lastName");

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
	 * Auslesen eines Profils mit einer bestimmten E-Mail Adresse. Da eine
	 * E-Mail Adresse nur einem Profil zugehört, kann nur ein Profile-Objekt
	 * ausgegeben werden.
	 * 
	 * @param email
	 * @return Profile-Objekt, das der übergebenen E-Mail Adresse entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */
	public Profile findByEmail(String email) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE email = '" + email + "' LIMIT 1");

			/*
			 * Da email eindeutig ist, kann maximal nur ein Tupel zurückgegeben
			 * werden. Prüfung, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {

				// Umwandlung des Ergebnis-Tupel in ein Objekt und Ausgabe des
				// Ergebnis-Objekts
				return map(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Auslesen aller Profile die einem gegebenen SearchProfile-Objekt
	 * entsprechen. Da mehrere Profile einem Suchprofil entsprechen können,
	 * können mehrere Profile-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param searchProfile,
	 *            das Objekt anhand dessen alle entsprechenden Profile
	 *            ausgegeben werden sollen.
	 * @return Eine ArrayList mit Profile-Objekten, die sämtliche Profile des
	 *         vorgegebenen Suchprofils repräsentieren.
	 */
	public ArrayList<Profile> findBySearchProfile(SearchProfile searchProfile) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			String sql = "SELECT p1.id, p1.firstName, p1.lastName, p1.dateOfBirth, p1.email, p1.height, p1.confession, p1.smoker, p1.hairColor, p1.gender, p1.orientation, s1.similarityValue FROM profiles p1 LEFT JOIN similarities s1 ON p1.id = s1.toProfile LEFT JOIN infos i1 ON p1.id = i1.profileId WHERE p1.id > 0 ";
			if (searchProfile.getId() != 0) {
				sql += " AND (SELECT COUNT(*) FROM infos i1 INNER JOIN infos i2 ON i2.searchprofileId = "
						+ searchProfile.getId()
						+ " WHERE i1.profileId = p1.id AND i1.informationValue = i2.informationValue) = (SELECT COUNT(*) FROM infos i3 WHERE i3.searchprofileId = "
						+ searchProfile.getId() + ")";
			}

			if (searchProfile.getFromHeight() != 0) {
				sql += " AND p1.height > " + searchProfile.getFromHeight() + " ";
			}

			if (searchProfile.getToHeight() != 0) {
				sql += " AND p1.height < " + searchProfile.getToHeight() + " ";
			}

			if (searchProfile.getHairColor() != null && searchProfile.getHairColor() != HairColor.DEFAULT) {
				sql += " AND p1.hairColor = '" + searchProfile.getHairColor() + "' ";
			}

			if (searchProfile.getConfession() != null && searchProfile.getConfession() != Confession.DEFAULT) {
				sql += " AND p1.confession = '" + searchProfile.getConfession() + "' ";
			}

			if (searchProfile.getGender() != null) {
				sql += " AND p1.gender = '" + searchProfile.getGender().name() + "' ";
			} else {
				Profile profile = searchProfile.getProfile();
				Gender gender = profile.getGender();

				Orientation orientation = searchProfile.getProfile().getOrientation();
				if (gender != null && gender != Gender.OTHERS && orientation != null) {
					if (orientation == Orientation.HETERO) {
						Gender searchedGender = gender == Gender.MALE ? Gender.FEMALE : Gender.MALE;
						sql += " AND p1.gender = '" + searchedGender.name() + "' ";
					} else if (orientation == Orientation.HOMO) {
						sql += " AND p1.gender = '" + gender.name() + "' ";
					}
				}
			}

			if (searchProfile.getFromAge() != 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar instance = Calendar.getInstance();
				instance.add(Calendar.YEAR, searchProfile.getFromAge());
				sql += " AND p1.dateOfBirth < '" + simpleDateFormat.format(instance.getTime()) + "' ";
			}

			if (searchProfile.getToAge() != 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Calendar instance = Calendar.getInstance();
				instance.add(Calendar.YEAR, -searchProfile.getToAge());
				sql += " AND p1.dateOfBirth > '" + simpleDateFormat.format(instance.getTime()) + "' ";
			}

			if (searchProfile.isNoVisited()) {
				sql += " AND NOT EXISTS (SELECT * FROM visits WHERE visits.fromProfile = "
						+ searchProfile.getProfile().getId() + " AND visits.toProfile = p1.id)";
			}

			sql += " AND NOT EXISTS (SELECT * FROM blockings WHERE (blockings.fromProfile = "
					+ searchProfile.getProfile().getId()
					+ " AND blockings.toProfile = p1.id) OR (blockings.fromProfile = p1.id AND blockings.toProfile = "
					+ searchProfile.getProfile().getId() + "))";

			sql += " AND p1.id != " + searchProfile.getProfile().getId();
			sql += " AND s1.fromProfile = " + searchProfile.getProfile().getId();
			sql += " GROUP BY p1.id ORDER BY s1.similarityValue DESC";

			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

			// Für jeden Eintrag im Suchergebnis wird nun ein Profile-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("p1.id"));
				profile.setFirstName(rs.getString("p1.firstName"));
				profile.setLastName(rs.getString("p1.lastName"));
				profile.setDateOfBirth(rs.getDate("p1.dateOfBirth"));
				profile.seteMail(rs.getString("p1.email"));
				profile.setHeight(rs.getInt("p1.height"));
				profile.setConfession(Profile.Confession.valueOf(rs.getString("p1.confession")));
				profile.setSmoker(rs.getBoolean("p1.smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs.getString("p1.hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("p1.gender")));
				profile.setOrientation(Profile.Orientation.valueOf(rs.getString("p1.orientation")));
				profile.setSimilarityValue(rs.getDouble("s1.similarityValue"));

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Profile deren Ähnlichkeitswert zu einem gegebenen
	 * Profil-Objekt größer als 0,5 ist.
	 * 
	 * Da mehrere Profile diesen Ähnlichkeitswert besitzen können, können
	 * mehrere Profile-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param fromProfile,
	 *            das Profil dessen ähnliche Profile auszulesen sind
	 * @return Eine ArrayList mit Profile-Objekten, deren Ähnlichkeitswert zum
	 *         gegebenen Profil-Objekt größer 0,5 ist.
	 */

	public ArrayList<Profile> findMostSimilarProfiles(Profile fromProfile) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender, orientation, similarities.similarityValue FROM profiles similarProfiles INNER JOIN similarities ON similarities.toProfile = similarProfiles.id WHERE similarities.fromProfile = "
							+ fromProfile.getId()
							+ " AND  similarities.similarityValue > 0.5 ORDER BY similarities.similarityValue DESC");

			// Für jeden Eintrag im Suchergebnis wird nun ein Profile-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));
				profile.setOrientation(Profile.Orientation.valueOf(rs.getString("orientation")));

				Similarity similarity = new Similarity();
				similarity.setFromProfile(fromProfile);

				profile.setSimilarityValue(rs.getDouble("similarities.similarityValue"));

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Diese Methode bildet das Resultset auf ein Java - Objekt ab.
	 * 
	 * @param rs,
	 *            das Resultset das auf ein Java-Objekt abgebildet werden soll
	 * @return Profile-Objekt
	 */

	private Profile map(ResultSet rs) throws SQLException {
		Profile profile = new Profile();
		profile.setId(rs.getInt("id"));
		profile.setFirstName(rs.getString("firstName"));
		profile.setLastName(rs.getString("lastName"));
		profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
		profile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));
		profile.setSmoker(rs.getBoolean("smoker"));
		profile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
		profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));
		profile.setOrientation(Profile.Orientation.valueOf(rs.getString("orientation")));
		return profile;
	}

}
