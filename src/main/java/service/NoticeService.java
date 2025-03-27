package service;

import java.util.List;
import entity.Notice;

public class NoticeService {

  public List<Notice> getNoticeList() {

    return getNoticeList("title", "", 1);
  }

  public List<Notice> getNoticeList(int page) {

    return getNoticeList("title", "", page);
  }

  public List<Notice> getNoticeList(String field, String keyword, int page) {
    String query =
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice LIMIT ? OFFSET ? ";

    return null;
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
        " SELECT no, writer_id AS writerId, title, content, hit, files, created_date AS createdDate, updated_date AS updatedDate FROM notice WHERE no = ?";
    
    return null;
  }

  public Notice getNextNotice(int id) {
    String query =
        " SELECT title FROM notice WHERE created_date > ( SELECT created_date FROM notice WHERE NO = 1) LIMIT 1 ";
    
    return null;
  }

  public Notice getPrevNotice(int id) {
    String query =
        " SELECT title FROM notice WHERE created_date < ( SELECT created_date FROM notice WHERE NO = 2) ORDER BY created_date DESC LIMIT 1; ";
    
    return null;
  }

}
