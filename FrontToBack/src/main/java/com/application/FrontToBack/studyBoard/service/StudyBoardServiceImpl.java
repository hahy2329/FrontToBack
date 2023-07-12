package com.application.FrontToBack.studyBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.studyBoard.dao.StudyBoardDAO;
import com.application.FrontToBack.studyBoard.dto.StudyDTO;
import com.application.FrontToBack.studyBoard.dto.StudyReplyDTO;

@Service
public class StudyBoardServiceImpl implements StudyBoardService {
	
	@Autowired
	private StudyBoardDAO studyBoardDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public int getAllStudyBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return studyBoardDAO.selectOneAllStudyBoardCnt(searchCntMap);
	}


	@Override
	public List<StudyDTO> getStudyBoardList(Map<String, Object> searchMap) throws Exception {
		return studyBoardDAO.selectStudyListBoard(searchMap);
	}


	@Override
	public void insertStudyBoard(StudyDTO studyDTO) throws Exception {
		studyDTO.setPasswd(bCryptPasswordEncoder.encode(studyDTO.getPasswd()));
		
		studyBoardDAO.insertStudyBoard(studyDTO);
		
	}


	@Override
	public StudyDTO getStudyBoardDetail(long boardId, boolean increaseRead) throws Exception {
		
		if(increaseRead) {
			studyBoardDAO.updateStudyReadCnt(boardId);
		}
			
		StudyDTO studyDTO = studyBoardDAO.getStudyBoardDetail(boardId);
		
		
		return studyDTO;
	}


	
	
	
	
	
	@Override
	public int getAllStudyReplyCnt(long boardId) throws Exception {
		return studyBoardDAO.selectOneAllStudyReplyCnt(boardId);
	}


	@Override
	public List<StudyReplyDTO> getAllStudyReplyList(long boardId) throws Exception {
		List<StudyReplyDTO> studyReplyList = studyBoardDAO.selectListStudyReply(boardId);
		return studyReplyList;
	}


	@Override
	public void updateStudyBoard(StudyDTO studyDTO) throws Exception {
		studyBoardDAO.updateStudyBoard(studyDTO);
		
	}


	@Override
	public void removeStudyBoard(StudyDTO studyDTO) throws Exception {
		studyBoardDAO.removeStudyBoard(studyDTO);
		
	}


	@Override
	public void studyAddReply(StudyReplyDTO studyReplyDTO) throws Exception {
		studyReplyDTO.setPasswd(bCryptPasswordEncoder.encode(studyReplyDTO.getPasswd()));
		studyBoardDAO.studyAddReply(studyReplyDTO);
		
	}


	@Override
	public StudyReplyDTO studyReplyDetail(long replyId) throws Exception {
		StudyReplyDTO studyReplyDTO = studyBoardDAO.studyReplyDetail(replyId);
		return studyReplyDTO;
	}


	@Override
	public void studyUpdateReply(StudyReplyDTO studyReplyDTO) throws Exception {
		
		studyBoardDAO.studyUpdateReply(studyReplyDTO);
	}


	@Override
	public void removeStudyReply(StudyReplyDTO studyReplyDTO) throws Exception {
		studyBoardDAO.removeStudyReply(studyReplyDTO);
		
	}


	@Override
	public List<StudyDTO> getMainStudyBoard() throws Exception {
		List<StudyDTO> studyDTO = studyBoardDAO.getMainStudyBoard();
		return studyDTO;
	}


	@Override
	public StudyDTO getPopularBoard() throws Exception {
		return studyBoardDAO.getPopularBoard();
	}


}
