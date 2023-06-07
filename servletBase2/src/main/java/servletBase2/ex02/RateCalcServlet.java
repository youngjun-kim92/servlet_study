package servletBase2.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class RateCalcServlet extends HttpServlet {
	private static float USD_RATE = 1327.80f;
	private static float JPY_RATE = 992.78f;
	private static float CNY_RATE = 192.63f;
	private static float GBP_RATE = 1650.46f;
	private static float EUR_RATE = 1455.53f;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//mime-type : text를 html로 보낼건데, charset은 utf-8로 해서깨지지 않게 할거야(2가지 보냄)
		response.setContentType("text/html;charset=utf-8"); 
		PrintWriter out = response.getWriter();
		//servletBase2웹애플리케이션/calc파라메타 ?(매개변수)command = (값)가나다라
		String command = request.getParameter("command");
		String won = request.getParameter("won");
		String operator = request.getParameter("operator");
		if(command !=null && command.equals("calculate")) {
			// command에 "calculate"를 검색했을 때 이쪽으로 옴
			// input이라 일단 다 String으로 옴. 근데 won은 float으로 받을거고, operator는 환전할 통화의 선택 value값이 넘어왔을 것.
			String result = calculate(Float.parseFloat(won), operator);
			out.print("<html><title>환율계산기</title>");
			out.print("<body><h2>변환결과</h2>");
			// 왜  if(operator == "euro") { operator = "유료";} 로 했을 때는 안들어갈까??   
			// == 은 주소값을 비교하는 거라고.. 일치하지 않으니까 안들어간 것. https://wakestand.tistory.com/66 참고.
			if(operator.equals("dollar")) { operator = "달러";}
			if(operator.equals("en")) { operator = "엔화"; }
			if(operator.equals("wian")) { operator = "위안"; }
			if(operator.equals("pound")) { operator = "파운드"; }
			if(operator.equals("euro")) { operator = "유료";}			
			out.print("<h3>" + won +"원 → " + result + operator  + "</h3>");			  				
			out.print("<a href='/servletBase2/calc'>환율계산기</a>");
			out.print("</body>");
			out.print("</html>");
		} else {
			//처음 로드하면 이 화면이 나올 것
			out.print("<html><title>환율계산기</title>");
			out.print("<body><h2>환율계산기</h2>");
											//여기 액션
			out.print("<form name ='frmCalc' action ='/servletBase2/calc'  method='get'>");
			out.print("원화 : <input type = 'text' name = 'won' size=10>");
			//아래에서 선택한 value가 operator에 들어감
			out.print("<select name = 'operator'>"); 
			out.print("<option value='dollar'>달러</option>");
			out.print("<option value='en'>엔화</option>");
			out.print("<option value='wian'>위안</option>");
			out.print("<option value='pound'>파운드</option>");
			out.print("<option value='euro'>유로</option>");
			out.print("/<select>");
			//type = hidden : 화면에 안보이지만 서버에 값을 보낼때 사용 
			// 안보이지만 calculate가 name에 들어가있는 상황
			out.print("<input type = 'hidden' name='command' value='calculate'>");
			// type='submit'이면 변환을 누르면 action이 일어나서
			//서버에 servletBase2컨텍스트안에 calc라는 이름을 가진 서블릿을 찾음 => 수행 
			out.print(" <input type='submit' value = ' 변환 '>");
			out.print("</form>");
			out.print("</body>");
			out.print("</html>");
		}
	}//do get End
	//환율계산의 결과값을 리턴하는 메서드
	private static String calculate(float won, String operator) {
		String result = null;
		if(operator.equals("dollar")) {
			//% : 소수 자리 표현    ex) %3.6f : 소수 이전 3자리, 소수 이하 6자리 표현. %.6f : 소수 이전은 관계 없고, 소수 이하는 6자리 표현하겠다
			// f 는 float의 f가 아님
			result = String.format("%.6f", won/USD_RATE);
		} else if (operator.equals("en")) {
			result = String.format("%.6f", won/JPY_RATE*100);
		} else if (operator.equals("wian")) {
			result = String.format("%.6f", won/CNY_RATE);
		} else if (operator.equals("pound")) {
			result = String.format("%.6f", won/GBP_RATE);
		} else if (operator.equals("euro")) {
			result = String.format("%.6f", won/EUR_RATE);
		}
		return result;
	}
}
