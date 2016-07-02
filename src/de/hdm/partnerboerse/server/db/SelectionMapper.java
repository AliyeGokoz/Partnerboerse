package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

/**
 * Die Mapper-Klasse <code>SelectionMapper</code> bildet <code>Selection
 * </code>-Objekte auf Datensätze in einer relationalen Datenbank ab.Durch die
 * Bereitstellung verschiedener Methoden können mit deren Hilfe beispielsweise
 * Objekte erzeugt, editiert, gelöscht oder gesucht werden. Das sogenannte
 * Mapping erfolgt bidirektional, d.h. Objekte können in DB-Strukturen und
 * DB-Strukturen in Objekte umgewandelt werden.
 * 
 * @see BlockingMapper
 * @see DescriptionMapper
 * @see InfoMapper
 * @see FavoritesListMapper
 * @see ProfileMapper
 * @see SearchProfileMapper
 * @see OptionMapper
 * @see SimilarityMapper
 * @see VisitListMapper
 * @author Roxana
 */

public class SelectionMapper {

	// Grundlegendes Select-Statement
		private static final String BASE_SELECT = "SELECT id, textualDescriptionForProfile, textualDescriptionForSearchProfile, propertyName FROM selections ";
	
		/**
	 * Die Instantiierung der Klasse SelectionMapper erfolgt nur einmal. Dies
	 * wird auch als <b>Singleton</b> bezeichnet.
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see SelectionMapper#selectionMapper()
	 */

	static SelectionMapper selectionMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new</code>.
	 */

	protected SelectionMapper() {
	}

	/**
	 * Durch
	 * <code>SelectionMapper.selectionMapper()</code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>SelectionMapper</code> existiert.
	 * Die Instantiierung des SelectionMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>SelectionMapper</code>-Objekt.
	 * 
	 * @see SelectionMapper#selectionMapper
	 */

	public static SelectionMapper selectionMapper() {
		if (selectionMapper == null) {
			selectionMapper = new SelectionMapper();
		}

		return selectionMapper;
	}

	/**
	 * Einfügen eines <code>Selection</code>-Objekts in die Datenbank. Dabei
	 * wird auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param selection
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Selection - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public Selection insert(Selection selection) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM selections ");

			if (rs.next()) {

				/*
				 * selection erhält den bisher maximalen, nun um 1
				 * inkrementierten Primärschlüssel.
				 */

				selection.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO selections (id, textualDescriptionForProfile, textualDescriptionForSearchProfile, propertyName) "
						+ "VALUES ("
						+ selection.getId()
						+ ",'"
						+ selection.getTextualDescriptionForProfile()
						+ "','"
						+ selection.getTextualDescriptionForSearchProfile()
						+ "','"
						+ selection.getPropertyName() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Selection.
		return selection;

	}

	/**
	 * Wiederholtes Schreiben eines Selection-Objekts in die Datenbank.
	 * 
	 * @param selection
	 *            , das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Selection update(Selection selection) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE selections " + "SET textualDescriptionForProfile=\""
					+ selection.getTextualDescriptionForProfile() + "\", " + "textualDescriptionForSearchProfile=\""
					+ selection.getTextualDescriptionForSearchProfile() + "\", " + "propertyName=\""
					+ selection.getPropertyName() + "WHERE id=" + selection.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe, der evtl. korrigierten Selection.
		return selection;
	}

	/**
	 * Löschen der Daten eines <code>Selection</code>-Objekts aus der Datenbank.
	 * 
	 * @param selection
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(Selection selection) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM selections " + "WHERE id="
					+ selection.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Suchen einer Auswahl mit vorgegebener ID. Da diese eindeutig ist, wird
	 * genau ein Objekt zurückgegeben.
	 * 
	 * @param id
	 *            Primärschlüsselattribut in DB
	 * @return Selection-Objekt, das dem übergebenen Schlüssel entspricht, null
	 *         bei nicht vorhandenem DB-Tupel.
	 */

	public Selection findByKey(int id) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = stmt
					.executeQuery(BASE_SELECT + "WHERE id=" + id + " ORDER BY propertyName");

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
	 * Auslesen aller Auswahlen.
	 * 
	 * @return Eine ArrayList mit Selection-Objekten, die sämtliche Auswahlen
	 *         repräsentieren. Bei evtl. Exceptions wird eine partiell gefüllte
	 *         oder ggf. auch leere ArrayList zurückgeliefert.
	 */

	public ArrayList<Selection> findAll() {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Selection> result = new ArrayList<Selection>();

		try {
			Statement stmt = con.createStatement();

			// Für jeden Eintrag im Suchergebnis wird nun ein Selection-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.

			ResultSet rs = stmt
					.executeQuery(BASE_SELECT + "ORDER BY propertyName");

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
	 * Diese Methode bildet das Resultset auf ein Java - Objekt ab.
	 * 
	 * @param rs,
	 *            das Resultset das auf ein Java-Objekt abgebildet werden soll
	 * @return Selection-Objekt
	 */

	private Selection map(ResultSet rs) throws SQLException {
		Selection selection = new Selection();
		selection.setId(rs.getInt("id"));
		selection.setTextualDescriptionForProfile(rs.getString("textualDescriptionForProfile"));
		selection.setTextualDescriptionForSearchProfile(rs.getString("textualDescriptionForSearchProfile"));
		selection.setPropertyName(rs.getString("propertyName"));
		return selection; 	
	}

	
}
