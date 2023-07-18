package com.application.FrontToBack.studyBoard.controller;

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

import com.application.FrontToBack.qnaBoard.controller.QnaBoardController;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.dto.StudyReplyDTO;
import com.application.FrontToBack.studyBoard.service.StudyBoardService;

@Controller
@RequestMapping("/boardAdvance")
public class StudyBoardController {
	
	@Autowired
	private StudyBoardService studyBoardService;
	
	
	public static boolean onScheduled = false;
	
	private static final Logger logger = LoggerFactory.getLogger(StudyBoardController.class);
	
	@Scheduled(cron = "0 10-40 20 * * *")
	public void autoUpdate() throws Exception{
		
		logger.info(new Date() + "스케줄러 실행");
		
		onScheduled = true;
		
	}
	
	
	
	@GetMapping("/studyList") 
	public ModelAndView studyList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		
		else {
			mv.setViewName("/boardAdvance/studyList");
			
			String searchKeyword = request.getParameter("searchKeyword");
			if(searchKeyword == null) searchKeyword = "total";
			//셀렉트 박스에서 (memberId, subject, content 중 택 1) 없으면 total 
			
			
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
			//숫자로 형 변환
			
			
			Map<String, String> searchCntMap = new HashMap<String, String>();
			searchCntMap.put("searchKeyword", searchKeyword);
			searchCntMap.put("searchWord", searchWord);
			
			int allBoardCnt = studyBoardService.getAllStudyBoardCnt(searchCntMap);
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
			mv.addObject("boardList", studyBoardService.getStudyBoardList(searchMap));
			
			
			return mv;
		}
	}
	
	@GetMapping("/studyAddBoard")
	public ModelAndView studyAddBoard() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/boardAdvance/studyAddBoard");
		return mv;
		
	}
	
	@PostMapping("/studyAddBoard")
	public ResponseEntity<Object> studyAddBoard(StudyDTO studyDTO, HttpServletRequest request) throws Exception{
		
		studyBoardService.insertStudyBoard(studyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록완료되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/studyList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/studyDetail")
	public ModelAndView studyDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			StudyDTO studyDTO = studyBoardService.getStudyBoardDetail(boardId, true);
			int allReplyCnt = studyBoardService.getAllStudyReplyCnt(boardId);
			List<StudyReplyDTO> studyReplyDTO = studyBoardService.getAllStudyReplyList(boardId);
			
			mv.addObject("studyDTO", studyDTO);
			mv.addObject("allReplyCnt", allReplyCnt);
			mv.addObject("studyReplyDTO", studyReplyDTO);
			
			mv.setViewName("/boardAdvance/studyDetail");
			
			return mv;
		}
		
		
	}
	
	@GetMapping("/studyUpdateBoard")
	public  ModelAndView studyUpdateBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		StudyDTO studyDTO = studyBoardService.getStudyBoardDetail(boardId, false);
		mv.addObject("studyDTO", studyDTO);
		mv.setViewName("/boardAdvance/studyUpdate");
		
		return mv;
	
	}
	
	@PostMapping("/studyUpdateBoard")
	public ResponseEntity<Object> studyUpdateBoard(StudyDTO studyDTO, HttpServletRequest request) throws Exception{
		
		studyBoardService.updateStudyBoard(studyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/studyList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
		
	}	
	
	
	@GetMapping("/studyRemoveBoard")
	public ModelAndView studyRemoveBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		StudyDTO studyDTO = studyBoardService.getStudyBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/studyRemove");
		mv.addObject("studyDTO", studyDTO);
		
		return mv;
		
		
	}
	
	@PostMapping("/studyRemoveBoard")
	public ResponseEntity<Object> studyRemoveBoard(StudyDTO studyDTO, HttpServletRequest request) throws Exception{
		
		studyBoardService.removeStudyBoard(studyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/boardAdvance/studyList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/studyAddReply")
	public ModelAndView studyAddReply(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/boardAdvance/studyAddReply");
		
		return mv;
	}
	
	@PostMapping("/studyAddReply")
	public ResponseEntity<Object> studyAddReply(StudyReplyDTO studyReplyDTO, HttpServletRequest request) throws Exception{
		
		studyBoardService.studyAddReply(studyReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록되었습니다.');";
		message +="location.href='"+ request.getContextPath() + "/boardAdvance/studyDetail?boardId=" +studyReplyDTO.getBoardId() + "';";
		message +="</script>";
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/studyUpdateReply")
	public ModelAndView studyUpdateReply(@RequestParam("replyId") long replyId)throws Exception {
		ModelAndView mv = new ModelAndView();
		StudyReplyDTO studyReplyDTO = studyBoardService.studyReplyDetail(replyId);
		mv.addObject("studyReplyDTO", studyReplyDTO);
		mv.setViewName("/boardAdvance/studyUpdateReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/studyUpdateReply")
	public ResponseEntity<Object> studyUpdateReply(StudyReplyDTO studyReplyDTO, HttpServletRequest request) throws Exception{
		
		studyBoardService.studyUpdateReply(studyReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정이 완료되었습니다.');";
		message +="location.href='"+request.getContextPath() + "/boardAdvance/studyDetail?boardId=" +studyReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/studyRemoveReply")
	public ModelAndView studyRemoveReply(@RequestParam("replyId") long replyId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		StudyReplyDTO studyReplyDTO = studyBoardService.studyReplyDetail(replyId);
		mv.addObject("studyReplyDTO", studyReplyDTO);
		mv.setViewName("/boardAdvance/studyRemoveReply");
		
		return mv;
				
	}
	
	@PostMapping("/studyRemoveReply")
	public ResponseEntity<Object> studyRemoveReply(StudyReplyDTO studyReplyDTO, HttpServletRequest request) throws Exception{
		
		studyBoardService.removeStudyReply(studyReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/studyDetail?boardId=" +studyReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
		
	}
	
	
	
	
	
}
