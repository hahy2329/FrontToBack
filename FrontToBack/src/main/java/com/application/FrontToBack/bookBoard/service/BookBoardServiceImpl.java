package com.application.FrontToBack.bookBoard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.FrontToBack.bookBoard.dao.BookBoardDAO;
import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.dto.BookReplyDTO;


@Service
public class BookBoardServiceImpl implements BookBoardSerive {
	
	@Autowired
	private BookBoardDAO bookBoardDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	
	@Override
	public int getAllBookBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return bookBoardDAO.selectOneAllBookBoardCnt(searchCntMap);
	}



	@Override
	public List<BookDTO> getBookBoardList(Map<String, Object> searchMap) throws Exception {
		return bookBoardDAO.selectBookListBoard(searchMap);
	}



	@Override
	public void insertBookBoard(BookDTO bookDTO) throws Exception {
		bookDTO.setPasswd(bCryptPasswordEncoder.encode(bookDTO.getPasswd()));
		
		bookBoardDAO.insertBookBoard(bookDTO);
		
	}
	
	
	
	@Override
	public BookDTO getBookBoardDetail(long boardId, boolean increaseRead) throws Exception {
		if(increaseRead) {
			bookBoardDAO.updateBookReadCnt(boardId);
		}
			
		BookDTO bookDTO = bookBoardDAO.getBookBoardDetail(boardId);
		
		
		return bookDTO;
	}



	@Override
	public int getAllBookReplyCnt(long boardId) throws Exception {
		return bookBoardDAO.selectOneAllBookReplyCnt(boardId);
	}



	@Override
	public List<BookReplyDTO> getAllBookReplyList(long boardId) throws Exception {
		List<BookReplyDTO> bookReplyList = bookBoardDAO.selectListBookReply(boardId);
		return bookReplyList;
	}



	@Override
	public void updateBookBoard(BookDTO bookDTO) throws Exception {
		bookBoardDAO.updateBookBoard(bookDTO);
		
	}



	@Override
	public void removeBookBoard(BookDTO bookDTO) throws Exception {
		bookBoardDAO.removeBookBoard(bookDTO);
		
	}



	@Override
	public void bookAddReply(BookReplyDTO bookReplyDTO) throws Exception {
		bookReplyDTO.setPasswd(bCryptPasswordEncoder.encode(bookReplyDTO.getPasswd()));
		bookBoardDAO.bookAddReply(bookReplyDTO);
		
	}



	@Override
	public BookReplyDTO bookReplyDetail(long replyId) throws Exception {
		BookReplyDTO bookReplyDTO = bookBoardDAO.bookReplyDetail(replyId);
		return bookReplyDTO;
	}



	@Override
	public void bookUpdateReply(BookReplyDTO bookReplyDTO) throws Exception {
		bookBoardDAO.bookUpdateReply(bookReplyDTO);
		
	}



	@Override
	public void removeBookReply(BookReplyDTO bookReplyDTO) throws Exception {
		bookBoardDAO.removeBookReply(bookReplyDTO);
		
	}



	@Override
	public List<BookDTO> getMainBookBoard() throws Exception {
		return bookBoardDAO.getMainBookBoard();
	}



	@Override
	public BookDTO getPopularBoard() throws Exception {
		return bookBoardDAO.getPopularBoard();
	}

}
