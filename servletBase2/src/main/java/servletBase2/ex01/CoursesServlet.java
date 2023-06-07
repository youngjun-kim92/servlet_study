package servletBase2.ex01;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clogin")
public class CoursesServlet extends HttpServlet {
		
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy메서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id= request.getParameter("user_id");
		String name= request.getParameter("user_name");
		System.out.println("신청 아이디 : " + id);
		System.out.println("이름 : " + name);
		String[] subject = request.getParameterValues("subject");
		if(subject == null) {
			System.out.println("신청한 과목이 없습니다.");
		} else {
			for(int i=0; i<subject.length; i++) {
				System.out.println("선택한 과목" + (i+1) + " : " + subject[i]);
			}
		}
	}

}
