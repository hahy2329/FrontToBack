package com.application.FrontToBack.member.controller;

import org.springframework.http.HttpHeaders;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.knowledgeBoard.controller.KnowledgeBoardController;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.member.service.MemberService;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;



@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	public static boolean onScheduled = false;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	
	@Scheduled(cron = "0 10-40 23 21 * *")
	public void autoUpdate() throws Exception{
		
		logger.info(new Date() + "스케줄러 실행");
		
		onScheduled = true;
		
	}
	
	
	@GetMapping("/registerMember")
	public ModelAndView registerMember() throws Exception {
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			mv.setViewName("/member/registerMember");
			return mv;
		}
	}
	
	@GetMapping("/checkDuplicatedId")
	public ResponseEntity<String> checkDuplicatedId(@RequestParam("memberId") String memberId) throws Exception{
		return new ResponseEntity<String>(memberService.checkDuplicatedId(memberId),HttpStatus.OK);
	}
	
	@GetMapping("/checkDuplicatedPasswd")
	public ResponseEntity<String> checkDuplicatedpasswd(@RequestParam("passwd") String passwd, @RequestParam("memberId") String memberId) throws Exception{
		
		return new ResponseEntity<String>(memberService.checkDuplicatedPasswd(passwd, memberId), HttpStatus.OK);
		
	}
	
	@GetMapping("/checkDuplicatedEmail")
	public ResponseEntity<String> checkDuplicatedEmail(@RequestParam("email") String email) throws Exception{
		
		return new ResponseEntity<String>(memberService.checkDuplicatedEmail(email),HttpStatus.OK);
		
	}
	
	@PostMapping("/registerMember")
	public ResponseEntity<Object> registerMember(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		memberService.addMember(memberDTO);
		
		String message = "<script>";
		message +="alert('회원가입이 완료되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
	}
	
	
	@GetMapping("/loginMember")
	public ModelAndView loginMember() {
		ModelAndView mv = new ModelAndView();
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			mv.setViewName("/member/loginMember");
			return mv;
		}
	}
	
	
	@PostMapping("/loginMember")
	public ResponseEntity<Object> loginMember(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		String message = "";
		
			MemberDTO memberData = memberService.loginMember(memberDTO);
			
			if(memberData != null) {
				HttpSession session = request.getSession();
				session.setAttribute("memberId", memberData.getMemberId());
				session.setAttribute("sort", memberData.getSort());
				session.setMaxInactiveInterval(3600);//초 단위 (3600 ==60분)
				
				message = "<script>";
				message +="alert('로그인 되었습니다.');";
				message +="location.href='" + request.getContextPath()+"/';";
				message +="</script>";
			}
			else {
				message ="<script>";
				message +="alert('아이디 혹은 패스워드를 다시 확인해주세요.');";
				message +="history.go(-1)";
				message +="</script>";
			}
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
			return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
			
		
		
		
	}
	
	
	@GetMapping("/updateMember")
	public ModelAndView updateMember(@RequestParam("memberId") String memberId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			MemberDTO memberData = memberService.getDetailMember(memberId);
			mv.addObject("memberDTO", memberData);
			mv.setViewName("/member/updateMember");
			
			return mv;
		}
		
		
	}
	
	@PostMapping("/updateMember")
	public ResponseEntity<Object> updateMember(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		
		String message = "";
		
		if(memberService.updateMember(memberDTO)) {
			message = "<script>";
			message +="alert('확인 결과, 정상적으로 수정 완료되었습니다.');";
			message +="location.href='" + request.getContextPath() +"/';";
			message +="</script>";
			
			HttpSession session = request.getSession();
			session.invalidate();
		}
		else {
			message ="<script>";
			message +="alert('확인 결과, 패스워드가 올바르지 않습니다.');";
			message +="history.go(-1)";
			message +="</script>";
		}
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
			return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	@GetMapping("/removeMember")
	public ModelAndView removeMember(@RequestParam("memberId") String memberId) throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			MemberDTO memberData = memberService.getDetailMember(memberId);
			mv.addObject("memberDTO", memberData);
			mv.setViewName("/member/removeMember");
			
			return mv;
		}	
	}
	
	@PostMapping("/removeMember")
	public ResponseEntity<Object> removeMember(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		
		String message = "";
		
		if(memberService.removeMember(memberDTO)) {
		
			HttpSession session = request.getSession();
			session.invalidate();
			
			message="<script>";
			message +="alert('정상적으로 회원탈퇴가 완료되었습니다.');";
			message +="location.href='" + request.getContextPath() +"/';";
			message +="</script>";
		}
		else {
			
			message ="<script>";
			message +="alert('패스워드를 다시 확인해주세요.');";
			message +="history.go(-1)";
			message +="</script>";
			
			
		}
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		return new ResponseEntity<Object>(message, responseHeaders,HttpStatus.OK);
		
		
	}
	
	@GetMapping("/logoutMember")
	public ResponseEntity<Object> logoutMember(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		String message = "<script>";
		message += "alert('로그아웃 되었습니다.');";
		message += "location.href='"+request.getContextPath() + "/';";
		message +="</script>";
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/myActivity")
	public ModelAndView myActivity(@RequestParam("memberId") String memberId) throws Exception {
		
		ModelAndView mv = new ModelAndView();
		
		if(onScheduled == true) {
			mv.setViewName("/main/serverInspection");
			return mv;
		}
		else {
			List<KnowledgeDTO> knowledgeList = memberService.getMyActivityKnowledge(memberId);
			List<QnaDTO> qnaList = memberService.getMyActivityQna(memberId);
			List<StudyDTO> studyList = memberService.getMyActivityStudy(memberId);
			List<BookDTO> bookList = memberService.getMyActivityBook(memberId);
			
			mv.addObject("knowledgeList", knowledgeList);
			mv.addObject("qnaList", qnaList);
			mv.addObject("studyList", studyList);
			mv.addObject("bookList", bookList);
			
			mv.setViewName("/member/myActivity");
		
			return mv;
		}	
			
		
	}
	
}
