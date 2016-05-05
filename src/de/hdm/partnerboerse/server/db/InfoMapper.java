package de.hdm.partnerboerse.server.db;

import java.sql.*;

import java.util.Vector;

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

			
			// TODO from
			ResultSet rs = stmt.executeQuery("SELECT id, informationValue"
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

	public Vector<Info> findAll() {
		Connection con = DBConnection.connection();

		Vector<Info> result = new Vector<Info>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, informationValue "
					+ "FROM infos ");

			while (rs.next()) {
				Info info = new Info();
				info.setId(rs.getInt("id"));
				info.setInformationValue(rs.getString("informationValue"));

				result.addElement(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}