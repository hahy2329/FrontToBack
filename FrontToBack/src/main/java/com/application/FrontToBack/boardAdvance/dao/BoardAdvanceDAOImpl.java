package com.application.FrontToBack.boardAdvance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.boardAdvance.dto.KnowledgeDTO;

@Repository
public class BoardAdvanceDAOImpl implements BoardAdvanceDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("knowledge.selectOneAllBoardCnt", searchCntMap);
	}

	@Override
	public List<KnowledgeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("knowledge.selectListBoard", searchMap);
	}

}
