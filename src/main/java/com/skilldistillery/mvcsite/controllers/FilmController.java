package com.skilldistillery.mvcsite.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.skilldistillery.mvcsite.data.FilmDAO;
import com.skilldistillery.mvcsite.entities.Film;

@Controller
public class FilmController {

	// THIS IS A TEST, REMOVE THIS

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = { "displayFilm.do" })
	public String displayFilm(Model model, String filmid) {

		int filmId;
		Film f = null;

		try {
			filmId = Integer.parseInt(filmid);
			f = filmDao.getFilmById(filmId);
		} catch (Exception e) {
		}
		model.addAttribute("film", f);
		return "WEB-INF/film.jsp";

	}

	@RequestMapping(path = "removeFilm.do")
	public String removeFilmById(Model model, String filmid) {

		int filmId;
		boolean filmDeleted = false;

		try {
			filmId = Integer.parseInt(filmid);
			if (filmId >= 1000)
				filmDeleted = filmDao.deleteFilmById(filmId);
		} catch (Exception e) {
			filmDeleted = false;
		}
		System.out.println("Film Deleted: " + filmDeleted);
		model.addAttribute("filmDeleted", filmDeleted);
		return "WEB-INF/film.jsp";
	}
	
	@RequestMapping(path = "removeFilmFromTable.do")
	public String removeFilmbyId2(Model model, String filmid, String searchTerm) {
		int filmId;
		boolean filmDeleted = false;

		try {
			filmId = Integer.parseInt(filmid);
			if (filmId >= 1000)
				filmDeleted = filmDao.deleteFilmById(filmId);
		} catch (Exception e) {
			filmDeleted = false;
		}
		System.out.println("Film Deleted: " + filmDeleted);
		model.addAttribute("filmDeleted", filmDeleted);
		return "redirect:/searchFilm.do?filmid=" + searchTerm;		
	}

	@RequestMapping(path = "updateFilm.do")
	public String updateFilm(Model model, Film film) {

		boolean updated = filmDao.updateFilm(film);
		if (updated) {
			model.addAttribute(film);
		} else {
			film = new Film();
		}
		return "WEB-INF/film.jsp?";
	}

	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		System.out.println("*** FilmController.addFilm() *** ");
		filmDao.addFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("status", "add");
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	

	// -------------------------------------------
	@RequestMapping(path = { "searchFilm.do" })
	public String searchByKeyWord(Model model, String filmid) {

		List<Film> films = null;

		try {
			System.out.println("Film id" + filmid);
			films = filmDao.searchFilms(filmid);
		} catch (Exception e) {
			
		}

		model.addAttribute("searchTerm", filmid);
		
		model.addAttribute("films", films);
		return "WEB-INF/viewFilms.jsp";

	}
}
