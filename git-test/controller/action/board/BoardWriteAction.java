package com.ezen.board.controller.action.board;

import java.io.IOException;

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

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 전달인수로 전달되는 내용을 BoardDto 객체에 담고,
		BoardDto bdto = new BoardDto();
		BoardDao bdao = BoardDao.getInstance();
		
		/*bdto.setUserid(request.getParameter("userid"));
		bdto.setPass(request.getParameter("pass"));
		bdto.setEmail(request.getParameter("email"));
		bdto.setTitle(request.getParameter("title"));
		bdto.setContent(request.getParameter("content"));
		*/
		
		// MultipartRequest 를 구성하고 객체를 이용해서 boardDto 를 채우고 insert 되도록 구성합니다
		// insertBoard 에서 imgfileName 저장도 추가합니다
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
		bdto.setImgfilename(multi.getFilesystemName("uploadFile"));
		
		
		// insertBoard 메서드를 이용해서 레코드를 추가하고
		bdao.insertBoard(bdto);
		
		// main.jsp 로 되돌아 갑니다.
		response.sendRedirect("board.do?command=main");
		
	}

}
