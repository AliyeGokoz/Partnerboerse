package de.hdm.partnerboerse.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import com.google.appengine.api.utils.SystemProperty;

import de.hdm.partnerboerse.shared.bo.Info;

/**
 * Verwaltung der Datenbank-Verbindung.
 * 
 * @author Claudia
 */
public class DBConnection {

	/**
	 * Die Instantiierung der Klasse DBConnection erfolgt nur einmal. Dies wird
	 * auch als <b>Singleton<b> bezeichnet.
	 * <p>
	 * Durch den Bezeichner <code>static</code> ist die Variable nur einmal für
	 * sämtliche eventuellen Instanzen dieser Klasse vorhanden. Sie speichert
	 * die einzige Instanz der Klasse.
	 */

	private static Connection con = null;

	/**
	 * Mit Hilfe folgender URL wird die Datenbank angesprochen.
	 */

	private static String googleUrl = "jdbc:google:mysql://partnerboerse-itprojekt:partnerboerse/partnerboerse_db?user=sampleuser&password=sampleuser";
	private static String localUrl = "jdbc:mysql://127.0.0.1:3306/partnerboerse?user=root&password";

	/**
	 * Durch
	 * <code>DBConnection.connection()<code> kann folgende statische Methode aufgerufen werden. 
	 * Durch sie wird die Singleton-Eigenschaft sichergestellt, in dem sie dafür sorgt, dass nur eine 
	 * Instanz von <code>DBConnection<code> existiert.
	 * <p>
	 * Die Instantiierung der DBConnection sollte immer durch den Aufruf dieser Methode erfolgen.
	 * 
	 * @return DAS <code>DBConncetion</code>-Objekt.
	 * 
	 * @see con
	 */
	public static Connection connection() {

		// Wenn es bisher keine Verbindung zur DB gab
		if (con == null) {
			String url = null;
			try {
				if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {

					Class.forName("com.mysql.jdbc.GoogleDriver");
					url = googleUrl;
				} else {

					Class.forName("com.mysql.jdbc.Driver");
					url = localUrl;
				}

				con = DriverManager.getConnection(url);
			} catch (Exception e) {
				con = null;
				e.printStackTrace();
			}
		}

		// Verbindung zurückgeben
		return con;
	}

	public static void main(String[] args) {
		DBConnection.connection();

		// Datenbankverbindung testen

		// Selection selection = new Selection();
		// selection.setPropertyName("Bücher");
		// selection.setTextualDescription("Ich lese gerne:");
		// new SelectionMapper().insert(selection);

		// Description description = new Description();
		// description.setPropertyName("Gedichte");
		// description.setTextualDescription("Ich habe folgendes Gedicht selber
		// geschrieben:");
		// new DescriptionMapper().insert(description);

		// Profile profile = new Profile();
		// profile.setFirstName("Jolanta");
		// profile.setLastName("Jolanta");
		// profile.setDateOfBirth(new Date());
		// profile.seteMail("Jolanta");
		// profile.setHeight(155);
		// profile.setConfession(Profile.Confession.BUDDHISTIC);
		// profile.setSmoker(true);
		// profile.setHairColor(Profile.HairColor.BLOND);
		// profile.setGender(Profile.Gender.FEMALE);
		// Profile insert = new ProfileMapper().insert(profile);
		//
		// insert.seteMail("updated");
		// insert.setDateOfBirth(new Date(100, 0, 1));
		// new ProfileMapper().update(insert);

		// new SelectionMapper().findAll();
		// Vector<Selection> selections = new SelectionMapper().findAll();
		// for (int i = 0; i < selections.size(); i++) {
		// Selection selection = selections.get(i);
		// System.out.println(selection.getPropertyName() + " " +
		// selection.getTextualDescription());
		// }

		// Info info = new Info();
		// info.setInformationValue("Erste Info");
		// new InfoMapper().insert(info);

		//
		// SelectionMapper info = new SelectionMapper();
		//
		// info.findAll();
		ArrayList<Info> infos = new InfoMapper().findAll();
		for (int i = 0; i < infos.size(); i++) {
			Info p = infos.get(i);
			System.out.println(p.getId() + " " + p.getProfile().geteMail());
		}

		Info findByKey = new InfoMapper().findByKey(1);
		System.out.println(findByKey.getProfile().getFirstName());
		System.out.println(findByKey.getSelection().getPropertyName());
		System.out.println(findByKey.getDescription());

		//
		// }
		//

	}
}
