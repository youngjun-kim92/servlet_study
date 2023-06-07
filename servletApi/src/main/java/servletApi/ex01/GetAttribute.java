package servletAPI.ex01;

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
		PrintWriter out = response.getWriter();
		
		//getServletContext() : 정보를 가져와서 반환하고, 없으면 생성
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(); //세션 정보가 있으면 반환, 없으면 생성
		
		//getAttribute는 객체를 받으므로 String으로 캐스팅
		String ctxGetMsg = (String)context.getAttribute("context");
		String sesGetMsg = (String)session.getAttribute("session");
		String reqGetMsg = (String)request.getAttribute("request");

		out.print("context값 : " + ctxGetMsg + "<br>");
		out.print("session값 : " + sesGetMsg + "<br>");
		out.print("request값 : " + reqGetMsg + "<br>");
	}

}
