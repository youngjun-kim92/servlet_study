package servletdb.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	//오라클 드라이버 설정
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url="jdbc:oracle:thin:@localhost:1521:XE"; //localhost 접속 안되는 사람은 ip적기, XE : 버전 이름
	private static final String user="userexjsp";
	private static final String pwd="1234";
	
	//DB연결 및 실행
	private Connection conn; //DB연결을 시도하는 객체
	private Statement stmt; //DB가 쿼리문을 수행하는데.. CURD를 statement가 실행해 줌 (재생 버튼 같은 느낌)
	
	//회원정보 목록 메서드
	public List<MemberVO> listMembers() {
		//클라이언트가 요청하면 응답할 형식<MemberVO>
		List<MemberVO> list = new ArrayList<MemberVO>();
		//DB가 끊어질 수 있으므로 의무적으로 try catch 요구
		try {
			connDB();
			String query = "select * from membertbl";
			//ResultSet : cursor 역할
			ResultSet rs = stmt.executeQuery(query); 
			//rs의 구조 : header, 1, 2, 3, end부분(□■■■□)으로 되어 있음(1,2,3은 실제 자료)(우리 자료가 3개임)  
			while(rs.next()) { // next 하면 1번 자료 있는 곳으로 이동시킴				
				//getString : varchar2 타입에 10byte 이면 문자열 => String
				//getInt(), getDouble(), getDate()등 자료형에 맞는 메소드 사용
				String id = rs.getString("id"); //""안에 쓰는 것 : 컬럼 이름(오타 안나게)
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				//vo 객체 생성
				MemberVO vo = new MemberVO();
				//()안의 값을 vo에 set함
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				//list 에 자료 담기
				list.add(vo);
			}
			//작업을 다 했으면 꼭 닫아주어야 함
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보 목록 처리 중 에러!");
			e.printStackTrace();
		}		
		return list;		
 	} 
	//데이터베이스 연결 메서드
	private void connDB() {
		try {
			//드라이버에 있는 클래스 정보를 가져옴
			//forName()패키지를 포함한 클래스 전체 이름을 받는 메소드
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			
			//진짜 연결하는 메소드
			//driver를 가져와서 내 프로젝트에서 생성했다는 뜻.= DB와 연결되었다.
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
			//쿼리 수행 클래스 statement이용
			stmt = conn.createStatement(); //이제 select, insert 등의 명령어사용 가능
			System.out.println("Statement 생성 성공!!");
		} catch (Exception e) {
			System.out.println("DB연결 오류!!");
		}
		
	}//connDB()
}//MemberDAO()
