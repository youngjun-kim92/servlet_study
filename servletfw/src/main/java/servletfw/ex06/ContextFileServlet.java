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
		PrintWriter out=response.getWriter();
		ServletContext context=getServletContext();
		InputStream inputS=context.getResourceAsStream("/WEB-INF/bin/init.txt");			//저장된 파일을 읽어옴
		BufferedReader buffer=new BufferedReader(new InputStreamReader(inputS));
		String menu=null, menu_member=null, menu_order=null, menu_goods=null;
		while((menu=buffer.readLine())!=null) {
			StringTokenizer tokenizer=new StringTokenizer(menu,",");
			menu_member=tokenizer.nextToken();
			menu_order=tokenizer.nextToken();
			menu_goods=tokenizer.nextToken();
		}
		out.print("<html><body>");
		out.print("<p>"+menu_member+"</p>");
		out.print("<p>"+menu_order+"</p>");
		out.print("<p>"+menu_goods+"</p>");
		out.print("</body></html>");
	}
}
