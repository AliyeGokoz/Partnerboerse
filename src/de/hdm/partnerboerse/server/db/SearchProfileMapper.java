package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM searchProfiles ");

			if (rs.next()) {

				searchProfile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO searchProfiles (id, age, hairColor, gender, height, confession) "
						+ "VALUES (" + searchProfile.getId());
				// + ",'"
				// TODO
				// + searchProfile.getAge()
				// + "','"
				// + searchProfile.getHairColor()
				// + ",'"
				// + searchProfile.getGender()
				// + ",'"
				// + searchProfile.getHeight()
				// + ",'"
				// + searchProfile.getConfession() + "')");

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

			stmt.executeUpdate("UPDATE searchProfiles " + "SET age=\"");
			// TODO
			// + searchProfile.getAge() + "\", " + "hairColor=\""
			// + searchProfile.getHairColor() + "\", " + "gender=\""
			// + searchProfile.getGender() + "\", " + "height=\""
			// + searchProfile.getHeight() + "\", " + "confession=\""
			// + searchProfile.getConfession() + "\" " + "WHERE id="
			// + searchProfile.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return searchProfile;
	}

	public void delete(SearchProfile searchProfile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM searchProfiles " + "WHERE id="
					+ searchProfile.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public SearchProfile findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, age, hairColor, gender, height, confession FROM searchProfiles "
							+ "WHERE id=" + id);

			if (rs.next()) {

				SearchProfile searchProfile = new SearchProfile();
				searchProfile.setId(rs.getInt("id"));
				// TODO
				// searchProfile.setAge(rs.getInt("age"));
				// searchProfile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// searchProfile.setGender(Profile.Gender.valueOf("gender"));
				// searchProfile.setHeight(rs.getInt("height"));
				// searchProfile.setConfession(Profile.Confession.valueOf("confession"));

				return searchProfile;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<SearchProfile> findAll() {
		Connection con = DBConnection.connection();

		Vector<SearchProfile> result = new Vector<SearchProfile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, age, hairColor, gender, height, confession "
							+ "FROM searchProfiles ");

			while (rs.next()) {
				SearchProfile searchProfile = new SearchProfile();
				searchProfile.setId(rs.getInt("id"));
				// TODO
				// searchProfile.setAge(rs.getInt("age"));
				// searchProfile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// searchProfile.setGender(Profile.Gender.valueOf("gender"));
				// searchProfile.setHeight(rs.getInt("height"));
				// searchProfile.setConfession(Profile.Confession.valueOf("confession"));

				result.addElement(searchProfile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<SearchProfile> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		Vector<SearchProfile> result = new Vector<SearchProfile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, age, hairColor, gender, height, confession FROM searchprofiles "
							+ "WHERE profile=" + profileId + " ORDER BY id");

			while (rs.next()) {
				SearchProfile searchProfile = new SearchProfile();
				searchProfile.setId(rs.getInt("id"));
				// searchProfile.setage(rs.getInt("age"));
				// searchProfile.setHeight(rs.getInt("height"));
				// searchProfile.setConfession(Profile.Confession.valueOf("confession"));
				// searchProfile.setSmoker(rs.getBoolean("smoker"));
				// searchProfile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// searchProfile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(searchProfile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Vector<SearchProfile> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}
	
	//Generierte Methoden von Impl-Klasse
	public ArrayList<SearchProfile> getAllSearchProfiles() {
		// TODO Auto-generated method stub
		return null;
	}

	public SearchProfile getSearchProfileByKey(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}