package servletfw.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firstb1")
public class FirstServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setAttribute("name", "홍길동");
		request.setAttribute("age", "50");
		request.setAttribute("address", "서울시 종로구");
		RequestDispatcher dispatcher = request.getRequestDispatcher("secondb1");
		dispatcher.forward(request, response);		
	}

}
