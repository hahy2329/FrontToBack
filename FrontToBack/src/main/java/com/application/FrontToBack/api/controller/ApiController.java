package com.application.FrontToBack.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApiController {

	
	@GetMapping("/api")
	public ModelAndView api() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/api");
		return mv;
	}
}
