package com.application.FrontToBack.member.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {
	
	private String memberId;
	private String passwd;
	private String memberNm;
	private String email;
	private String emailstsYn;
	private String sort;
	private Date joinDt;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailstsYn() {
		return emailstsYn;
	}
	public void setEmailstsYn(String emailstsYn) {
		this.emailstsYn = emailstsYn;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Date getJoinDt() {
		return joinDt;
	}
	public void setJoinDt(Date joinDt) {
		this.joinDt = joinDt;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberId=" + memberId + ", passwd=" + passwd + ", memberNm=" + memberNm + ", email=" + email
				+ ", emailstsYn=" + emailstsYn + ", sort=" + sort + ", joinDt=" + joinDt + ", toString()="
				+ super.toString() + "]";
	}
	
	
	

	
}
