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
import com.application.FrontToBack.boardAdvance.dto.QnaDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyReplyDTO;
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
	
	@GetMapping("/KnowledgeAddReply")
	public ModelAndView KnowledgeAddReply(@RequestParam("boardId") long boardId) {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/boardAdvance/knowledgeAddReply");
		
		return mv;
		
	}
	
	
	
	@PostMapping("/KnowledgeAddReply")
	public ResponseEntity<Object> KnowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.knowledgeAddReply(knowledgeReplyDTO);
		
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
		KnowledgeReplyDTO knowledgeReplyDTO = boardAdvanceService.knowledgeReplyDetail(replyId);
		mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
		mv.setViewName("/boardAdvance/knowledgeUpdateReply");
		
		return mv;
		
		
	}
	
	@GetMapping("/checkDuplicatedPasswd")
	public ResponseEntity<String> checkDuplicatedPasswd(@RequestParam("passwd") String passwd, @RequestParam("writer") String writer ) throws Exception{
		return new ResponseEntity<String>(boardAdvanceService.checkDuplicatedPasswd(writer, passwd),HttpStatus.OK);
	}
	
	@PostMapping("/knowledgeUpdateReply")
	public ResponseEntity<Object> knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.knowledgeUpdateReply(knowledgeReplyDTO);
		
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
		KnowledgeReplyDTO knowledgeReplyDTO = boardAdvanceService.knowledgeReplyDetail(replyId);
		
		mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
		mv.setViewName("/boardAdvance/knowledgeRemoveReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/knowledgeRemoveReply")
	public ResponseEntity<Object> knowledgeRemoveReply(KnowledgeReplyDTO knowledgeReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.removeKnowledgeReply(knowledgeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/knowledgeDetail?boardId=" +knowledgeReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
	}
	
	
	
	//-------------------------------  2.qna관련 게시판  --------------------------
	
	
	@GetMapping("/qnaList") 
	public ModelAndView qnaList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/boardAdvance/qnaList");
		
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
		
		int allBoardCnt = boardAdvanceService.getAllQnaBoardCnt(searchCntMap);
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
		mv.addObject("boardList", boardAdvanceService.getQnaBoardList(searchMap));
		
		
		return mv;
		
	}
	
	@GetMapping("/qnaAddBoard")
	public ModelAndView qnaAddBoard() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/boardAdvance/qnaAddBoard");
		return mv;
	}
	
	@PostMapping("/qnaAddBoard")
	public ResponseEntity<Object> qnaAddBoard(QnaDTO qnaDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.insertQnaBoard(qnaDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록완료되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/qnaList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/qnaDetail")
	public ModelAndView qnaDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		QnaDTO qnaDTO = boardAdvanceService.getQnaBoardDetail(boardId, true);
		int allReplyCnt = boardAdvanceService.getAllQnaReplyCnt(boardId);
		List<QnaReplyDTO> qnaReplyDTO = boardAdvanceService.getAllQnaReplyList(boardId);
		
		mv.addObject("qnaDTO", qnaDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		
		mv.setViewName("/boardAdvance/qnaDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/qnaUpdateBoard")
	public ModelAndView qnaUpdateBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		QnaDTO qnaDTO = boardAdvanceService.getQnaBoardDetail(boardId, false);
		mv.addObject("qnaDTO", qnaDTO);
		mv.setViewName("/boardAdvance/qnaUpdate");
		
		return mv;
		
		
	}
	
	@PostMapping("/qnaUpdateBoard")
	public ResponseEntity<Object> qnaUpdateBoard(QnaDTO qnaDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.updateQnaBoard(qnaDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/qnaList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
		
	}
	
	@GetMapping("/qnaRemoveBoard")
	public ModelAndView qnaRemoveBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		QnaDTO qnaDTO = boardAdvanceService.getQnaBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/qnaRemove");
		mv.addObject("qnaDTO", qnaDTO);
		
		return mv;
		
		
	}
	
	@PostMapping("/qnaRemoveBoard")
	public ResponseEntity<Object> qnaRemoveBoard(QnaDTO qnaDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.removeQnaBoard(qnaDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/boardAdvance/qnaList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/qnaAddReply")
	public ModelAndView qnaAddReply(@RequestParam("boardId") long boardId) throws Exception{
	
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/boardAdvance/qnaAddReply");
		
		return mv;
	}
	
	@PostMapping("/qnaAddReply")
	public ResponseEntity<Object> qnaAddReply(QnaReplyDTO qnaReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.qnaAddReply(qnaReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록되었습니다.');";
		message +="location.href='"+ request.getContextPath() + "/boardAdvance/qnaDetail?boardId=" +qnaReplyDTO.getBoardId() + "';";
		message +="</script>";
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/qnaUpdateReply")
	public ModelAndView qnaUpdateReply(@RequestParam("replyId") long replyId)throws Exception {
		ModelAndView mv = new ModelAndView();
		QnaReplyDTO qnaReplyDTO = boardAdvanceService.qnaReplyDetail(replyId);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		mv.setViewName("/boardAdvance/qnaUpdateReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/qnaUpdateReply")
	public ResponseEntity<Object> qnaUpdateReply(QnaReplyDTO qnaReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.qnaUpdateReply(qnaReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정이 완료되었습니다.');";
		message +="location.href='"+request.getContextPath() + "/boardAdvance/qnaDetail?boardId=" +qnaReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/qnaRemoveReply")
	public ModelAndView qnaRemoveReply(@RequestParam("replyId") long replyId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		QnaReplyDTO qnaReplyDTO = boardAdvanceService.qnaReplyDetail(replyId);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		mv.setViewName("/boardAdvance/qnaRemoveReply");
		
		return mv;
				
	}
	
	@PostMapping("/qnaRemoveReply")
	public ResponseEntity<Object> qnaRemoveReply(QnaReplyDTO qnaReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.removeQnaReply(qnaReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/qnaDetail?boardId=" +qnaReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
		
	}
	
	//------------------------------3.스터디그룹 관련 게시판 기능 ---------------------------------------------------
	
	
	@GetMapping("/studyList") 
	public ModelAndView studyList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
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
		
		int allBoardCnt = boardAdvanceService.getAllStudyBoardCnt(searchCntMap);
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
		mv.addObject("boardList", boardAdvanceService.getStudyBoardList(searchMap));
		
		
		return mv;
		
	}
	
	@GetMapping("/studyAddBoard")
	public ModelAndView studyAddBoard() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/boardAdvance/studyAddBoard");
		return mv;
		
	}
	
	@PostMapping("/studyAddBoard")
	public ResponseEntity<Object> studyAddBoard(StudyDTO studyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.insertStudyBoard(studyDTO);
		
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
		StudyDTO studyDTO = boardAdvanceService.getStudyBoardDetail(boardId, true);
		int allReplyCnt = boardAdvanceService.getAllStudyReplyCnt(boardId);
		List<StudyReplyDTO> studyReplyDTO = boardAdvanceService.getAllStudyReplyList(boardId);
		
		mv.addObject("studyDTO", studyDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("studyReplyDTO", studyReplyDTO);
		
		mv.setViewName("/boardAdvance/studyDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/studyUpdateBoard")
	public  ModelAndView studyUpdateBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		StudyDTO studyDTO = boardAdvanceService.getStudyBoardDetail(boardId, false);
		mv.addObject("studyDTO", studyDTO);
		mv.setViewName("/boardAdvance/studyUpdate");
		
		return mv;
	
	}
	
	@PostMapping("/studyUpdateBoard")
	public ResponseEntity<Object> studyUpdateBoard(StudyDTO studyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.updateStudyBoard(studyDTO);
		
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
		StudyDTO studyDTO = boardAdvanceService.getStudyBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/studyRemove");
		mv.addObject("studyDTO", studyDTO);
		
		return mv;
		
		
	}
	
	@PostMapping("/studyRemoveBoard")
	public ResponseEntity<Object> studyRemoveBoard(StudyDTO studyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.removeStudyBoard(studyDTO);
		
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
		
		boardAdvanceService.studyAddReply(studyReplyDTO);
		
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
		StudyReplyDTO studyReplyDTO = boardAdvanceService.studyReplyDetail(replyId);
		mv.addObject("studyReplyDTO", studyReplyDTO);
		mv.setViewName("/boardAdvance/studyUpdateReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/studyUpdateReply")
	public ResponseEntity<Object> studyUpdateReply(StudyReplyDTO studyReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.studyUpdateReply(studyReplyDTO);
		
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
		StudyReplyDTO studyReplyDTO = boardAdvanceService.studyReplyDetail(replyId);
		mv.addObject("studyReplyDTO", studyReplyDTO);
		mv.setViewName("/boardAdvance/studyRemoveReply");
		
		return mv;
				
	}
	
	@PostMapping("/studyRemoveReply")
	public ResponseEntity<Object> studyRemoveReply(StudyReplyDTO studyReplyDTO, HttpServletRequest request) throws Exception{
		
		boardAdvanceService.removeStudyReply(studyReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/studyDetail?boardId=" +studyReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
		
	}
	
	
	
}
