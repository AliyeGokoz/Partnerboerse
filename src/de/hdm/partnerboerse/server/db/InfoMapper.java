package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class InfoMapper {

	private static InfoMapper infoMapper = null;

	protected InfoMapper() {
	}

	public static InfoMapper infoMapper() {
		if (infoMapper == null) {
			infoMapper = new InfoMapper();
		}

		return infoMapper;
	}

	public Info insert(Info info) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM infos ");

			if (rs.next()) {

				info.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO infos (id, informationValue) "
						+ "VALUES (" + info.getId() + ",'"
						+ info.getInformationValue() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return info;
	}

	public Info update(Info info) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE infos " + "SET informationValue=\""
					+ info.getInformationValue() + "WHERE id=" + info.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return info;
	}

	public void delete(Info info) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM infos " + "WHERE id="
					+ info.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Info findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = "SELECT infos.id AS id, informationValue, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescription AS dtd, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId"
					+ " WHERE infos.id=" + id;

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				info.setProfile(profile);

				Description description = new Description();
				description.setId(rs.getInt("did"));
				description.setTextualDescription(rs.getString("dtd"));
				description.setPropertyName(rs.getString("dpn"));

				info.setDescription(description);

				Selection selection = new Selection();
				selection.setId(rs.getInt("sid"));
				selection.setTextualDescription(rs.getString("std"));
				selection.setPropertyName(rs.getString("spn"));

				info.setSelection(selection);

				return info;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public ArrayList<Info> findAll() {

		ArrayList<Info> result = new ArrayList<>();

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = "SELECT infos.id AS id, informationValue, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescription AS dtd, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				info.setProfile(profile);

				Description description = new Description();
				description.setId(rs.getInt("did"));
				description.setTextualDescription(rs.getString("dtd"));
				description.setPropertyName(rs.getString("dpn"));

				info.setDescription(description);

				Selection selection = new Selection();
				selection.setId(rs.getInt("sid"));
				selection.setTextualDescription(rs.getString("std"));
				selection.setPropertyName(rs.getString("spn"));

				info.setSelection(selection);

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	public ArrayList<Info> findByProfile(int profileId) {

		ArrayList<Info> result = new ArrayList<>();

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = "SELECT infos.id AS id, informationValue, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescription AS dtd, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId"
					+ " WHERE profileId=" + profileId + " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				info.setProfile(profile);

				Description description = new Description();
				description.setId(rs.getInt("did"));
				description.setTextualDescription(rs.getString("dtd"));
				description.setPropertyName(rs.getString("dpn"));

				info.setDescription(description);

				Selection selection = new Selection();
				selection.setId(rs.getInt("sid"));
				selection.setTextualDescription(rs.getString("std"));
				selection.setPropertyName(rs.getString("spn"));

				info.setSelection(selection);

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	public ArrayList<Info> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	public ArrayList<Info> findBySelection(int selectionId) {

		ArrayList<Info> result = new ArrayList<>();

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = "SELECT infos.id AS id, informationValue, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescription AS dtd, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId"
					+ "WHERE selection=" + selectionId + " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				info.setProfile(profile);

				Description description = new Description();
				description.setId(rs.getInt("did"));
				description.setTextualDescription(rs.getString("dtd"));
				description.setPropertyName(rs.getString("dpn"));

				info.setDescription(description);

				Selection selection = new Selection();
				selection.setId(rs.getInt("sid"));
				selection.setTextualDescription(rs.getString("std"));
				selection.setPropertyName(rs.getString("spn"));

				info.setSelection(selection);

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	public ArrayList<Info> findBySelection(Selection selection) {

		return findBySelection(selection.getId());
	}

	public ArrayList<Info> findByDescription(int descriptionId) {

		ArrayList<Info> result = new ArrayList<>();
		
		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = "SELECT infos.id AS id, informationValue, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn, descriptions.id AS did, descriptions.textualDescription AS dtd, descriptions.propertyName AS dpn, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM infos LEFT JOIN selections ON selections.id = infos.selectionId LEFT JOIN descriptions ON descriptions.id = infos.descriptionId LEFT JOIN profiles ON profiles.id = infos.profileId"
					+ "WHERE description=" + descriptionId
					+ " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				info.setProfile(profile);

				Description description = new Description();
				description.setId(rs.getInt("did"));
				description.setTextualDescription(rs.getString("dtd"));
				description.setPropertyName(rs.getString("dpn"));

				info.setDescription(description);

				Selection selection = new Selection();
				selection.setId(rs.getInt("sid"));
				selection.setTextualDescription(rs.getString("std"));
				selection.setPropertyName(rs.getString("spn"));

				info.setSelection(selection);

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
		
	}

	public ArrayList<Info> findByDescription(Description description) {
		return findByDescription(description.getId());
	}
}