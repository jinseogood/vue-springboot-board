package me.jsk.app.board.domain;

public class BoardVO {
  private int docNo;
  private String title;
  private String content;
  private String writer;
  private String regDttm;
  private int view;
  private int reply;

  public int getDocNo() {
    return this.docNo;
  }

  public void setDocNo(int docNo) {
    this.docNo = docNo;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getWriter() {
    return this.writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getRegDttm() {
    return this.regDttm;
  }

  public void setRegDttm(String regDttm) {
    this.regDttm = regDttm;
  }

  public int getView() {
    return this.view;
  }

  public void setView(int view) {
    this.view = view;
  }

  public int getReply() {
    return this.reply;
  }

  public void setReply(int reply) {
    this.reply = reply;
  }

}
