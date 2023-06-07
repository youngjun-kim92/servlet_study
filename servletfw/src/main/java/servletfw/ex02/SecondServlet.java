package servletfw.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/secondb1")
public class SecondServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String name = (String)request.getAttribute("name");
		String age = (String)request.getAttribute("age");
		String address = (String)request.getAttribute("address");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h2>회원정보</h2>");
		out.println("<p>이름: " + name + "</p>");
		out.println("<p>나이: " + age + "</p>");
		out.println("<p>주소: " + address + "</p>");
		out.println("</body></html>");
		
	}

}
