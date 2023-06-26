package com.application.FrontToBack.bookSearch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookSearchController {

	
	@GetMapping("/bookSearch")
	public ModelAndView bookSearch() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/bookSearch");
		return mv;
	}
	
	@GetMapping("/video")
	public ModelAndView video() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/video");
		return mv;
	}
}
