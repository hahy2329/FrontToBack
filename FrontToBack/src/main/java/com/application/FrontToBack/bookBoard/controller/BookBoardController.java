package com.application.FrontToBack.bookBoard.controller;

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

import com.application.FrontToBack.HomeController;
import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.dto.BookReplyDTO;
import com.application.FrontToBack.bookBoard.service.BookBoardSerive;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;

@Controller
@RequestMapping("/boardAdvance")
public class BookBoardController {

	@Autowired
	private BookBoardSerive bookBoardService;
	
	
	public static boolean onScheduled = false;
	
	private static final Logger logger = LoggerFactory.getLogger(BookBoardController.class);
	
	
	
	@GetMapping("/bookList")
	public ModelAndView bookList(HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		
		else {
			mv.setViewName("/boardAdvance/bookList");
			
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
			
			int allBoardCnt = bookBoardService.getAllBookBoardCnt(searchCntMap);
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
			mv.addObject("boardList", bookBoardService.getBookBoardList(searchMap));
			
			
			return mv;
		}
		
	}
	
	@Scheduled(cron = "0 10-40 23 * * *")
	public void autoUpdate() throws Exception{
		
		logger.info(new Date() + "스케줄러 실행");
		
		onScheduled = true;
		
	}
	
	
	
	@GetMapping("/bookAddBoard")
	public ModelAndView bookAddBoard() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		
		else {
			mv.setViewName("/boardAdvance/bookAddBoard");
			return mv;
		}
	}
	
	@PostMapping("/bookAddBoard")
	public ResponseEntity<Object> bookAddBoard(BookDTO bookDTO, HttpServletRequest request) throws Exception{
		
		bookBoardService.insertBookBoard(bookDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록완료되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/bookList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/bookDetail")
	public ModelAndView bookDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {	
			BookDTO bookDTO = bookBoardService.getBookBoardDetail(boardId, true);
			int allReplyCnt = bookBoardService.getAllBookReplyCnt(boardId);
			List<BookReplyDTO> bookReplyDTO = bookBoardService.getAllBookReplyList(boardId);
			
			mv.addObject("bookDTO", bookDTO);
			mv.addObject("allReplyCnt", allReplyCnt);
			mv.addObject("bookReplyDTO", bookReplyDTO);
			
			mv.setViewName("/boardAdvance/bookDetail");
			
			return mv;
		}
		
	}
	
	@GetMapping("/bookUpdateBoard")
	public ModelAndView bookUpdateBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		BookDTO bookDTO = bookBoardService.getBookBoardDetail(boardId, false);
		mv.addObject("bookDTO", bookDTO);
		mv.setViewName("/boardAdvance/bookUpdate");
		
		return mv;
		
		
	}
	
	@PostMapping("/bookUpdateBoard")
	public ResponseEntity<Object> bookUpdateBoard(BookDTO bookDTO, HttpServletRequest request) throws Exception{
		
		bookBoardService.updateBookBoard(bookDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/boardAdvance/bookList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
		
	}
	
	
	
	@GetMapping("/bookRemoveBoard")
	public ModelAndView bookRemoveBoard(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		BookDTO bookDTO = bookBoardService.getBookBoardDetail(boardId, false);
		mv.setViewName("/boardAdvance/bookRemove");
		mv.addObject("bookDTO", bookDTO);
		
		return mv;
		
		
	}
	
	@PostMapping("/bookRemoveBoard")
	public ResponseEntity<Object> bookRemoveBoard(BookDTO bookDTO, HttpServletRequest request) throws Exception{
		
		bookBoardService.removeBookBoard(bookDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/boardAdvance/bookList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
	}
	
	@GetMapping("/bookAddReply")
	public ModelAndView bookAddReply(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/boardAdvance/bookAddReply");
		
		return mv;
	}
	
	@PostMapping("/bookAddReply")
	public ResponseEntity<Object> bookAddReply(BookReplyDTO bookReplyDTO, HttpServletRequest request) throws Exception{
		
		bookBoardService.bookAddReply(bookReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록되었습니다.');";
		message +="location.href='"+ request.getContextPath() + "/boardAdvance/bookDetail?boardId=" +bookReplyDTO.getBoardId() + "';";
		message +="</script>";
		
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/bookUpdateReply")
	public ModelAndView bookUpdateReply(@RequestParam("replyId") long replyId)throws Exception {
		ModelAndView mv = new ModelAndView();
		BookReplyDTO bookReplyDTO = bookBoardService.bookReplyDetail(replyId);
		mv.addObject("bookReplyDTO", bookReplyDTO);
		mv.setViewName("/boardAdvance/bookUpdateReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/bookUpdateReply")
	public ResponseEntity<Object> bookUpdateReply(BookReplyDTO bookReplyDTO, HttpServletRequest request) throws Exception{
		
		bookBoardService.bookUpdateReply(bookReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정이 완료되었습니다.');";
		message +="location.href='"+request.getContextPath() + "/boardAdvance/bookDetail?boardId=" +bookReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
	
	}
	
	
	@GetMapping("/bookRemoveReply")
	public ModelAndView bookRemoveReply(@RequestParam("replyId") long replyId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		BookReplyDTO bookReplyDTO = bookBoardService.bookReplyDetail(replyId);
		mv.addObject("bookReplyDTO", bookReplyDTO);
		mv.setViewName("/boardAdvance/bookRemoveReply");
		
		return mv;
				
	}
	
	@PostMapping("/bookRemoveReply")
	public ResponseEntity<Object> bookRemoveReply(BookReplyDTO bookReplyDTO, HttpServletRequest request) throws Exception{
		
		bookBoardService.removeBookReply(bookReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" +request.getContextPath()+ "/boardAdvance/bookDetail?boardId=" +bookReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
		
		
		
	}
	
}
