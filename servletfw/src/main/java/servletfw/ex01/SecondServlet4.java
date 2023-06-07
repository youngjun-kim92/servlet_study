package servletfw.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/second4")
public class SecondServlet4 extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		int age=Integer.parseInt(request.getParameter("age"));
		out.println("<html><body>");
		out.println("<p>"+name+"님 환영합니다.</p>");
		if(age>=20) {
			out.print("<p>구입할 수 있는 나이입니다.</p>");
		}
		else {
			out.print("<p>"+age+"세는 구입할 수 없는 나이입니다.</p>");
		}
		out.println("<p>dispatch를 이용한 포워딩 실습입니다.</p>");
		out.println("</body></html>");
	}

}
