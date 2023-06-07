package servletlink.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");;
		PrintWriter out = response.getWriter();
		String id = "";
		Boolean isLogon = false;
		//기존 세션이 존재하면 그 정보 반환, 없으면 null 반환
		// => 테이블에 정보 없으면 로그인 안되게 하는 것
		HttpSession session = request.getSession(false);
		if(session != null) {
			//로그인 하고 /show 들어온 것
			isLogon = (Boolean)session.getAttribute("isLogon"); //true가 들어감
			if(isLogon == true) {
				id = (String)session.getAttribute("log_id");
				out.print("<html><body>");
				out.print("<p>" + id + "님은 골드회원이라 할인권이 있습니다.</p>");
				out.print("<p>할인권을 이용하여 쇼핑을 즐겨보세요.</p>");
				out.print("</body></html>");
			}else {
				//세션이 만료됐을 때
				//Redirect방법으로 sendRedirect()을 이용하여 포워딩
				response.sendRedirect("logindb.html");
				}
		} else {  //session이 null인것
			//로그인 없이 /show 들어온 것
			out.print("<script>alert('로그인 한 후에 이용해 주세요'); location.href='logindb.html';</script>");
			
		}
	}
}
