package servletfw.ex07;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
		urlPatterns = { 
				"/initp1", 
				"/initp2"
		}, 
		initParams = { 
				@WebInitParam(name = "email", value = "admin@gmail.com"), 
				@WebInitParam(name = "tel", value = "010-7777-7777")
		})
public class InitParamServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//서블릿 초기화 파라메타, email 값을 String변수 email에 넣음 
		String email = getInitParameter("email");
		String tel = getInitParameter("tel");
		out.print("<html><body>");
		out.print("<p>관리자 이메일 : " + email + "</p>");
		out.print("<p>관리자 전화번호 : " + tel + "</p>");
		out.print("</body></html>");
	}

}
