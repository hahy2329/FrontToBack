package com.application.FrontToBack.admin.dao;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.admin.dto.AdminDTO;


@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception {
		return sqlSession.selectOne("admin.selectOneAdminDetail", adminDTO);
	}

	@Override
	public void forceRemoveKnowledgeBoard(long boardId) throws Exception {
		sqlSession.delete("admin.forceRemoveKnowledgeBoard", boardId);
		
	}

	@Override
	public void forceRemoveKnowledgeReplyBoard(long replyId) throws Exception {
		sqlSession.delete("admin.forceRemoveKnowledgeReplyBoard", replyId);
		
	}

	@Override
	public void forceRemoveQnaBoard(long boardId) throws Exception {
		sqlSession.delete("admin.forceRemoveQnaBoard", boardId);
		
	}

	@Override
	public void forceRemoveQnaReplyBoard(long replyId) throws Exception {
		sqlSession.delete("admin.forceRemoveQnaReplyBoard", replyId);
		
	}

	@Override
	public void forceRemoveStudyBoard(long boardId) throws Exception {
		sqlSession.delete("admin.forceRemoveStudyBoard",boardId);
		
	}

	@Override
	public void forceRemoveStudyReplyBoard(long replyId) throws Exception {
		sqlSession.delete("admin.forceRemoveStudyReplyBoard",replyId);
		
	}

	@Override
	public void forceRemoveBookBoard(long boardId) throws Exception {
		sqlSession.delete("admin.forceRemoveBookBoard",boardId);
		
	}

	@Override
	public void forceRemoveBookReplyBoard(long replyId) throws Exception {
		sqlSession.delete("admin.forceRemoveBookReplyBoard",replyId);
		
	}

	

}
