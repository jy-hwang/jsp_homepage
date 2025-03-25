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
import entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    int no = Integer.parseInt(request.getParameter("no"));

    String dbUrl = System.getProperty("db.url");
    String dbUsername = System.getProperty("db.username");
    String dbPassword = System.getProperty("db.password");
    PreparedStatement pStmt = null;
    ResultSet rSet = null;
    Connection conn = null;

    try {
      Class.forName("org.mariadb.jdbc.Driver");
      conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

      String query =
          " SELECT no, writer_id AS writerId, title, content, files, hit, created_date AS createdDate, updated_date AS updatedDate FROM notice WHERE no = ?; ";

      pStmt = conn.prepareStatement(query);
      pStmt.setInt(1, no);

      rSet = pStmt.executeQuery();
      rSet.next();

      int noticeNo = rSet.getInt("no");
      String title = rSet.getString("title");
      String createdDate = rSet.getString("createdDate");
      String writerId = rSet.getString("writerId");
      String files = rSet.getString("files") == null ? "" : rSet.getString("files");
      String content = rSet.getString("content");
      int hit = rSet.getInt("hit");

      Notice notice = new Notice(noticeNo, title, createdDate, writerId, files, content, hit);

      request.setAttribute("notice", notice);


    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rSet != null) {
          rSet.close();
        }
        if (pStmt != null) {
          pStmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }

    // redirect : 아예 다른 요청

    // forward : 앞 뒤를 연결하는 '같은 요청'.
    request.getRequestDispatcher("/WEB-INF/views/notice/detail.jsp").forward(request, response);

  }


}
