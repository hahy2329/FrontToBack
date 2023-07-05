package com.application.FrontToBack.noticeBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.noticeBoard.dto.NoticeDTO;
import com.application.FrontToBack.noticeBoard.dto.NoticeReplyDTO;

@Repository
public class NoticeBoardDAOImpl implements NoticeBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectOneAllBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("notice.selectOnaAllBoardCnt", searchCntMap);
	}

	@Override
	public List<NoticeDTO> selectListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("notice.selectListBoard", searchMap);
	}

	@Override
	public void updateReadCnt(long boardId) throws Exception {
		sqlSession.update("notice.updateNoticeReadCnt",boardId);
		
	}

	@Override
	public NoticeDTO getNoticeBoardDetail(long boardId) throws Exception {
		return sqlSession.selectOne("notice.getNoticeBoardDetail", boardId);
	}

	@Override
	public int selectOneAllNoticeReplyCnt(long boardId) throws Exception {
		return sqlSession.selectOne("notice.selectOneAllNoticeReplyCnt",boardId);
	}

	@Override
	public List<NoticeReplyDTO> selectListNoticeReply(long boardId) throws Exception {
		return sqlSession.selectList("notice.selectListNoticeReply",boardId);
	}

	@Override
	public void noticeAddReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		sqlSession.insert("notice.insertNoticeReply", noticeReplyDTO);
		
	}

	@Override
	public NoticeReplyDTO noticeReplyDetail(long replyId) throws Exception {
		return sqlSession.selectOne("notice.noticeReplyDetail", replyId);
	}

	@Override
	public void noticeUpdateReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		sqlSession.update("notice.updateNoticeReply", noticeReplyDTO);
		
	}

	@Override
	public void removeNoticeReply(NoticeReplyDTO noticeReplyDTO) throws Exception {
		sqlSession.delete("notice.removeNoticeReply", noticeReplyDTO);
		
	}

	@Override
	public List<NoticeDTO> getMainNoticeBoard() throws Exception {
		return sqlSession.selectList("notice.getMainNoticeBoard");
	}

}
