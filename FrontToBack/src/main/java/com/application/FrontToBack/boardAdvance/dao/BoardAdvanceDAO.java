package com.application.FrontToBack.boardAdvance.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;

public interface BoardAdvanceDAO {

	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<KnowledgeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception;
}