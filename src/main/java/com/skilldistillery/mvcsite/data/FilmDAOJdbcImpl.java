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

import com.skilldistillery.mvcsite.entities.Actor;
import com.skilldistillery.mvcsite.entities.Film;

public class FilmDAOJdbcImpl implements FilmDAO {
	
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
					
					film.setCast(findActorsByFilmId(film.getId()));
			
					String categories = findFilmCategory(film);
					film.setCategory(categories);
					
					
					
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
	public boolean addFilm(Film film) {
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "INSERT INTO film ("
					+ "title, "
					+ "description, "
					+ "release_year, "
					+ "language_id, "
					+ "rental_duration, "
					+ "rental_rate, "
					+ "length, "
					+ "replacement_cost, "
					+ "rating, "
					+ "special_features)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			conn.setAutoCommit(false); // START TRANSACTION

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, film.getTitle());
			ps.setString(2, film.getDescription());
			if (film.getReleaseYear() == null) {
				ps.setDate(3, null);
			} else {
				ps.setInt(3, film.getReleaseYear());
			}
			if (film.getLanguageId() == null) {
				ps.setInt(4, 1);
			} else {
				ps.setInt(4, film.getLanguageId());
			}
			if (film.getRentalDuration() == null) {
				ps.setInt(5, 3);
			} else {
				ps.setInt(5, film.getRentalDuration());
			}
			if (film.getRental_rate() == null) {
				ps.setDouble(6, 4.99);
			} else {
				ps.setDouble(6, film.getRental_rate());
			}
			if (film.getLength() == null) {
				ps.setString(7, null);
			} else {
				ps.setInt(7,  film.getLength());
			}
			if (film.getReplacementCost() == null) {
				ps.setDouble(8, 19.99);
			} else {
				ps.setDouble(8, film.getReplacementCost());
			}
			ps.setString(9,  film.getRating());
			ps.setString(10, null);
			
			System.out.println("*** SQL: " + ps);
			
			ps.executeUpdate();
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
					+ "title = ? ,\n"
					+ "description = ? ,\n"
					+ "release_year = ?,\n"
					+ "language_id = ?,\n"
					+ "rental_duration = ?,\n"
					+ "rental_rate = ?,\n"
					+ "length = ?,\n"
					+ "replacement_cost = ?,\n"
					+ "rating = ?,\n"
					+ "special_features = ?\n"
					+ "WHERE id = ?;";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			if (film.getReleaseYear() == null) {
				stmt.setDate(3, null);
			} else {
				stmt.setString(3, film.getReleaseYear()  + "");
			}
			if (film.getLanguageId() == null) {
				stmt.setInt(4, 1);
			} else {
				stmt.setInt(4, film.getLanguageId());
			}
			if (film.getRentalDuration() == null) {
				stmt.setInt(5, 3);
			} else {
				stmt.setInt(5, film.getRentalDuration());
			}
			if (film.getRental_rate() == null) {
				stmt.setDouble(6, 4.99);
			} else {
				stmt.setDouble(6, film.getRental_rate());
			}
			if (film.getLength() == null) {
				stmt.setString(7, null);
			} else {
				stmt.setInt(7, film.getLength());
			}
			if (film.getReplacementCost() == null) {
				stmt.setDouble(8, 19.99);
			} else {
				stmt.setDouble(8, film.getReplacementCost());
			}
			stmt.setString(9, film.getRating());
			stmt.setString(10, null);
			stmt.setInt(11, film.getId()); //WHERE id= ?
			
			System.out.println("*** SQL: " + stmt);

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
					// release_year and length can be null, so display as null (not 0)
					if (rs.getString("release_year") == null) {
						film.setReleaseYear(null);
					} else {
						film.setReleaseYear(rs.getInt("release_year"));
					}
					if (rs.getString("length") == null) {
						film.setLength(null);
					} else {
						film.setLength(rs.getInt("length"));
					}
					//NOT NULL, ALWAYS RECEIVED FROM FORM
					film.setLanguageId(rs.getInt("language_id"));
					film.setRentalDuration(rs.getInt("rental_duration"));
					film.setRental_rate(rs.getDouble("rental_rate"));
					film.setReplacementCost(rs.getDouble("Replacement_cost"));
					film.setRating(rs.getString("rating"));
					film.setLanguage(rs.getString("language.name"));
					
					// YES NULL

					String rsFeatures = rs.getString("special_features");
					if(rsFeatures != null) {
						String[] featuresArr = rs.getString("special_features").split(",");
						Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
						film.setSpecialFeatures(featuresSet);
					}
					else {
						film.setSpecialFeatures(new HashSet<>());
					}

					
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
	
	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<Actor>();

		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);

			String sql = "select id, first_name, last_name FROM actor join film_actor on actor.id = film_actor.actor_id where film_actor.film_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, filmId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt(1));
				actor.setFirstName(rs.getString(2));
				actor.setLastName(rs.getString(3));
				actors.add(actor);
//				findFilmByKeyWord(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actors;
	}
	@Override
	public String findFilmCategory(Film film) {

		if (film == null) {
			return null;
		}

		String category = null;

		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		
		
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sql = "select category from film_list JOIN film ON film.id = film_list.fid where film.id = ?";
			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, film.getId());
			rs = stmt.executeQuery();

			while (rs.next()) {

				category = rs.getString(1);
				film.setCategory(category);

			}

			return category;

		} catch (SQLException e) {
			System.out.println("Database error");
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				} 
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.err.println(sqle);
			}
		}

		return null;

}
} 