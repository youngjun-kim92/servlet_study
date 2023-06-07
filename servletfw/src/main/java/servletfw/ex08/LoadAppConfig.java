package servletfw.ex08;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="loadConfig", urlPatterns= {"/loadConfig"}, loadOnStartup=1)
//loadOnStartup = 값으로 서버가 로딩될 때 수행될 우선순위를 줌
public class LoadAppConfig extends HttpServlet {
	private ServletContext context;
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init메서드 호출"); //수행됐는지 확인용
		//매개변수 config를 이용
		context = config.getServletContext();
		String menu_member = context.getInitParameter("menu_member");
		String menu_order = context.getInitParameter("menu_order");
		String menu_goods = context.getInitParameter("menu_goods");
		//setAttribute : 바인딩 하기 위해 자료를 세팅
		context.setAttribute("menu_member", menu_member); //뒤에 있는 변수값으로 ""안의 변수에 세팅
		context.setAttribute("menu_order", menu_order);
		context.setAttribute("menu_goods", menu_goods);		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String menu_member = (String)context.getAttribute("menu_member");
		String menu_order = (String)context.getAttribute("menu_order");
		String menu_goods = (String)context.getAttribute("menu_goods");
		out.print("<html><body>");
		out.print("<h2>메뉴이름</h2>");
		out.print("<p>" + menu_member + "</p>");
		out.print("<p>" + menu_order + "</p>");
		out.print("<p>" + menu_goods + "</p>");
		out.print("</body></html>");
	}

}

