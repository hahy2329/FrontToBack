package com.application.FrontToBack.boardAdvance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;
import com.application.FrontToBack.boardAdvance.dto.KnowledgeReplyDTO;
import com.application.FrontToBack.boardAdvance.dto.QnaDTO;

@Repository
public class BoardAdvanceDAOImpl implements BoardAdvanceDAO {

	@Autowired
	private SqlSession sqlSession;
	
	
	
	// -----------------------------1.지식 관련 게시판 기능 -------------------------------
	
	@Override
	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("knowledge.selectOneAllBoardCnt", searchCntMap);
	}

	@Override
	public List<KnowledgeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("knowledge.selectListBoard", searchMap);
	}

	@Override
	public void insertKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		sqlSession.insert("knowledge.insertKnowledgeBoard", knowledgeDTO);
		
	}

	@Override
	public void updateReadCnt(long boardId) throws Exception {
		sqlSession.update("knowledge.updateKnowledgeReadCnt", boardId);
		
	}

	@Override
	public KnowledgeDTO getKnowledgeBoardDetail(long boardId) throws Exception {
		KnowledgeDTO knowledgeDTO = sqlSession.selectOne("knowledge.getKnowledgeBoardDetail", boardId);
		return knowledgeDTO;
	}


	@Override
	public void updateKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		sqlSession.update("knowledge.updateKnowledgeBoard", knowledgeDTO);
		
	}

	@Override
	public void removeKnowledgeBoard(KnowledgeDTO knowledgeDTO) throws Exception {
		sqlSession.delete("knowledge.removeKnowledgeBoard", knowledgeDTO);
		
	}


	@Override
	public int selectOneAllKnowledgeReplyCnt(long boardId) throws Exception {
		return sqlSession.selectOne("knowledge.selectOneAllKnowledgeReplyCnt", boardId);
	}

	@Override
	public List<KnowledgeReplyDTO> selectListKnowledgeReply(long boardId) throws Exception {
		return sqlSession.selectList("knowledge.selectListKnowledgeReply", boardId);
	}

	@Override
	public void knowledgeAddReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		sqlSession.insert("knowledge.insertKnowledgeReply",knowledgeReplyDTO);
		
	}

	@Override
	public KnowledgeReplyDTO knowledgeReplyDetail(long replyId) throws Exception {
		return sqlSession.selectOne("knowledge.knowledgeReplyDetail", replyId);
	}

	@Override
	public String getEncodePasswd(String writer) throws Exception {
		return sqlSession.selectOne("knowledge.getEncodePasswd", writer);
	}

	@Override
	public String checkDuplicatedWriter(String writer) throws Exception {
		return sqlSession.selectOne("knowledge.selectOneDuplicateWriter", writer);
	}

	@Override
	public void knowledgeUpdateReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		sqlSession.update("knowledge.updateKnowledgeReply", knowledgeReplyDTO);
		
	}

	@Override
	public void removeKnowledgeReply(KnowledgeReplyDTO knowledgeReplyDTO) throws Exception {
		sqlSession.delete("knowledge.removeKnowledgeReply", knowledgeReplyDTO);
		
	}
	
	
	
	// --------------------------2.qna 관련 게시판 기능 -----------------------------------
	
	@Override
	public int selectOneAllQnaBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("qna.selectOneAllQnaBoardCnt", searchCntMap);
	}

	@Override
	public List<QnaDTO> selectQnaListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("qna.selectListQnaBoard", searchMap);
	}
	
	

}
