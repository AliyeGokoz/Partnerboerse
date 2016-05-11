package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.util.Vector;

import de.hdm.partnerboerse.shared.bo.*;

public class ProfileMapper {

	private static ProfileMapper profileMapper = null;

	protected ProfileMapper() {

	}

	public static ProfileMapper profileMapper() {
		if (profileMapper == null) {
			profileMapper = new ProfileMapper();
		}
		return profileMapper;
	}

	public Profile insert(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM profiles ");

			if (rs.next()) {

				profile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				stmt.executeUpdate(
						"INSERT INTO profiles (id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender) "
								+ "VALUES (" + profile.getId() + ",'" + profile.getFirstName() + "','"
								+ profile.getLastName() + ",'" + profile.getDateOfBirth() + ",'" + profile.geteMail()
								// TODO
								// + ",'" + profile.getHeight() + ",'" +
								// profile.getConfession() + ",'"
								// + profile.getSmoker() + ",'" +
								// profile.getHairColor() + ",'" +
								// profile.getGender()
								+ "')");
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return profile;
	}

	public Profile update(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("UPDATE profiles " + "SET firstName=\"" + profile.getFirstName() + "\", " + "lastName=\""
					+ profile.getLastName() + "\", " + "dateOfBirth=\"" + profile.getDateOfBirth() + "\", " + "email=\""
					+ profile.geteMail() + "\", " + "height=\"" + profile.getHeight() + "\", " + "confession=\""
					// TODO
					// + profile.getConfession() + "\", " + "smoker=\"" +
					// profile.getSmoker() + "\", " + "hairColor=\""
					// + profile.getHairColor() + "\", " + "gender=\"" +
					// profile.getGender()
					+ "\" " + "WHERE id=" + profile.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return profile;
	}

	public void delete(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM profiles " + "WHERE id=" + profile.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public Vector<Profile> findAll() {
		Connection con = DBConnection.connection();

		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "ORDER BY lastName");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				// profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
				// profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Profile findByKey(int id) {

		Connection con = DBConnection.connection();

		try {

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
							+ "WHERE id=" + id + " ORDER BY lastName");

			if (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				// profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
				// profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// profile.setGender(Profile.Gender.valueOf("gender"));

				return profile;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Vector<Profile> findByLastName(String lastName) {
		Connection con = DBConnection.connection();
		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "WHERE lastName LIKE '" + lastName + "' ORDER BY lastName");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				// profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
				// profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Profile> findByFirstName(String firstName) {
		Connection con = DBConnection.connection();
		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "WHERE lastName LIKE '" + firstName + "' ORDER BY firstName");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				// profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
				// profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Profile> findByName(String lastName, String firstName) {
		Connection con = DBConnection.connection();
		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "WHERE lastName LIKE '" + lastName + "WHERE firstName LIKE '"
							+ firstName + "' ORDER BY lastName");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				// profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
				// profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Vector<Profile> findByFavoritesList(int favoritesListId) {
		Connection con = DBConnection.connection();
		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
							+ "WHERE favoritesList=" + favoritesListId + " ORDER BY id");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				// profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
				// profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
				// profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Vector<Profile> findByFavoritesList(FavoritesList favoritesList) {

