package com.skilldistillery.mvcsite.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.skilldistillery.mvcsite.entities.Actor;
import java.util.List;

import com.skilldistillery.mvcsite.entities.Film;

public class FilmDAOJdbcImpl implements FilmDAO {
	
	
	public static void main(String[] args) {
		FilmDAOJdbcImpl dao = new FilmDAOJdbcImpl();
		boolean deleted = dao.deleteFilmById(1009);
		System.out.println("Deleted: " + deleted);
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
		// TODO Auto-generated method stub
		return null;
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
			
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			updateCount = stmt.executeUpdate();
			
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



