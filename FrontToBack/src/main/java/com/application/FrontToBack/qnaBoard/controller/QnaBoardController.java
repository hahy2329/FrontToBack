package com.application.FrontToBack.qnaBoard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;
import com.application.FrontToBack.qnaBoard.service.QnaBoardService;

@Controller
@RequestMapping("/boardAdvance")
public class QnaBoardController {
	
	@Autowired
	private QnaBoardService qnaBoardService;
	
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
		
		int allBoardCnt = qnaBoardService.getAllQnaBoardCnt(searchCntMap);
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
		mv.addObject("boardList", qnaBoardService.getQnaBoardList(searchMap));
		
		
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
		
		qnaBoardService.insertQnaBoard(qnaDTO);
		
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
		QnaDTO qnaDTO = qnaBoardService.getQnaBoardDetail(boardId, true);
		int allReplyCnt = qnaBoardService.getAllQnaReplyCnt(boardId);
		List<QnaReplyDTO> qnaReplyDTO = qnaBoardService.getAllQnaReplyList(boardId);
		
		mv.addObject("qnaDTO", qnaDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		
		mv.setViewName("/boardAdvance/qnaDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/qnaUpdateBoard")
	public ModelAndView qnaUpdateBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		QnaDTO qnaDTO = qnaBoardService.getQnaBoardDetail(boardId, false);
		mv.addObject("qnaDTO", qnaDTO);
		mv.setViewName("/boardAdvance/qnaUpdate");
		
		return mv;
		
		
	}
	
	@PostMapping("/qnaUpdateBoard")
	public ResponseEntity<Object> qnaUpdateBoard(QnaDTO qnaDTO, HttpServletRequest request) throws Exception{
		
		qnaBoardService.updateQnaBoard(qnaDTO);
		
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
		QnaDTO qnaDTO = qnaBoardService.getQnaBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/qnaRemove");
		mv.addObject("qnaDTO", qnaDTO);
		
		return mv;
		
		
	}
	
	@PostMapping("/qnaRemoveBoard")
	public ResponseEntity<Object> qnaRemoveBoard(QnaDTO qnaDTO, HttpServletRequest request) throws Exception{
		
		qnaBoardService.removeQnaBoard(qnaDTO);
		
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
		
		qnaBoardService.qnaAddReply(qnaReplyDTO);
		
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
		QnaReplyDTO qnaReplyDTO = qnaBoardService.qnaReplyDetail(replyId);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		mv.setViewName("/boardAdvance/qnaUpdateReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/qnaUpdateReply")
	public ResponseEntity<Object> qnaUpdateReply(QnaReplyDTO qnaReplyDTO, HttpServletRequest request) throws Exception{
		
		qnaBoardService.qnaUpdateReply(qnaReplyDTO);
		
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
		QnaReplyDTO qnaReplyDTO = qnaBoardService.qnaReplyDetail(replyId);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		mv.setViewName("/boardAdvance/qnaRemoveReply");
		
		return mv;
				
	}
	
	@PostMapping("/qnaRemoveReply")
	public ResponseEntity<Object> qnaRemoveReply(QnaReplyDTO qnaReplyDTO, HttpServletRequest request) throws Exception{
		
		qnaBoardService.removeQnaReply(qnaReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/qnaDetail?boardId=" +qnaReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
		
	}
	
	
}
