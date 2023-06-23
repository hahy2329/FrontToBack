package com.application.FrontToBack.boardAdvance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.boardAdvance.dao.BoardAdvanceDAO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;

@Service
public class BoardAdvanceServiceImpl implements BoardAdvanceService {
	
	@Autowired
	private BoardAdvanceDAO boardAdvanceDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
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
	
	
	


}
