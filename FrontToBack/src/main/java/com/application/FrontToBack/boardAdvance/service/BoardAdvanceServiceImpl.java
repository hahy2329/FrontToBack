package com.application.FrontToBack.boardAdvance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.boardAdvance.dao.BoardAdvanceDAO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaReplyDTO;

@Service
public class BoardAdvanceServiceImpl implements BoardAdvanceService {
	
	@Autowired
	private BoardAdvanceDAO boardAdvanceDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	//----------------------1. 지식 관련 게시판 기능 -----------------------------------
	@Override
	public int getAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return boardAdvanceDAO.selectOneAllBoardCnt(searchCntMap);
	}


	@Override
	public List<KnowledgeDTO> getBoardList(Map<String, Object> searchMap) throws Exception {
		return boardAdvanceDAO.selectListBoard(searchMap);
	}


	@Override
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		knowledgeDTO.setPasswd(bCryptPasswordEncoder.encode(knowledgeDTO.getPasswd()));
		
		boardAdvanceDAO.insertKnowledgeBoard(knowledgeDTO);
		
	}


	@Override
	public KnowledgeDTO getKnowledgeBoardDetail(long boardId, boolean increaseRead) throws Exception {
		
		if(increaseRead) {
			boardAdvanceDAO.updateReadCnt(boardId);
		}
			
		KnowledgeDTO knowledgeDTO = boardAdvanceDAO.getKnowledgeBoardDetail(boardId);
		
		
		return knowledgeDTO;
	}


	@Override
	public void updateKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		boardAdvanceDAO.updateKnowledgeBoard(knowledgeDTO);
		
	}


	@Override
	public void removeKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		boardAdvanceDAO.removeKnowledgeBoard(knowledgeDTO);
		
	}

	
	
	
	@Override
	public int getAllKnowledgeReplyCnt(long boardId) throws Exception {
		return boardAdvanceDAO.selectOneAllKnowledgeReplyCnt(boardId);
	}


	@Override
	public List<KnowledgeReplyDTO> getAllKnowledgeReplyList(long boardId) throws Exception {
		
		List<KnowledgeReplyDTO> knowledgeReplyList = boardAdvanceDAO.selectListKnowledgeReply(boardId);
		return knowledgeReplyList;
	}


	@Override
	public void knowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		knowledgeReplyDTO.setPasswd(bCryptPasswordEncoder.encode(knowledgeReplyDTO.getPasswd()));
		boardAdvanceDAO.knowledgeAddReply(knowledgeReplyDTO);
		
	}


	@Override
	public KnowledgeReplyDTO knowledgeReplyDetail(long replyId) throws Exception {
		KnowledgeReplyDTO knowledgeReplyDTO = boardAdvanceDAO.knowledgeReplyDetail(replyId);
		return knowledgeReplyDTO;
	}


	@Override
	public String checkDuplicatedPasswd(String writer, String passwd) throws Exception {
		if(bCryptPasswordEncoder.matches(passwd, boardAdvanceDAO.getEncodePasswd(writer))) return "duplicate";
		else 			return "notDuplicate";
		
	}


	@Override
	public String checkDuplicatedWriter(String writer) throws Exception {
		if(boardAdvanceDAO.checkDuplicatedWriter(writer) == null)   return "duplicate"; 
		else				return "notDuplicate";
	}


	@Override
	public void knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		boardAdvanceDAO.knowledgeUpdateReply(knowledgeReplyDTO);
		
	}


	@Override
	public void removeKnowledgeReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		boardAdvanceDAO.removeKnowledgeReply(knowledgeReplyDTO);
		
	}


	// ------------------------2.qna관련 게시판 기능 --------------------------------
	
	@Override
	public int getAllQnaBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return boardAdvanceDAO.selectOneAllQnaBoardCnt(searchCntMap);
	}


	@Override
	public List<QnaDTO> getQnaBoardList(Map<String, Object> searchMap) throws Exception {
		return boardAdvanceDAO.selectQnaListBoard(searchMap);
	}


	@Override
	public void insertQnaBoard(QnaDTO qnaDTO) throws Exception {
		qnaDTO.setPasswd(bCryptPasswordEncoder.encode(qnaDTO.getPasswd()));
		
		boardAdvanceDAO.insertQnaBoard(qnaDTO);
		
	}


	@Override
	public QnaDTO getQnaBoardDetail(long boardId, boolean increaseRead) throws Exception {
		if(increaseRead) {
			boardAdvanceDAO.updateQnaReadCnt(boardId);
		}
			
		QnaDTO qnaDTO = boardAdvanceDAO.getQnaBoardDetail(boardId);
		
		
		return qnaDTO;
	}


	@Override
	public int getAllQnaReplyCnt(long boardId) throws Exception {
		return boardAdvanceDAO.selectOneAllQnaReplyCnt(boardId);
	}


	@Override
	public List<QnaReplyDTO> getAllQnaReplyList(long boardId) throws Exception {
		List<QnaReplyDTO> qnaReplyList = boardAdvanceDAO.selectListQnaReply(boardId);
		return qnaReplyList;
	}


	@Override
	public void updateQnaBoard(QnaDTO qnaDTO) throws Exception {
		boardAdvanceDAO.updateQnaBoard(qnaDTO);
		
	}


	@Override
	public void removeQnaBoard(QnaDTO qnaDTO) throws Exception {
		boardAdvanceDAO.removeQnaBoard(qnaDTO);
		
	}


	@Override
	public void qnaAddReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		qnaReplyDTO.setPasswd(bCryptPasswordEncoder.encode(qnaReplyDTO.getPasswd()));
		boardAdvanceDAO.qnaAddReply(qnaReplyDTO);
		
	}


	@Override
	public QnaReplyDTO qnaReplyDetail(long replyId) throws Exception {
		QnaReplyDTO qnaReplyDTO = boardAdvanceDAO.qnaReplyDetail(replyId);
		return qnaReplyDTO;
	}


	@Override
	public void qnaUpdateReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		boardAdvanceDAO.qnaUpdateReply(qnaReplyDTO);
		
	}


	@Override
	public void removeQnaReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		boardAdvanceDAO.removeQnaReply(qnaReplyDTO);
		
	}
	
	
	
	
	


}
