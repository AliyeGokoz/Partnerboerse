package de.hdm.partnerboerse.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;
import com.google.appengine.api.utils.SystemProperty;
import de.hdm.partnerboerse.shared.bo.Selection;

public class DBConnection {

	private static Connection con = null;


	private static String googleUrl = ""; // TODO
	private static String localUrl = "jdbc:mysql://127.0.0.1:3306/partnerboerse?user=root&password";

	public static Connection connection() {

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

		return con;
	}
	
	public static void main(String[] args) {
		DBConnection.connection();

		//Datenbankverbindung testen
		
//		Selection selection = new Selection();
//		selection.setPropertyName("Musik");
//		selection.setTextualDescription("Ich höre gerne:");
//		new SelectionMapper().insert(selection);
		
//		new SelectionMapper().findAll();
//		Vector<Selection> selections = new SelectionMapper().findAll();
//		for (int i = 0; i < selections.size(); i++) {
//			Selection selection = selections.get(i);
//			System.out.println(selection.getPropertyName() + " " + selection.getTextualDescription());
//		}
		

		
//		Info info = new Info();
//		info.setInformationValue("Erste Info");
//		new InfoMapper().insert(info);
	}

}
