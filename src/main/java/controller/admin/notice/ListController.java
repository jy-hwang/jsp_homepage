package controller.admin.notice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.NoticeView;
import service.NoticeService;
import util.CommonBase;
import util.StringUtils;

@WebServlet("/admin/board/notice/list")
public class ListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String tempField = request.getParameter("f");
    String tempKeyword = request.getParameter("k");
    String tempPage = request.getParameter("p");
    String field = "title";
    if (!StringUtils.isEmpty(tempField)) {
      field = tempField;
    }

    String keyword = "";
    if (!StringUtils.isEmpty(tempKeyword)) {
      keyword = tempKeyword;
    }

    int page = 1;
    if (!StringUtils.isEmpty(tempPage)) {
      page = Integer.parseInt(tempPage);
    }

    NoticeService service = new NoticeService();
    List<NoticeView> list = service.getNoticeList(field, keyword, page);
    int totalCount = service.getNoticeCount(field, keyword);

    Map<String, Integer> pagination = new HashMap<String, Integer>();
    pagination.put("pageSize", CommonBase.PAGE_SIZE);
    pagination.put("pageBlockSize", CommonBase.PAGE_BLOCK_SIZE);

    request.setAttribute("list", list);
    request.setAttribute("pagination", pagination);
    request.setAttribute("totalCount", totalCount);

    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/admin/board/notice/list.jsp").forward(request, response);

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String[] openIds = request.getParameterValues("open-id");
    String[] delIds = request.getParameterValues("del-id");
    String cmd = request.getParameter("cmd");
    NoticeService service = new NoticeService();

    switch (cmd) {
      case "일괄공개":
        for (String openId : openIds) {
          System.out.printf("open id : %s%n", openId);
        }
        break;

      case "일괄삭제":
        int[] ids = new int[delIds.length];
        for (int i = 0; i < delIds.length; i++) {
          ids[i] = Integer.parseInt(delIds[i]);
        }
        int result = service.removeNoticeAll(ids);
        break;
    }

  }

}
