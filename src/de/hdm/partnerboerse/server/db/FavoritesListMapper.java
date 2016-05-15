package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM favoriteslists ");

			if (rs.next()) {
				favoritesList.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO favoriteslists (id, fromProfile, toProfile) " + "VALUES (" + favoritesList.getId()
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

			stmt.executeUpdate("UPDATE favoriteslists " + "SET fromProfile=\"" + favoritesList.getFromProfile() + "\", "
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

			stmt.executeUpdate("DELETE FROM favoriteslists " + "WHERE id=" + favoritesList.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<FavoritesList> findAll() {
		Connection con = DBConnection.connection();

		Vector<FavoritesList> result = new Vector<FavoritesList>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, fromProfile, toProfile" + "FROM favoriteslists " + "ORDER BY fromProfile");

			while (rs.next()) {
				FavoritesList favoritesList = new FavoritesList();
				favoritesList.setId(rs.getInt("id"));
				// favoritesList.setFromProfile(rs.getProfile("fromProfile"));
				// favoritesList.setToProfile(rs.getProfile("toProfile"));

				result.addElement(favoritesList);
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

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile FROM favoriteslists " + "WHERE id=" + id
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

	public Vector<FavoritesList> findByProfile(int profileId) {
		Connection con = DBConnection.connection();
		Vector<FavoritesList> result = new Vector<FavoritesList>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile FROM favoriteslists " + "WHERE profile="
					+ profileId + " ORDER BY id");

			while (rs.next()) {
				FavoritesList favoritesList = new FavoritesList();
				favoritesList.setId(rs.getInt("id"));
				// favoritesList.setFromProfile(rs.getProfile("fromProfile"));
				// favoritesList.setToProfile(rs.getProfile("toProfile"));

				result.addElement(favoritesList);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Vector<FavoritesList> findByProfile(Profile profile) {

		return findByProfile(profile.getId());
	}

	//Generierte Methoden aus Impl-Klasse
	public ArrayList<FavoritesList> getAllFavoritesLists() {
		// TODO Auto-generated method stub
		return null;
	}

	public FavoritesList getFavoritesListByKey(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
