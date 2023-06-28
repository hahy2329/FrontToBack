package com.application.FrontToBack.qnaBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.qnaBoard.dto.QnaDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaReplyDTO;

@Repository
public class QnaBoardDAOImpl implements QnaBoardDAO {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectOneAllQnaBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("qna.selectOneAllQnaBoardCnt", searchCntMap);
	}

	@Override
	public List<QnaDTO> selectQnaListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("qna.selectListQnaBoard", searchMap);
	}

	@Override
	public void insertQnaBoard(QnaDTO qnaDTO) throws Exception {
		sqlSession.insert("qna.insertQnaBoard", qnaDTO);
		
	}

	@Override
	public void updateQnaReadCnt(long boardId) throws Exception {
		sqlSession.update("qna.updateQnaReadCnt", boardId);
		
	}

	@Override
	public QnaDTO getQnaBoardDetail(long boardId) throws Exception {
		QnaDTO qnaDTO = sqlSession.selectOne("qna.getQnaBoardDetail", boardId);
		return qnaDTO;
	}

	@Override
	public int selectOneAllQnaReplyCnt(long boardId) throws Exception {
		return sqlSession.selectOne("qna.selectOneAllQnaReplyCnt", boardId);
	}

	@Override
	public List<QnaReplyDTO> selectListQnaReply(long boardId) throws Exception {
		return sqlSession.selectList("qna.selectListQnaReply", boardId);
	}

	@Override
	public void updateQnaBoard(QnaDTO qnaDTO) throws Exception {
		sqlSession.update("qna.updateQnaBoard", qnaDTO);
		
	}

	@Override
	public void removeQnaBoard(QnaDTO qnaDTO) throws Exception {
		sqlSession.delete("qna.removeQnaBoard", qnaDTO);
		
	}

	@Override
	public void qnaAddReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		sqlSession.insert("qna.insertQnaReply",qnaReplyDTO);
		
	}

	@Override
	public QnaReplyDTO qnaReplyDetail(long replyId) throws Exception {
		return sqlSession.selectOne("qna.qnaReplyDetail", replyId);
	}

	@Override
	public void qnaUpdateReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		sqlSession.update("qna.updateQnaReply", qnaReplyDTO);
		
	}

	@Override
	public void removeQnaReply(QnaReplyDTO qnaReplyDTO) throws Exception {
		sqlSession.delete("qna.removeQnaReply", qnaReplyDTO);
		
	}
	
	
	@Override
	public List<QnaDTO> getMainQnaBoard() throws Exception {
		return sqlSession.selectList("qna.getMainQnaBoard");
	}
	
}
