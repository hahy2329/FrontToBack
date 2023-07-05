package com.application.FrontToBack.noticeBoard.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class NoticeReplyDTO {

	private long replyId;
	private String passwd;
	private String content;
	private Date enrollDt;
	private int readCnt;
	private long boardId;
	private String adminId;
	private String memberId;
	
	public long getReplyId() {
		return replyId;
	}
	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
	}
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "NoticeReplyDTO [replyId=" + replyId + ", passwd=" + passwd + ", content=" + content + ", enrollDt="
				+ enrollDt + ", readCnt=" + readCnt + ", boardId=" + boardId + ", adminId=" + adminId + ", memberId="
				+ memberId + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
