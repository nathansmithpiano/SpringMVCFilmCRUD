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
	
	@RequestMapping(path = "addFilm.do",
			params = { "title", "description", "releaseYear", "languageId", "rentalDuration", 
					"rentalRate", "length", "replacementCost", "rating", "specialFeatures" },
			method = RequestMethod.GET)
	public ModelAndView addFilm(String title, String description, Integer releaseYear, 
								int languageId, int rentalDuration, int rentalRate,
								Integer length, Double replacementCost, String rating, 
								Set<String> specialFeatures) {
		System.out.println("*** FilmController.addFilm() *** ");
		ModelAndView mv = new ModelAndView();
		Film f = new Film();
//		mv.addObject("president", p);
		mv.setViewName("WEB-INF/result.jsp");
		return mv;
	}
	
//	From Miguel:
//	String[] featuresArr = rs.getString("special_features").split(",");
//	Set<String> featuresSet = new HashSet<>(Arrays.asList(featuresArr));
//	film.setSpecialFeatures(featuresSet);

}
