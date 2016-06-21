package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdm.partnerboerse.shared.bo.*;
import de.hdm.partnerboerse.shared.bo.Profile.Confession;
import de.hdm.partnerboerse.shared.bo.Profile.HairColor;

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
				String sql = "INSERT INTO profiles (id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender) "
						+ "VALUES (" + profile.getId() + ",'" + profile.getFirstName() + "','" + profile.getLastName()
						+ "','" + simpleDateFormat.format(profile.getDateOfBirth()) + "','" + profile.geteMail()

						+ "'," + profile.getHeight() + ",'" + profile.getConfession() + "','"
						+ (profile.isSmoker() ? 1 : 0) + "','" + profile.getHairColor() + "','" + profile.getGender()
						+ "')";
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
					+ "hairColor=\"" + profile.getHairColor() + "\", " + "gender=\"" + profile.getGender() + "\" "
					+ "WHERE id=" + profile.getId());

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

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "ORDER BY lastName");

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
			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
							+ "WHERE id=" + id + " ORDER BY lastName");

			/*
			 * Da id der Primärschlüssel ist, kann maximal nur ein Tupel
			 * zurückgegeben werden. Prüfung, ob ein Ergebnis vorliegt.
			 */
			if (rs.next()) {
				// Umwandlung des Ergebnis-Tupel in ein Objekt und Ausgabe des
				// Ergebnis-Objekts

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

				return profile;
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
			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles WHERE email = '" + email + "' LIMIT 1");

			/*
			 * Da email eindeutig ist, kann maximal nur ein Tupel zurückgegeben
			 * werden. Prüfung, ob ein Ergebnis vorliegt.
			 */
			while (rs.next()) {

				// Umwandlung des Ergebnis-Tupel in ein Objekt und Ausgabe des
				// Ergebnis-Objekts
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

				return profile;
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

			String sql = "SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles WHERE profiles.id > 0";

			if (searchProfile.getFromHeight() != 0) {
				sql += " AND profiles.height > " + searchProfile.getFromHeight() + " ";
			}

			if (searchProfile.getToHeight() != 0) {
				sql += " AND profiles.height < " + searchProfile.getToHeight() + " ";
			}

			if (searchProfile.getHairColor() != null && searchProfile.getHairColor() != HairColor.DEFAULT) {
				sql += " AND profiles.hairColor = '" + searchProfile.getHairColor() + "' ";
			}

			if (searchProfile.getConfession() != null && searchProfile.getConfession() != Confession.DEFAULT) {
				sql += " AND profiles.confession = '" + searchProfile.getConfession() + "' ";
			}

			if (searchProfile.getGender() != null) {
				sql += " AND profiles.gender = '" + searchProfile.getGender() + "' ";
			}

			if (searchProfile.getFromAge() != 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Calendar instance = Calendar.getInstance();
				instance.add(Calendar.YEAR, searchProfile.getFromAge());
				sql += " AND profiles.dateOfBirth < '" + simpleDateFormat.format(instance.getTime()) + "' ";
			}

			if (searchProfile.getToAge() != 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

				Calendar instance = Calendar.getInstance();
				instance.add(Calendar.YEAR, -searchProfile.getToAge());
				sql += " AND profiles.dateOfBirth > '" + simpleDateFormat.format(instance.getTime()) + "' ";
			}

			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);

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
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender, similarities.similarityValue FROM profiles similarProfiles INNER JOIN similarities ON similarities.toProfile = similarProfiles.id WHERE similarities.fromProfile = "
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
	 * Auslesen aller von einem gegebenen Profil noch nicht besuchten Profile.
	 * 
	 * Da mehrere Profile von einem gegebenen Profil noch nicht besucht sein
	 * können, können mehrere Profile-Objekte in einer ArrayList ausgegeben
	 * werden.
	 * 
	 * @param visitingProfile,
	 *            das Profil dessen Nicht-Besuche ausgelesen werden sollen
	 * @return Eine ArrayList mit Profile-Objekten, die das gegebene
	 *         Profile-Objekt nicht besucht hat
	 */

	public ArrayList<Profile> findNotViewedProfiles(Profile vistingProfile) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles LEFT JOIN visits ON visits.toProfile = profiles.id WHERE visits.id IS NULL OR NOT visits.fromProfile = "
							+ vistingProfile.getId() + " GROUP BY profiles.id");

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

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	public static void main(String[] args) {
		ProfileMapper profileMapper = new ProfileMapper();
		profileMapper.findAll();
		profileMapper.findByEmail("");
		profileMapper.findByKey(1);
	}

}
