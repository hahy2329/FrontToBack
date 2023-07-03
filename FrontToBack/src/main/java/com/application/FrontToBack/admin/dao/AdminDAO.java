package com.application.FrontToBack.admin.dao;



import com.application.FrontToBack.admin.dto.AdminDTO;


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
}
 