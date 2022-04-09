package com.skilldistillery.mvcsite.data;

import java.util.List;

import com.skilldistillery.mvcsite.entities.Film;

public interface FilmDAO {
	
	public Film getFilmById(int id); 				//US1
	public Film addFilm(Film film);					//US2
	public boolean deleteFilmById(int id);				//US3
	public boolean updateFilm(Film film);				//US4
	public List<Film> searchFilms(String keyword);	//US5

}
