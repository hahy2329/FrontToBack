package com.application.FrontToBack.studyBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.dto.StudyReplyDTO;

@Repository
public class StudyBoardImpl implements StudyBoardDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectOneAllStudyBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("study.selectOneAllStudyBoardCnt", searchCntMap);
	}

	@Override
	public List<StudyDTO> selectStudyListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("study.selectListStudyBoard", searchMap);
	}

	@Override
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception {
		sqlSession.insert("study.insertStudyBoard", studyDTO);
		
	}

	@Override
	public void updateStudyReadCnt(long boardId) throws Exception {
		sqlSession.update("study.updateStudyReadCnt", boardId);
		
	}

	@Override
	public StudyDTO getStudyBoardDetail(long boardId) throws Exception {
		StudyDTO studyDTO = sqlSession.selectOne("study.getStudyBoardDetail", boardId);
		return studyDTO;
	}

	@Override
	public int selectOneAllStudyReplyCnt(long boardId) throws Exception {
		return sqlSession.selectOne("study.selectOneAllStudyReplyCnt", boardId);
	}

	@Override
	public List<StudyReplyDTO> selectListStudyReply(long boardId) throws Exception {
		return sqlSession.selectList("study.selectListStudyReply", boardId);
	}

	@Override
	public void updateStudyBoard(StudyDTO studyDTO) throws Exception {
		sqlSession.update("study.updateStudyBoard", studyDTO);
		
	}

	@Override
	public void removeStudyBoard(StudyDTO studyDTO) throws Exception {
		sqlSession.delete("study.removeStudyBoard", studyDTO);
		
	}

	@Override
	public void studyAddReply(StudyReplyDTO studyReplyDTO) throws Exception {
		sqlSession.insert("study.insertStudyReply",studyReplyDTO);
	}

	@Override
	public StudyReplyDTO studyReplyDetail(long replyId) throws Exception {
		return sqlSession.selectOne("study.studyReplyDetail", replyId);
	}

	@Override
	public void studyUpdateReply(StudyReplyDTO studyReplyDTO) throws Exception {
		sqlSession.update("study.updateStudyReply", studyReplyDTO);
		
	}

	@Override
	public void removeStudyReply(StudyReplyDTO studyReplyDTO) throws Exception {
		sqlSession.delete("study.removeStudyReply", studyReplyDTO);
		
	}

	@Override
	public List<StudyDTO> getMainStudyBoard() throws Exception {
		return sqlSession.selectList("study.getMainStudyBoard");
	}

	
}
