package com.application.FrontToBack.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommonController {
	
	
	@GetMapping("/main")
	public ModelAndView main() {
		ModelAndView mv= new ModelAndView();
		mv.setViewName("/main");
		return mv;
	}
	
	
}
