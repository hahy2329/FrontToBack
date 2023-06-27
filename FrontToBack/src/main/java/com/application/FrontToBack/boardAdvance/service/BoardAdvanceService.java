package com.application.FrontToBack.boardAdvance.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyReplyDTO;

public interface BoardAdvanceService {
	// --------------------1.지식 관련 게시판 기능 -------------------------------------
	public int getAllBoardCnt(Map<String,String> searchCntMap) throws Exception;
	public List<KnowledgeDTO> getBoardList(Map<String,Object> searchMap) throws Exception;
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	public KnowledgeDTO getKnowledgeBoardDetail(long boardId, boolean increaseRead) throws Exception;
	public void updateKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	public void removeKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	
	public int getAllKnowledgeReplyCnt(long boardId) throws Exception;
	public List<KnowledgeReplyDTO> getAllKnowledgeReplyList(long boardId) throws Exception;
	public void knowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception;
	public KnowledgeReplyDTO knowledgeReplyDetail(long replyId) throws Exception;
	public String checkDuplicatedPasswd(String writer, String passwd) throws Exception;
	public String checkDuplicatedWriter(String writer) throws Exception;
	public void knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception;
	public void removeKnowledgeReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception;
	
	
	public List<KnowledgeDTO> getMainKnowledgeBoard() throws Exception;
	
	
	// ----------------------2.qna 관련 게시판 기능 --------------------------------------
	
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
	
	// ----------------------3. study관련 게시판 기능 -----------------------------------------------
	
	public int getAllStudyBoardCnt(Map<String,String> searchCntMap) throws Exception;
	public List<StudyDTO> getStudyBoardList(Map<String,Object> searchMap) throws Exception;
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception;
	public StudyDTO getStudyBoardDetail(long boardId, boolean increaseRead) throws Exception;
	public void updateStudyBoard(StudyDTO studyDTO) throws Exception;
	public void removeStudyBoard(StudyDTO studyDTO) throws Exception;
	
	
	
	
	public int getAllStudyReplyCnt(long boardId) throws Exception;
	public List<StudyReplyDTO> getAllStudyReplyList(long boardId) throws Exception;
	public void studyAddReply(StudyReplyDTO studyReplyDTO) throws Exception;
	public StudyReplyDTO studyReplyDetail(long replyId) throws Exception;
	public void studyUpdateReply(StudyReplyDTO studyReplyDTO) throws Exception;
	public void removeStudyReply(StudyReplyDTO studyReplyDTO) throws Exception;
	
	
	
	public List<StudyDTO> getMainStudyBoard() throws Exception;
	
}
