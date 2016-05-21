package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class VisitListMapper {

	private static final String BASE_SELECT = "SELECT visits.id AS vid,"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender FROM visits LEFT JOIN profiles AS fromProfile ON fromProfile.id = visits.fromProfile,"
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM visits LEFT JOIN profiles AS fromProfile ON toProfile.id = visits.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = visits.toProfile";

	private static VisitListMapper visitListMapper = null;

	protected VisitListMapper() {

	}

	public static VisitListMapper visitListMapper() {
		if (visitListMapper == null) {
			visitListMapper = new VisitListMapper();
		}
		return visitListMapper;
	}

	public VisitList insert(VisitList visitList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM visits ");

			if (rs.next()) {
				visitList.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO visits (id, fromProfile, toProfile) " + "VALUES (" + visitList.getId()
						+ ",'" + visitList.getFromProfile() + "','" + visitList.getToProfile() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return visitList;
	}

	public VisitList update(VisitList visitList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE visits " + "SET fromProfile=\"" + visitList.getFromProfile() + "\", "
					+ "toProfile=\"" + visitList.getToProfile() + "\" " + "WHERE id=" + visitList.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return visitList;
	}

	public void delete(VisitList visitList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM visits " + "WHERE id=" + visitList.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public ArrayList<VisitList> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<VisitList> result = new ArrayList<VisitList>();

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

	public VisitList findByKey(int id) {

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

	public ArrayList<VisitList> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		ArrayList<VisitList> result = new ArrayList<VisitList>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, fromProfile, toProfile FROM visits " + "WHERE profile=" + profileId + " ORDER BY id");

			while (rs.next()) {
				VisitList visitList = new VisitList();
				visitList.setId(rs.getInt("id"));
				// visitList.setFromProfile(rs.getProfile("fromProfile"));
				// visitList.setToProfile(rs.getProfile("toProfile"));

				result.add(visitList);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<VisitList> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	private VisitList map(ResultSet rs) throws SQLException {
		VisitList visitList = new VisitList();
		visitList.setId(rs.getInt("id"));

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

		visitList.setFromProfile(profileFrom);
		visitList.setToProfile(profileTo);

		return visitList;

	}

}
