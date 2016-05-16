package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;


import de.hdm.partnerboerse.shared.bo.*;

public class DescriptionMapper {

	private static DescriptionMapper descriptionMapper = null;

	protected DescriptionMapper() {
	}

	public static DescriptionMapper descriptionMapper() {
		if (descriptionMapper == null) {
			descriptionMapper = new DescriptionMapper();
		}

		return descriptionMapper;
	}

	public Description insert(Description description) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM descriptions ");

			if (rs.next()) {

				description.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO descriptions (id, textualDescription, propertyName) "
						+ "VALUES ("
						+ description.getId()
						+ ",'"
						+ description.getTextualDescription()
						+ "','"
						+ description.getPropertyName() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return description;
	}

	public Description update(Description description) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE descriptions "
					+ "SET textualDescription=\""
					+ description.getTextualDescription() + "\", "
					+ "propertyName=\"" + description.getPropertyName()
					+ "WHERE id=" + description.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return description;
	}

	public void delete(Description description) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM descriptions " + "WHERE id="
					+ description.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Description findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, textualDescription, propertyName FROM descriptions "
							+ "WHERE id=" + id + " ORDER BY propertyName");

			if (rs.next()) {

				Description description = new Description();
				description.setId(rs.getInt("id"));
				description.setTextualDescription(rs
						.getString("textualDescription"));
				description.setPropertyName(rs.getString("propertyName"));

				return description;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public ArrayList<Description> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Description> result = new ArrayList<Description>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, textualDescription, propertyName "
							+ "FROM descriptions " + "ORDER BY propertyName");

			while (rs.next()) {
				Description description = new Description();
				description.setId(rs.getInt("id"));
				description.setTextualDescription(rs
						.getString("textualDescription"));
				description.setPropertyName(rs.getString("propertyName"));

				result.add(description);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;	
	}
	
}