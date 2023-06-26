package com.application.FrontToBack.boardAdvance.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class QnaReplyDTO {
	
	private long replyId;
	private String passwd;
	private String content;
	private Date enrollDt;
	private long boardId;
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
	@Override
	public String toString() {
		return "QnaReplyDTO [replyId=" + replyId + ", passwd=" + passwd + ", content=" + content + ", enrollDt="
				+ enrollDt + ", boardId=" + boardId + ", memberId=" + memberId + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
	
	
}
