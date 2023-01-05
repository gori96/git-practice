package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;

public class DeleteBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("num");
		
		BoardDao bdao = BoardDao.getInstance();
		bdao.deleteBoard(num);
		
		String url = "board.do?command=main";
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}
