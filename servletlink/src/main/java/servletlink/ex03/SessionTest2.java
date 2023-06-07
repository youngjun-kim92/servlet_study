package servletlink.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sess2")
public class SessionTest2 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//getSession() : 기존의 세션 객체가 존재하면 기존 세션을 리턴, 없으면 새로 생성
		HttpSession session = request.getSession();
		out.print("세션 아이디 : " + session.getId() + "<br>");
		//getCreationTime() : 세션 생성된 시간 
		out.print("최초 세션 생성 시간 : " + new Date(session.getCreationTime()) + "<br>");
		//getLastAccessedTime() : 마지막 접근 시간
		out.print("최근 세션 접근 시간 : " + new Date(session.getLastAccessedTime()) + "<br>");
		//getMaxInactiveInterval() : 세션 유효 시간(s단위)
		//세션 유효시간 변경, 초 단위로 줌
		session.setMaxInactiveInterval(10); //유효시간 : 10초
		out.print("세션 유효 시간 : " + session.getMaxInactiveInterval() + "<br>");
		if(session.isNew()) { //맨 처음 생성시에만 출력될 것
			out.print("새 세션이 만들어졌습니다.");
		}
			
	}

}
