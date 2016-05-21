package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;

import de.hdm.partnerboerse.shared.bo.*;

public class FavoritesListMapper {

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

				stmt.executeUpdate(
						"INSERT INTO favorites (id, fromProfile, toProfile) " + "VALUES (" + favoritesList.getId()
								+ ",'" + favoritesList.getFromProfile() + "','" + favoritesList.getToProfile() + "')");
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

			ResultSet rs = stmt.executeQuery(
					"SELECT id, fromProfile, toProfile" + "FROM favorites " + "ORDER BY fromProfile");

			while (rs.next()) {
				FavoritesList favoritesList = new FavoritesList();
				favoritesList.setId(rs.getInt("id"));
				// favoritesList.setFromProfile(rs.getProfile("fromProfile"));
				// favoritesList.setToProfile(rs.getProfile("toProfile"));

				result.add(favoritesList);
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

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile FROM favorites " + "WHERE id=" + id
					+ " ORDER BY fromProfile");

			if (rs.next()) {
				FavoritesList favoritesList = new FavoritesList();
				favoritesList.setId(rs.getInt("id"));
				// favoritesList.setFromProfile(rs.getProfile("fromProfile"));
				// favoritesList.setToProfile(rs.getProfile("toProfile"));

				return favoritesList;
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

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile FROM favorites " + "WHERE profile="
					+ profileId + " ORDER BY id");

			while (rs.next()) {
				FavoritesList favoritesList = new FavoritesList();
				favoritesList.setId(rs.getInt("id"));
				// favoritesList.setFromProfile(rs.getProfile("fromProfile"));
				// favoritesList.setToProfile(rs.getProfile("toProfile"));

				result.add(favoritesList);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<FavoritesList> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	
}
