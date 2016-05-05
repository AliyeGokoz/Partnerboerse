package de.hdm.partnerboerse.server.db;

import java.sql.*;

import java.util.Vector;

import de.hdm.partnerboerse.shared.bo.*;

public class SelectionMapper {

	private static SelectionMapper selectionMapper = null;

	protected SelectionMapper() {
	}

	public static SelectionMapper selectionMapper() {
		if (selectionMapper == null) {
			selectionMapper = new SelectionMapper();
		}

		return selectionMapper;
	}

	public Selection insert(Selection selection) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM selections ");

			if (rs.next()) {

				selection.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO selections (id, textualDescription, propertyName) "
						+ "VALUES ("
						+ selection.getId()
						+ ",'"
						+ selection.getTextualDescription()
						+ "','"
						+ selection.getPropertyName() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selection;

	}

	public Selection update(Selection selection) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE selections "
					+ "SET textualDescription=\""
					+ selection.getTextualDescription() + "\", "
					+ "propertyName=\"" + selection.getPropertyName()
					+ "WHERE id=" + selection.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return selection;
	}

	public void delete(Selection selection) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM selections " + "WHERE id="
					+ selection.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Selection findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, textualDescription, propertyName FROM selections "
							+ "WHERE id=" + id + " ORDER BY propertyName");

			if (rs.next()) {

				Selection selection = new Selection();
				selection.setId(rs.getInt("id"));
				selection.setTextualDescription(rs.getString("textualDescription"));
				selection.setPropertyName(rs.getString("propertyName"));

				return selection;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Selection> findAll() {
		Connection con = DBConnection.connection();

		Vector<Selection> result = new Vector<Selection>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, textualDescription, propertyName "
							+ "FROM selections " + "ORDER BY propertyName");

			while (rs.next()) {
				Selection selection = new Selection();
				selection.setId(rs.getInt("id"));
				selection.setTextualDescription(rs.getString("textualDescription"));
				selection.setPropertyName(rs.getString("propertyName"));

				result.addElement(selection);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}