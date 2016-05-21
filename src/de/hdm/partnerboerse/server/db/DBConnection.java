package de.hdm.partnerboerse.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

import com.google.appengine.api.utils.SystemProperty;

import de.hdm.partnerboerse.shared.bo.Info;

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

		// Datenbankverbindung testen

		// Selection selection = new Selection();
		// selection.setPropertyName("Bücher");
		// selection.setTextualDescription("Ich lese gerne:");
		// new SelectionMapper().insert(selection);

		// Description description = new Description();
		// description.setPropertyName("Gedichte");
		// description.setTextualDescription("Ich habe folgendes Gedicht selber geschrieben:");
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
