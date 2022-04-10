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

	@RequestMapping(path = { "displayFilm.do" })
	public String displayFilm(Model model, String filmid) {
		
		int filmId;
		Film f = null;
		
		try {
			filmId = Integer.parseInt(filmid);
			f = filmDao.getFilmById(filmId);
		}catch(Exception e) {
		}
		model.addAttribute("film", f);
		return "WEB-INF/film.jsp";
		
	}
	
	@RequestMapping(path = "removeFilm.do")
	public String removeFilmById(Model model, Film film) {
		
		int filmId;
		boolean filmDeleted = false;
		
		try {
			filmId = film.getId();
			if(filmId > 1000)
				filmDeleted = filmDao.deleteFilmById(filmId);
		}catch(Exception e) {
			filmDeleted = false;			
		}
		System.out.println("Film Deleted: " + filmDeleted);
		
		model.addAttribute("status", "remove");
		model.addAttribute("film", film);
		model.addAttribute("filmDeleted", filmDeleted);
		return "WEB-INF/message.jsp";
		
	}
	
	@RequestMapping(path = "updateFilm.do")
	public String updateFilm(Model model, Film film) {
		
		boolean updated = filmDao.updateFilm(film);
		if(updated) {
			model.addAttribute("film", film);
			model.addAttribute("status", "update");
			model.addAttribute("filmUpdated", updated);
		} else {
			film = new Film();
		}
		return "WEB-INF/message.jsp";
	}
	
	
	@RequestMapping(path = "addFilm.do")
	public String addFilm(Model model, Film film) {
		System.out.println("*** Got this far");
		boolean added = filmDao.addFilm(film);
		if (added) {
			model.addAttribute("film", film);
			model.addAttribute("status", "add");
			model.addAttribute("filmAdded", added);
		} else {
			film = new Film();
		}
		return "WEB-INF/message.jsp";
	}
	//-------------------------------------------
	@RequestMapping(path = { "searchFilm.do" })
	public String searchByKeyWord(Model model, String filmid) {
		
		String m = "mon";
		Film f = null;
		
		try {
			
				filmDao.searchFilms(m);
		}catch(Exception e) {
		}
		return "WEB-INF/film.jsp";
		
	}
}
	

