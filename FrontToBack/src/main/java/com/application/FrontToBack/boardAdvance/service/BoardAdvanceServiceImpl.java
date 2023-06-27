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
import com.application.FrontToBack.boardAdvance.dto.StudyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyReplyDTO;

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
	
	
	@Override
	public List<KnowledgeDTO> getMainKnowledgeBoard() throws Exception {
		List<KnowledgeDTO> knowledgeDTO = boardAdvanceDAO.getMainKnowledgeBoard();
		return knowledgeDTO;
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
	
	
	@Override
	public List<QnaDTO> getMainQnaBoard() throws Exception {
		List<QnaDTO> qnaDTO = boardAdvanceDAO.getMainQnaBoard();
		return qnaDTO;
	}

	
	// ---------------------------3.study관련 게시판 기능 -------------------------------
	
	
	@Override
	public int getAllStudyBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return boardAdvanceDAO.selectOneAllStudyBoardCnt(searchCntMap);
	}


	@Override
	public List<StudyDTO> getStudyBoardList(Map<String, Object> searchMap) throws Exception {
		return boardAdvanceDAO.selectStudyListBoard(searchMap);
	}


	@Override
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception {
		studyDTO.setPasswd(bCryptPasswordEncoder.encode(studyDTO.getPasswd()));
		
		boardAdvanceDAO.insertStudyBoard(studyDTO);
		
	}


	@Override
	public StudyDTO getStudyBoardDetail(long boardId, boolean increaseRead) throws Exception {
		
		if(increaseRead) {
			boardAdvanceDAO.updateStudyReadCnt(boardId);
		}
			
		StudyDTO studyDTO = boardAdvanceDAO.getStudyBoardDetail(boardId);
		
		
		return studyDTO;
	}


	
	
	
	
	
	@Override
	public int getAllStudyReplyCnt(long boardId) throws Exception {
		return boardAdvanceDAO.selectOneAllStudyReplyCnt(boardId);
	}


	@Override
	public List<StudyReplyDTO> getAllStudyReplyList(long boardId) throws Exception {
		List<StudyReplyDTO> studyReplyList = boardAdvanceDAO.selectListStudyReply(boardId);
		return studyReplyList;
	}


	@Override
	public void updateStudyBoard(StudyDTO studyDTO) throws Exception {
		boardAdvanceDAO.updateStudyBoard(studyDTO);
		
	}


	@Override
	public void removeStudyBoard(StudyDTO studyDTO) throws Exception {
		boardAdvanceDAO.removeStudyBoard(studyDTO);
		
	}


	@Override
	public void studyAddReply(StudyReplyDTO studyReplyDTO) throws Exception {
		studyReplyDTO.setPasswd(bCryptPasswordEncoder.encode(studyReplyDTO.getPasswd()));
		boardAdvanceDAO.studyAddReply(studyReplyDTO);
		
	}


	@Override
	public StudyReplyDTO studyReplyDetail(long replyId) throws Exception {
		StudyReplyDTO studyReplyDTO = boardAdvanceDAO.studyReplyDetail(replyId);
		return studyReplyDTO;
	}


	@Override
	public void studyUpdateReply(StudyReplyDTO studyReplyDTO) throws Exception {
		
		boardAdvanceDAO.studyUpdateReply(studyReplyDTO);
	}


	@Override
	public void removeStudyReply(StudyReplyDTO studyReplyDTO) throws Exception {
		boardAdvanceDAO.removeStudyReply(studyReplyDTO);
		
	}


	@Override
	public List<StudyDTO> getMainStudyBoard() throws Exception {
		List<StudyDTO> studyDTO = boardAdvanceDAO.getMainStudyBoard();
		return studyDTO;
	}


	
	


}
