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

	@Autowired
	private FilmDAO filmDao;

	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "WEB-INF/home.jsp";
	}
	@RequestMapping(path = { "displayFilms.do" })
	public String displayFilm() {
		return "WEB-INF/displayFilms.jsp";
	}
	
	@RequestMapping(path = "addFilm.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film, String[] sf) {
		System.out.println("*** FilmController.addFilm() *** ");
		ModelAndView mv = new ModelAndView();
		System.out.println(film);
	//	mv.addObject("president", p);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
	@RequestMapping(path = "removeFilm.do")
	public String removeFilmById(Model model, String filmid) {
		
		int filmId;
		boolean filmDeleted = false;
		
		try {
			filmId = Integer.parseInt(filmid);
			if(filmId >= 1000)
				filmDeleted = filmDao.deleteFilmById(filmId);
		}catch(Exception e) {
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
	
}
