package com.application.FrontToBack;



import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.service.BookBoardSerive;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.service.KnowledgeBoardService;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.service.NoticeBoardService;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.service.QnaBoardService;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.service.StudyBoardService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	
	public static boolean onScheduled = false;
	
	@Autowired
	private StudyBoardService studyBoardService;
	
	@Autowired 
	private KnowledgeBoardService knowledgeBoardService;
	
	@Autowired
	private QnaBoardService qnaBoardService;
	
	@Autowired
	private BookBoardSerive bookBoardSerive;
	
	@Autowired 
	private NoticeBoardService noticeBoardService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled==true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		
		else {
			
			List<StudyDTO> studyList = studyBoardService.getMainStudyBoard();
			List<KnowledgeDTO> knowledgeList = knowledgeBoardService.getMainKnowledgeBoard();
			List<QnaDTO> qnaList = qnaBoardService.getMainQnaBoard();
			List<BookDTO> bookList = bookBoardSerive.getMainBookBoard();
			List<NoticeDTO> noticeList = noticeBoardService.getMainNoticeBoard();
			
			
			mv.addObject("qnaList", qnaList);
			mv.addObject("studyList", studyList);
			mv.addObject("knowledgeList", knowledgeList);
			mv.addObject("bookList", bookList);
			mv.addObject("noticeList", noticeList);
			
			mv.setViewName("/main");
			
			return mv;
		}
	}
	
	@Scheduled(cron = "0 10-40 23 21 * *")
	public void autoUpdate() throws Exception{
		
		logger.info(new Date() + "스케줄러 실행");
		
		System.out.println("안녕하신가요~~");
		onScheduled = true;
		
	}
	
	
}
