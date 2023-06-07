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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
//	doGet, doPost 다 받을 수 있는 doHandle 생성
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //날아온 요청이 깨지지 않게 함
		//응답받을 타입 설정 text/html, 인코딩정보 입력
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();

		//변경된 부분>>>>>>>>>>>>>>>
		PrintWriter out = response.getWriter();
		String command=request.getParameter("command");
		if(command != null && command.equals("addMember")) { //input hidden 에서 넘어온 정보
			String _id = request.getParameter("id"); //내가 넣은 id 의 값을 _id에 넣음
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");
			//DB에 저장하기 위해 세팅
			MemberVO vo = new MemberVO();			
			vo.setId(_id);
			vo.setPwd(_pwd);
			vo.setName(_name);
			vo.setEmail(_email);
			dao.addMember(vo);
		}else if(command != null && command.equals("delMember")) {
			//삭제 알고리즘
			String id = request.getParameter("id");
			dao.delMember(id);
		}
	
		
		//listMembers()가 DB에서 회원목록을 가져옴 => list에 들어감
		List<MemberVO> list = dao.listMembers();
		
		//회원정보 뿌려줌		
		out.print("<html>");
		out.print("<body>");
		out.print("<h2 align='center'>회원정보목록</h2>");
		out.print("<table align='center' border='1' width='800'>");
		out.print("<tr align='center' bgcolor='lightblue'>");
		out.print("<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th><th>삭제</th>");
		out.print("</tr>");
		for(int i=0; i<list.size(); i++) {
			//id 얻어오기
			String id = list.get(i).getId();
			String pwd = list.get(i).getPwd();
			String name = list.get(i).getName();
			String email = list.get(i).getEmail();
			Date joinDate = list.get(i).getJoinDate();
			out.print("<tr>");
			out.print("<td>" + id + "</td>");
			out.print("<td>" + pwd + "</td>");
			out.print("<td>" + name + "</td>");
			out.print("<td>" + email + "</td>");
			out.print("<td>" + joinDate + "</td>");			
			out.print("<td><a href='/servletdb/member?command=delMember&id=" + id + "'>삭제</a></td>");
			out.print("</tr>");
		}		
		out.print("</table>");
		//새 회원 등록 폼 호출링크
		out.print("<p align='center'><a href='/servletdb/memberForm.html'>새 회원 등록하기</a></p>");
		out.print("</body>");
		out.print("</html>");

	}
}
