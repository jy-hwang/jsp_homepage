package controller.admin.notice;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Notice;
import service.NoticeService;
import util.StringUtils;

@WebServlet("/admin/board/notice/detail")
public class DetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int no = Integer.parseInt(request.getParameter("no"));

    NoticeService service = new NoticeService();

    Notice notice = service.getNotice(no);

    request.setAttribute("notice", notice);

    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/admin/board/notice/detail.jsp").forward(request, response);
  }

}
