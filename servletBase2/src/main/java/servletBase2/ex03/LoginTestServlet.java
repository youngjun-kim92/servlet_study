package servletBase2.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginTest")
public class LoginTestServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		if(user_id != null && user_id.length() !=0) {
			if(user_id.equals("admin")) {
				out.println("<html>");
				out.println("<body>");
				out.println("<p>관리자로 로그인 하셨습니다.</p>");
				out.println("<input type='button' value='상품정보 수정하기'>");
				out.println("<input type='button' value='상품정보 삭제하기'>");
				out.println("</body>");
				out.println("</html>");
			}else {
				out.println("<html>");
				out.println("<body>");
				out.println("<p>"+ user_id + "님 환영합니다!</p>");
				out.println("</body>");
				out.println("</html>");					
			}
		} else {
			out.println("<html>");
			out.println("<body>");
			out.println("<p>아이디를 입력하세요</p>");
			out.println("<a href='http://127.0.0.1:8090/servletBase2/memberInfo/loginTest.html'>로그인창으로 이동</a>");
			out.println("</body>");
			out.println("</html>");
		}
	}

}
