package com.application.FrontToBack.knowledgeBoard.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeReplyDTO;

public interface KnowledgeBoardService {

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
	
	public KnowledgeDTO getPopularBoard() throws Exception;
	
	
}
