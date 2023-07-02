package com.application.FrontToBack.admin.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.knowledgeBoard.dto.KnowledgeDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception {
		return sqlSession.selectOne("admin.selectOneAdminDetail", adminDTO);
	}

	@Override
	public List<KnowledgeDTO> getAdminKnowledgeList() throws Exception {
		return sqlSession.selectList("admin.selectAdminKnowledgeList");
	}

}
