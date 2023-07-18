package com.application.FrontToBack.knowledgeBoard.controller;

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

import com.application.FrontToBack.bookBoard.controller.BookBoardController;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.knowledgeBoard.service.KnowledgeBoardService;


@Controller
@RequestMapping("/boardAdvance")
public class KnowledgeBoardController {
	
	@Autowired
	private KnowledgeBoardService knowledgeBoardService;
	

	public static boolean onScheduled = false;
	
	private static final Logger logger = LoggerFactory.getLogger(KnowledgeBoardController.class);
	
	@GetMapping("/knowledgeList") 
	public ModelAndView knowledgeList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			mv.setViewName("/boardAdvance/knowledgeList");
			
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
			
			int allBoardCnt = knowledgeBoardService.getAllBoardCnt(searchCntMap);
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
			mv.addObject("boardList", knowledgeBoardService.getBoardList(searchMap));
			
			
			return mv;
		}
			
	}
	
	@Scheduled(cron = "0 10-40 20 * * *")
	public void autoUpdate() throws Exception{
		
		logger.info(new Date() + "스케줄러 실행");
		
		onScheduled = true;
		
	}
	
	
	@GetMapping("/knowledgeAddBoard")
	public ModelAndView knowledgeAddBoard() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			mv.setViewName("/boardAdvance/knowledgeAdd");
			return mv;
		}
	}
	
	@PostMapping("/knowledgeAddBoard")
	public ResponseEntity<Object> knowledgeAddBoard(KnowledgeDTO knowledgeDTO, HttpServletRequest request) throws Exception{
		
		knowledgeBoardService.insertKnowledgeBoard(knowledgeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록완료되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/knowledgeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/knowledgeDetail")
	public ModelAndView knowledgeDetail(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			KnowledgeDTO knowledgeDTO = knowledgeBoardService.getKnowledgeBoardDetail(boardId, true);
			int allReplyCnt = knowledgeBoardService.getAllKnowledgeReplyCnt(boardId);
			List<KnowledgeReplyDTO> knowledgeReplyDTO = knowledgeBoardService.getAllKnowledgeReplyList(boardId);
			
			mv.addObject("knowledgeDTO", knowledgeDTO);
			mv.addObject("allReplyCnt", allReplyCnt);
			mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
			
			mv.setViewName("/boardAdvance/knowledgeDetail");
			
			return mv;
		}	
		
	}
	
	@GetMapping("/knowledgeUpdateBoard")
	public ModelAndView knowledgeUpdateBoard(@RequestParam("boardId") long boardId) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		KnowledgeDTO knowledgeDTO = knowledgeBoardService.getKnowledgeBoardDetail(boardId, false);
		mv.addObject("knowledgeDTO", knowledgeDTO);
		System.out.println(knowledgeDTO);
		mv.setViewName("/boardAdvance/knowledgeUpdate");
		
		return mv;
		
		
	}
	
	
	@PostMapping("/knowledgeUpdateBoard")
	public ResponseEntity<Object> knowledgeUpdateBoard(KnowledgeDTO knowledgeDTO, HttpServletRequest request) throws Exception{
		
		knowledgeBoardService.updateKnowledgeBoard(knowledgeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/knowledgeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
	}
	
	
	@GetMapping("/knowledgeRemoveBoard")
	public ModelAndView knowledgeRemoveBoard(@RequestParam("boardId") long boardId) throws Exception{
		ModelAndView mv = new ModelAndView();
		KnowledgeDTO knowledgeDTO = knowledgeBoardService.getKnowledgeBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/knowledgeRemove");
		mv.addObject("knowledgeDTO", knowledgeDTO);
		
		return mv;
		
	}
	
	@PostMapping("/knowledgeRemoveBoard")
	public ResponseEntity<Object> knowledgeRemoveBoard(KnowledgeDTO knowledgeDTO, HttpServletRequest request) throws Exception{
		
		knowledgeBoardService.removeKnowledgeBoard(knowledgeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/boardAdvance/knowledgeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/KnowledgeAddReply")
	public ModelAndView KnowledgeAddReply(@RequestParam("boardId") long boardId) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/boardAdvance/knowledgeAddReply");
		
		return mv;
		
	}
	
	
	
	@PostMapping("/KnowledgeAddReply")
	public ResponseEntity<Object> KnowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO, HttpServletRequest request) throws Exception{
		
		knowledgeBoardService.knowledgeAddReply(knowledgeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록되었습니다.');";
		message +="location.href='"+ request.getContextPath() + "/boardAdvance/knowledgeDetail?boardId=" +knowledgeReplyDTO.getBoardId() + "';";
		message +="</script>";
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/knowledgeUpdateReply")
	public ModelAndView knowledgeUpdateReply(@RequestParam("replyId") long replyId) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		KnowledgeReplyDTO knowledgeReplyDTO = knowledgeBoardService.knowledgeReplyDetail(replyId);
		mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
		mv.setViewName("/boardAdvance/knowledgeUpdateReply");
		
		return mv;
		
		
	}
	
	@GetMapping("/checkDuplicatedPasswd")
	public ResponseEntity<String> checkDuplicatedPasswd(@RequestParam("passwd") String passwd, @RequestParam("writer") String writer ) throws Exception{
		return new ResponseEntity<String>(knowledgeBoardService.checkDuplicatedPasswd(writer, passwd),HttpStatus.OK);
	}
	
	@PostMapping("/knowledgeUpdateReply")
	public ResponseEntity<Object> knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO, HttpServletRequest request) throws Exception{
		
		knowledgeBoardService.knowledgeUpdateReply(knowledgeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정이 완료되었습니다.');";
		message +="location.href='"+request.getContextPath() + "/boardAdvance/knowledgeDetail?boardId=" +knowledgeReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
	}
	
	
	
	@GetMapping("/knowledgeRemoveReply")
	public ModelAndView knowledgeRemoveReply(@RequestParam("replyId") long replyId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		KnowledgeReplyDTO knowledgeReplyDTO = knowledgeBoardService.knowledgeReplyDetail(replyId);
		
		mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
		mv.setViewName("/boardAdvance/knowledgeRemoveReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/knowledgeRemoveReply")
	public ResponseEntity<Object> knowledgeRemoveReply(KnowledgeReplyDTO knowledgeReplyDTO, HttpServletRequest request) throws Exception{
		
		knowledgeBoardService.removeKnowledgeReply(knowledgeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/knowledgeDetail?boardId=" +knowledgeReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
	}
	
	
}
