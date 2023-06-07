package servletApi.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginTest extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String user_id=request.getParameter("user_id");
		String user_pwd=request.getParameter("user_pwd");
		LoginImp1 loginUser=new LoginImp1(user_id, user_pwd);
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
		}
		out.print("<html>");
		out.print("<head>");
		out.print("<script>");
		out.print("setTimeout('history.go(0);',5000)");
		out.print("</script>");
		out.print("</head>");
		out.print("<body>");
		out.print("<p>아이디 : "+loginUser.user_id+"님이 접속</p>");
		out.print("<p>총 접속자수는 "+LoginImp1.total_user+"</p>");
		out.print("</body>");
		out.print("</html>");
	}

}
