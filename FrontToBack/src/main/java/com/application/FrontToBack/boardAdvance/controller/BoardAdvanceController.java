package com.application.FrontToBack.boardAdvance.controller;

import org.springframework.http.HttpHeaders;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.boardAdvance.service.BoardAdvanceService;

@Controller
@RequestMapping("/boardAdvance")
public class BoardAdvanceController {
	
	
	@Autowired
	private BoardAdvanceService boardAdvanceService;
	
	
	//-------------------1.지식관련 게시판 기능-------------------------------------- 
	
	@GetMapping("/knowledgeList") 
	public ModelAndView knowledge(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
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
		
		int allBoardCnt = boardAdvanceService.getAllBoardCnt(searchCntMap);
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
		mv.addObject("boardList", boardAdvanceService.getBoardList(searchMap));
		
		
		return mv;
		
	}
	
	@GetMapping("/knowledgeAddBoard")
	public ModelAndView knowledgeAddBoard() throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/boardAdvance/knowledgeAdd");
		return mv;
	}
	
	@PostMapping("/knowledgeAddBoard")
	public ResponseEntity<Object> knowledgeAddBoard(KnowledgeDTO knowledgeDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.insertKnowledgeBoard(knowledgeDTO);
		
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
		KnowledgeDTO knowledgeDTO = boardAdvanceService.getKnowledgeBoardDetail(boardId, true);
		int allReplyCnt = boardAdvanceService.getAllKnowledgeReplyCnt(boardId);
		List<KnowledgeReplyDTO> knowledgeReplyDTO = boardAdvanceService.getAllKnowledgeReplyList(boardId);
		
		mv.addObject("knowledgeDTO", knowledgeDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
		
		mv.setViewName("/boardAdvance/knowledgeDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/knowledgeUpdateBoard")
	public ModelAndView knowledgeUpdateBoard(@RequestParam("boardId") long boardId) throws Exception {
		
		
		ModelAndView mv = new ModelAndView();
		KnowledgeDTO knowledgeDTO = boardAdvanceService.getKnowledgeBoardDetail(boardId, false);
		mv.addObject("knowledgeDTO", knowledgeDTO);
		System.out.println(knowledgeDTO);
		mv.setViewName("/boardAdvance/knowledgeUpdate");
		
		return mv;
		
		
	}
	
	
	@PostMapping("/knowledgeUpdateBoard")
	public ResponseEntity<Object> knowledgeUpdateBoard(KnowledgeDTO knowledgeDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.updateKnowledgeBoard(knowledgeDTO);
		
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
		KnowledgeDTO knowledgeDTO = boardAdvanceService.getKnowledgeBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/knowledgeRemove");
		mv.addObject("knowledgeDTO", knowledgeDTO);
		
		return mv;
		
	}
	
	@PostMapping("/knowledgeRemoveBoard")
	public ResponseEntity<Object> knowledgeRemoveBoard(KnowledgeDTO knowledgeDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.removeKnowledgeBoard(knowledgeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/boardAdvance/knowledgeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	
}
