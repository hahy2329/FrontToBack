package com.application.FrontToBack.boardAdvance.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class QnaDTO {
	
	private long boardId;
	private String memberId;
	private String subject;
	private String content;
	private String sort;
	private String passwd;
	private long readCnt;
	private Date enrollDt;
	
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public long getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(long readCnt) {
		this.readCnt = readCnt;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}
	@Override
	public String toString() {
		return "QnaDTO [boardId=" + boardId + ", memberId=" + memberId + ", subject=" + subject + ", content=" + content
				+ ", sort=" + sort + ", passwd=" + passwd + ", readCnt=" + readCnt + ", enrollDt=" + enrollDt
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
}
