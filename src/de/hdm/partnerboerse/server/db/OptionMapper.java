package de.hdm.partnerboerse.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.log.LogService.LogLevel;

import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Selection;

/**
 * Die Mapper-Klasse <code>OptionMapper</code> bildet <code>Option
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
 * @see SelectionMapper
 * @see SimilarityMapper
 * @see VisitListMapper
 * @author Claudia
 */
public class OptionMapper {

	// Grundlegendes Select-Statement
	private static final String BASE_SELECT = "SELECT options.id AS id, `option`, selections.id AS sid, selections.textualDescriptionForProfile AS stdfp, selections.textualDescriptionForSearchProfile AS stdfsp, selections.propertyName AS spn FROM options LEFT JOIN selections ON selections.id = options.selectionId ";

	/**
	 * Die Instantiierung der Klasse OptionMapper erfolgt nur einmal. Dies wird
	 * auch als <b>Singleton</b> bezeichnet.
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 * 
	 * @see OptionMapper#optionMapper()
	 */
	private static OptionMapper optionMapper = null;

	/**
	 * Dieser geschützte Konstruktor verhindert das Erzeugen von neuen Instanzen
	 * dieser Klasse mit dem Aufruf <code>new</code>.
	 */
	protected OptionMapper() {
	}

	/**
	 * Durch
	 * <code>OptionMapper.optionMapper()</code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>OptionMapper</code> existiert.
	 * Die Instantiierung des OptionMapper sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return <code>OptionMapper</code>-Objekt.
	 * 
	 * @see optionMapper
	 */

	public static OptionMapper optionMapper() {
		if (optionMapper == null) {
			optionMapper = new OptionMapper();
		}

		return optionMapper;
	}

	/**
	 * Einfügen eines <code>Option</code>-Objekts in die Datenbank. Dabei wird
	 * auch der Primärschlüssel des übergebenen Objekts geprüft und ggf.
	 * berichtigt.
	 * 
	 * @param option
	 *            das zu speichernde Objekt
	 * @return das bereits übergebene Option - Objekt, jedoch mit ggf.
	 *         korrigierter <code>id</code>.
	 */

	public Option insert(Option option) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			// Momentan höchsten Primärschlüsselwert prüfen
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM options ");

			if (rs.next()) {

				/*
				 * option erhält den bisher maximalen, nun um 1 inkrementierten
				 * Primärschlüssel.
				 */
				option.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				// Einfügeoperation erfolgt
				stmt.executeUpdate("INSERT INTO infos (id, option) " + "VALUES (" + option.getId() + ",'"
						+ option.getOption() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Rückgabe der evtl. korrigierten Option
		return option;
	}

	/**
	 * Wiederholtes Schreiben eines Option-Objekts in die Datenbank.
	 * 
	 * @param option
	 *            das Objekt, das in die DB geschrieben werden soll
	 * @return das als Parameter übergebene Objekt
	 */

	public Option update(Option option) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE options " + "SET id=\"" + option.getId() + "\", " + "option=\""
					+ option.getOption() + "WHERE id=" + option.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Rückgabe der evtl. korrigierten Option
		return option;
	}

	/**
	 * Löschen der Daten eines <code>Option</code>-Objekts aus der Datenbank.
	 * 
	 * @param option
	 *            das aus der DB zu löschende Objekt
	 */

	public void delete(Option option) {
		// DB-Verbindung holen
		Connection con = DBConnection.connection();

		try {
			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM options " + "WHERE id=" + option.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Auslesen aller Optionen einer bestimmten Selection mit Hilfe der
	 * Selection-ID. Da eine Auswahl mehrere Optionen haben kann, können mehrere
	 * Option-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param selectionId
	 *            Fremdschlüsselattribut in DB
	 * @return Eine ArrayList mit Option-Objekten, die sämtliche Optionen der
	 *         vorgegebenen Auswahl repräsentieren.
	 */
	public ArrayList<Option> findBySelection(int selectionId) {

		// DB-Verbindung holen
		Connection con = DBConnection.connection();
		// Vorbereitung der Ergebnis-ArrayList
		ArrayList<Option> result = new ArrayList<Option>();

		
		String sql = "";
		try {

			// Leeres SQL-Statement (JDBC) anlegen
			Statement stmt = con.createStatement();

			sql += BASE_SELECT + " WHERE selectionId=" + selectionId + " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			// Für jeden Eintrag im Suchergebnis wird nun ein Option-Objekt
			// erstellt und zur Ergebnis-ArrayList hinzugefügt.
			while (rs.next()) {
				result.add(map(rs));
			}

		} catch (SQLException e) {
			Logger.getLogger("Error").log(Level.SEVERE, "Error while get options: " + sql, e);
			return null;
		}

		// Ergebnis-ArrayList zurückgeben
		return result;
	}

	/**
	 * Auslesen aller Optionen einer bestimmten Selection mit Hilfe eines
	 * Selection-Objekts. Da eine Auswahl mehrere Optionen haben kann, können
	 * mehrere Option-Objekte in einer ArrayList ausgegeben werden.
	 * 
	 * @param selection,
	 *            die Auswahl dessen Optionen ausgelesen werden sollen
	 * @return Eine ArrayList mit Option-Objekten, die sämtliche Optionen der
	 *         vorgegebenen Auswahl repräsentieren.
	 */
	public ArrayList<Option> findBySelection(Selection selection) {

		return findBySelection(selection.getId());
	}

	/**
	 * Diese Methode bildet das Resultset auf ein Java - Objekt ab.
	 * 
	 * @param rs,
	 *            das Resultset das auf ein Java-Objekt abgebildet werden soll
	 * @return Option-Objekt
	 */

	private Option map(ResultSet rs) throws SQLException {
		Option option = new Option();
		option.setId(rs.getInt("id"));
		option.setOption(rs.getString("option"));

		Selection selection = new Selection();
		selection.setId(rs.getInt("sid"));
		selection.setTextualDescriptionForProfile(rs.getString("stdfp"));
		selection.setTextualDescriptionForSearchProfile(rs.getString("stdfsp"));
		selection.setPropertyName(rs.getString("spn"));

		option.setSelection(selection);
		return option;
	}

}
