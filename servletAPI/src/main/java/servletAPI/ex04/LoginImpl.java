package servletApi.ex04;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImpl implements HttpSessionBindingListener {
	public String user_id;
	public String user_pwd;
	public static int total_user=0;
	
	//생성자
	public LoginImpl() {
		
	}
	public LoginImpl(String user_id, String user_pwd) {
		this.user_id = user_id;
		this.user_pwd = user_pwd;
	}
	
	//세션에 저장시 접속자수를 증가시킴
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println(user_id + "님이 접속하였습니다.");
		++total_user;		
	}
	//세션에서 소멸시 접속자수를 감소시킴
	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println(user_id + "님이 접속을 해제하였습니다.");
		total_user--;
	}
	//다른 연산자와 같이 있을 때 ++ 가 앞에 있거나 뒤에 있거나 큰 차이가 있지만, 단독으로 쓰일 때는 순서에 상관 없음

}
