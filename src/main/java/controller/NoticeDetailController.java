package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int no = Integer.parseInt(request.getParameter("no"));

    String dbUrl = System.getProperty("db.url");
    String dbUsername = System.getProperty("db.username");
    String dbPassword = System.getProperty("db.password");
    try {
      Class.forName("org.mariadb.jdbc.Driver");
      Connection con = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

      String query = " SELECT no, writer_id AS writerId, title, content, files, hit, created_date AS createdDate, updated_date AS updatedDate FROM notice WHERE no = ?; ";

      PreparedStatement pStmt = con.prepareStatement(query);
      pStmt.setInt(1, no);

      ResultSet rSet = pStmt.executeQuery(); 
      rSet.next();
      int noticeNo = rSet.getInt("no");
      String title = rSet.getString("title");
      String createdDate = rSet.getString("createdDate");
      String writerId = rSet.getString("writerId");
      String files = rSet.getString("files") == null ? "" : rSet.getString("files");
      String content = rSet.getString("content");
      int hit = rSet.getInt("hit");

      request.setAttribute("noticeNo", noticeNo);
      request.setAttribute("title", title);
      request.setAttribute("createdDate", createdDate);
      request.setAttribute("writerId", writerId);
      request.setAttribute("files", files);
      request.setAttribute("content", content);
      request.setAttribute("hit", hit);
      
      rSet.close();
      pStmt.close();
      con.close();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // redirect : 아예 다른 요청
    
    // forward : 앞 뒤를 연결하는 '같은 요청'.
   request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
    
  }
  
  
}
