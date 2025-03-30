package controller.admin.notice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Notice;
import service.NoticeService;
import util.StringUtils;

@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/admin/board/notice/reg.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String tempDisclose = request.getParameter("disclose");
    String files = "";
    boolean disclose = false;

    if (!StringUtils.isEmpty(tempDisclose)) {
      disclose = true;
    }

    Notice notice = new Notice();
    notice.setTitle(title);
    notice.setContent(content);
    notice.setFiles(files);
    notice.setDisclose(disclose);
    notice.setWriterId("admin");

    NoticeService service = new NoticeService();
    int result = service.insertNotice(notice);

    response.sendRedirect("list");

  }
}
