package com.ezen.board.controller.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UpdateBoardAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전달된 값들을 BoardDto 에 넣고 updateBoard 를 실행해서 BoardView.jsp 로 되돌아 가되,
		// board.do?command=boardViewWithoutCount 로 돌아가기위해 command 와 class 를 추가하세요
		
		BoardDto bdto = new BoardDto();
		BoardDao bdao = BoardDao.getInstance();
		
		/*
		bdto.setUserid(request.getParameter("userid"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setTitle(request.getParameter("title"));
		bdto.setContent(request.getParameter("content"));
		bdto.setNum( Integer.parseInt(request.getParameter("num")));
		*/
		
		// 멀티파트리퀘스트를 이용하여 수정 프로세스가 정상실행되게끔 코딩해주세요
		// 현재 코드와 Dao 의 updateBoard 메서드를 모두 수정합니다
		
		HttpSession session = request.getSession();
		ServletContext context = session.getServletContext();
		String path = context.getRealPath("upload");
		
		MultipartRequest multi = new MultipartRequest(
				request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
		);
		
		bdto.setUserid(multi.getParameter("userid"));
		bdto.setPass(multi.getParameter("pass"));
		bdto.setEmail(multi.getParameter("email"));
		bdto.setTitle(multi.getParameter("title"));
		bdto.setContent(multi.getParameter("content"));
		bdto.setNum( Integer.parseInt(multi.getParameter("num")));
		if(multi.getFilesystemName("newFile") == null)
			bdto.setImgfilename(multi.getParameter("oldFile"));
		else
			bdto.setImgfilename(multi.getFilesystemName("newFile"));
	
		
		bdao.updateBoard(bdto);
		
		String url = "board.do?command=boardViewWithoutCount&num=" + bdto.getNum();
		RequestDispatcher dp = request.getRequestDispatcher(url);
		dp.forward(request, response);
		
	
	}

}
