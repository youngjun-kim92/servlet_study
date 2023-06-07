package servletlink.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/set")
public class SetCookie extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//java.util.Date
		Date today = new Date();
		//new Cookie("쿠키이름", "쿠키값"); 쿠키값이 쿠키이름에 저장됨
		//한글은 깨지므로 URLEncoder.encode()이용
		Cookie ck = new Cookie("cookieTest", URLEncoder.encode("주말 잘 보내세요^^", "utf-8"));
		//setMaxAge() : 쿠키에 유효시간 주는 것
		ck.setMaxAge(24*60*60); //하루의 유효시간
		
		//쿠키 생성 & 저장
		response.addCookie(ck);
		// <html><body> 없어도 문자가 넘어가긴 함. (JS, CSS 적용하려면 <html><body> 있어야 함)
		out.print("쿠키가 생성된 시간: " + today + "<br>");
		out.print("쿠키가 저장되었습니다.");		
		
		//쿠키값 가져오기
		
		
	}

}
