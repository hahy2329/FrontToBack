package com.application.FrontToBack.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.knowledgeBoard.dao.KnowledgeBoardDAO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.member.dao.MemberDAO;
import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;

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
	public String checkDuplicatedPasswd(String passwd, String memberId) throws Exception {
		if(bCryptPasswordEncoder.matches(passwd, memberDAO.getEncodePasswd(memberId))) return "duplicate";
		else return "notDuplicated";
	}
	
	@Override
	public String checkDuplicatedEmail(String email) throws Exception {
		if(memberDAO.selectDuplicatedEmail(email) == null) return "duplicate";
		else		return "notDuplicate";
	}

	@Override
	public void addMember(MemberDTO memberDTO) throws Exception {
		
		memberDTO.setPasswd(bCryptPasswordEncoder.encode(memberDTO.getPasswd()));
		if(memberDTO.getEmailstsYn() == null) memberDTO.setEmailstsYn("N");
		memberDAO.insertMember(memberDTO);
		
	}

	@Override
	public MemberDTO loginMember(MemberDTO memberDTO) throws Exception {
		
		
		if(bCryptPasswordEncoder.matches(memberDTO.getPasswd(), memberDAO.getEncodePasswd(memberDTO.getMemberId()))) {
			
			MemberDTO memberData = memberDAO.getMemberOneData(memberDTO);
			
			return memberData;
			
		}
		else {
			
			return null;
		}
		
		
	}

	@Override
	public MemberDTO getDetailMember(String memberId) throws Exception {
		MemberDTO memberDTO = memberDAO.getDetailMember(memberId);
		return memberDTO;
	}

	@Override
	public boolean updateMember(MemberDTO memberDTO) throws Exception {
		boolean isUpdate = false;
		
		if(bCryptPasswordEncoder.matches(memberDTO.getPasswd(), memberDAO.getEncodePasswd(memberDTO.getMemberId()) )) {
			isUpdate = true;
			
			if(memberDTO.getEmailstsYn() ==null) memberDTO.setEmailstsYn("N");
			memberDAO.updateMember(memberDTO);
		}
		
		return isUpdate;
	}

	@Override
	public boolean removeMember(MemberDTO memberDTO) throws Exception {
		
			
		boolean isRemove = false;
		
		if(bCryptPasswordEncoder.matches(memberDTO.getPasswd(), memberDAO.getEncodePasswd(memberDTO.getMemberId()))) {
			memberDAO.removeMember(memberDTO);
			isRemove = true;
		}
		
		return isRemove;
	}

	@Override
	public List<KnowledgeDTO> getMyActivityKnowledge(String memberId) throws Exception {
		return memberDAO.getMyActivityKnowledge(memberId);
	}

	@Override
	public List<QnaDTO> getMyActivityQna(String memberId) throws Exception {
		return memberDAO.getMyActivityQna(memberId);
	}

	@Override
	public List<StudyDTO> getMyActivityStudy(String memberId) throws Exception {
		return memberDAO.getMyActivityStudy(memberId);
	}

	@Override
	public List<BookDTO> getMyActivityBook(String memberId) throws Exception {
		return memberDAO.getMyActivityBook(memberId);
	}

	@Override
	public List<MemberDTO> getMemberList() throws Exception {
		return memberDAO.getMemberList();
	}

	@Override
	public int getCountMember() throws Exception {
		return memberDAO.getCountMember();
	}

	



}
