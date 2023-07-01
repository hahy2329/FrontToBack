package com.application.FrontToBack.administrator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/administrator")
public class AdministratorController {
	
	@GetMapping("/introduce")
	public ModelAndView introduce() throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/administrator/introduce");
		return mv;
		
	}
	
	
}
