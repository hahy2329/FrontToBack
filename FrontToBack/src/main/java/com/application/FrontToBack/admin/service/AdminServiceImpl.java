package com.application.FrontToBack.admin.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.admin.dao.AdminDAO;
import com.application.FrontToBack.admin.dto.AdminDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Override
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception {
		return adminDAO.getAdminDetail(adminDTO);
	}

	@Override
	public void forceRemoveKnowledgeBoard(long boardId) throws Exception {
		adminDAO.forceRemoveKnowledgeBoard(boardId);
		
	}

	@Override
	public void forceRemoveKnowledgeReplyBoard(long replyId) throws Exception {
		adminDAO.forceRemoveKnowledgeReplyBoard(replyId);
		
	}

	@Override
	public void forceRemoveQnaBoard(long boardId) throws Exception {
		adminDAO.forceRemoveQnaBoard(boardId);
		
	}

	@Override
	public void forceRemoveQnaReplyBoard(long replyId) throws Exception {
		adminDAO.forceRemoveQnaReplyBoard(replyId);
		
	}

	@Override
	public void forceRemoveStudyBoard(long boardId) throws Exception {
		adminDAO.forceRemoveStudyBoard(boardId);
		
	}

	@Override
	public void forceRemoveStudyReplyBoard(long replyId) throws Exception {
		adminDAO.forceRemoveStudyReplyBoard(replyId);
		
	}

	@Override
	public void forceRemoveBookBoard(long boardId) throws Exception {
		adminDAO.forceRemoveBookBoard(boardId);
		
	}

	@Override
	public void forceRemoveBookReplyBoard(long replyId) throws Exception {
		adminDAO.forceRemoveBookReplyBoard(replyId);
		
	}

	

}
