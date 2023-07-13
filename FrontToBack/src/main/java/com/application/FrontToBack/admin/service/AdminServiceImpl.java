package com.application.FrontToBack.admin.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.admin.dao.AdminDAO;
import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.member.dao.MemberDAO;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;
	
	@Autowired
	private MemberDAO memberDAO;
	
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

	@Override
	public String checkDuplicatedPasswd(String passwd, String adminId) throws Exception {
		if(passwd.equals(adminDAO.getAdminPasswd(adminId))) {
			return "duplicate";
		}
		else {
			return "notDuplicated";
		}
	}

	@Override
	public void insertNoticeBoard(NoticeDTO noticeDTO) throws Exception {
		
		adminDAO.insertNoticeBoard(noticeDTO);
		
	}

	@Override
	public void noticeUpdate(NoticeDTO noticeDTO) throws Exception {
		adminDAO.noticeUpdate(noticeDTO);
		
	}

	@Override
	public void noticeRemove(NoticeDTO noticeDTO) throws Exception {
		adminDAO.noticeRemove(noticeDTO);
		
	}

	@Override
	public void noticeReplyForceRemove(long replyId) throws Exception {
		adminDAO.noticeReplyForceRemove(replyId);
		
	}

	@Override
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		adminDAO.noticeUpdateReply(noticeReplyDTO);
		
	}

	@Override
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		adminDAO.noticeAddReply(noticeReplyDTO);
		
	}

	@Override
	public void noticeRemoveReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		adminDAO.noticeRemoveReply(noticeReplyDTO);
		
	}

	@Override
	public void memberForceRemove(String memberId) throws Exception {
		adminDAO.memberForceRemove(memberId);
		
	}

	@Override
	public String checkDuplicatedMemberId(String memberId) throws Exception {
		if(memberId.equals(memberDAO.selectDuplicatedId(memberId))) return "duplicate";
		
		else 		return "notDuplicate";
		
	}

	

}
