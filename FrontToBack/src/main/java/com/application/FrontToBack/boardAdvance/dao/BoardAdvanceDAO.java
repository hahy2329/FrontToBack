package com.application.FrontToBack.boardAdvance.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;

public interface BoardAdvanceDAO {

	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<KnowledgeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	public void updateReadCnt(long boardId) throws Exception;
	public KnowledgeDTO getKnowledgeBoardDetail(long boardId) throws Exception;
	public void updateKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	public void removeKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
	
	
	public int selectOneAllKnowledgeReplyCnt(long boardId) throws Exception;
	public List<KnowledgeReplyDTO> selectListKnowledgeReply(long boardId) throws Exception;
}
