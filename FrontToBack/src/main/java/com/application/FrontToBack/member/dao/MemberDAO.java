package com.application.FrontToBack.member.dao;

import com.application.FrontToBack.member.dto.MemberDTO;

public interface MemberDAO {
	
	public String selectDuplicatedId(String memberId)throws Exception;
	public void insertMember(MemberDTO memberDTO) throws Exception;
}