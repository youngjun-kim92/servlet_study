package servletBase.ex01;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet메서드 호출됨");
	}

	@Override
	public void destroy() {
		System.out.println("destory메서드 호출됨");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("init메서드 호출됨");
	}

}
