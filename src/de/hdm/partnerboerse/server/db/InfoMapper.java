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

			ResultSet rs = stmt
					.executeQuery("SELECT id, informationValue FROM infos"
							+ "WHERE id=" + id);

			if (rs.next()) {

				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				return info;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public ArrayList<Info> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Info> result = new ArrayList<Info>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, informationValue "
					+ "FROM infos ");

			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Info> findByInformationValue(String informationValue) {
		Connection con = DBConnection.connection();
		ArrayList<Info> result = new ArrayList<Info>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, informationValue "
					+ "FROM infos " + "WHERE informationValue LIKE '"
					+ informationValue + "' ORDER BY informationValue");

			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				result.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public ArrayList<Info> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		ArrayList<Info> result = new ArrayList<Info>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, informationValue FROM infos "
							+ "WHERE profile=" + profileId + " ORDER BY id");

			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				result.add(info);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Info> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	public ArrayList<Info> findBySelection(int selectionId) {
		Connection con = DBConnection.connection();
		ArrayList<Info> result = new ArrayList<Info>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, informationValue FROM infos "
							+ "WHERE selection=" + selectionId + " ORDER BY id");

			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				result.add(info);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Info> findBySelection(Selection selection) {

		return findBySelection(selection.getId());
	}

	public ArrayList<Info> findByDescription(int descriptionId) {
		Connection con = DBConnection.connection();
		ArrayList<Info> result = new ArrayList<Info>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, informationValue FROM infos "
							+ "WHERE description=" + descriptionId
							+ " ORDER BY id");

			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				result.add(info);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Info> findByDescription(Description description) {

		return findByDescription(description.getId());
	}



}