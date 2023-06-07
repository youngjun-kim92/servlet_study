package servletApi.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/get")
public class GetAttribute extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		ServletContext context=getServletContext();
		HttpSession session=request.getSession();
		String ctxGetMsg=(String)context.getAttribute("context");
		String sesGetMsg=(String)context.getAttribute("session");
		String reqGetMsg=(String)context.getAttribute("request");
		out.print("context값 : " +ctxGetMsg+"<br>");
		out.print("session값 : " +sesGetMsg+"<br>");
		out.print("request값 : " +reqGetMsg+"<br>");
	}

}
