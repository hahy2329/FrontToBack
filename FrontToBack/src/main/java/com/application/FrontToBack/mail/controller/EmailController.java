package com.application.FrontToBack.mail.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.mail.dto.EmailDTO;
import com.application.FrontToBack.mail.service.EmailService;

@Controller
@RequestMapping("/email")
public class EmailController {
	
	@Inject
	EmailService emailService;
	
	@GetMapping("/write")
	public ModelAndView write() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/email/write");
		return mv;
	}
	
	@PostMapping("/send")
	public ResponseEntity<Object> send(EmailDTO emailDTO, HttpServletRequest request){
		
		try {
			
			emailService.sendMail(emailDTO);
			String message = "<script>";
			message +="alert('메일이 발송되었습니다.');";
			message +="location.href='" + request.getContextPath() + "/';";
			message +="</script>";
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
			return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			String message = "<script>";
			message +="alert('메일 발송 실패.');";
			message +="history.go(-1)";
			message +="</script>";
			
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("Content-Type", "text/html; charset=utf-8");
			
			return new ResponseEntity<Object>(message, responseHeaders, HttpStatus.OK);
			
		}
		
		
	}
	
	
}
