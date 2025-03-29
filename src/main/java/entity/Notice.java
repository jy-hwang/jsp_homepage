package entity;

import java.util.Date;

public class Notice {
  private int noticeNo;

  private String title;

  private Date createdDate;

  private Date updatedDate;

  private String writerId;

  private String files;

  private String content;

  private int hit;

  public Notice() {}

  public Notice(int noticeNo, String title, Date createdDate, String writerId, String files,
      String content, int hit) {
    super();
    this.noticeNo = noticeNo;
    this.title = title;
    this.createdDate = createdDate;
    this.writerId = writerId;
    this.files = files;
    this.content = content;
    this.hit = hit;
  }

  public int getNoticeNo() {
    return noticeNo;
  }

  public void setNoticeNo(int noticeNo) {
    this.noticeNo = noticeNo;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getWriterId() {
    return writerId;
  }

  public void setWriterId(String writerId) {
    this.writerId = writerId;
  }

  public String getFiles() {
    return files;
  }

  public void setFiles(String files) {
    this.files = files;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getHit() {
    return hit;
  }

  public void setHit(int hit) {
    this.hit = hit;
  }

  @Override
  public String toString() {
    return "Notice [noticeNo=" + noticeNo + ", title=" + title + ", createdDate=" + createdDate
        + ", updatedDate=" + updatedDate + ", writerId=" + writerId + ", files=" + files
        + ", content=" + content + ", hit=" + hit + "]";
  }

}
