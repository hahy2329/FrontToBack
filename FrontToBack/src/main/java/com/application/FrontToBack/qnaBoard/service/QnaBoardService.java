package com.application.FrontToBack.qnaBoard.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;

public interface QnaBoardService {
	
	
	public int getAllQnaBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<QnaDTO> getQnaBoardList(Map<String,Object> searchMap) throws Exception;
	public void insertQnaBoard(QnaDTO qnaDTO) throws Exception;
	public QnaDTO getQnaBoardDetail(long boardId, boolean increaseRead) throws Exception;
	public void updateQnaBoard(QnaDTO qnaDTO) throws Exception;
	public void removeQnaBoard(QnaDTO qnaDTO) throws Exception;
	
	
	
	
	public int getAllQnaReplyCnt(long boardId) throws Exception;
	public List<QnaReplyDTO> getAllQnaReplyList(long boardId) throws Exception;
	public void qnaAddReply(QnaReplyDTO qnaReplyDTO) throws Exception;
	public QnaReplyDTO qnaReplyDetail(long replyId) throws Exception;
	public void qnaUpdateReply(QnaReplyDTO qnaReplyDTO) throws Exception;
	public void removeQnaReply(QnaReplyDTO qnaReplyDTO) throws Exception;
	
	public List<QnaDTO> getMainQnaBoard() throws Exception;
}
