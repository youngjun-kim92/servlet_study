package servletfw.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		List memberList=(List)request.getAttribute("memberList");
		out.print("<html>");
		out.print("<body>");
		out.print("<h2 align='center'>회원정보목록</h2>");
		out.print("<table align='center' border='1' width='800'>");
		out.print("<tr align='center' bgcolor='lightgreen'>");
		out.print("<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th>");
		out.print("</tr>");
		for(int i=0;i<memberList.size();i++){
			MemberVO memVo=(MemberVO)memberList.get(i);
			String id=memVo.getId();
			String pwd=memVo.getPwd();
			String name=memVo.getName();
			String email=memVo.getEmail();
			Date joinDate=memVo.getJoinDate();
			out.print("<tr>");
			out.print("<td>"+id+"</td>");
			out.print("<td>"+pwd+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+email+"</td>");
			out.print("<td>"+joinDate+"</td>");
			out.print("<td><a href='#'>삭제</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<p align='center'><a href='#'>새 회원 등록하기</a></p>");
		out.print("</body>");
		out.print("</html>");
	}

}
