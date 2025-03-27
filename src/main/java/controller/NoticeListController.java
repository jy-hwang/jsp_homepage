package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Notice;
import service.NoticeService;
import util.StringUtils;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String tempField = request.getParameter("f");
    String tempKeyword = request.getParameter("k");
    
    String field = "title";
    if(!StringUtils.isEmpty(tempField)){
     field = tempField; 
    }
    
    String keyword = "";
    if(!StringUtils.isEmpty(tempKeyword)){
      keyword = tempKeyword; 
     }
    
    NoticeService service = new NoticeService();

    List<Notice> list = service.getNoticeList(field, keyword, 1);

    int totalCount = service.getNoticeCount();
    
    System.out.println("totalCount : " + totalCount);

    request.setAttribute("list", list);
    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(request, response);


  }
}
