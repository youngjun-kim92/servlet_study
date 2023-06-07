package servletBase2.ex01;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/clogin2")
public class CoursesServlet2 extends HttpServlet {
	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출");
	}

	public void destroy() {
		System.out.println("destroy메서드 호출");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//Enumeration 은 getParameterNames로 다양한 Name들을 한꺼번에 받을 수 있음
		Enumeration enu=request.getParameterNames();
		//몇개인지 모를때 사용하는 반복문 while
		// enu에 요소가 있는 동안 while실행
		while(enu.hasMoreElements()) {
			//enu.nextElement()는 객체이므로 String으로 형변환
			String name = (String)enu.nextElement();
			String[] values = request.getParameterValues(name);
			//크기를 알 수 없는 배열은 향상된 for문을 사용
			for(String val : values) {
				//name에 이름을 하나씩 넘겨받음
				// values에 있는 값을 val로 하나씩 넘겨받음
				System.out.println("매개변수이름(" + name + "): " + val);
			}
		}
		
	}
}
