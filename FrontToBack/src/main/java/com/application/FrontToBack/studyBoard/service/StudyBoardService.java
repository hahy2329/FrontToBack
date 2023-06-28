package com.application.FrontToBack.studyBoard.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.dto.StudyReplyDTO;

public interface StudyBoardService {
	
	
	public int getAllStudyBoardCnt(Map<String,String> searchCntMap) throws Exception;
	public List<StudyDTO> getStudyBoardList(Map<String,Object> searchMap) throws Exception;
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception;
	public StudyDTO getStudyBoardDetail(long boardId, boolean increaseRead) throws Exception;
	public void updateStudyBoard(StudyDTO studyDTO) throws Exception;
	public void removeStudyBoard(StudyDTO studyDTO) throws Exception;
	
	
	
	
	public int getAllStudyReplyCnt(long boardId) throws Exception;
	public List<StudyReplyDTO> getAllStudyReplyList(long boardId) throws Exception;
	public void studyAddReply(StudyReplyDTO studyReplyDTO) throws Exception;
	public StudyReplyDTO studyReplyDetail(long replyId) throws Exception;
	public void studyUpdateReply(StudyReplyDTO studyReplyDTO) throws Exception;
	public void removeStudyReply(StudyReplyDTO studyReplyDTO) throws Exception;
	
	
	
	public List<StudyDTO> getMainStudyBoard() throws Exception;
}
