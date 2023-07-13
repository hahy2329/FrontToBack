package com.application.FrontToBack.admin.dao;



import com.application.FrontToBack.admin.dto.AdminDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;


public interface AdminDAO {
	
	public AdminDTO getAdminDetail(AdminDTO adminDTO) throws Exception;
	public void forceRemoveKnowledgeBoard(long boardId) throws Exception;
	public void forceRemoveKnowledgeReplyBoard(long replyId) throws Exception;
	public void forceRemoveQnaBoard(long boardId) throws Exception;
	public void forceRemoveQnaReplyBoard(long replyId) throws Exception;
	public void forceRemoveStudyBoard(long boardId) throws Exception;
	public void forceRemoveStudyReplyBoard(long replyId) throws Exception;
	public void forceRemoveBookBoard(long boardId) throws Exception;
	public void forceRemoveBookReplyBoard(long replyId) throws Exception;
	public String getAdminPasswd(String adminId) throws Exception;
	public void insertNoticeBoard(NoticeDTO noticeDTO) throws Exception;
	public void noticeUpdate(NoticeDTO noticeDTO) throws Exception;
	public void noticeRemove(NoticeDTO noticeDTO) throws Exception;
	public void noticeReplyForceRemove(long replyId) throws Exception;
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	public void noticeRemoveReply(NoticeReplyDTO noticeReplyDTO) throws Exception;
	
	public void memberForceRemove(String memberId) throws Exception;

}
 