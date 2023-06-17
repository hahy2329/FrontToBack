package com.application.FrontToBack.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public String selectDuplicatedId(String memberId) throws Exception {
		return sqlSession.selectOne("member.selectDuplicatedId", memberId);
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

}
