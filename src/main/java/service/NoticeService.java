package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import entity.Notice;

public class NoticeService {

  public static final int PAGE_SIZE = 5;

  public List<Notice> getNoticeList() {

    return getNoticeList("title", "", 1);
  }

  public List<Notice> getNoticeList(int page) {

    return getNoticeList("title", "", page);
  }

  public List<Notice> getNoticeList(String field, String keyword, int page) {
    List<Notice> list = new ArrayList<Notice>();

    // field <<- title, content, writer_id

    String query =
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice"
            + " WHERE "+ field +" LIKE ? LIMIT ? OFFSET ? ";

    /*
     * 1, 6, 11, 16, 21, ... => an = 1 + (page - 1) * 5
     * 5, 10, 15, 20, ... => page * 5
     * pageSize 5
     * page 4
     * offset = (page - 1) * pageSize = (4 - 1) * 5 = 15
     *
     */

    int offset = (page - 1) * PAGE_SIZE;

    String dbUrl = System.getProperty("db.url");
    String dbUsername = System.getProperty("db.username");
    String dbPassword = System.getProperty("db.password");
    Connection conn = null;
    PreparedStatement pStmt = null;
    ResultSet rSet = null;

    try {

      Class.forName("org.mariadb.jdbc.Driver");

      conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);

      pStmt = conn.prepareStatement(query);
      pStmt.setString(1, "%" + keyword + "%");
      pStmt.setInt(2, PAGE_SIZE);
      pStmt.setInt(3, offset);
      
      rSet = pStmt.executeQuery();

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

    return list;
  }

  public int getNoticeCount() {

    return getNoticeCount("title", "");
  }

  public int getNoticeCount(String field, String keyword) {
    String query = " SELECT count(*) as totalCount FROM notice ";

    return 0;
  }

  public Notice getNotice(int id) {
    String query =
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice WHERE no = ? ";

    return null;
  }

  public Notice getNextNotice(int id) {
    String query =
        " SELECT title FROM notice WHERE created_date > ( SELECT created_date FROM notice WHERE NO = ?) LIMIT 1 ";

    return null;
  }

  public Notice getPrevNotice(int id) {
    String query =
        " SELECT title FROM notice WHERE created_date < ( SELECT created_date FROM notice WHERE NO = ?) ORDER BY created_date DESC LIMIT 1 ";

    return null;
  }

}
