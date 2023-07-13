package com.application.FrontToBack.admin.dao;



import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;


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

	@Override
	public String getAdminPasswd(String adminId) throws Exception {
		return sqlSession.selectOne("admin.getAdminPasswd",adminId);
	}

	@Override
	public void insertNoticeBoard(NoticeDTO noticeDTO) throws Exception {
		sqlSession.insert("admin.insertNoticeBoard", noticeDTO);
		
	}

	@Override
	public void noticeUpdate(NoticeDTO noticeDTO) throws Exception {
		sqlSession.update("admin.noticeUpdate", noticeDTO);
		
	}

	@Override
	public void noticeRemove(NoticeDTO noticeDTO) throws Exception {
		sqlSession.delete("admin.noticeRemove",noticeDTO);
		
	}

	@Override
	public void noticeReplyForceRemove(long replyId) throws Exception {
		sqlSession.delete("admin.noticeReplyForceRemove", replyId);
		
	}

	@Override
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		sqlSession.update("admin.noticeUpdateReply", noticeReplyDTO);
		
	}

	@Override
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		sqlSession.insert("admin.noticeAddReply", noticeReplyDTO);
		
	}

	@Override
	public void noticeRemoveReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		sqlSession.delete("admin.noticeRemoveReply",noticeReplyDTO);
		
	}

	@Override
	public void memberForceRemove(String memberId) throws Exception {
		sqlSession.delete("admin.memberForceRemove", memberId);
		
	}

	

}
