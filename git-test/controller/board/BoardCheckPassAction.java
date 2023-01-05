package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;

public class BoardCheckPassAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String num = request.getParameter("num");
		String pass = request.getParameter("pass");
		
		BoardDao bdao = BoardDao.getInstance();
		BoardDto bdto = bdao.getBoard(num);
		
		String url = null;
		if( bdto.getPass() == null ) { // 데이터베이스 오류
			request.setAttribute("message", "비밀번호오류 관리자에게 문의 하세요");
			url = "board/boardCheckPass.jsp";
		}else if(bdto.getPass().equals(pass)) { // 둘이 같다면, checkSuccess.jsp로 이동
			url = "board/checkSuccess.jsp";
		}else {
			request.setAttribute("message", "비밀번호가 틀렸습니다");
			url = "board/boardCheckPass.jsp";
		}
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);

	}

}
