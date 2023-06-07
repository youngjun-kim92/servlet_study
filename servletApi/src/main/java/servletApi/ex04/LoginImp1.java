package servletApi.ex04;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImp1 implements HttpSessionBindingListener{
	String user_id;
	String user_pwd;
	static int total_user=0;
	
	public LoginImp1() {
	
	}
	
	public LoginImp1(String user_id, String user_pwd) {
		this.user_id=user_id;
		this.user_pwd=user_pwd;
	}
	
	//세션에 저장시 접속자 수를 증가시킴
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("사용자가 접속하였습니다.");
		++total_user;
	}
	
	//세션에 소멸시 접속자 수를 감소시킴
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("사용자가 접속을 해제 하였습니다.");
		total_user--;
	}
	
	
}
