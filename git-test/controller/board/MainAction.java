package com.ezen.board.controller.action.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.board.controller.action.Action;
import com.ezen.board.dao.BoardDao;
import com.ezen.board.dto.BoardDto;
import com.ezen.board.util.Paging;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		BoardDao bdao = BoardDao.getInstance();
		
		
		// ���� �Խ����� ó�� ���������� �ε��Ѵٸ�, ���������� ǥ�õ� ������������ 1������ �Դϴ�
		int page = 1;
		
		HttpSession session = request.getSession();
		// ���� �Խ����� ó�� ���������� �ε� �ϴ°� �ƴ϶�, 2, 3, 4... �� ���� �ٸ� �������� Ŭ���ؼ� ����Ŭ������
		// execute �� ����ȴٸ�, �Ķ���ͷ� ���޵� ���� ���� ǥ���� �������� �˴ϴ�.
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt( request.getParameter("page"));
			session.setAttribute("page", page);     // ���� ������ ������� ���ư��� ���� �� ����
		}else if(session.getAttribute("page")!=null) {
			page = (Integer)session.getAttribute("page");
		}else {
			session.removeAttribute("page");
		}
		
		// �׷��� ������ ���������� ��ȣ�� Paging ��ü�� ����� ��������� �����մϴ�
		Paging paging = new Paging();
		paging.setPage(page);
		
		
		int count = bdao.getAllCount();
		
		
		paging.setTotalCount(count);
		
		ArrayList<BoardDto> list = bdao.selectAll(paging);
		
		
		
		for(BoardDto bdto : list) {
			int cnt = bdao.getReplyCnt(bdto.getNum());
			bdto.setReplycnt(cnt);
		}
		
		
		request.setAttribute("bList", list);
		request.setAttribute("paging", paging);
		
		RequestDispatcher rd = request.getRequestDispatcher("board/main.jsp");
		rd.forward(request, response);

	}

}











