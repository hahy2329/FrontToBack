package com.application.FrontToBack.boardAdvance.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;

public interface BoardAdvanceService {

	public int getAllBoardCnt(Map<String,String> searchCntMap) throws Exception;
	public List<KnowledgeDTO> getBoardList(Map<String,Object> searchMap) throws Exception;
}
