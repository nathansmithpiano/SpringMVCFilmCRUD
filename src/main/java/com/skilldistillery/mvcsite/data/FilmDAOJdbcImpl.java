package com.skilldistillery.mvcsite.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.skilldistillery.mvcsite.entities.Film;

public class FilmDAOJdbcImpl implements FilmDAO {
	

	public static void main(String[] args) {
		FilmDAOJdbcImpl dao = new FilmDAOJdbcImpl();
		Film film = dao.getFilmById(100);
		System.out.println(film);
		film.setDescription("improved updeate 2");
		boolean updated = dao.updateFilm(film);
		
		System.out.println("Updated: " + updated);
	}
	
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false"
			+ "&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";

	static {
		String packageName = "com.mysql.cj.jdbc";
		String className = "Driver";

		try {
			Class.forName(packageName + "." + className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println(className + " not found.");
			throw new RuntimeException("Unable to load MYSQL " + className + " class");
		}
	}

	@Override
	public Film getFilmById(int id) {
		Film film = null;

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select film.id, film.title, film.description, film.release_year, film.language_id, film.rental_duration, film.rental_rate, "
					+ " film.length, film.replacement_cost, film.rating, film.special_features, language.name "
					+ " from film join language on film.language_id = language.id WHERE film.id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			{

				if (rs.next()) {
					film = new Film();

					film.setId(rs.getInt("id"));
					film.setTitle(rs.getString("title"));
					film.setDescription(rs.getString("description"));
					film.setReleaseYear(rs.getInt("release_year"));
					film.setLanguageId(rs.getInt("language_id"));
					film.setRentalDuration(rs.getInt("rental_duration"));
					film.setRental_rate(rs.getDouble("rental_rate"));
					film.setLength(rs.getInt("length"));
					film.setReplacementCost(rs.getDouble("Replacement_cost"));
					film.setRating(rs.getString("rating"));
					
					
					String features = rs.getString("special_features");
					if(features != null) {
						String[] featuresArr = rs.getString("special_features").split(",");
						
						Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
						film.setSpecialFeatures(featuresSet);
					}
					else{
						film.setSpecialFeatures(new HashSet<>());
					}
					
					film.setLanguage(rs.getNString("language.name"));
					
//					film.setActors(findActorsByFilmId(id));

				}
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}
	
	@Override
	public Film addFilm(Film film) {
		Film newFilm = null;
		
		return newFilm;
	}

	@Override
	public boolean deleteFilmById(int id) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			
			conn.setAutoCommit(false); // START TRANSACTION
			
			String sql = "DELETE FROM film WHERE id = ?";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			int updateCount = stmt.executeUpdate();
			if(updateCount == 0) {
				return false;
			}
			
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public boolean updateFilm(Film film) {
		Connection conn = null;
		try {
			/*
			 * assume everything except the actor's id (PK) may have changed update the
			 * actor's fn, ln, and their current list of films in the database
			 */
			conn = DriverManager.getConnection(URL, user, pass);

			conn.setAutoCommit(false); // START TRANSACTION

			String sql = "UPDATE film SET \n"
					+ "title= ? ,\n"
					+ "description= ? ,\n"
					+ "release_year= ?,\n"
					+ "language_id= ?,\n"
					+ "rental_duration = ?,\n"
					+ "rental_rate = ?,\n"
					+ "length = ?,\n"
					+ "replacement_cost = ?,\n"
					+ "rating = ?,\n"
					+ "special_features = ?\n"
					+ "WHERE id= ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRental_rate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, null);
			stmt.setInt(11, film.getId());
			
//			System.out.println(stmt);

			int updateCount = stmt.executeUpdate();
			if(updateCount == 0) {
				return false;
			}
			if (updateCount == 1) {
				conn.commit(); // COMMIT TRANSACTION
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} // ROLLBACK TRANSACTION ON ERROR
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	@Override
	public List<Film> searchFilms(String keyword) {
		List<Film> films = new ArrayList<Film>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String keyWordConcat = "%" + keyword + "%";
			String sql = "select film.id, film.title, film.description, film.release_year, film.language_id, "
					+ " film.rental_duration, film.rental_rate, film.length, film.replacement_cost, film.rating, film.special_features, language.name"
					+ " from film join language on film.language_id = language.id "
					+ " WHERE title LIKE ? or description LIKE ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, keyWordConcat);
			ps.setString(2, keyWordConcat);
			ResultSet rs = ps.executeQuery();
			{

				while (rs.next()) {
					Film film = new Film();

					film.setId(rs.getInt("id"));
					film.setTitle(rs.getString("title"));
					film.setDescription(rs.getString("description"));
					film.setReleaseYear(rs.getInt("release_year"));
					film.setLanguageId(rs.getInt("language_id"));
					film.setRentalDuration(rs.getInt("rental_duration"));
					film.setRental_rate(rs.getDouble("rental_rate"));
					film.setLength(rs.getInt("length"));
					film.setReplacementCost(rs.getDouble("Replacement_cost"));
					film.setRating(rs.getString("rating"));
					film.setLanguage(rs.getNString("language.name"));

					String[] featuresArr = rs.getString("special_features").split(",");
					Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
					film.setSpecialFeatures(featuresSet);
					
					films.add(film);

				}
				rs.close();
				ps.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	
	}

}
