package com.application.FrontToBack.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.admin.service.AdminService;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.service.KnowledgeBoardService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	
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
		mv.setViewName("/admin/adminPage");
		return mv;
		
	}
	
	@GetMapping("/knowledgeList")
	public ModelAndView knowledgeList() throws Exception{
		
		ModelAndView mv = new ModelAndView();
		List<KnowledgeDTO> knowledgeList = adminService.getAdminKnowledgeList();
		mv.setViewName("/admin/knowledgeList");
		mv.addObject("knowledgeList", knowledgeList);
		return mv;
	}
	
	
	
}
