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
		
		if(ac==null) System.out.println("ac가 null입니다. command 값 확인~!");
		else ac.execute(request, response);
		
		// 현재 사이트의 모든 기능은 기능별로 개별 클래스가 만들어져서 그안의 멤버메서드(execute)가 담고 있습니다.
		// 그 클래스들은 모두 Action 인터페이스를 implements 하고 있으며, execute 메서드는 이 인터페이스의
		// 추상메서드가 오버라이딩 된 메서드입니다.
		
		// ActionFactory 의 getAction 메서드는 전달해준 command 값 별로 해당 클래스의 객체를 Action 레퍼런스변수에
		// 저장하여 리턴합니다.
		// 리턴받은 ac는 execute를 호출해서 해당 기능이 실행되게 합니다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
