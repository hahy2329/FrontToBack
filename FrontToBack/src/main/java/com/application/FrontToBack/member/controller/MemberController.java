package com.application.FrontToBack.member.controller;

import org.springframework.http.HttpHeaders;

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

import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.member.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/register")
	public ModelAndView register() throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/member/register");
		return mv;
	}
	
	@GetMapping("/checkDuplicatedId")
	public ResponseEntity<String> checkDuplicatedId(@RequestParam("memberId") String memberId) throws Exception{
		return new ResponseEntity<String>(memberService.checkDuplicatedId(memberId),HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(MemberDTO memberDTO, HttpServletRequest request) throws Exception{
		
		memberService.addMember(memberDTO);
		
		String message = "<script>";
		message +="alert('회원가입이 완료되었습니다.');";
		message +="location.href='" + request.getContextPath() +"/';";
		message +="</script>";
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		
		
		return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
	}
	
	
}
