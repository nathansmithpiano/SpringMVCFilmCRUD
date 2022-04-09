package com.skilldistillery.mvcsite.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
//		mv.addObject("president", p);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
//	From Miguel:
//	String[] featuresArr = rs.getString("special_features").split(",");
//	Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
//	film.setSpecialFeatures(featuresSet);

}
