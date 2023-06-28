package com.application.FrontToBack.qnaBoard.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;

public interface QnaBoardDAO {

	
	public int selectOneAllQnaBoardCnt(Map<String,String> searchCntMap) throws Exception;
	public List<QnaDTO> selectQnaListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertQnaBoard(QnaDTO qnaDTO) throws Exception;
	public void updateQnaReadCnt(long boardId) throws Exception;
	public QnaDTO getQnaBoardDetail(long boardId) throws Exception;
	public void updateQnaBoard(QnaDTO qnaDTO) throws Exception;
	public void removeQnaBoard(QnaDTO qnaDTO) throws Exception;
	
	
	
	
	public int selectOneAllQnaReplyCnt(long boardId) throws Exception;
	public List<QnaReplyDTO> selectListQnaReply(long boardId) throws Exception;
	public void qnaAddReply(QnaReplyDTO qnaReplyDTO) throws Exception;
	public QnaReplyDTO qnaReplyDetail(long replyId) throws Exception;
	public void qnaUpdateReply(QnaReplyDTO qnaReplyDTO) throws Exception;
	public void removeQnaReply(QnaReplyDTO qnaReplyDTO) throws Exception;
	
	public List<QnaDTO> getMainQnaBoard() throws Exception;
}
