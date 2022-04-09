package com.skilldistillery.mvcsite.controllers;

import java.util.Set;

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

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "WEB-INF/home.jsp";
	}

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

	@RequestMapping(path = "updateFilm.do")
	public String updateFilm(Model model, Film film) {

		return "WEB-INF/film";
	}

	@RequestMapping(path = "addFilm.do", params = { "title", "description", "releaseYear", "languageId",
			"rentalDuration", "rentalRate", "length", "replacementCost", "rating",
			"specialFeatures" }, method = RequestMethod.GET)
	public ModelAndView addFilm(String title, String description, Integer releaseYear, int languageId,
			int rentalDuration, int rentalRate, Integer length, Double replacementCost, String rating,
			Set<String> specialFeatures) {
		System.out.println("*** FilmController.addFilm() *** ");
		ModelAndView mv = new ModelAndView();
		Film f = new Film();
//		mv.addObject("president", p);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;

	}

}
