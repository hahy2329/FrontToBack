package com.application.FrontToBack.qnaBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.qnaBoard.dao.QnaBoardDAO;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;

@Service
public class QnaBoardServiceImpl implements QnaBoardService {
	
	@Autowired
	private QnaBoardDAO qnaBoardDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int getAllQnaBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return qnaBoardDAO.selectOneAllQnaBoardCnt(searchCntMap);
	}


	@Override
	public List<QnaDTO> getQnaBoardList(Map<String, Object> searchMap) throws Exception {
		return qnaBoardDAO.selectQnaListBoard(searchMap);
	}


	@Override
	public void insertQnaBoard(QnaDTO qnaDTO) throws Exception {
		qnaDTO.setPasswd(bCryptPasswordEncoder.encode(qnaDTO.getPasswd()));
		
		qnaBoardDAO.insertQnaBoard(qnaDTO);
		
	}


	@Override
	public QnaDTO getQnaBoardDetail(long boardId, boolean increaseRead) throws Exception {
		if(increaseRead) {
			qnaBoardDAO.updateQnaReadCnt(boardId);
		}
			
		QnaDTO qnaDTO = qnaBoardDAO.getQnaBoardDetail(boardId);
		
		
		return qnaDTO;
	}


	@Override
	public int getAllQnaReplyCnt(long boardId) throws Exception {
		return qnaBoardDAO.selectOneAllQnaReplyCnt(boardId);
	}


	@Override
	public List<QnaReplyDTO> getAllQnaReplyList(long boardId) throws Exception {
		List<QnaReplyDTO> qnaReplyList = qnaBoardDAO.selectListQnaReply(boardId);
		return qnaReplyList;
	}


	@Override
	public void updateQnaBoard(QnaDTO qnaDTO) throws Exception {
		qnaBoardDAO.updateQnaBoard(qnaDTO);
		
	}


	@Override
	public void removeQnaBoard(QnaDTO qnaDTO) throws Exception {
		qnaBoardDAO.removeQnaBoard(qnaDTO);
		
	}


	@Override
	public void qnaAddReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		qnaReplyDTO.setPasswd(bCryptPasswordEncoder.encode(qnaReplyDTO.getPasswd()));
		qnaBoardDAO.qnaAddReply(qnaReplyDTO);
		
	}


	@Override
	public QnaReplyDTO qnaReplyDetail(long replyId) throws Exception {
		QnaReplyDTO qnaReplyDTO = qnaBoardDAO.qnaReplyDetail(replyId);
		return qnaReplyDTO;
	}


	@Override
	public void qnaUpdateReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		qnaBoardDAO.qnaUpdateReply(qnaReplyDTO);
		
	}


	@Override
	public void removeQnaReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		qnaBoardDAO.removeQnaReply(qnaReplyDTO);
		
	}
	
	
	@Override
	public List<QnaDTO> getMainQnaBoard() throws Exception {
		List<QnaDTO> qnaDTO = qnaBoardDAO.getMainQnaBoard();
		return qnaDTO;
	}


	@Override
	public QnaDTO getPopularBoard() throws Exception {
		return qnaBoardDAO.getPopularBoard();
	}
	
	
}
