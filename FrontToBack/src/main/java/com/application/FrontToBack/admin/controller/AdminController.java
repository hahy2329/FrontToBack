package com.application.FrontToBack.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.admin.service.AdminService;
import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.dto.BookReplyDTO;
import com.application.FrontToBack.bookBoard.service.BookBoardSerive;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.knowledgeBoard.service.KnowledgeBoardService;
import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.member.service.MemberService;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;
import com.application.FrontToBack.noticeBoard.service.NoticeBoardService;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;
import com.application.FrontToBack.qnaBoard.service.QnaBoardService;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.dto.StudyReplyDTO;
import com.application.FrontToBack.studyBoard.service.StudyBoardService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private KnowledgeBoardService knowledgeBoardService;

	@Autowired
	private QnaBoardService qnaBoardService;
	
	@Autowired
	private StudyBoardService studyBoardService;
	
	@Autowired
	private BookBoardSerive bookBoardService;
	
	@Autowired
	private NoticeBoardService noticeBoardService;
	
	@Autowired
	private MemberService memberService;
	
	
	
	@GetMapping("/loginAdmin")
	public ModelAndView login() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/loginAdmin");
		return mv;
		
	}
	
	@PostMapping("/loginAdmin")
	public ResponseEntity<Object> loginAdmin(AdminDTO adminDTO, HttpServletRequest request) throws Exception{
		
		String message = "";
		
		AdminDTO adminData = adminService.getAdminDetail(adminDTO);
		
		
		if(adminData != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("adminId", adminData.getAdminId());
			session.setAttribute("sort", adminData.getSort());
			session.setMaxInactiveInterval(3600);
			
			message ="<script>";
			message +="alert('정상적으로 로그인 되었습니다.');";
			message +="location.href='" + request.getContextPath()+ "/';";
			message +="</script>";
		}
		
		else {
			message = "<script>";
			message +="alert('아이디 혹은 패스워드를 다시 확인해주세요.');";
			message +="history.go(-1)";
			message +="</script>";
			
			
			
		}
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/logoutAdmin")
	public ResponseEntity<Object> logoutAdmin(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String message = "<script>";
		message +="alert('로그아웃 되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/';";
		message +="</script>";
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/adminPage")
	public ModelAndView adminPage() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		KnowledgeDTO knowledgeDTO = knowledgeBoardService.getPopularBoard();
		QnaDTO qnaDTO = qnaBoardService.getPopularBoard();
		StudyDTO studyDTO = studyBoardService.getPopularBoard();
		BookDTO bookDTO = bookBoardService.getPopularBoard();
		int count = memberService.getCountMember();
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		Date now = new Date();
		
		String nowTime = sdf1.format(now);
		
		
		
		
		mv.setViewName("/admin/adminPage");
		mv.addObject("knowledgeDTO", knowledgeDTO);
		mv.addObject("qnaDTO", qnaDTO);
		mv.addObject("studyDTO", studyDTO);
		mv.addObject("bookDTO", bookDTO);
		mv.addObject("count", count);
		mv.addObject("nowTime", nowTime);
		
		return mv;
		
		
		
		
		
		
	}
	
	@GetMapping("/knowledgeList")
	public ModelAndView knowledgeList(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/knowledgeList");
		
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
	
	
	@GetMapping("/knowledgeDetail")
	public ModelAndView knowledgeDetail(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		KnowledgeDTO knowlegeDTO = knowledgeBoardService.getKnowledgeBoardDetail(boardId, true);
		int allReplyCnt = knowledgeBoardService.getAllKnowledgeReplyCnt(boardId);
		List<KnowledgeReplyDTO> knowledgeReplyDTO = knowledgeBoardService.getAllKnowledgeReplyList(boardId);
		
		mv.addObject("knowledgeDTO", knowlegeDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("knowledgeReplyDTO", knowledgeReplyDTO);
		
		mv.setViewName("/admin/knowledgeDetail");
		
		return mv;
	}
	
	@GetMapping("/knowledgeForceRemove")
	public ResponseEntity<Object> knowledgeForceRemove(long boardId, HttpServletRequest request) throws Exception{
		
		adminService.forceRemoveKnowledgeBoard(boardId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/knowledgeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/knowledgeReplyForceRemove")
	public ResponseEntity<Object> replyForceRemove(long replyId, HttpServletRequest request) throws Exception{
		
		adminService.forceRemoveKnowledgeReplyBoard(replyId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/knowledgeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/qnaList")
	public ModelAndView qnaList(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/qnaList");
		
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
	
	
	@GetMapping("/qnaDetail")
	public ModelAndView qnaDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		QnaDTO qnaDTO = qnaBoardService.getQnaBoardDetail(boardId, true);
		int allReplyCnt = qnaBoardService.getAllQnaReplyCnt(boardId);
		List<QnaReplyDTO> qnaReplyDTO = qnaBoardService.getAllQnaReplyList(boardId);
		
		mv.addObject("qnaDTO", qnaDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("qnaReplyDTO", qnaReplyDTO);
		
		mv.setViewName("/admin/qnaDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/qnaForceRemove")
	public ResponseEntity<Object> qnaForceRemove(long boardId, HttpServletRequest request) throws Exception{
		
		adminService.forceRemoveQnaBoard(boardId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/qnaList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/qnaReplyForceRemove")
	public ResponseEntity<Object> qnaReplyForceRemove(long replyId, HttpServletRequest request) throws Exception{
		
		adminService.forceRemoveQnaReplyBoard(replyId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/qnaList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/studyList")
	public ModelAndView studyList(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/studyList");
		
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
	
	@GetMapping("/studyDetail")
	public ModelAndView studyDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		StudyDTO studyDTO = studyBoardService.getStudyBoardDetail(boardId, true);
		int allReplyCnt = studyBoardService.getAllStudyReplyCnt(boardId);
		List<StudyReplyDTO> studyReplyDTO = studyBoardService.getAllStudyReplyList(boardId);
		
		mv.addObject("studyDTO", studyDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("studyReplyDTO", studyReplyDTO);
		
		mv.setViewName("/admin/studyDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/studyForceRemove")
	public ResponseEntity<Object> studyForceRemove(long boardId, HttpServletRequest request) throws Exception{
		
		adminService.forceRemoveStudyBoard(boardId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/studyList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	
	@GetMapping("/studyReplyForceRemove")
	public ResponseEntity<Object> studyReplyForceRemove(long replyId, HttpServletRequest request) throws Exception{
		
		
		adminService.forceRemoveStudyReplyBoard(replyId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/studyList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/bookList")
	public ModelAndView bookList(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/bookList");
		
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
	
	@GetMapping("/bookDetail")
	public ModelAndView bookDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		BookDTO bookDTO = bookBoardService.getBookBoardDetail(boardId, true);
		int allReplyCnt = bookBoardService.getAllBookReplyCnt(boardId);
		List<BookReplyDTO> bookReplyDTO = bookBoardService.getAllBookReplyList(boardId);
		
		mv.addObject("bookDTO", bookDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("bookReplyDTO", bookReplyDTO);
		
		mv.setViewName("/admin/bookDetail");
		
		return mv;
		
		
	}
	
	@GetMapping("/bookForceRemove")
	public ResponseEntity<Object> bookForceRemove(long boardId, HttpServletRequest request) throws Exception{
		
		adminService.forceRemoveBookBoard(boardId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/bookList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/bookReplyForceRemove")
	public ResponseEntity<Object> bookReplyForceRemove(long replyId, HttpServletRequest request) throws Exception{
		
		
		adminService.forceRemoveBookReplyBoard(replyId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/bookList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/noticeList")
	public ModelAndView noticeList(HttpServletRequest request) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/noticeList");
		
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
	
	@GetMapping("/noticeAddBoard")
	public ModelAndView noticeAddBoard() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/noticeAddBoard");
		return mv;
		
	}
	
	@GetMapping("/checkDuplicatedPasswd")
	public ResponseEntity<String> checkDuplicatedPasswd(@RequestParam("passwd") String passwd, @RequestParam("adminId") String adminId) throws Exception{
		
		return new ResponseEntity<String>(adminService.checkDuplicatedPasswd(passwd, adminId), HttpStatus.OK);
		
		
	}
	
	@PostMapping("/noticeAddBoard")
	public ResponseEntity<Object> noticeAddBoard(NoticeDTO noticeDTO, HttpServletRequest request) throws Exception{
		
		adminService.insertNoticeBoard(noticeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록완료되었습니다.');";
		message +="location.href='"+request.getContextPath() +"/admin/noticeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
	}
	
	
	
	@GetMapping("/noticeDetail")
	public ModelAndView noticeDetail(@RequestParam("boardId") long boardId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		NoticeDTO noticeDTO = noticeBoardService.getNoticeBoardDetail(boardId, true);
		int allReplyCnt = noticeBoardService.getAllNoticeReplyCnt(boardId);
		List<NoticeReplyDTO> noticeReplyDTO = noticeBoardService.getAllNoticeReplyList(boardId);
		
		mv.addObject("noticeDTO", noticeDTO);
		mv.addObject("allReplyCnt", allReplyCnt);
		mv.addObject("noticeReplyDTO", noticeReplyDTO);
		
		mv.setViewName("/admin/noticeDetail");
		
		return mv;
		
		
	}
	
	
	@GetMapping("/noticeUpdate")
	public ModelAndView noticeForceUpdate(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		NoticeDTO noticeDTO = noticeBoardService.getNoticeBoardDetail(boardId, false);
		mv.addObject("noticeDTO", noticeDTO);
		mv.setViewName("/admin/noticeUpdate");
		
		return mv;
		
	}
	
	
	@PostMapping("/noticeUpdate")
	public ResponseEntity<Object> noticeUpdate(NoticeDTO noticeDTO, HttpServletRequest request) throws Exception{
		
		adminService.noticeUpdate(noticeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정 완료되었습니다.');";
		message +="location.href='"+request.getContextPath()+"/admin/noticeDetail" + noticeDTO.getBoardId() + "';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/noticeRemove")
	public ModelAndView noticeRemove(@RequestParam("boardId")long boardId, HttpServletRequest request) throws Exception{
		
		
		ModelAndView mv = new ModelAndView();
		NoticeDTO noticeDTO = noticeBoardService.getNoticeBoardDetail(boardId, false);
		mv.addObject("noticeDTO", noticeDTO);
		mv.setViewName("/admin/noticeRemove");
		
		return mv;
	}
	
	@PostMapping("/noticeRemove")
	public ResponseEntity<Object> noticeRemove(NoticeDTO noticeDTO, HttpServletRequest request) throws Exception{
		
		adminService.noticeRemove(noticeDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제가 완료되었습니다.');";
		message +="location.href='"+request.getContextPath()+"/admin/noticeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/noticeReplyForceRemove")
	public ResponseEntity<Object> noticeReplyForceRemove(long replyId, HttpServletRequest request) throws Exception{
		
		adminService.noticeReplyForceRemove(replyId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/admin/noticeList';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message,responseHeaders,HttpStatus.OK);
	}
	
	
	@GetMapping("/noticeAddReply")
	public ModelAndView noticeAddReply(@RequestParam("boardId") long boardId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("boardId", boardId);
		mv.setViewName("/admin/noticeAddReply");
		
		return mv;
		
		
	}
	
	
	@PostMapping("/noticeAddReply")
	public ResponseEntity<Object> noticeAddReply(NoticeReplyDTO noticeReplyDTO, HttpServletRequest request) throws Exception{
		
		adminService.noticeAddReply(noticeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 등록되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/admin/noticeDetail?boardId="+ noticeReplyDTO.getBoardId() +"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
		
	}
	
	
	@GetMapping("/noticeUpdateReply")
	public ModelAndView noticeUpdateReply(@RequestParam("replyId") long replyId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		NoticeReplyDTO noticeReplyDTO = noticeBoardService.noticeReplyDetail(replyId);
		mv.addObject("noticeReplyDTO", noticeReplyDTO);
		mv.setViewName("/admin/noticeUpdateReply");
		
		return mv;
		
	}
	
	@RequestMapping("/noticeUpdateReply")
	public ResponseEntity<Object> noticeUpdateReply(NoticeReplyDTO noticeReplyDTO, HttpServletRequest request) throws Exception{
		
		adminService.noticeUpdateReply(noticeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 수정되었습니다.');";
		message +="location.href='"+ request.getContextPath() +"/admin/noticeDetail?boardId"+noticeReplyDTO.getBoardId()+"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
		
		
	}
	
	
	@GetMapping("/noticeReplyRemove")
	public ModelAndView noticeReplyRemove(@RequestParam("replyId") long replyId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		NoticeReplyDTO noticeReplyDTO = noticeBoardService.noticeReplyDetail(replyId);
		mv.addObject("noticeReplyDTO", noticeReplyDTO);
		mv.setViewName("/admin/noticeRemoveReply");
		
		return mv;
		
		
	}
	
	@PostMapping("/noticeReplyRemove")
	public ResponseEntity<Object> noticeReplyRemove(NoticeReplyDTO noticeReplyDTO, HttpServletRequest request) throws Exception{
		
		adminService.noticeRemoveReply(noticeReplyDTO);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제되었습니다.');";
		message +="location.href='"+ request.getContextPath() +"/admin/noticeDetail?boardId="+noticeReplyDTO.getBoardId()+"';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
		
		
	}
	
	@GetMapping("/memberList")
	public ModelAndView memberList() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		List<MemberDTO> memberList = memberService.getMemberList();
		mv.addObject("memberList", memberList);
		mv.setViewName("/admin/memberList");
		
		return mv;
		
	}
	
	
	@GetMapping("/memberExcelExport")
	public void memberExcelExport(HttpServletResponse response) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hh_mm");
		String fileName = sdf.format(new Date()) +"_memberList.xls";
		
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("회원리스트");
		Row row = null;
		Cell cell = null;
		
		int rowNo = 0;
		
		CellStyle headStyle = wb.createCellStyle();
		
		headStyle.setBorderTop(BorderStyle.THIN);
		headStyle.setBorderBottom(BorderStyle.THIN);
		headStyle.setBorderLeft(BorderStyle.THIN);
		headStyle.setBorderRight(BorderStyle.THIN);
		
		
		headStyle.setFillForegroundColor(HSSFColorPredefined.YELLOW.getIndex());
		headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		
		headStyle.setAlignment(HorizontalAlignment.CENTER);
		
		CellStyle bodyStyle = wb.createCellStyle();
		bodyStyle.setBorderTop(BorderStyle.THIN);
		bodyStyle.setBorderBottom(BorderStyle.THIN);
		bodyStyle.setBorderLeft(BorderStyle.THIN);
		bodyStyle.setBorderRight(BorderStyle.THIN);
		
		 row = sheet.createRow(rowNo++);
		    cell = row.createCell(0);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("회원아이디");
		    cell = row.createCell(1);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("회원이름");
		    cell = row.createCell(2);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("이메일");
		    cell = row.createCell(3);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("이메일 수신");
		    cell = row.createCell(4);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("포지션");
		    cell = row.createCell(5);
		    cell.setCellStyle(headStyle);
		    cell.setCellValue("주소");
		    
		    
		  for(MemberDTO memberDTO : memberService.getMemberList()) {
			  
			  row = sheet.createRow(rowNo++);
		        cell = row.createCell(0);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(memberDTO.getMemberId());  
		        cell = row.createCell(1);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(memberDTO.getMemberNm());  
		        cell = row.createCell(2);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(memberDTO.getEmail());
		        cell = row.createCell(3);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(memberDTO.getEmailstsYn());
		        cell = row.createCell(4);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(memberDTO.getSort());
		        cell = row.createCell(5);
		        cell.setCellStyle(bodyStyle);
		        cell.setCellValue(memberDTO.getRoadAddress() + " " + memberDTO.getJibunAddress() + " " + memberDTO.getNamujiAddress());
		        
		        
		  }
		  
		  
		  response.setContentType("ms-vnd/excel");
		  response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		  
		  
		  wb.write(response.getOutputStream());
		  wb.close();
		
		
		
	}
	
	
	@GetMapping("/memberDelete")
	public ModelAndView memberDelete() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/memberDelete");
		return mv;
		
	}
	
	@GetMapping("/checkDuplicatedMemberId")
	public ResponseEntity<String> checkDuplicatedMemberId(@RequestParam("memberId") String memberId) throws Exception{
		
		return new ResponseEntity<String>(adminService.checkDuplicatedMemberId(memberId),HttpStatus.OK);
		
	}
	
	
	@PostMapping("/memberDelete")
	public ResponseEntity<Object> memberDelete(String memberId, HttpServletRequest request) throws Exception{
		
		adminService.memberForceRemove(memberId);
		
		String message = "<script>";
		message +="alert('정상적으로 삭제 되었습니다.');";
		message +="location.href='" + request.getContextPath() + "/admin/adminPage';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
		
		
	}
	
	
}
