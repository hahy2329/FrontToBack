package com.application.FrontToBack.admin.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class AdminDTO {
	
	private String adminId;
	private String passwd;
	private String adminNm;
	private String sort;
	private Date joinDt;
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getAdminNm() {
		return adminNm;
	}
	public void setAdminNm(String adminNm) {
		this.adminNm = adminNm;
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
		return "AdminDTO [adminId=" + adminId + ", passwd=" + passwd + ", adminNm=" + adminNm + ", sort=" + sort
				+ ", joinDt=" + joinDt + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
}
