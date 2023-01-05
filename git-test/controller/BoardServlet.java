package com.ezen.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.board.controller.action.Action;

/**
 * Servlet implementation class BoardServlet
 */
@WebServlet("/board.do")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String command = request.getParameter("command");
		
		ActionFactory af = ActionFactory.getInstance();
		Action ac = af.getAction(command);
		
		if(ac==null) System.out.println("ac�� null�Դϴ�. command �� Ȯ��~!");
		else ac.execute(request, response);
		
		// ���� ����Ʈ�� ��� ����� ��ɺ��� ���� Ŭ������ ��������� �׾��� ����޼���(execute)�� ��� �ֽ��ϴ�.
		// �� Ŭ�������� ��� Action �������̽��� implements �ϰ� ������, execute �޼���� �� �������̽���
		// �߻�޼��尡 �������̵� �� �޼����Դϴ�.
		
		// ActionFactory �� getAction �޼���� �������� command �� ���� �ش� Ŭ������ ��ü�� Action ���۷���������
		// �����Ͽ� �����մϴ�.
		// ���Ϲ��� ac�� execute�� ȣ���ؼ� �ش� ����� ����ǰ� �մϴ�.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}