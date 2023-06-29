package com.application.FrontToBack.bookBoard.service;

import java.util.List;
import java.util.Map;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.dto.BookReplyDTO;






public interface BookBoardSerive {
	
	public int getAllBookBoardCnt(Map<String, String> searchCntMap) throws Exception;
	public List<BookDTO> getBookBoardList(Map<String,Object> searchMap) throws Exception;
	public void insertBookBoard(BookDTO bookDTO) throws Exception;
	public BookDTO getBookBoardDetail(long boardId, boolean increaseRead) throws Exception;
	public void updateBookBoard(BookDTO bookDTO) throws Exception;
	public void removeBookBoard(BookDTO bookDTO) throws Exception;
	
	
	
	
	public int getAllBookReplyCnt(long boardId) throws Exception;
	public List<BookReplyDTO> getAllBookReplyList(long boardId) throws Exception;
	public void bookAddReply(BookReplyDTO bookReplyDTO) throws Exception;
	public BookReplyDTO bookReplyDetail(long replyId) throws Exception;
	public void bookUpdateReply(BookReplyDTO bookReplyDTO) throws Exception;
	public void removeBookReply(BookReplyDTO bookReplyDTO) throws Exception;
	
	public List<BookDTO> getMainBookBoard() throws Exception;
}
