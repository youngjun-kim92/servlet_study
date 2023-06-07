package servletBase2.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/gugu")
public class GuguDanServlet extends HttpServlet {

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
		String dan = request.getParameter("dan");		
		
		if( dan != null & dan.length() != 0 ) {
			int intdan = Integer.parseInt(dan);
				out.println("<html>");
				out.println("<body>");
				out.println("<p> ** "+ intdan + "단 **</p>");
				for(int i=1; i<10; i++) {
					out.println("<p>"+ intdan + " x " + i + " = " + (intdan * i) + "</p>");	
				}
				out.println("<a href='http://127.0.0.1:8090/servletBase2/gugudan/gugu.html'>출력할 단 입력창으로 이동</a>");
				out.println("</body>");
				out.println("</html>");			
		} else {
				out.println("<html>");
				out.println("<body>");
				out.println("단을 입력해주세요<br>");
				out.println("<a href='http://127.0.0.1:8090/servletBase2/gugudan/gugu.html'>출력할 단 입력창으로 이동</a>");
				out.println("</body>");
				out.println("</html>");
		}
	}

}
