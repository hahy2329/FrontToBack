package com.application.FrontToBack.studyBoard.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.dto.StudyReplyDTO;

public interface StudyBoardDAO {
	
	
	public int selectOneAllStudyBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<StudyDTO> selectStudyListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception;
	public void updateStudyReadCnt(long boardId) throws Exception;
	public StudyDTO getStudyBoardDetail(long boardId) throws Exception;
	public void updateStudyBoard(StudyDTO studyDTO) throws Exception;
	public void removeStudyBoard(StudyDTO studyDTO) throws Exception;
	
	
	
	
	public int selectOneAllStudyReplyCnt(long boardId) throws Exception;
	public List<StudyReplyDTO> selectListStudyReply(long boardId) throws Exception;
	public void studyAddReply(StudyReplyDTO studyReplyDTO) throws Exception;
	public StudyReplyDTO studyReplyDetail(long replyId) throws Exception;
	public void studyUpdateReply(StudyReplyDTO studyReplyDTO) throws Exception;
	public void removeStudyReply(StudyReplyDTO studyReplyDTO) throws Exception;
	
	
	public List<StudyDTO> getMainStudyBoard() throws Exception;
	
}
