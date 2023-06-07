package servletApi.ex05;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class LoginImpl implements HttpSessionListener {
	String user_id;
	String user_pwd;
	static int total_user=0;
    
    public LoginImpl() {
       
    }
    public LoginImpl(String user_id, String user_pwd) {
    	this.user_id=user_id;
        this.user_pwd=user_pwd;
    }

	//세션 생성시 처리되는 메소드
    public void sessionCreated(HttpSessionEvent se)  { 
        System.out.println("세션이 생성됨 ->" + user_id+"님이 접속");
    }

	//세션 소멸시 처리되는 메소드
    public void sessionDestroyed(HttpSessionEvent se)  { 
        
    }
	
}
