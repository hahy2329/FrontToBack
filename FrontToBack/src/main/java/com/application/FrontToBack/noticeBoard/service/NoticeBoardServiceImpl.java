package com.application.FrontToBack.noticeBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.noticeBoard.dao.NoticeBoardDAO;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {
	
	@Autowired
	private NoticeBoardDAO noticeBoardDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int getAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return noticeBoardDAO.selectOneAllBoardCnt(searchCntMap);
		
	}

	@Override
	public List<NoticeDTO> getBoardList(Map<String, Object> searchMap) throws Exception {
		return noticeBoardDAO.selectListBoard(searchMap);
	}

	@Override
	public NoticeDTO getNoticeBoardDetail(long boardId, boolean increaseRead) throws Exception {
		if(increaseRead) {
			noticeBoardDAO.updateReadCnt(boardId);
		}
		
		NoticeDTO noticeDTO = noticeBoardDAO.getNoticeBoardDetail(boardId);
		
		return noticeDTO;
	}

	@Override
	public int getAllNoticeReplyCnt(long boardId) throws Exception {
		return noticeBoardDAO.selectOneAllNoticeReplyCnt(boardId);
	}

	@Override
	public List<NoticeReplyDTO> getAllNoticeReplyList(long boardId) throws Exception {
		List<NoticeReplyDTO> noticeReplyDTO = noticeBoardDAO.selectListNoticeReply(boardId);
		return noticeReplyDTO;
	}

	@Override
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		noticeReplyDTO.setPasswd(bCryptPasswordEncoder.encode(noticeReplyDTO.getPasswd()));
		noticeBoardDAO.noticeAddReply(noticeReplyDTO);
	}

	@Override
	public NoticeReplyDTO noticeReplyDetail(long replyId) throws Exception {
		NoticeReplyDTO noticeReplyDTO = noticeBoardDAO.noticeReplyDetail(replyId);
		return noticeReplyDTO;
	}

	@Override
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		noticeBoardDAO.noticeUpdateReply(noticeReplyDTO);
		
	}

	@Override
	public void removeNoticeReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		noticeBoardDAO.removeNoticeReply(noticeReplyDTO);
		
	}

	@Override
	public List<NoticeDTO> getMainNoticeBoard() throws Exception {
		return noticeBoardDAO.getMainNoticeBoard();
	}

}
