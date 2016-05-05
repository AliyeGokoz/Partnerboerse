package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.partnerboerse.shared.bo.*;

public class ProfileMapper {

	private static ProfileMapper profileMapper = null;

	protected ProfileMapper() {

	}

	public static ProfileMapper profileMapper() {
		if (profileMapper == null) {
			profileMapper = new ProfileMapper();
		}
		return profileMapper;
	}

	public Profile insert(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM profiles ");

			if (rs.next()) {

				profile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO profiles (id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender) "
								+ "VALUES (" + profile.getId() + ",'" + profile.getFirstName() + "','"
								+ profile.getLastName() + ",'" + profile.getDateOfBirth() + ",'" + profile.geteMail()
								// TODO
								// + ",'" + profile.getHeight() + ",'" +
								// profile.getConfession() + ",'"
								// + profile.getSmoker() + ",'" +
								// profile.getHairColor() + ",'" +
								// profile.getGender()
								+ "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return profile;
	}

	public Profile update(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE profiles " + "SET firstName=\"" + profile.getFirstName() + "\", " + "lastName=\""
					+ profile.getLastName() + "\", " + "dateOfBirth=\"" + profile.getDateOfBirth() + "\", " + "email=\""
					+ profile.geteMail() + "\", " + "height=\"" + profile.getHeight() + "\", " + "confession=\""
					// TODO
					// + profile.getConfession() + "\", " + "smoker=\"" +
					// profile.getSmoker() + "\", " + "hairColor=\""
					// + profile.getHairColor() + "\", " + "gender=\"" +
					// profile.getGender()
					+ "\" " + "WHERE id=" + profile.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return profile;
	}

	public void delete(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM profiles " + "WHERE id=" + profile.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<Profile> findAll() {
		Connection con = DBConnection.connection();

		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "ORDER BY lastName");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
//				profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
//				profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//				profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Profile findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
							+ "WHERE id=" + id + " ORDER BY lastName");

			if (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
//				profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
//				profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//				profile.setGender(Profile.Gender.valueOf("gender"));

				return profile;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}
}
