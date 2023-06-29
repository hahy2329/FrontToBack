package com.application.FrontToBack.bookBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.application.FrontToBack.bookBoard.dto.BookDTO;
import com.application.FrontToBack.bookBoard.dto.BookReplyDTO;


@Repository
public class BookBoardDAOImpl implements BookBoardDAO {
	
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int selectOneAllBookBoardCnt(Map<String, String> searchCntMap) throws Exception {
		return sqlSession.selectOne("book.selectOneAllBookBoardCnt", searchCntMap);
	}

	@Override
	public List<BookDTO> selectBookListBoard(Map<String, Object> searchMap) throws Exception {
		return sqlSession.selectList("book.selectListBookBoard", searchMap);
	}

	@Override
	public void insertBookBoard(BookDTO bookDTO) throws Exception {
		sqlSession.insert("book.insertBookBoard", bookDTO);
		
	}

	@Override
	public void updateBookReadCnt(long boardId) throws Exception {
		sqlSession.update("book.updateBookReadCnt", boardId);
		
	}

	@Override
	public BookDTO getBookBoardDetail(long boardId) throws Exception {
		BookDTO bookDTO = sqlSession.selectOne("book.getBookBoardDetail", boardId);
		return bookDTO;
	}

	@Override
	public int selectOneAllBookReplyCnt(long boardId) throws Exception {
		return sqlSession.selectOne("book.selectOneAllBookReplyCnt", boardId);
	}

	@Override
	public List<BookReplyDTO> selectListBookReply(long boardId) throws Exception {
		return sqlSession.selectList("book.selectListBookReply", boardId);
	}

	@Override
	public void updateBookBoard(BookDTO bookDTO) throws Exception {
		sqlSession.update("book.updateBookBoard", bookDTO);
		
	}

	@Override
	public void removeBookBoard(BookDTO bookDTO) throws Exception {
		sqlSession.delete("book.removeBookBoard", bookDTO);
		
	}

	@Override
	public void bookAddReply(BookReplyDTO bookReplyDTO) throws Exception {
		sqlSession.insert("book.insertBookReply",bookReplyDTO);
		
	}

	@Override
	public BookReplyDTO bookReplyDetail(long replyId) throws Exception {
		return sqlSession.selectOne("book.bookReplyDetail", replyId);
	}

	@Override
	public void bookUpdateReply(BookReplyDTO bookReplyDTO) throws Exception {
		sqlSession.update("book.updateBookReply", bookReplyDTO);
		
	}

	@Override
	public void removeBookReply(BookReplyDTO bookReplyDTO) throws Exception {
		sqlSession.delete("book.removeBookReply", bookReplyDTO);
		
	}

	@Override
	public List<BookDTO> getMainBookBoard() throws Exception {
		return sqlSession.selectList("book.getMainBookBoard");
	}
	
	
	
}
