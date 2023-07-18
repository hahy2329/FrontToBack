package com.application.FrontToBack.noticeBoard.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.member.controller.MemberController;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;
import com.application.FrontToBack.noticeBoard.service.NoticeBoardService;

@Controller
@RequestMapping("/boardAdvance")
public class NoticeBoardController {

	
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	
	public static boolean onScheduled = false;
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);
	
	
	@Scheduled(cron = "0 10-40 20 * * *")
	public void autoUpdate() throws Exception{
		
		logger.info(new Date() + "스케줄러 실행");
		
		onScheduled = true;
		
	}
	
	@GetMapping("/noticeList")
	public ModelAndView noticeList(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			mv.setViewName("boardAdvance/noticeList");
			
			String searchKeyword = request.getParameter("searchKeyword");
			if(searchKeyword == null) searchKeyword = "total";
			
			String searchWord = request.getParameter("searchWord");
			if(searchWord == null) searchWord = "";
			//검색 바에서 키워드 입력, 없으면 ""
			
			
			int onePageViewCnt = 10; //초깃값, 한 페이지 당 10개씩 보여주기 
			
			if(request.getParameter("onePageViewCnt") != null) {
				onePageViewCnt = Integer.parseInt(request.getParameter("onePageViewCnt"));
			} 
			
			
			String temp = request.getParameter("currentPageNumber"); //현재 페이지 
			if(temp ==null) {
				temp = "1";
			}
			
			int currentPageNumber = Integer.parseInt(temp);
			
			
			Map<String, String> searchCntMap = new HashMap<String, String>();
			searchCntMap.put("searchKeyword", searchKeyword);
			searchCntMap.put("searchWord", searchWord);
			
			int allBoardCnt = noticeBoardService.getAllBoardCnt(searchCntMap);
			//전체 검색결과 수
			
			
			int allPageCnt = allBoardCnt / onePageViewCnt + 1;
			
			if(allBoardCnt % onePageViewCnt==0) allPageCnt --;
			
			int startPage = (currentPageNumber - 1) /10 * 10 +1;
			//스타트 페이지 
			
			if(startPage == 0) {
				startPage =1;
			}
			
			
			int endPage = startPage + 9;
			//총 10페이지 씩 단위로 구성 예정(예) 1 ~ 10, 11~20 ...)
			
			if(endPage > allPageCnt) endPage = allPageCnt;
			
			
			int startBoardIdx = (currentPageNumber - 1) * onePageViewCnt;
			
			mv.addObject("startPage", startPage); //스타트 페이지
			mv.addObject("endPage", endPage); //끝 페이지
			mv.addObject("allBoardCnt", allBoardCnt); // 전체검색결과 갯수 
			mv.addObject("allPageCnt", allPageCnt); // 전체 페이지 수 
			mv.addObject("onePageViewCnt", onePageViewCnt); //한 페이지에 보여질 갯수 
			mv.addObject("currentPageNumber", currentPageNumber); //현재 페이지  
			mv.addObject("startBoardIdx", startBoardIdx); //각 게시글에 주어지는 일련번호
			mv.addObject("searchKeyword", searchKeyword); //검색 범위
			mv.addObject("searchWord",searchWord); // 검색 키워드
			
			Map<String, Object> searchMap = new HashMap<String, Object>();
			searchMap.put("onePageViewCnt", onePageViewCnt);
			searchMap.put("startBoardIdx", startBoardIdx);
			searchMap.put("searchKeyword", searchKeyword);
			searchMap.put("searchWord", searchWord);
			mv.addObject("boardList", noticeBoardService.getBoardList(searchMap));
			
			
			return mv;
		}
	}
	
	@GetMapping("/noticeDetail")
	public ModelAndView noticeDetail(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			NoticeDTO noticeDTO = noticeBoardService.getNoticeBoardDetail(boardId, true);
			int allReplyCnt = noticeBoardService.getAllNoticeReplyCnt(boardId);
			List<NoticeReplyDTO> noticeReplyDTO = noticeBoardService.getAllNoticeReplyList(boardId);
			
			
			mv.addObject("noticeDTO", noticeDTO);
			mv.addObject("allReplyCnt", allReplyCnt);
			mv.addObject("noticeReplyDTO", noticeReplyDTO);
			
			mv.setViewName("/boardAdvance/noticeDetail");
			
			return mv;
		}	
		
	}
	
	
	@GetMapping("/noticeAddReply")
	public ModelAndView noticeAddReply(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/boardAdvance/noticeAddReply");
		
		return mv;
	}
	
	@PostMapping("/noticeAddReply")
	public ResponseEntity<Object> noticeAddReply(NoticeReplyDTO noticeReplyDTO, HttpServletRequest request) throws Exception{
		
		noticeBoardService.noticeAddReply(noticeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록되었습니다.');";
		message +="location.href='"+ request.getContextPath() + "/boardAdvance/noticeDetail?boardId=" +noticeReplyDTO.getBoardId() + "';";
		message +="</script>";
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/noticeUpdateReply")
	public ModelAndView noticeUpdateReply(@RequestParam("replyId") long replyId) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		NoticeReplyDTO noticeReplyDTO = noticeBoardService.noticeReplyDetail(replyId);
		mv.addObject("noticeReplyDTO", noticeReplyDTO);
		mv.setViewName("/boardAdvance/noticeUpdateReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/noticeUpdateReply")
	public ResponseEntity<Object> noticeUpdateReply(NoticeReplyDTO noticeReplyDTO, HttpServletRequest request) throws Exception{
		
		noticeBoardService.noticeUpdateReply(noticeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정이 완료되었습니다.');";
		message +="location.href='"+request.getContextPath() + "/boardAdvance/noticeDetail?boardId=" +noticeReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/noticeRemoveReply")
	public ModelAndView noticeRemoveReply(@RequestParam("replyId") long replyId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		NoticeReplyDTO noticeReplyDTO = noticeBoardService.noticeReplyDetail(replyId);
		
		mv.addObject("noticeReplyDTO", noticeReplyDTO);
		mv.setViewName("/boardAdvance/noticeRemoveReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/noticeRemoveReply")
	public ResponseEntity<Object> noticeRemoveReply(NoticeReplyDTO noticeReplyDTO, HttpServletRequest request) throws Exception{
		
		noticeBoardService.removeNoticeReply(noticeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/noticeDetail?boardId=" +noticeReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
	}
	
	
	
}
