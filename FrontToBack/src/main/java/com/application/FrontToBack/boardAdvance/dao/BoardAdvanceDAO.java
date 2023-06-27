package com.application.FrontToBack.boardAdvance.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyDTO;
import com.application.FrontToBack.boardAdvance.dto.StudyReplyDTO;

public interface BoardAdvanceDAO {

	//--------------------------1.지식 관련 게시판 기능 -----------------------------------
	
	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<KnowledgeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	public void updateReadCnt(long boardId) throws Exception;
	public KnowledgeDTO getKnowledgeBoardDetail(long boardId) throws Exception;
	public void updateKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	public void removeKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	
	
	public int selectOneAllKnowledgeReplyCnt(long boardId) throws Exception;
	public List<KnowledgeReplyDTO> selectListKnowledgeReply(long boardId) throws Exception;
	public void knowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception;
	public KnowledgeReplyDTO knowledgeReplyDetail(long replyId) throws Exception;
	public String getEncodePasswd(String writer) throws Exception;
	public String checkDuplicatedWriter(String writer) throws Exception;
	public void knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception;
	public void removeKnowledgeReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception;
	
	
	// --------------------------2.qna 관련 게시판 기능 -------------------------------------
	
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
	
	// --------------------------3.study 관련 게시판 기능 -------------------------------------
	public int selectOneAllStudyBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<StudyDTO> selectStudyListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception;
	public void updateStudyReadCnt(long boardId) throws Exception;
	public StudyDTO getStudyBoardDetail(long boardId) throws Exception;
	
	
	
	
	public int selectOneAllStudyReplyCnt(long boardId) throws Exception;
	public List<StudyReplyDTO> selectListStudyReply(long boardId) throws Exception;
}
