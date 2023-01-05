package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;

public class UpdateBoardFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전달된 게시물 번호로 게시물을 조회하고 그 게시물을 request에 담아서 updateBoardForm.jsp 로 이동
		
		String num = request.getParameter("num");
		BoardDao bdao = BoardDao.getInstance();
		BoardDto bdto = bdao.getBoard(num);
		
		request.setAttribute("board", bdto);
		
		RequestDispatcher dp = request.getRequestDispatcher("board/updateBoardForm.jsp");
		dp.forward(request, response);
		
	}

}
