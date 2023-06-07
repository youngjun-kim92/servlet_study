package servletdb.ex04;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao=new MemberDAO();
		PrintWriter out=response.getWriter();
		String command=request.getParameter("command");
		if(command!=null&&command.equals("addMember")) {
			String _id=request.getParameter("id");
			String _pwd=request.getParameter("pwd");
			String _name=request.getParameter("name");
			String _email=request.getParameter("email");
			MemberVO vo=new MemberVO();
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);
			dao.addMember(vo);
		}
		else if(command!=null && command.equals("delMember")) {
			String id=request.getParameter("id");
			dao.delMember(id);
		}
		List<MemberVO> list=dao.listMembers();
		out.print("<html>");
		out.print("<body>");
		out.print("<h2 align='center'>회원정보목록</h2>");
		out.print("<table align='center' border='1' width='800'>");
		out.print("<tr align='center' bgcolor='lightgreen'>");
		out.print("<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th>");
		out.print("</tr>");
		for(int i=0;i<list.size();i++){
			String id=list.get(i).getId();
			String pwd=list.get(i).getPwd();
			String name=list.get(i).getName();
			String email=list.get(i).getEmail();
			Date joinDate=list.get(i).getJoinDate();
			out.print("<tr>");
			out.print("<td>"+id+"</td>");
			out.print("<td>"+pwd+"</td>");
			out.print("<td>"+name+"</td>");
			out.print("<td>"+email+"</td>");
			out.print("<td>"+joinDate+"</td>");
			out.print("<td><a href='/servletdb/member?command=delMember&id="+id+"'>삭제</a></td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<p align='center'><a href='/servletdb/memberForm.html'>새 회원 등록하기</a></p>");
		out.print("</body>");
		out.print("</html>");
	}

}
