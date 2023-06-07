package servletAPI.ex05;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LoginImpl implements HttpSessionListener {
	String user_id;
	String user_pwd;
	static int total_user = 0;
    
	//생성자
    public LoginImpl() {	}
    public LoginImpl(String user_id, String user_pwd) {
    	this.user_id = user_id;
    	this.user_pwd = user_pwd;  	  
    }
    
    // 세션 생성시 수행하는 메서드
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("세션이 생성됨 =>" + user_id + "님이 접속");
    	++total_user;
    }
    //세션 소멸시 수행하는 메서드
    public void sessionDestroyed(HttpSessionEvent se)  {
    	System.out.println("세션이 소멸됨 =>" + user_id + "님이 접속종료");
    	--total_user;
    }
	
}