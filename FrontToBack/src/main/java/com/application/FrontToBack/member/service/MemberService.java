package com.application.FrontToBack.member.service;

import java.util.List;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;

public interface MemberService {
	public String checkDuplicatedId(String memberId) throws Exception;
	public String checkDuplicatedPasswd(String passwd, String memberId) throws Exception;
	public String checkDuplicatedEmail(String email) throws Exception;
	public void addMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO loginMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO getDetailMember(String memberId) throws Exception;
	public boolean updateMember(MemberDTO memberDTO) throws Exception;
	public boolean removeMember(MemberDTO memberDTO) throws Exception;
	public List<KnowledgeDTO> getMyActivityKnowledge(String memberId) throws Exception;
	public List<QnaDTO> getMyActivityQna(String memberId) throws Exception;
	public List<StudyDTO> getMyActivityStudy(String memberId) throws Exception;
	public List<BookDTO> getMyActivityBook(String memberId) throws Exception;
	public List<MemberDTO> getMemberList() throws Exception;
	
	
}
