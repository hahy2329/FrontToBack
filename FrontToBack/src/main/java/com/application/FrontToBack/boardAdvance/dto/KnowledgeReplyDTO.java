package com.application.FrontToBack.boardAdvance.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class KnowledgeReplyDTO {
	
	private long replyId;
	private String writer;
	private String passwd;
	private String content;
	private long readCnt;
	private Date enrollDt;
	private long boardId;
	public long getReplyId() {
		return replyId;
	}
	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	@Override
	public String toString() {
		return "KnowledgeReplyDTO [replyId=" + replyId + ", writer=" + writer + ", passwd=" + passwd + ", content="
				+ content + ", readCnt=" + readCnt + ", enrollDt=" + enrollDt + ", boardId=" + boardId + ", toString()="
				+ super.toString() + "]";
	}
	
	
	
	
	
}
