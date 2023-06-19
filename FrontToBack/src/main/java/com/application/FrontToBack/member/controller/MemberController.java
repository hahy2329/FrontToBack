package com.application.FrontToBack.member.controller;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/register")
	public ModelAndView registerMember() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/member/register");
		return mv;
	}
	
	@GetMapping("/checkDuplicatedId")
	public ResponseEntity<String> checkDuplicatedId(@RequestParam("memberId") String memberId) throws Exception{
		return new ResponseEntity<String>(memberService.checkDuplicatedId(memberId),HttpStatus.OK);
	}
	
	@GetMapping("/checkDuplicatedPasswd")
	public ResponseEntity<String> checkDuplicatedpasswd(@RequestParam("passwd") String passwd, @RequestParam("memberId") String memberId) throws Exception{
		
		return new ResponseEntity<String>(memberService.checkDuplicatedPasswd(passwd, memberId), HttpStatus.OK);
		
	}
	
	@PostMapping("/register")
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
	
	
	@GetMapping("/login")
	public ModelAndView loginMember() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/member/login");
		return mv;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Object> loginMember(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		String message = "";
		
			MemberDTO memberData = memberService.loginMember(memberDTO);
			
			if(memberData != null) {
				HttpSession session = request.getSession();
				session.setAttribute("memberId", memberData.getMemberId());
				session.setAttribute("sort", memberData.getSort());
				session.setMaxInactiveInterval(1800);//초 단위 (1800 ==30분)
				
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
		MemberDTO memberData = memberService.getDetailMember(memberId);
		mv.addObject("memberDTO", memberData);
		mv.setViewName("/member/update");
		
		return mv;
		
		
		
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
	
	
}
