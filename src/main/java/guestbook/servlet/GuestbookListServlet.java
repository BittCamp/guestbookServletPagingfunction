package guestbook.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guestbook.bean.GuestbookDTO;
import guestbook.dao.GuestbookDAO;

@WebServlet("/list")
public class GuestbookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터
		int pg = Integer.parseInt(request.getParameter("pg"));
		
		int endNum = pg*3;
		int startNum = endNum-2;
		
		//DB
		GuestbookDAO guestbookDAO = new GuestbookDAO();
		List<GuestbookDTO> list = guestbookDAO.getAllList(startNum, endNum);
		
		//페이징 처러
		int totalA = guestbookDAO.getTotalA(); //총글수
		int totalP = (totalA-1)/3+1; //총페이지수, int totalP = (총글수+2)/3;
			
		//응답
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		
		out.println("<head>");
		out.println("<style>");
		out.println("#currentPaging { color: red; text-decoration: underline; }");
		out.println("#paging { color: black; text-decoration: none; }");
		out.println("</style>");
		out.println("</head>");
				
		out.println("<body align='center'>");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		if(list != null) {
			for(GuestbookDTO guestbookDTO : list) {
				out.println("<table border='1' align='center'>");
				out.println("<tr>");
				out.println("<td width='100' align = 'center'>작성자</td>");
				out.println("<td width='150' align = 'center'>" + guestbookDTO.getName() + "</td>");
				out.println("<td width='100' align = 'center'>작성일</td>");
				out.println("<td width='150' align = 'center'>" + sdf.format(guestbookDTO.getLogtime()) + "</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align = 'center'>이메일</td>");
				out.println("<td colspan='3' align='center'>" + guestbookDTO.getEmail() + "</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align = 'center'>홈페이지</td>");
				out.println("<td colspan='3' align='center'>" + guestbookDTO.getHomepage() + "</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td align = 'center'>제목</td>");
				out.println("<td colspan='3' align='center'>" + guestbookDTO.getSubject() + "</td>");
				out.println("</tr>");
				
				out.println("<tr>");
				out.println("<td colspan='4'><pre>" + guestbookDTO.getContent() + "</pre></td>");
				out.println("</tr>");
				out.println("</table>");
				
				out.println("<hr width='500' color='red'>");
			}//for
			
			//페이징 처리
			out.println("<div>");
			out.println("<div style='width: 500px; margin: auto;'>");
			for(int i=1; i<=totalP; i++) {
				if(i == pg)
					out.println("<a href='/guestbookServlet/list?pg=" + i + "' id='currentPaging'>" + i + "</a>");
				else
					out.println("<a href='/guestbookServlet/list?pg=" + i + "' id='paging'>" + i + "</a>");
			}//for
			out.println("</div></div>");
			
		}//if
		
		out.println("</body>");
		out.println("</html>");
		
		
	}

}










