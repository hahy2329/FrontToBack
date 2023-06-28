package com.application.FrontToBack.knowledgeBoard.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class KnowledgeReplyDTO {
	
	private long replyId;
	private String memberId;
	private String passwd;
	private String content;
	private Date enrollDt;
	private long boardId;
	public long getReplyId() {
		return replyId;
	}
	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
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
	@Override
	public String toString() {
		return "KnowledgeReplyDTO [replyId=" + replyId + ", memberId=" + memberId + ", passwd=" + passwd + ", content="
				+ content + ", enrollDt=" + enrollDt + ", boardId=" + boardId + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	
	
	
	
}
