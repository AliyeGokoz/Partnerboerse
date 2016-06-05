package de.hdm.partnerboerse.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.Option;
import de.hdm.partnerboerse.shared.bo.Selection;

public class OptionMapper {

	private static final String BASE_SELECT = "SELECT options.id AS id, option, selections.id AS sid, selections.textualDescription AS std, selections.propertyName AS spn FROM options LEFT JOIN selections ON selections.id = options.selectionId";
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

	public ArrayList<Option> findBySelection(int selectionId) {

		ArrayList<Option> result = new ArrayList<Option>();

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			String sql = BASE_SELECT + " WHERE selectionId=" + selectionId
					+ " ORDER BY id";

			System.out.println(sql);

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				result.add(map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}

	public ArrayList<Option> findBySelection(Selection selection) {

		return findBySelection(selection.getId());
	}

	private Option map(ResultSet rs) throws SQLException {
		Option option = new Option();
		option.setId(rs.getInt("id"));
		option.setOption(rs.getString("option"));

		Selection selection = new Selection();
		selection.setId(rs.getInt("sid"));
		selection.setTextualDescription(rs.getString("std"));
		selection.setPropertyName(rs.getString("spn"));

		option.setSelection(selection);
		return option;
	}
	
	public static void main(String[] args) {
		OptionMapper optionMapper = new OptionMapper();
		optionMapper.findBySelection(1);
	}

}
