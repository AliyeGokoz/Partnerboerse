package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class SimilarityMapper {

	private static final String BASE_SELECT = "SELECT similarities.id AS sid, similarityValue"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender FROM similarities LEFT JOIN profiles AS fromProfile ON fromProfile.id = similarities.fromProfile,"
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM similarities LEFT JOIN profiles AS fromProfile ON toProfile.id = similarities.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = similarities.toProfile";

	private static SimilarityMapper similarityMapper = null;

	protected SimilarityMapper() {

	}

	public static SimilarityMapper similarityMapper() {
		if (similarityMapper == null) {
			similarityMapper = new SimilarityMapper();
		}
		return similarityMapper;
	}

	public Similarity insert(Similarity similarity) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid FROM similarities");

			if (rs.next()) {
				similarity.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO similarities (id, fromProfile, toProfile, similarityValue) "
						+ "VALUES (" + similarity.getId() + "," + similarity.getFromProfile().getId() + ","
						+ similarity.getToProfile().getId() + "," + similarity.getSimilarityValue() + ")");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return similarity;
	}

	public Similarity update(Similarity similarity) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE similarities " + "SET fromProfile=\"" + similarity.getFromProfile() + "\", "
					+ "toProfile=\"" + similarity.getToProfile() + "\", " + "similarityValue=\""
					+ similarity.getSimilarityValue() + "\" " + "WHERE id=" + similarity.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return similarity;
	}

	public void delete(Similarity similarity) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM similarities " + "WHERE id=" + similarity.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public ArrayList<Similarity> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Similarity> result = new ArrayList<Similarity>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT);

			while (rs.next()) {

				result.add(map(rs));

			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Similarity findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + "WHERE id=" + id + " ORDER BY fromProfile");

			if (rs.next()) {

				return map(rs);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public ArrayList<Similarity> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		ArrayList<Similarity> result = new ArrayList<Similarity>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + "WHERE profile=" + profileId + " ORDER BY id");

			while (rs.next()) {

				result.add(map(rs));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Similarity> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	public Similarity findByFromAndTo(Profile from, Profile to) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					BASE_SELECT + "WHERE fromProfile=" + from.getId() + " AND toProfile=" + to.getId() + " LIMIT 1");

			while (rs.next()) {

				return map(rs);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return null;
	}

	private Similarity map(ResultSet rs) throws SQLException {
		Similarity similarity = new Similarity();
		similarity.setId(rs.getInt("id"));
		similarity.setSimilarityValue(rs.getDouble("similarityValue"));

		Profile profileFrom = new Profile();
		profileFrom.setId(rs.getInt("tpId"));
		profileFrom.setFirstName(rs.getString("tpFirstName"));
		profileFrom.setLastName(rs.getString("tpLastName"));
		profileFrom.setDateOfBirth(rs.getDate("tpDateOfBirth"));
		profileFrom.seteMail(rs.getString("tpEmail"));
		profileFrom.setHeight(rs.getInt("tpHeight"));
		profileFrom.setConfession(Profile.Confession.valueOf(rs.getString("tpConfession")));
		profileFrom.setSmoker(rs.getBoolean("tpSmoker"));
		profileFrom.setHairColor(Profile.HairColor.valueOf(rs.getString("tpHairColor")));
		profileFrom.setGender(Profile.Gender.valueOf(rs.getString("tpGender")));

		Profile profileTo = new Profile();
		profileTo.setId(rs.getInt("tpId"));
		profileTo.setFirstName(rs.getString("tpFirstName"));
		profileTo.setLastName(rs.getString("tpLastName"));
		profileTo.setDateOfBirth(rs.getDate("tpDateOfBirth"));
		profileTo.seteMail(rs.getString("tpEmail"));
		profileTo.setHeight(rs.getInt("tpHeight"));
		profileTo.setConfession(Profile.Confession.valueOf(rs.getString("tpConfession")));
		profileTo.setSmoker(rs.getBoolean("tpSmoker"));
		profileTo.setHairColor(Profile.HairColor.valueOf(rs.getString("tpHairColor")));
		profileTo.setGender(Profile.Gender.valueOf(rs.getString("tpGender")));

		similarity.setFromProfile(profileFrom);
		similarity.setToProfile(profileTo);

		return similarity;

	}

}
