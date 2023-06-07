package servletdb.ex03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;			//Statement가 DBMS에 전달하는 SQL문은 단순한 문자열이므로 DBMS가 이해할 수 있도록 컴파일하고 실행합니다.
	private DataSource dataFactory;
	
	//생성자
	public MemberDAO() {
		try {
			//JNDI(Java Naming and Directory Interface)에 접근하기 위해 기본 경로(java:/comp/env)를 지정
			Context ctx=new InitialContext();
			Context envContext=(Context)ctx.lookup("java:/comp/env");
			//톰캣 context.xml에 설정한 name값인 jdbc/oracle을 이용해 톰캣에 미리 연결한 DataSource를 받아옵니다
			dataFactory=(DataSource)envContext.lookup("jdbc/oracle");
		}
		catch (Exception e) {
			System.out.println("커넥션풀 연결실패!!");
		} 
	}
	
	//회원정보 목록 메서드
	public List<MemberVO> listMembers() {
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			conn=dataFactory.getConnection();
			String query="select * from membertbl";
			pstmt=conn.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery(query);
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String email=rs.getString("email");
				Date joinDate=rs.getDate("joinDate");
				MemberVO vo=new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(Exception e) {
			System.out.println("회원정보 목록 처리 중 에러!");
			e.printStackTrace();
		}
		return list;
	}
	
}
