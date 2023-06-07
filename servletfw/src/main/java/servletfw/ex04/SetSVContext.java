package servletfw.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/setC")
public class SetSVContext extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//ServletContext 객체를 가져옴 
		ServletContext context = getServletContext();
		List member = new ArrayList<>();
		member.add("홍길순");
		member.add(60);
		context.setAttribute("member", member);
		out.print("<html><body>");
		out.print("<p>이름과 나이를 설정함</p>");
		out.print("</body></html>");
	}

}
