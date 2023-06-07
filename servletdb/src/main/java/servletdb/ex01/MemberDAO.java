package servletdb.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static final String driver="oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@192.168.4.212:1521:XE";
	private static final String user="userexjsp";
	private static final String pwd="1234";
	private Connection conn;
	private Statement stmt;			//Statement가 DBMS에 전달하는 SQL문은 단순한 문자열이므로 DBMS가 이해할 수 있도록 컴파일하고 실행합니다.
	
	//회원정보 목록 메서드
	public List<MemberVO> listMembers() {
		List<MemberVO> list=new ArrayList<MemberVO>();
		try {
			connDB();
			String query="select * from membertbl";
			ResultSet rs=stmt.executeQuery(query);
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
			stmt.close();
			conn.close();
		}
		catch(Exception e) {
			System.out.println("회원정보 목록 처리 중 에러!");
			e.printStackTrace();
		}
		return list;
	}
	
	//데이터베이스 연결 메서드
	private void connDB() {
		try {
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공!!");
			conn=DriverManager.getConnection(url,user,pwd);
			System.out.println("Connection 생성 성공!!");
		}
		catch (Exception e) {
			System.out.println("DB연결오류!!");
		}
	}
}
