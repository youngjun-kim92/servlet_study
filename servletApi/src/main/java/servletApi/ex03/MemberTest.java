package servletApi.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member")
public class MemberTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String id=request.getParameter("user_id");
		String pwd=request.getParameter("user_pwd");
		String name=request.getParameter("user_name");
		String address=request.getParameter("user_address");
		out.print("<html><body>");
		out.print("<h2>회원 가입 정보</h2>");
		out.print("<p>아이디 : "+id+"</p>");
		out.print("<p>비밀번호 : "+pwd+"</p>");
		out.print("<p>이름 : "+name+"</p>");
		out.print("<p>주소 : "+address+"</p>");
		out.print("</body></html>");
	}

}
