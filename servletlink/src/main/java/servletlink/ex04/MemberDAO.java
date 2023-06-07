package servletlink.ex04;

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
	
	//DB연결 및 실행
	private Connection conn; //DB연결을 시도하는 객체
//	private Statement stmt; //DB가 쿼리문을 수행하는데.. CURD를 statement가 실행해 줌 (재생 버튼 같은 느낌)
	private PreparedStatement pstmt; //preparedStatement로 수정 !---------statement 삭제▶◀
	private DataSource dataFactory;
	
	//생성자(Ctrl + Space + Enter)
	public MemberDAO() {
		try {
			//JNDI(Java Naming and Directory Interface)
			//JNDI에 접근하기 위해 기본 경로(java:/comp/env)를 지정			
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			//톰켓 context.xml에 설정한 name값인 jdbc/oracle 을 이용해 톰켓에 미리 연결한 DataSource를 받아옴
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			System.out.println("커넥션풀 연결실패!!"); //서블릿에서 new로 객체 생성 시 호출되는데, 실패가 뜨면 context.xml 확인
		}
	}
	
	//회원정보 목록 메서드
	public List<MemberVO> listMembers() {
		//클라이언트가 요청하면 응답할 형식<MemberVO>
		List<MemberVO> list = new ArrayList<MemberVO>();
		//DB가 끊어질 수 있으므로 의무적으로 try catch 요구
		try {
			conn=dataFactory.getConnection();
			String query = "select * from membertbl";
			//---------추가 ▶◀
			pstmt = conn.prepareStatement(query);
			//ResultSet : cursor 역할
			ResultSet rs = pstmt.executeQuery(query); 
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
			pstmt.close(); //수정▶◀
			conn.close();
		} catch (Exception e) {
			System.out.println("회원정보 목록 처리 중 에러!");
			e.printStackTrace();
		}		
		return list;		
 	} 
	//회원 등록 메서드
	public void addMember(MemberVO memVo){
		//DB관련해서는 항상 에러가 날 수 있기 때문에 try-catch 필수
		try {
			conn = dataFactory.getConnection(); // 데이터베이스 연결
			String id = memVo.getId();
			String pwd = memVo.getPwd();
			String name = memVo.getName();
			String email = memVo.getEmail();
			//Statement 썼다면 이렇게 했어야 했다.
//			String query = "insert into membertbl (id, pwd, name, email) values('" + id + "','" + pwd + "','" + name + "','" + email + "')'";
			//prepareStatement는 이렇게
			String query = "insert into membertbl (id, pwd, name, email) values(?,?,?,?)";
			pstmt = conn.prepareStatement(query); //pstmt가 쿼리문을 접수
			//?순서대로 set
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate(); //자료 등록 실행
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원등록 중 에러!!");
			e.printStackTrace(); //실제 에러 확인용
		}
	}
	//회원 삭제 메서드
	public void delMember(String id) {
		try {
			conn=dataFactory.getConnection();
			String query = "delete from membertbl where id=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			//insert, update, delete 는 내용이 변하는 것이므로 executeUpdate()이용
			pstmt.executeUpdate(); //삭제 실행
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println("회원 삭제 중 에러!!");
			e.printStackTrace();
		} 
	}
	//회원 확인 여부 메소드
	public Boolean isExisted(MemberVO memVo) {
		boolean result = false;
		String id = memVo.getId();
		String pwd = memVo.getPwd();
		try {
			conn = dataFactory.getConnection();
			//decode(표현식, 조건1, 결과1,.., 기본결과 default값) // 기본결과 default는 거짓일 때 수행됨
			//1이면(자료 발견되면) 'true'(문자로 리턴), 발견 안되면 'false' as 별칭 result
			/*방법 1. decode 사용
			 * String query = "select decode(count(*), 1, 'true', 'false') as result" + //다음 줄에 한칸 띄어야 함 from과 띄어지도록
			" from membertbl where id=? and pwd=?";*/
			//방법 2.
			String query = "select * from membertbl where id=? and pwd=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			//수행결과로 ResultSet객체의 값을 반환. select 구문을 수행할 때 사용되는 함수
			ResultSet rs = pstmt.executeQuery();
			//rs.next(); //커서를 첫번째 레코드로 위치(값이 없으면 첫번째 레코드로 안옴. 방법1.은 true, false값으로 무조건 자료가 있도록 만듦)
			//방법 2. 값이 있어서 가져오면 next로 이동되며 result에 true를 가져옴 
			if(rs.next()) {
				result = true;
			}
			result = Boolean.parseBoolean(rs.getString("result"));
		} catch (Exception e) {
			System.out.println("회원 확인 여부 처리중 에러!!");
			e.printStackTrace(); //에러추적
		}
		return result;
	}
}//MemberDAO()
