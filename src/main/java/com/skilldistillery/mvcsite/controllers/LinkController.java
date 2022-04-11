package com.skilldistillery.mvcsite.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {
	
	@RequestMapping(path = { "/", "home.do" })
	public String home() {
		return "redirect:/searchFilm.do?filmid=";
	}

	@RequestMapping(path = { "goAddFilm.do" })
	public String goAddFilm() {
		return "WEB-INF/addFilm.jsp";
	}
	
}