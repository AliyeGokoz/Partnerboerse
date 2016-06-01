package de.hdm.partnerboerse.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.hdm.partnerboerse.shared.bo.Option;

public class OptionMapper {

	private static OptionMapper optionMapper = null;

	protected OptionMapper() {
	}

	public static OptionMapper optionMapper() {
		if (optionMapper == null) {
			optionMapper = new OptionMapper();
		}

		return optionMapper;
	}

	public Option insert(Option option) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM options ");

			if (rs.next()) {

				option.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO infos (id, option) "
						+ "VALUES (" + option.getId() + ",'"
						+ option.getOption() + "')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return option;
	}

	public Option update(Option option) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE options " + "SET id=\"" + option.getId()
					+ "\", " + "option=\"" + option.getOption() + "WHERE id="
					+ option.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return option;
	}

	public void delete(Option option) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM options " + "WHERE id="
					+ option.getId());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// TODO
	public Option findBySelection(Option option) {
		return null;

	}
}
