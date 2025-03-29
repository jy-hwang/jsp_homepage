package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entity.Notice;
import entity.NoticeView;
import util.CommonBase;
import util.DatabaseUtil;

public class NoticeService {

  /* Admin 용 서비스 추가*/
  public int publishNoticeAll(int[] ids) {
    
    return 0;
  }

  public int removeNoticeAll(int[] ids) {
    
    return 0;
  }

  public int insertNotice(Notice notice) {
    
    return 0;
  }

  public int deleteNotice(int id) {
    
    return 0;
  }

  public int updateNotice(Notice notice) {
    
    return 0;
  }

  public List<Notice> getNoticeNewestList() {
    
    return null;
  }

  /* 일반 사용자용 서비스 */  
  public List<NoticeView> getNoticeList() {

    return getNoticeList("title", "", 1);
  }

  public List<NoticeView> getNoticeList(int page) {

    return getNoticeList("title", "", page);
  }

  public List<NoticeView> getNoticeList(String field, String keyword, int page) {
    List<NoticeView> list = new ArrayList<NoticeView>();

    // field <<- title, writer_id

    String query =
        " SELECT no, title, writerId, hit, files, createdDate, updatedDate, commentCount FROM notice_view "
            + " WHERE " + field + " LIKE ? ORDER BY createdDate DESC LIMIT ? OFFSET ? ";

    /*
     * 1, 6, 11, 16, 21, ... => an = 1 + (page - 1) * 5 5, 10, 15, 20, ... => page * 5 pageSize 5
     * page 4 offset = (page - 1) * pageSize = (4 - 1) * 5 = 15
     *
     */

    int offset = (page - 1) * CommonBase.PAGE_SIZE;

    Connection conn = null;
    PreparedStatement pStmt = null;
    ResultSet rSet = null;

    try {
      conn = DatabaseUtil.getConnection();

      pStmt = conn.prepareStatement(query);
      pStmt.setString(1, "%" + keyword + "%");
      pStmt.setInt(2, CommonBase.PAGE_SIZE);
      pStmt.setInt(3, offset);

      rSet = pStmt.executeQuery();

      while (rSet.next()) {
        int noticeNo = rSet.getInt("no");
        String title = rSet.getString("title");
        Date createdDate = rSet.getDate("createdDate");
        String writerId = rSet.getString("writerId");
        String files = rSet.getString("files") == null ? "" : rSet.getString("files");
        int hit = rSet.getInt("hit");
        int commentCount = rSet.getInt("commentCount");

        NoticeView notice =
            new NoticeView(noticeNo, title, createdDate, writerId, files, hit, commentCount);
        list.add(notice);
      }

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
    int count = 0;
    String query = " SELECT count(no) as totalCount FROM notice " + " WHERE " + field + " LIKE ? ";

    Connection conn = null;
    PreparedStatement pStmt = null;
    ResultSet rSet = null;

    try {
      conn = DatabaseUtil.getConnection();

      pStmt = conn.prepareStatement(query);
      pStmt.setString(1, "%" + keyword + "%");
      rSet = pStmt.executeQuery();

      if (rSet.next()) {
        count = rSet.getInt("totalCount");
      }

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

    return count;
  }

  public Notice getNotice(int id) {
    Notice notice = null;

    String query =
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice WHERE no = ? ";

    Connection conn = null;
    PreparedStatement pStmt = null;
    ResultSet rSet = null;

    try {
      conn = DatabaseUtil.getConnection();

      pStmt = conn.prepareStatement(query);
      pStmt.setInt(1, id);
      rSet = pStmt.executeQuery();

      if (rSet.next()) {
        int noticeNo = rSet.getInt("no");
        String title = rSet.getString("title");
        Date createdDate = rSet.getDate("createdDate");
        String writerId = rSet.getString("writerId");
        String files = rSet.getString("files") == null ? "" : rSet.getString("files");
        String content = rSet.getString("content");
        int hit = rSet.getInt("hit");

        notice = new Notice(noticeNo, title, createdDate, writerId, files, content, hit);
      }

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

    return notice;
  }

  public Notice getNextNotice(int id) {
    Notice notice = null;

    String query =
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice "
            + " WHERE created_date > ( SELECT created_date FROM notice WHERE NO = ?) LIMIT 1 ";

    Connection conn = null;
    PreparedStatement pStmt = null;
    ResultSet rSet = null;

    try {
      conn = DatabaseUtil.getConnection();

      pStmt = conn.prepareStatement(query);
      pStmt.setInt(1, id);
      rSet = pStmt.executeQuery();

      if (rSet.next()) {
        int noticeNo = rSet.getInt("no");
        String title = rSet.getString("title");
        Date createdDate = rSet.getDate("createdDate");
        String writerId = rSet.getString("writerId");
        String files = rSet.getString("files") == null ? "" : rSet.getString("files");
        String content = rSet.getString("content");
        int hit = rSet.getInt("hit");

        notice = new Notice(noticeNo, title, createdDate, writerId, files, content, hit);
      }

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

    return notice;
  }

  public Notice getPrevNotice(int id) {
    Notice notice = null;

    String query =
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice "
            + " WHERE created_date < ( SELECT created_date FROM notice WHERE NO = ?) ORDER BY created_date DESC LIMIT 1 ";

    Connection conn = null;
    PreparedStatement pStmt = null;
    ResultSet rSet = null;

    try {
      conn = DatabaseUtil.getConnection();

      pStmt = conn.prepareStatement(query);
      pStmt.setInt(1, id);
      rSet = pStmt.executeQuery();

      if (rSet.next()) {
        int noticeNo = rSet.getInt("no");
        String title = rSet.getString("title");
        Date createdDate = rSet.getDate("createdDate");
        String writerId = rSet.getString("writerId");
        String files = rSet.getString("files") == null ? "" : rSet.getString("files");
        String content = rSet.getString("content");
        int hit = rSet.getInt("hit");

        notice = new Notice(noticeNo, title, createdDate, writerId, files, content, hit);
      }

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

    return notice;
  }

}
