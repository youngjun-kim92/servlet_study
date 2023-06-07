package servletlink.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login2")
public class LoginServlet2 extends HttpServlet {
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String user_id=request.getParameter("user_id");
		String user_pw=request.getParameter("user_pw");
		String user_hp=request.getParameter("user_hp");
		String user_email=request.getParameter("user_email");
		String user_address=request.getParameter("user_address");
		String data="<html><body>";
		data+="로그인하였습니다.<br>";
		data+="아이디 : "+user_id+"<br>";
		data+="핸드폰 : "+user_hp+"<br>";
		data+="이메일 : "+user_email+"<br>";
		data+="주소 : "+user_address+"<br>";
		data+="<a href='/servletlink/sports?user_id="+user_id+"'>스포츠 중계보기</a>";
		data+="</body></html>";
		out.print(data);
	}
}
