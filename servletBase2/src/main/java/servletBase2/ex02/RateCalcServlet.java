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
	private static float USD_RATE=1327.80f;
	private static float JPY_RATE=992.78f;
	private static float CNY_RATE=192.63f;
	private static float GBP_RATE=1650.46f;
	private static float EUR_RATE=1455.53f;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		String command=request.getParameter("command");
		String won=request.getParameter("won");
		String operator=request.getParameter("operator");
		if(command!=null && command.equals("calculate")) {
			String result=calculate(Float.parseFloat(won),operator);
			out.print("<html><title>환율계산기</title>");
			out.print("<body><h2>변환결과(원화 =>"+operator+")</h2>");
			out.print("<h3>"+won+" => "+result+"</h3>");
			out.print("<a href='/servletBase2/calc'>환율계산기</a>");
			out.print("</body>");
			out.print("</html>");
		}
		else {
			out.print("<html><title>환율계산기</title>");
			out.print("<body><h2>환율계산기</h2>");
			out.print("<form name='frmCalc' action='/servletBase2/calc' method='get'>");
			out.print("원화 : <input type='text' name='won' size=10>");
			out.print("<select name='operator'>");
			out.print("<option value='dollar'>달러</option>");
			out.print("<option value='en'>엔화</option>");
			out.print("<option value='wian'>위안</option>");
			out.print("<option value='pound'>파운드</option>");
			out.print("<option value='euro'>유로</option>");
			out.print("</select>");
			out.print("<input type='hidden' name='command' value='calculate'>");
			out.print("<input type='submit' value='변환'>");
			out.print("</form>");
			out.print("</body>");
			out.print("</html>");
			
		}
	}
	//환율계산의 결과값을 리턴하는 메서드
	private static String calculate(float won, String operator) {
		String result=null;
		if(operator.equals("dollar")) {
			result=String.format("%.6f", won/USD_RATE);
		}
		else if(operator.equals("en")) {
			result=String.format("%.6f", won/JPY_RATE*100);
		}
		else if(operator.equals("wian")) {
			result=String.format("%.6f", won/CNY_RATE);
		}
		else if(operator.equals("pound")) {
			result=String.format("%.6f", won/GBP_RATE);
		}
		else if(operator.equals("euro")) {
			result=String.format("%.6f", won/EUR_RATE);
		}
		return result;
	}
}
