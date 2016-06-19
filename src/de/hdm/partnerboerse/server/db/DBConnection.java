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

	}

