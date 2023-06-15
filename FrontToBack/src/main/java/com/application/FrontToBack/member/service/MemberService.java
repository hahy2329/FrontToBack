package com.application.FrontToBack.member.service;

import com.application.FrontToBack.member.dto.MemberDTO;

public interface MemberService {
	public String checkDuplicatedId(String memberId) throws Exception;
	public void addMember(MemberDTO memberDTO) throws Exception;
}
