package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.partnerboerse.shared.bo.*;

public class VisitListMapper {

	private static VisitListMapper visitListMapper = null;

	protected VisitListMapper() {

	}

	public static VisitListMapper visitListMapper() {
		if (visitListMapper == null) {
			visitListMapper = new VisitListMapper();
		}
		return visitListMapper;
	}

	public VisitList insert(VisitList visitList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM visitlists ");

			if (rs.next()) {
				visitList.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO visitlists (id, fromProfile, toProfile) " + "VALUES (" + visitList.getId() + ",'"
								+ visitList.getFromProfile() + "','" + visitList.getToProfile() + "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return visitList;
	}

	public VisitList update(VisitList visitList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE visitlists " + "SET fromProfile=\"" + visitList.getFromProfile() + "\", "
					+ "toProfile=\"" + visitList.getToProfile() + "\" " + "WHERE id=" + visitList.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return visitList;
	}

	public void delete(VisitList visitList) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM visitlists " + "WHERE id=" + visitList.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<VisitList> findAll() {
		Connection con = DBConnection.connection();

		Vector<VisitList> result = new Vector<VisitList>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, fromProfile, toProfile" + "FROM visitlists " + "ORDER BY fromProfile");

			while (rs.next()) {
				VisitList visitList = new VisitList();
				visitList.setId(rs.getInt("id"));
//				visitList.setFromProfile(rs.getProfile("fromProfile"));
//				visitList.setToProfile(rs.getProfile("toProfile"));

				result.addElement(visitList);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return result;
	}

	public VisitList findByKey(int id) {

		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, fromProfile, toProfile FROM visitlists " + "WHERE id=" + id + " ORDER BY fromProfile");

			if (rs.next()) {
				VisitList visitList = new VisitList();
				visitList.setId(rs.getInt("id"));
//				visitList.setFromProfile(rs.getProfile("fromProfile"));
//				visitList.setToProfile(rs.getProfile("toProfile"));

				return visitList;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}
	
	public Vector<VisitList> findByProfile (int profileId) {
	    Connection con = DBConnection.connection();
	    Vector<VisitList> result = new Vector<VisitList>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, fromProfile, toProfile FROM visitslists "
	       + "WHERE profile=" + profileId + " ORDER BY id");

	  
	      while (rs.next()) {
	         VisitList visitList = new VisitList();
	         visitList.setId(rs.getInt("id"));
//		 visitList.setFromProfile(rs.getProfile("fromProfile"));
//		 visitList.setToProfile(rs.getProfile("toProfile"));

	   
	        result.addElement(visitList);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }


	public Vector<VisitList> findByProfile(Profile profile) {

	    return findByProfile(profile.getId());
	  }
}
