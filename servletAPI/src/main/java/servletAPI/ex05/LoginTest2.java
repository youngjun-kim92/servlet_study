package servletApi.ex05;

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
import javax.servlet.http.HttpSession;

import servletApi.ex04.LoginImpl;


@WebServlet("/login2")
public class LoginTest2 extends HttpServlet {
	ServletContext context=null;
	List user_list=new ArrayList(); 	//로그인한 접속자 ID를 저장
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		context=getServletContext();
		HttpSession session=request.getSession();
		String user_id=request.getParameter("user_id");
		String user_pwd=request.getParameter("user_pwd");
		LoginImpl loginUser=new LoginImpl(user_id, user_pwd);
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
			user_list.add(user_id);
			context.setAttribute("user_list", user_list);
		}
		out.print("<html>");
		out.print("<body>");
		out.print("<p>아이디 : "+loginUser.user_id+"님이 접속</p>");
		out.print("<p>총 접속자수는 "+LoginImpl.total_user+"</p>");
		out.print("<p>현재 접속한 명단(아이디)</p>");
		List list=(ArrayList)context.getAttribute("user_list");
		for(int i=0;i<list.size();i++) {
			out.print("<p>"+list.get(i)+"</p>");
		}
		out.print("<a href='logout?user_id="+user_id+"'>로그아웃</a>");
		out.print("</body>");
		out.print("</html>");
		
	}

}