		return findByFavoritesList(favoritesList.getId());
	}

	public Vector<Profile> findBySearchProfile(int searchProfileId) {
		Connection con = DBConnection.connection();
		Vector<Profile> result = new Vector<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(
					"SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
							+ "WHERE searchprofile=" + searchProfileId + " ORDER BY id");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
//				profile.setConfession(Profile.Confession.valueOf("confession"));
				profile.setSmoker(rs.getBoolean("smoker"));
//				profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//				profile.setGender(Profile.Gender.valueOf("gender"));

				result.addElement(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public Vector<Profile> findBySearchProfile(SearchProfile searchProfile) {
	
	    return findBySearchProfile(searchProfile.getId());
	  }

	public Vector<Profile> findByInfo (int infoId) {
	    Connection con = DBConnection.connection();
	    Vector<Profile> result = new Vector<Profile>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
	       + "WHERE info=" + infoId + " ORDER BY id");

	  
	      while (rs.next()) {
	      Profile profile = new Profile();
	      profile.setId(rs.getInt("id"));
	        profile.setFirstName(rs.getString("firstName"));
	        profile.setLastName(rs.getString("lastName"));
	        profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
//		profile.setConfession(Profile.Confession.valueOf("confession"));
		profile.setSmoker(rs.getBoolean("smoker"));
//		profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//		profile.setGender(Profile.Gender.valueOf("gender"));

	   
	        result.addElement(profile);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }

	public Vector<Profile> findByInfo(Info info) {

	    return findByInfo(info.getId());
	  }
	
	public Vector<Profile> findByVisitList (int visitListId) {
	    Connection con = DBConnection.connection();
	    Vector<Profile> result = new Vector<Profile>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
	       + "WHERE visitList=" + visitListId + " ORDER BY id");

	  
	      while (rs.next()) {
	      Profile profile = new Profile();
	      profile.setId(rs.getInt("id"));
	        profile.setFirstName(rs.getString("firstName"));
	        profile.setLastName(rs.getString("lastName"));
	        profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
//		profile.setConfession(Profile.Confession.valueOf("confession"));
		profile.setSmoker(rs.getBoolean("smoker"));
//		profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//		profile.setGender(Profile.Gender.valueOf("gender"));

	   
	        result.addElement(profile);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }

	public Vector<Profile> findByVisitList(VisitList visitList) {

	    return findByVisitList(visitList.getId());
	  }

	public Vector<Profile> findBySimilarity (int similarityId) {
	    Connection con = DBConnection.connection();
	    Vector<Profile> result = new Vector<Profile>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
	       + "WHERE similarity=" + similarityId + " ORDER BY id");

	  
	      while (rs.next()) {
	      Profile profile = new Profile();
	      profile.setId(rs.getInt("id"));
	        profile.setFirstName(rs.getString("firstName"));
	        profile.setLastName(rs.getString("lastName"));
	        profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
//		profile.setConfession(Profile.Confession.valueOf("confession"));
		profile.setSmoker(rs.getBoolean("smoker"));
//		profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//		profile.setGender(Profile.Gender.valueOf("gender"));

	   
	        result.addElement(profile);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }
	public Vector<Profile> findBySimilarity(Similarity similarity) {

	    return findBySimilarity(similarity.getId());
	  }
	
	public Vector<Profile> findByBlocking (int blockingId) {
	    Connection con = DBConnection.connection();
	    Vector<Profile> result = new Vector<Profile>();

	    try {
	      Statement stmt = con.createStatement();

	      ResultSet rs = stmt.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
	       + "WHERE blocking=" + blockingId + " ORDER BY id");

	  
	      while (rs.next()) {
	      Profile profile = new Profile();
	      profile.setId(rs.getInt("id"));
	        profile.setFirstName(rs.getString("firstName"));
	        profile.setLastName(rs.getString("lastName"));
	        profile.setDateOfBirth(rs.getDate("dateOfBirth"));
		profile.seteMail(rs.getString("email"));
		profile.setHeight(rs.getInt("height"));
//		profile.setConfession(Profile.Confession.valueOf("confession"));
		profile.setSmoker(rs.getBoolean("smoker"));
//		profile.setHairColor(Profile.HairColor.valueOf("hairColor"));
//		profile.setGender(Profile.Gender.valueOf("gender"));

	   
	        result.addElement(profile);
	      }
	    }
	    catch (SQLException e2) {
	      e2.printStackTrace();
	    }

	    return result;
	  }


	public Vector<Profile> findByBlocking(Blocking blocking) {

	    return findByBlocking(blocking.getId());
	  }

	


}
