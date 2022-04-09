package com.skilldistillery.mvcsite.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
					
					
					String[] featuresArr = rs.getString("special_features").split(",");
					Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
					film.setSpecialFeatures(featuresSet);
					
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
	public Film deleteFilmById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film updateFilmById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Film> searchFilms(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}
