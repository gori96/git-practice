package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.ReplyDto;

public class AddReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardnum = Integer.parseInt(request.getParameter("boardnum"));
		String userid = request.getParameter("userid");
		String content = request.getParameter("content");
		
		ReplyDto rdto = new ReplyDto();
		
		rdto.setUserid(userid);
		rdto.setContent(content);
		rdto.setBoardnum(boardnum);

		BoardDao bdao = BoardDao.getInstance();
		bdao.insertReply(rdto);
		
		response.sendRedirect("board.do?command=boardViewWithoutCount&num=" + boardnum);
	}

}
