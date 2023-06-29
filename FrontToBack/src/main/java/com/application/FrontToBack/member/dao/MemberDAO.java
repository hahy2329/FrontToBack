package com.application.FrontToBack.member.dao;

import java.util.List;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;

public interface MemberDAO {
	
	public String selectDuplicatedId(String memberId)throws Exception;
	public void insertMember(MemberDTO memberDTO) throws Exception;
	public String selectDuplicatedEmail(String email) throws Exception;
	public String getEncodePasswd(String memberId) throws Exception;
	public MemberDTO getMemberOneData(MemberDTO memberDTO) throws Exception;
	public MemberDTO getDetailMember(String memberId) throws Exception;
	public void updateMember(MemberDTO memberDTO) throws Exception;
	public void removeMember(MemberDTO memberDTO) throws Exception;
	public List<KnowledgeDTO> getMyActivityKnowledge(String memberId) throws Exception;
	public List<QnaDTO> getMyActivityQna(String memberId) throws Exception;
	public List<StudyDTO> getMyActivityStudy(String memberId) throws Exception;
	public List<BookDTO> getMyActivityBook(String memberId) throws Exception;
}
