package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class FavoritesListMapper {

	private static final String BASE_SELECT = "SELECT favorites.id AS fid,"
			+ " fromProfile.id AS fpId, fromProfile.firstName AS fpFirstName, fromProfile.lastName AS fpLastName, fromProfile.dateOfBirth AS fpDateOfBirth, fromProfile.email AS fpEmail, fromProfile.height AS fpHeight, fromProfile.confession AS fpConfession, fromProfile.smoker AS fpSmoker, fromProfile.hairColor AS fpHairColor, fromProfile.gender AS fpGender, "
			+ " toProfile.id AS tpId, toProfile.firstName AS tpFirstName, toProfile.lastName AS tpLastName, toProfile.dateOfBirth AS tpDateOfBirth, toProfile.email AS tpEmail, toProfile.height AS tpHeight, toProfile.confession AS tpConfession, toProfile.smoker AS tpSmoker, toProfile.hairColor AS tpHairColor, toProfile.gender AS tpGender FROM favorites LEFT JOIN profiles AS fromProfile ON fromProfile.id = favorites.fromProfile"
			+ " LEFT JOIN profiles AS toProfile ON toProfile.id = favorites.toProfile";

	private static FavoritesListMapper favoritesListMapper = null;

	protected FavoritesListMapper() {

	}

	public static FavoritesListMapper favoritesListMapper() {
		if (favoritesListMapper == null) {
			favoritesListMapper = new FavoritesListMapper();
		}
		return favoritesListMapper;
	}

	public FavoritesList insert(FavoritesList favoritesList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM favorites ");

			if (rs.next()) {
				favoritesList.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO favorites (id, fromProfile, toProfile) " + "VALUES ("
						+ favoritesList.getId() + ",'" + favoritesList.getFromProfile().getId() + "','"
						+ favoritesList.getToProfile().getId() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return favoritesList;
	}

	public FavoritesList update(FavoritesList favoritesList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE favorites " + "SET fromProfile=\"" + favoritesList.getFromProfile() + "\", "
					+ "toProfile=\"" + favoritesList.getToProfile() + "\" " + "WHERE id=" + favoritesList.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return favoritesList;
	}

	public void delete(FavoritesList favoritesList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM favorites " + "WHERE id=" + favoritesList.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public ArrayList<FavoritesList> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<FavoritesList> result = new ArrayList<FavoritesList>();

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

	public FavoritesList findByKey(int id) {
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

	public ArrayList<FavoritesList> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		ArrayList<FavoritesList> result = new ArrayList<FavoritesList>();

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

	public boolean doFavoritesListEntryExist(Profile fromProfile, Profile toProfile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT fromProfile FROM favorites WHERE fromProfile="
					+ fromProfile.getId() + " AND toProfile=" + toProfile.getId());
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return false;
	}

	public ArrayList<FavoritesList> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	private FavoritesList map(ResultSet rs) throws SQLException {
		FavoritesList favoritesList = new FavoritesList();
		favoritesList.setId(rs.getInt("fid"));

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

		favoritesList.setFromProfile(profileFrom);
		favoritesList.setToProfile(profileTo);

		return favoritesList;

	}

}
