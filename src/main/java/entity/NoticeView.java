package entity;

import java.util.Date;

public class NoticeView extends Notice {

  private int commentCount;

  public int getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(int commentCount) {
    this.commentCount = commentCount;
  }

  public NoticeView() {}

  public NoticeView(int noticeNo, String title, Date createdDate, String writerId, String files,
      int hit, int commentCount) {
    super(noticeNo, title, createdDate, writerId, files, "", hit);
    this.commentCount = commentCount;
  }

}
/*
(int noticeNo, String title, Date createdDate, String writerId, String files,
      String content, int hit) {

*/