package com.application.FrontToBack.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.member.dao.MemberDAO;
import com.application.FrontToBack.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public String checkDuplicatedId(String memberId) throws Exception {
		if(memberDAO.selectDuplicatedId(memberId) ==null) return "duplicate";
		else		return "notDuplicate";
	}

	@Override
	public void addMember(MemberDTO memberDTO) throws Exception {
		
		memberDTO.setPasswd(bCryptPasswordEncoder.encode(memberDTO.getPasswd()));
		if(memberDTO.getEmailstsYn() == null) memberDTO.setEmailstsYn("N");
		memberDAO.insertMember(memberDTO);
		
	}

}
