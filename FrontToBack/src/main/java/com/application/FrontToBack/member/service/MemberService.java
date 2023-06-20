package com.application.FrontToBack.member.service;

import com.application.FrontToBack.member.dto.MemberDTO;

public interface MemberService {
	public String checkDuplicatedId(String memberId) throws Exception;
	public String checkDuplicatedPasswd(String passwd, String memberId) throws Exception;
	public void addMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO loginMember(MemberDTO memberDTO) throws Exception;
	public MemberDTO getDetailMember(String memberId) throws Exception;
	public boolean updateMember(MemberDTO memberDTO) throws Exception;
	public boolean removeMember(MemberDTO memberDTO) throws Exception;
	
	
}
