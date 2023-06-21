package com.application.FrontToBack.boardAdvance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.boardAdvance.dao.BoardAdvanceDAO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;

@Service
public class BoardAdvanceServiceImpl implements BoardAdvanceService {
	
	@Autowired
	private BoardAdvanceDAO boardAdvanceDAO;
	
	
	@Override
	public int getAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return boardAdvanceDAO.selectOneAllBoardCnt(searchCntMap);
	}


	@Override
	public List<KnowledgeDTO> getBoardList(Map<String, Object> searchMap) throws Exception {
		return boardAdvanceDAO.selectListBoard(searchMap);
	}

}
