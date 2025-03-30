package controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import entity.Notice;
import service.NoticeService;
import util.StringUtils;

@MultipartConfig(
    //location = "/tmp",
    fileSizeThreshold = 1024 * 1014
    , maxFileSize = 1024 * 1024 * 5
    , maxRequestSize = 1024 * 1024 * 5 * 5
    )
@WebServlet("/admin/board/notice/reg")
public class RegController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/admin/board/notice/reg.jsp").forward(request, response);
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("===================================");
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String tempDisclose = request.getParameter("disclose");
    Part filePart = request.getPart("file");
    String fileName = filePart.getSubmittedFileName();
    
    InputStream fis = filePart.getInputStream();
    // webroot : /upload -> c:/temp/upload
    String realPath = request.getServletContext().getRealPath("/uploads");

    String filePath = realPath + File.separator + fileName;
    System.out.println("filePath : " + filePath);
    FileOutputStream fos = new FileOutputStream(filePath);

    byte[] buf = new byte[1024];
    int size = 0;
    while ((size = fis.read(buf)) != -1) {
      fos.write(buf, 0, size);
    }
    fos.close();
    fis.close();


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

//    NoticeService service = new NoticeService();
//    int result = service.insertNotice(notice);

    response.sendRedirect("list");

  }
}
