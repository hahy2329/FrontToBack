package com.application.FrontToBack.bookBoard.dao;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.dto.BookReplyDTO;
import com.application.FrontToBack.qnaBoard.dto.QnaDTO;







public interface BookBoardDAO {
	
	public int selectOneAllBookBoardCnt(Map<String,String> searchCntMap) throws Exception;
	public List<BookDTO> selectBookListBoard(Map<String, Object> searchMap) throws Exception;
	public void insertBookBoard(BookDTO bookDTO) throws Exception;
	public void updateBookReadCnt(long boardId) throws Exception;
	public BookDTO getBookBoardDetail(long boardId) throws Exception;
	public void updateBookBoard(BookDTO bookDTO) throws Exception;
	public void removeBookBoard(BookDTO bookDTO) throws Exception;
	
	
	
	public int selectOneAllBookReplyCnt(long boardId) throws Exception;
	public List<BookReplyDTO> selectListBookReply(long boardId) throws Exception;
	public void bookAddReply(BookReplyDTO bookReplyDTO) throws Exception;
	public BookReplyDTO bookReplyDetail(long replyId) throws Exception;
	public void bookUpdateReply(BookReplyDTO bookReplyDTO) throws Exception;
	public void removeBookReply(BookReplyDTO bookReplyDTO) throws Exception;
	
	public List<BookDTO> getMainBookBoard() throws Exception;
	
}
