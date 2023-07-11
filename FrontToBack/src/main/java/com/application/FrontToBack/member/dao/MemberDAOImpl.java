package com.application.FrontToBack.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.member.dto.MemberDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectDuplicatedId(String memberId) throws Exception {
		return sqlSession.selectOne("member.selectDuplicatedId", memberId);
	}

	@Override
	public String selectDuplicatedEmail(String email) throws Exception {
		
		String answer= sqlSession.selectOne("member.selectDuplicatedEmail", email);
		
		return answer;
	}
	
	
	@Override
	public void insertMember(MemberDTO memberDTO) throws Exception {
		sqlSession.insert("member.insertMember",memberDTO);
		
	}

	@Override
	public String getEncodePasswd(String memberId) throws Exception {
		String passwd = sqlSession.selectOne("member.selectGetEncodePasswd", memberId);
		return passwd;
	}

	@Override
	public MemberDTO getMemberOneData(MemberDTO memberDTO) throws Exception {
		MemberDTO memberData = sqlSession.selectOne("member.selectGetOneMemberData", memberDTO);
		return memberData;
	}

	@Override
	public MemberDTO getDetailMember(String memberId) throws Exception {
		MemberDTO memberDTO = sqlSession.selectOne("member.selectOneDetailMember", memberId);
		return memberDTO;
	}

	@Override
	public void updateMember(MemberDTO memberDTO) throws Exception {
		sqlSession.update("member.updateOneMember", memberDTO);
		
	}

	@Override
	public void removeMember(MemberDTO memberDTO) throws Exception {
		sqlSession.delete("member.removeOneMember", memberDTO);
		
	}

	@Override
	public List<KnowledgeDTO> getMyActivityKnowledge(String memberId) throws Exception {
		return sqlSession.selectList("member.getMyActivityKnowledge", memberId);
	}

	@Override
	public List<QnaDTO> getMyActivityQna(String memberId) throws Exception {
		return sqlSession.selectList("member.getMyActivityQna", memberId);
	}

	@Override
	public List<StudyDTO> getMyActivityStudy(String memberId) throws Exception {
		return sqlSession.selectList("member.getMyActivityStudy", memberId);
	}

	@Override
	public List<BookDTO> getMyActivityBook(String memberId) throws Exception {
		return sqlSession.selectList("member.getMyActivityBook", memberId);
	}

	@Override
	public List<MemberDTO> getMemberList() throws Exception {
		return sqlSession.selectList("member.getMemberList");
	}

	
	
	

}
