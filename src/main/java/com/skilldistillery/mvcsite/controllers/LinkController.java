package com.skilldistillery.mvcsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {
	
	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "WEB-INF/home.jsp";
	}
	
	@RequestMapping(path = { "goViewFilm.do" })
	public String goViewFilm() {
		return "WEB-INF/viewFilm.jsp";
	}
	
	@RequestMapping(path = { "goAddFilm.do" })
	public String goAddFilm() {
		return "WEB-INF/addFilm.jsp";
	}
	
	@RequestMapping(path = { "goSearchFilm.do" })
	public String goSearchFilm() {
		return "WEB-INF/searchFilm.jsp";
	}

}
