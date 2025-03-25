package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    List<Notice> list = new ArrayList<Notice>();

    String dbUrl = System.getProperty("db.url");
    String dbUsername = System.getProperty("db.username");
    String dbPassword = System.getProperty("db.password");
    Connection conn = null;
    Statement stmt = null;
    ResultSet rSet = null;

    try {

      Class.forName("org.mariadb.jdbc.Driver");

      conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

      String query =
          " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice; ";

      stmt = conn.createStatement();

      rSet = stmt.executeQuery(query);

      while (rSet.next()) {

        int noticeNo = rSet.getInt("no");
        String title = rSet.getString("title");
        String createdDate = rSet.getString("createdDate");
        String writerId = rSet.getString("writerId");
        String files = rSet.getString("files") == null ? "" : rSet.getString("files");
        String content = rSet.getString("content");
        int hit = rSet.getInt("hit");

        Notice notice = new Notice(noticeNo, title, createdDate, writerId, files, content, hit);
        list.add(notice);
      }
      request.setAttribute("list", list);
      // forward : 앞 뒤를 연결하는 '같은 요청'.
      request.getRequestDispatcher("/notice/list.jsp").forward(request, response);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (rSet != null) {
          rSet.close();
        }
        if (stmt != null) {
          stmt.close();
        }
        if (conn != null) {
          conn.close();
        }
      } catch (SQLException e2) {
        e2.printStackTrace();
      }
    }
  }
}
