package entity;

public class Notice {
  private int noticeNo;

  private String title;

  private String createdDate;

  private String writerId;

  private String files;

  private String content;

  private int hit;

  public Notice() {}

  public Notice(int noticeNo, String title, String createdDate, String writerId, String files,
      String content, int hit) {
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

  public String getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
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
        + ", writerId=" + writerId + ", files=" + files + ", content=" + content + ", hit=" + hit
        + "]";
  }


}
