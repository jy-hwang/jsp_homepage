package controller.admin.notice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
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
    String tempPath = request.getServletContext().getRealPath("/");
    File projectFolder = new File(tempPath);
    String projectName = projectFolder.getName();
    String realPath = getRealUploadPath(projectName);
    
    String title = request.getParameter("title");
    String content = request.getParameter("content");
    String tempDisclose = request.getParameter("disclose");
    
    Collection<Part> parts = request.getParts();
    StringBuilder builder = new StringBuilder();
    for(Part p : parts){
      
      if(p.getName().equals("file") && p.getSize() > 0){
        
        Part filePart = p;
        String fileName = filePart.getSubmittedFileName();
        
        builder.append(fileName);
        builder.append(",");
        InputStream fis = filePart.getInputStream();
        
        String filePath = realPath + File.separator + fileName;
        FileOutputStream fos = new FileOutputStream(filePath);
    
        byte[] buf = new byte[1024];
        int size = 0;
        while ((size = fis.read(buf)) != -1) {
          fos.write(buf, 0, size);
        }
        fos.close();
        fis.close();
      }
    }
    int tempLength = builder.length();
    builder.delete(tempLength - 1, tempLength);
    

    boolean disclose = false;

    if (!StringUtils.isEmpty(tempDisclose)) {
      disclose = true;
    }

    Notice notice = new Notice();
    notice.setTitle(title);
    notice.setContent(content);
    notice.setFiles(builder.toString());
    notice.setDisclose(disclose);
    notice.setWriterId("admin");

    NoticeService service = new NoticeService();
    int result = service.insertNotice(notice);

    response.sendRedirect("list");

  }
  
  public String getRealUploadPath(String projectName){
    String tempUploadPath = System.getProperty("upload.path");

    String uploadPath = tempUploadPath + projectName + "/uploads";
    
    File uploadDir = new File(uploadPath);
    if (!uploadDir.exists()) {
        boolean created = uploadDir.mkdirs(); // 상위 디렉토리까지 모두 생성
        if (created) {
            System.out.println("업로드 폴더 생성됨: " + uploadPath);
        } else {
            System.out.println("업로드 폴더 생성 실패!");
        }
    } else {
        System.out.println("업로드 폴더가 이미 존재함.");
    } 
    
    return uploadPath;
  }
  
}
