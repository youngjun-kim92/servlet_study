package servletfw.ex05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/initMenu")
public class ContextParamServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
		//getInitParameter()의 ""안에 param-name을 써주면 param-value값을 가져와서 String menu_member변수에 넣어줌
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		out.print("<html><body>");
		out.print("<p>" + menu_member + "</p>");
		out.print("<p>" + menu_order + "</p>");
		out.print("<p>" + menu_goods + "</p>");
		out.print("</body></html>");
	}

}
