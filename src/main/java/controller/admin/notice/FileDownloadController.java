package controller.admin.notice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownloadController extends HttpServlet {

  private static final String UPLOAD_DIR = System.getProperty("upload.path");

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String tempPath = request.getServletContext().getRealPath("/");
    File projectFolder = new File(tempPath);
    String projectName = projectFolder.getName();

    String uploadPath = UPLOAD_DIR + projectName + "/uploads";

    String filename = request.getParameter("filename");
    if (filename == null || filename.contains("..")) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, "잘못된 파일 요청입니다.");
      return;
    }

    File file = new File(uploadPath + File.separator + filename);
    if (!file.exists()) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "파일을 찾을 수 없습니다.");
      return;
    }

    // 파일 응답 설정
    response.setContentType(getServletContext().getMimeType(file.getName()));
    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
    response.setContentLengthLong(file.length());

    // 파일 스트림 전송
    try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
      byte[] buffer = new byte[4096];
      int bytesRead;
      while ((bytesRead = fis.read(buffer)) != -1) {
        os.write(buffer, 0, bytesRead);
      }
    }
  }

}
