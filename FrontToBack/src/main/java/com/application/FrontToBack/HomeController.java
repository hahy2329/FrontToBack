package com.application.FrontToBack;



import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.service.KnowledgeBoardService;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.service.QnaBoardService;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.service.StudyBoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private StudyBoardService studyBoardService;
	
	@Autowired 
	private KnowledgeBoardService knowledgeBoardService;
	
	@Autowired
	private QnaBoardService qnaBoardService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = new ModelAndView();
		List<StudyDTO> studyList = studyBoardService.getMainStudyBoard();
		List<KnowledgeDTO> knowledgeList = knowledgeBoardService.getMainKnowledgeBoard();
		List<QnaDTO> qnaList = qnaBoardService.getMainQnaBoard();
		
		mv.addObject("qnaList", qnaList);
		mv.addObject("studyList", studyList);
		mv.addObject("knowledgeList", knowledgeList);
		mv.setViewName("/main");
		
		return mv;
	}
	
	
	
	
}
