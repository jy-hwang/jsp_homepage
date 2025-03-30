package controller.admin.notice;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/admin/board/notice/reg.jsp").forward(request, response);
  }
  
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    
    String tempDisclose= request.getParameter("disclose");
    
    PrintWriter out = response.getWriter();
    
    out.printf("title : %s <br >",title);
    out.printf("content : %s <br >",content);
    out.printf("tempDisclose : %s <br >",tempDisclose);
    
  }
}
