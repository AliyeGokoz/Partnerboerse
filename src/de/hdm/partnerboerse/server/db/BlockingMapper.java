package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class BlockingMapper {

	private static final String BASE_SELECT = "SELECT blockings.id AS bid,"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender, "
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM blockings LEFT JOIN profiles AS fromProfile ON fromProfile.id = blockings.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = blockings.toProfile";

	private static BlockingMapper blockingMapper = null;

	protected BlockingMapper() {

	}

	public static BlockingMapper blockingMapper() {
		if (blockingMapper == null) {
			blockingMapper = new BlockingMapper();
		}
		return blockingMapper;
	}

	public Blocking insert(Blocking blocking) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM blockings ");

			if (rs.next()) {
				blocking.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO blockings (id, fromProfile, toProfile) " + "VALUES (" + blocking.getId()
						+ ",'" + blocking.getFromProfile().getId() + "','" + blocking.getToProfile().getId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return blocking;
	}

	public Blocking update(Blocking blocking) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE blockings " + "SET fromProfile=\"" + blocking.getFromProfile() + "\", "
					+ "toProfile=\"" + blocking.getToProfile() + "\" " + "WHERE id=" + blocking.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return blocking;
	}

	public void delete(Blocking blocking) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM blockings " + "WHERE id=" + blocking.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public ArrayList<Blocking> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Blocking> result = new ArrayList<Blocking>();

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

	public Blocking findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE blockings.id=" + id + " ORDER BY fromProfile");

			if (rs.next()) {
				return map(rs);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public ArrayList<Blocking> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		ArrayList<Blocking> result = new ArrayList<Blocking>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(BASE_SELECT + " WHERE fromProfile=" + profileId);

			while (rs.next()) {
				result.add(map(rs));
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Blocking> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	private Blocking map(ResultSet rs) throws SQLException {
		Blocking blocking = new Blocking();
		blocking.setId(rs.getInt("bid"));

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

		blocking.setFromProfile(profileFrom);
		blocking.setToProfile(profileTo);

		return blocking;
	}

	public static void main(String[] args) {
		BlockingMapper blockingMapper = new BlockingMapper();
		blockingMapper.findByKey(1);
		blockingMapper.findByProfile(1);
		blockingMapper.findAll();
	}

	public boolean doBlockingExist(Profile fromProfile, Profile toProfile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT fromProfile FROM blockings WHERE fromProfile="
					+ fromProfile.getId() + " AND toProfile=" + toProfile.getId());
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return false;
	}

}
