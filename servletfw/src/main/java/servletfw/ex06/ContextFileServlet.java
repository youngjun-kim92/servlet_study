package servletfw.ex06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cFile")
public class ContextFileServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		ServletContext context = getServletContext();
		//InputStream: 파일 읽어오는것 / OutStream : 파일 저장
		InputStream inputS = context.getResourceAsStream("/WEB-INF/bin/init.txt");
		//파일은 buffer을 이용하여 하나씩 담음
		BufferedReader buffer = new BufferedReader(new InputStreamReader(inputS));
		//못읽어올 수 있으니까 초기값 null로 설정
		String menu = null, menu_member = null, menu_order=null, menu_goods = null;
		//얼마나 있는지 모르니까 while문으로
		//readLine(): 한줄을 읽어오는 메소드
		while((menu=buffer.readLine()) != null ) {
			//menu를 ","로 구분해서 tokenizer에 넣음 
			StringTokenizer tokenizer = new StringTokenizer(menu, ",");
			menu_member = tokenizer.nextToken();
			menu_order = tokenizer.nextToken();
			menu_goods = tokenizer.nextToken();
		}
		out.print("<html><body>");
		out.print("<p>" + menu_member + "</p>");
		out.print("<p>" + menu_order + "</p>");
		out.print("<p>" + menu_goods + "</p>");
		out.print("</body></html>");
		
	}
}
