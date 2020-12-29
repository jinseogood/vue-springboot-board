package me.jsk.app.board.domain;

public class ReplyVO {
  private int replyNo;
  private int docNo;
  private String writer;
  private String content;
  private String regDttm;

  public int getReplyNo() {
    return this.replyNo;
  }

  public void setReplyNo(int replyNo) {
    this.replyNo = replyNo;
  }

  public int getDocNo() {
    return this.docNo;
  }

  public void setDocNo(int docNo) {
    this.docNo = docNo;
  }

  public String getWriter() {
    return this.writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getRegDttm() {
    return this.regDttm;
  }

  public void setRegDttm(String regDttm) {
    this.regDttm = regDttm;
  }

}
