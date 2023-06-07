package servletlink.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logindb")
public class LoginServletdb extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		MemberVO memVo = new MemberVO();
		memVo.setId(user_id);
		memVo.setPwd(user_pw);
		MemberDAO dao = new MemberDAO();
		//memVo 에는 id와 pw값 등이 세팅되어있음
		//isExisted()를 만들어서 회원정보(id, pw)가 있으면 true, 없으면 false를 리턴하는 메소드
		Boolean result = dao.isExisted(memVo);
		out.print("<html><body>");
		if(result) {	//회원정보가 있을 때			
			//세션 만들기
			HttpSession session = request.getSession();
			//매개변수 isLogon, /true 대신 result 를 넣어줘도 됨(result에 true가 들어있기에) 
			session.setAttribute("isLogon", true);
			session.setAttribute("log_id", user_id);
			out.print("<p>안녕하세요. " + user_id + "님이 로그인 하셨습니다.</p>");
			out.print("<a href = 'show'>회원정보보기</a>"); //같은 곳에 있어서 가능한 것
		}else {		//id, pw가 없을 때
			out.print("<p>회원 정보가 틀립니다.</p>");
			out.print("<a href='logindb.html'>다시 로그인하기</a>");			
		}
		out.print("</body></html>");
	}
}
