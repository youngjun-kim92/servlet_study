package servletAPI.ex03;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

//@WebFilter("/*")
public class EncoderFilter extends HttpFilter implements Filter {
    ServletContext context;
    
    public EncoderFilter() {
        super();
       
    }

	public void destroy() {
		
	}
	
	//실제 필터 기능을 구현하는 메소드
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter 메서드 수행");
		//수행할 작업
		request.setCharacterEncoding("utf-8"); 
		//다음으로 넘기는 작업
		chain.doFilter(request, response); 		
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("utf-8 인코딩 작업을 필터에서 수행");
		context = fConfig.getServletContext();
	}

}
