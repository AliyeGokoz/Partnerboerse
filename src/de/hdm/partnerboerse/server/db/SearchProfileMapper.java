package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class SearchProfileMapper {

	private static SearchProfileMapper searchProfileMapper = null;

	protected SearchProfileMapper() {
	}

	public static SearchProfileMapper searchProfileMapper() {
		if (searchProfileMapper == null) {
			searchProfileMapper = new SearchProfileMapper();
		}

		return searchProfileMapper;
	}

	public SearchProfile insert(SearchProfile searchProfile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM searchprofiles ");

			if (rs.next()) {

				searchProfile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO searchprofiles (id, fromAge, toAge, hairColor, gender, fromHeight, toHeight, confession) "
								+ "VALUES (" + searchProfile.getId() + ",'" + searchProfile.getFromAge() + "','"
								+ searchProfile.getToAge() + "','" + searchProfile.getHairColor() + ",'"
								+ searchProfile.getGender() + ",'" + searchProfile.getFromHeight() + ",'"
								+ searchProfile.getToHeight() + ",'" + searchProfile.getConfession() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return searchProfile;
	}

	public SearchProfile update(SearchProfile searchProfile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE searchprofiles " + "SET fromAge=\"" + searchProfile.getFromAge() + "\", "
					+ "toAge=\"" + searchProfile.getToAge() + "\", " + "hairColor=\"" + searchProfile.getHairColor()
					+ "\", " + "gender=\"" + searchProfile.getGender() + "\", " + "fromHeight=\""
					+ searchProfile.getFromHeight() + "\", " + "toHeight=\"" + searchProfile.getToHeight() + "\", "
					+ "confession=\"" + searchProfile.getConfession() + "\" " + "WHERE id=" + searchProfile.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return searchProfile;
	}

	public void delete(SearchProfile searchProfile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM searchprofiles " + "WHERE id=" + searchProfile.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SearchProfile findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = "SELECT searchprofiles.id AS id, fromAge, toAge, hairColor, gender, fromHeight, toHeight, confession, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM searchprofiles LEFT JOIN profiles ON profiles.id = searchprofiles.profileId "
					+ " WHERE searchprofiles.id=" + id;

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {

				SearchProfile searchProfile = new SearchProfile();
				searchProfile.setId(rs.getInt("id"));
				searchProfile.setFromAge(rs.getInt("fromAge"));
				searchProfile.setToAge(rs.getInt("toAge"));
				searchProfile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
				searchProfile.setGender(Profile.Gender.valueOf(rs.getString("gender")));
				searchProfile.setFromHeight(rs.getInt("fromHeight"));
				searchProfile.setToHeight(rs.getInt("ToHeight"));
				searchProfile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				searchProfile.setProfile(profile);

				return searchProfile;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public ArrayList<SearchProfile> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			Statement stmt = con.createStatement();

			String sql = "SELECT searchprofiles.id AS id, fromAge, toAge, searchprofiles.hairColor AS spHairColor, gender, fromHeight, toHeight, confession, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM searchprofiles LEFT JOIN profiles ON profiles.id = searchprofiles.profileId ";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				SearchProfile searchProfile = new SearchProfile();
				searchProfile.setId(rs.getInt("id"));
				searchProfile.setFromAge(rs.getInt("fromAge"));
				searchProfile.setToAge(rs.getInt("toAge"));
				searchProfile.setHairColor(Profile.HairColor.valueOf(rs.getString("spHairColor")));
				searchProfile.setGender(Profile.Gender.valueOf(rs.getString("gender")));
				searchProfile.setFromHeight(rs.getInt("fromHeight"));
				searchProfile.setToHeight(rs.getInt("ToHeight"));
				searchProfile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				searchProfile.setProfile(profile);

				result.add(searchProfile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<SearchProfile> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		ArrayList<SearchProfile> result = new ArrayList<SearchProfile>();

		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT searchprofiles.id AS id, fromAge, toAge, hairColor, gender, fromHeight, toHeight, confession, profiles.id AS pid, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM searchprofiles LEFT JOIN profiles ON profiles.id = searchprofiles.profileId "
					+ "WHERE profile=" + profileId + " ORDER BY id";
			
			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				SearchProfile searchProfile = new SearchProfile();
				searchProfile.setId(rs.getInt("id"));
				searchProfile.setFromAge(rs.getInt("fromAge"));
				searchProfile.setToAge(rs.getInt("toAge"));
				searchProfile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
				searchProfile.setGender(Profile.Gender.valueOf(rs.getString("gender")));
				searchProfile.setFromHeight(rs.getInt("fromHeight"));
				searchProfile.setToHeight(rs.getInt("ToHeight"));
				searchProfile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));

				Profile profile = new Profile();
				profile.setId(rs.getInt("pid"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				searchProfile.setProfile(profile);

				result.add(searchProfile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<SearchProfile> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

}