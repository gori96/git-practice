package com.ezen.board.controller.action.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.dto.ReplyDto;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("num");
		
		BoardDao bdao = BoardDao.getInstance();
		//BoardDto bdto = bdao.selectBoardOne(num);
		// 조회수 증가와 게시물 조회를 하나의 메서드에서 실행. -> 권장하지 않음 (원치않을때에도 조회수 1 증가)
		
		// 1. 해당 게시물의 조회수를 1 증가시킵니다. 메서드 이름 readCountPlusOne
		bdao.readCountPlusOne(num);
		// 2. num 값에 해당하는 게시물을 조회해서 리턴받습니다. 메서드 이름 getBoard
		BoardDto bdto = bdao.getBoard(num);
		
		ArrayList<ReplyDto> list  = bdao.selectReply(num);
		
		request.setAttribute("board", bdto);
		request.setAttribute("replyList", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("board/boardView.jsp");
		rd.forward(request, response);
	
		

	}

}
