package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.ArrayList;


import de.hdm.partnerboerse.shared.bo.*;

public class SimilarityMapper {

	private static SimilarityMapper similarityMapper = null;

	protected SimilarityMapper() {

	}

	public static SimilarityMapper similarityMapper() {
		if (similarityMapper == null) {
			similarityMapper = new SimilarityMapper();
		}
		return similarityMapper;
	}

	public Similarity insert(Similarity similarity) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM similarities ");

			if (rs.next()) {
				similarity.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate("INSERT INTO similarities (id, fromProfile, toProfile, similarityValue) "
						+ "VALUES (" + similarity.getId() + ",'" + similarity.getFromProfile() + "','"
						+ similarity.getToProfile() + "','" + similarity.getSimilarityValue() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return similarity;
	}

	public Similarity update(Similarity similarity) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE similarities " + "SET fromProfile=\"" + similarity.getFromProfile() + "\", "
					+ "toProfile=\"" + similarity.getToProfile() + "\", " + "similarityValue=\""
					+ similarity.getSimilarityValue() + "\" " + "WHERE id=" + similarity.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return similarity;
	}

	public void delete(Similarity similarity) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM similarities " + "WHERE id=" + similarity.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public ArrayList<Similarity> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Similarity> result = new ArrayList<Similarity>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile, similarityValue "
					+ "FROM similarities " + "ORDER BY similarityValue");

			while (rs.next()) {
				Similarity similarity = new Similarity();
				similarity.setId(rs.getInt("id"));
//				similarity.setFromProfile(rs.getProfile("fromProfile"));
//				similarity.setToProfile(rs.getProfile("toProfile"));
				similarity.setSimilarityValue(rs.getDouble("similarityValue"));

				result.add(similarity);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Similarity findByKey(int id) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile, similarityValue FROM similarities "
					+ "WHERE id=" + id + " ORDER BY similarityValue");

			if (rs.next()) {
				Similarity similarity = new Similarity();
				similarity.setId(rs.getInt("id"));
//				similarity.setFromProfile(rs.getProfile("fromProfile"));
//				similarity.setToProfile(rs.getProfile("toProfile"));
				similarity.setSimilarityValue(rs.getDouble("similarityValue"));

				return similarity;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	 
	public ArrayList<Similarity> findByProfile (int profileId) {
	    Connection con = DBConnection.connection();
	    ArrayList<Similarity> result = new ArrayList<Similarity>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile, similarityValue FROM similarities "
	       + "WHERE profile=" + profileId + " ORDER BY id");

	  
	      while (rs.next()) {
	      Similarity similarity = new Similarity();
	      similarity.setId(rs.getInt("id"));
//	      similarity.setFromProfile(rs.getProfile("fromProfile"));				
//	      similarity.setToProfile(rs.getProfile("toProfile"));
	      similarity.setSimilarityValue(rs.getDouble("similarityValue"));

	   
	        result.add(similarity);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }

	public ArrayList<Similarity> findByProfile(Profile profile) {

	  
	    return findByProfile(profile.getId());
	  }



	
}
