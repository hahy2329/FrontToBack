package com.application.FrontToBack.knowledgeBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.knowledgeBoard.dao.KnowledgeBoardDAO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeReplyDTO;

@Service
public class KnowledgeBoardServiceImpl implements KnowledgeBoardService {
	
	@Autowired
	private KnowledgeBoardDAO knowledgeBoardDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int getAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return knowledgeBoardDAO.selectOneAllBoardCnt(searchCntMap);
	}


	@Override
	public List<KnowledgeDTO> getBoardList(Map<String, Object> searchMap) throws Exception {
		return knowledgeBoardDAO.selectListBoard(searchMap);
	}


	@Override
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		knowledgeDTO.setPasswd(bCryptPasswordEncoder.encode(knowledgeDTO.getPasswd()));
		
		knowledgeBoardDAO.insertKnowledgeBoard(knowledgeDTO);
		
	}


	@Override
	public KnowledgeDTO getKnowledgeBoardDetail(long boardId, boolean increaseRead) throws Exception {
		
		if(increaseRead) {
			knowledgeBoardDAO.updateReadCnt(boardId);
		}
			
		KnowledgeDTO knowledgeDTO = knowledgeBoardDAO.getKnowledgeBoardDetail(boardId);
		
		
		return knowledgeDTO;
	}


	@Override
	public void updateKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		knowledgeBoardDAO.updateKnowledgeBoard(knowledgeDTO);
		
	}


	@Override
	public void removeKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		knowledgeBoardDAO.removeKnowledgeBoard(knowledgeDTO);
		
	}

	
	
	
	@Override
	public int getAllKnowledgeReplyCnt(long boardId) throws Exception {
		return knowledgeBoardDAO.selectOneAllKnowledgeReplyCnt(boardId);
	}


	@Override
	public List<KnowledgeReplyDTO> getAllKnowledgeReplyList(long boardId) throws Exception {
		
		List<KnowledgeReplyDTO> knowledgeReplyList = knowledgeBoardDAO.selectListKnowledgeReply(boardId);
		return knowledgeReplyList;
	}


	@Override
	public void knowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		knowledgeReplyDTO.setPasswd(bCryptPasswordEncoder.encode(knowledgeReplyDTO.getPasswd()));
		knowledgeBoardDAO.knowledgeAddReply(knowledgeReplyDTO);
		
	}


	@Override
	public KnowledgeReplyDTO knowledgeReplyDetail(long replyId) throws Exception {
		KnowledgeReplyDTO knowledgeReplyDTO = knowledgeBoardDAO.knowledgeReplyDetail(replyId);
		return knowledgeReplyDTO;
	}


	@Override
	public String checkDuplicatedPasswd(String writer, String passwd) throws Exception {
		if(bCryptPasswordEncoder.matches(passwd, knowledgeBoardDAO.getEncodePasswd(writer))) return "duplicate";
		else 			return "notDuplicate";
		
	}


	@Override
	public String checkDuplicatedWriter(String writer) throws Exception {
		if(knowledgeBoardDAO.checkDuplicatedWriter(writer) == null)   return "duplicate"; 
		else				return "notDuplicate";
	}


	@Override
	public void knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		knowledgeBoardDAO.knowledgeUpdateReply(knowledgeReplyDTO);
		
	}


	@Override
	public void removeKnowledgeReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		knowledgeBoardDAO.removeKnowledgeReply(knowledgeReplyDTO);
		
	}
	
	
	@Override
	public List<KnowledgeDTO> getMainKnowledgeBoard() throws Exception {
		List<KnowledgeDTO> knowledgeDTO = knowledgeBoardDAO.getMainKnowledgeBoard();
		return knowledgeDTO;
	}
	

	
}
