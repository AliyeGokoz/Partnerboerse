package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class BlockingMapper {

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
						+ ",'" + blocking.getFromProfile() + "','" + blocking.getToProfile() + "')");
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

			ResultSet rs = stmt
					.executeQuery("SELECT id, fromProfile, toProfile" + "FROM blockings " + "ORDER BY fromProfile");

			while (rs.next()) {
				Blocking blocking = new Blocking();
				blocking.setId(rs.getInt("id"));
				// blocking.setFromProfile(rs.getProfile("fromProfile"));
				// blocking.setToProfile(rs.getProfile("toProfile"));

				result.add(blocking);
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

			ResultSet rs = stmt.executeQuery(
					"SELECT id, fromProfile, toProfile FROM blockings " + "WHERE id=" + id + " ORDER BY fromProfile");

			if (rs.next()) {
				Blocking blocking = new Blocking();
				blocking.setId(rs.getInt("id"));
				// blocking.setFromProfile(rs.getProfile("fromProfile"));
				// blocking.setToProfile(rs.getProfile("toProfile"));

				return blocking;
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

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile FROM blockings " + "WHERE profile="
					+ profileId + " ORDER BY id");

			while (rs.next()) {
				Blocking blocking = new Blocking();
				blocking.setId(rs.getInt("id"));
				// blocking.setFromProfile(rs.getProfile("fromProfile"));
				// blocking.setToProfile(rs.getProfile("toProfile"));

				result.add(blocking);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Blocking> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}


}
