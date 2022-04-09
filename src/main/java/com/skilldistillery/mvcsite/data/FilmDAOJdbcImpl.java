package com.skilldistillery.mvcsite.data;

import java.util.List;

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
		// TODO Auto-generated method stub
		return null;
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
