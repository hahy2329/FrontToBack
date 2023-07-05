package com.application.FrontToBack.noticeBoard.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;

public interface NoticeBoardDAO {
	
	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<NoticeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception;
	public void updateReadCnt(long boardId) throws Exception;
	public NoticeDTO getNoticeBoardDetail(long boardId) throws Exception;
	
	public int selectOneAllNoticeReplyCnt(long boardId) throws Exception;
	public List<NoticeReplyDTO> selectListNoticeReply(long boardId) throws Exception;
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	public NoticeReplyDTO noticeReplyDetail(long replyId) throws Exception;
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	public void removeNoticeReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	
	
	public List<NoticeDTO> getMainNoticeBoard() throws Exception;
	
}
