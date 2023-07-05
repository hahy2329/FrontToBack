package com.application.FrontToBack.noticeBoard.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;

public interface NoticeBoardService {
	
	public int getAllBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<NoticeDTO> getBoardList(Map<String,Object> searchMap) throws Exception;
	public NoticeDTO getNoticeBoardDetail(long boardId, boolean increaseRead) throws Exception;
	
	
	public int getAllNoticeReplyCnt(long boardId) throws Exception;
	public List<NoticeReplyDTO> getAllNoticeReplyList(long boardId) throws Exception;
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	public NoticeReplyDTO noticeReplyDetail(long replyId) throws Exception;
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	public void removeNoticeReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	
	public List<NoticeDTO> getMainNoticeBoard() throws Exception;
}
