package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>DescriptionMapper</code> bildet <code>Description
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see BlockingMapper, FavoritesListMapper, InfoMapper, OptionMapper,
 *      ProfileMapper, SearchProfileMapper, SelectionMapper, SimilarityMapper,
 *      VisitListMapper
 * @author Roxana
 */

public class DescriptionMapper {
	
	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT id, textualDescriptionForProfile, textualDescriptionForSearchProfile, propertyName FROM descriptions";

	/**
	 * Die Instantiierung der Klasse DescriptionMapper erfolgt nur einmal. Dies
	 * wird auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see descriptionMapper()
	 */

	private static DescriptionMapper descriptionMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new<code>.
	 */

	protected DescriptionMapper() {
	}

	/**
	 * Durch
	 * <code>DescriptionMapper.descriptionMapper()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>DescriptionMapper<code> existiert.
	 * <p>
	 * Die Instantiierung des DescriptionMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>DescriptionMapper</code>-Objekt.
	 * 
	 * @see descriptionMapper
	 */

	public static DescriptionMapper descriptionMapper() {
		if (descriptionMapper == null) {
			descriptionMapper = new DescriptionMapper();
		}

		return descriptionMapper;
	}

	/**
	 * Einfügen eines <code>Description</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param description
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Description - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public Description insert(Description description) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM descriptions ");

			if (rs.next()) {

				/*
				 * description erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */

				description.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO descriptions (id, textualDescriptionForProfile, textualDescriptionForSearchProfile, propertyName) "
						+ "VALUES ("
						+ description.getId()
						+ ",'"
						+ description.getTextualDescriptionForProfile()
						+ "','"
						+ description.getTextualDescriptionForSearchProfile()
						+ "','"
						+ description.getPropertyName() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Description.
		return description;
	}

	/**
	 * Wiederholtes Schreiben eines Description-Objekts in die Datenbank.
	 * 
	 * @param description
	 *            , das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Description update(Description description) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE descriptions " + "SET textualDescriptionForProfile=\""
					+ description.getTextualDescriptionForProfile() + "\", " + "textualDescriptionForSearchProfile=\""
					+ description.getTextualDescriptionForSearchProfile() + "\", " + "propertyName=\""
					+ description.getPropertyName() + "WHERE id=" + description.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Description.
		return description;
	}

	/**
	 * Löschen der Daten eines <code>Description</code>-Objekts aus der
	 * Datenbank.
	 * 
	 * @param description
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(Description description) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM descriptions " + "WHERE id="
					+ description.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen einer Beschreibung mit vorgegebener ID. Da diese eindeutig ist,
	 * wird genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return Description-Objekt, das dem übergebenen Schlüssel entspricht,
	 *         null bei nicht vorhandenem DB-Tupel.
	 */

	public Description findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE id=" + id
					+ " ORDER BY propertyName");

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
	 * Auslesen aller Beschreibungen.
	 * 
	 * @return Eine ArrayList mit Description-Objekten, die sämtliche
	 *         Beschreibungen repräsentiert. Bei evtl. Exceptions wird eine
	 *         partiell gefüllte oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<Description> findAll() {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Description> result = new ArrayList<Description>();

		try {
			Statement stmt = con.createStatement();

			// Für jeden Eintrag im Suchergebnis wird nun ein Description-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.

			ResultSet rs = stmt.executeQuery(BASE_SELECT
					+ " ORDER BY propertyName");

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Diese Methode bildet das ResultSet auf ein Java-Objekt ab.
	 *
	 * @param rs
	 *            , das ResultSet, dass auf ein Java-Objekt abgebildet werden soll
	 * @return Description-Objekt
	 */
	
	private Description map(ResultSet rs) throws SQLException {
		Description description = new Description();
		description.setId(rs.getInt("id"));
		description.setTextualDescriptionForProfile(rs.getString("textualDescriptionForProfile"));
		description.setTextualDescriptionForSearchProfile(rs.getString("textualDescriptionForSearchProfile"));
		description.setPropertyName(rs.getString("propertyName"));
		return description;
	}



}