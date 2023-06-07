package servletlink.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login4")
public class SessionLogin2 extends HttpServlet {
	
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
		//최초 접속 시 세션 생성,  재접속시 값 가져옴
		HttpSession session = request.getSession();
		//login2에서 입력한 id, pw가 왼쪽의 변수에 들어감 
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		//맨 처음 접속시 조건에 맞으므로 if안쪽의 명령어 수행됨 
		if(session.isNew()) { //
			if(user_id != null) { // id에 값이 있는 경우
				//session 의 id를 바인딩해서 set 함
				session.setAttribute("user_id", user_id); //session에 세팅되는 값
//				out.print("<a href='login4'>로그인 상태 확인</a>");
			//>>>>>이 부분 추가됨 <<<<<<
				//세션에 직접 접근해서 로그인 상태 유무를 파악하게 하는 것
				String url = response.encodeURL("login4");				
				out.print("<a href=" + url +">로그인 상태 확인</a>");				
			}else { //id가 null일 경우 이쪽
				out.print("<a href='login4.html'>다시 로그인 하기</a>");
				session.invalidate(); //혹시 다른 정보가 있을 기존 세션 삭제
			}
		} else {//재접속 시 이쪽으로 넘어옴
			//session은 객체이므로 String으로 캐스팅
			user_id = (String)session.getAttribute("user_id");
			if(user_id != null && user_id.length() != 0) {
				out.print("안녕하세요 " + user_id + "님의 방문을 환영합니다");				
			} else { //30분이 지나서 세션이 소멸되었던지 해서 값이 없을 경우 이쪽으로
				out.print("<a href='login4.html'>다시 로그인 하기</a>");
				session.invalidate(); //혹시 다른 정보가 있을 기존 세션 삭제
			}
		}
	}

}
