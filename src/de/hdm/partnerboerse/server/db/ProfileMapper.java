package de.hdm.partnerboerse.server.db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

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

			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid "
					+ "FROM profiles ");

			if (rs.next()) {

				profile.setId(rs.getInt("maxid") + 1);

				stmt = con.createStatement();

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");

				String sql = "INSERT INTO profiles (id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender) "
						+ "VALUES ("
						+ profile.getId()
						+ ",'"
						+ profile.getFirstName()
						+ "','"
						+ profile.getLastName()
						+ "','"
						+ simpleDateFormat.format(profile.getDateOfBirth())
						+ "','"
						+ profile.geteMail()

						+ "',"
						+ profile.getHeight()
						+ ",'"
						+ profile.getConfession()
						+ "','"
						+ (profile.isSmoker() ? 1 : 0)
						+ "','"
						+ profile.getHairColor()
						+ "','"
						+ profile.getGender()
						+ "')";
				System.out.println(sql);
				stmt.executeUpdate(sql);
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

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");

			stmt.executeUpdate("UPDATE profiles " + "SET firstName=\""
					+ profile.getFirstName() + "\", " + "lastName=\""
					+ profile.getLastName() + "\", " + "dateOfBirth=\""
					+ simpleDateFormat.format(profile.getDateOfBirth())
					+ "\", " + "email=\"" + profile.geteMail() + "\", "
					+ "height=\"" + profile.getHeight() + "\", "
					+ "confession=\""

					+ profile.getConfession() + "\", " + "smoker=\""
					+ (profile.isSmoker() ? 1 : 0) + "\", " + "hairColor=\""
					+ profile.getHairColor() + "\", " + "gender=\""
					+ profile.getGender() + "\" " + "WHERE id="
					+ profile.getId());

		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return profile;
	}

	public void delete(Profile profile) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			stmt.executeUpdate("DELETE FROM profiles " + "WHERE id="
					+ profile.getId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public ArrayList<Profile> findAll() {
		Connection con = DBConnection.connection();

		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles " + "ORDER BY lastName");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				result.add(profile);
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

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles "
							+ "WHERE id=" + id + " ORDER BY lastName");

			if (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				return profile;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Profile findByEmail(String email) {
		Connection con = DBConnection.connection();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender "
							+ "FROM profiles WHERE email = '"
							+ email
							+ "' LIMIT 1");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				return profile;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<Profile> findBySearchProfile(SearchProfile searchProfile) {

		Connection con = DBConnection.connection();
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			Statement stmt = con.createStatement();

			String sql = "SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles WHERE profiles.id > 0";

			if (searchProfile.getFromHeight() != 0) {
				sql += "AND profiles.height > " + searchProfile.getFromHeight()
						+ " ";
			}

			if (searchProfile.getToHeight() != 0) {
				sql += "AND profiles.height < " + searchProfile.getToHeight()
						+ " ";
			}

			if (searchProfile.getHairColor() != null) {
				sql += "AND profiles.hairColor = '"
						+ searchProfile.getHairColor() + "' ";
			}

			if (searchProfile.getConfession() != null) {
				sql += "AND profiles.confession = '"
						+ searchProfile.getConfession() + "' ";
			}

			if (searchProfile.getGender() != null) {
				sql += "AND profiles.gender = '" + searchProfile.getGender()
						+ "' ";
			}

			if (searchProfile.getFromAge() != 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Date dateOfBirth = new Date(System.currentTimeMillis()
						- searchProfile.getFromAge() * 365 * 24 * 60 * 60
						* 1000);
				sql += "AND profiles.dateOfBirth < '"
						+ simpleDateFormat.format(dateOfBirth) + "' ";
			}

			if (searchProfile.getToAge() != 0) {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy-MM-dd");
				Date dateOfBirth = new Date(System.currentTimeMillis()
						- searchProfile.getToAge() * 365 * 24 * 60 * 60 * 1000);
				sql += "AND profiles.dateOfBirth > '"
						+ simpleDateFormat.format(dateOfBirth) + "' ";
			}

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Profile> findMostSimilarProfiles(Profile fromProfile) {
		Connection con = DBConnection.connection();
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles similarProfiles INNER JOIN similarities ON similarities.toProfile = similarProfiles.id WHERE similarities.fromProfile = "
							+ fromProfile.getId()
							+ " AND  similarities.similarityValue > 0.5 ORDER BY similarities.similarityValue DESC");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}

	public ArrayList<Profile> findNotViewedProfiles(Profile vistingProfile) {
		Connection con = DBConnection.connection();
		ArrayList<Profile> result = new ArrayList<Profile>();

		try {
			Statement stmt = con.createStatement();

			ResultSet rs = stmt
					.executeQuery("SELECT id, firstName, lastName, dateOfBirth, email, height, confession, smoker, hairColor, gender FROM profiles LEFT JOIN visits ON visits.toProfile = profiles.id WHERE visits.id IS NULL OR NOT visits.fromProfile = "
							+ vistingProfile.getId() + " GROUP BY profiles.id");

			while (rs.next()) {
				Profile profile = new Profile();
				profile.setId(rs.getInt("id"));
				profile.setFirstName(rs.getString("firstName"));
				profile.setLastName(rs.getString("lastName"));
				profile.setDateOfBirth(rs.getDate("dateOfBirth"));
				profile.seteMail(rs.getString("email"));
				profile.setHeight(rs.getInt("height"));
				profile.setConfession(Profile.Confession.valueOf(rs
						.getString("confession")));
				profile.setSmoker(rs.getBoolean("smoker"));
				profile.setHairColor(Profile.HairColor.valueOf(rs
						.getString("hairColor")));
				profile.setGender(Profile.Gender.valueOf(rs.getString("gender")));

				result.add(profile);
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}

		return result;
	}
	
	public static void main(String[] args) {
		ProfileMapper profileMapper = new ProfileMapper();
		profileMapper.findAll();
		profileMapper.findByEmail("");
		profileMapper.findByKey(1);
	}

}
