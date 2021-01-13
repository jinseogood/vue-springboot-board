package me.jsk.app.board.domain;

public class BoardVO {
  private int docNo;
  private String title;
  private String content;
  private String writer;
  private String regDttm;
  private int view;
  private int reply;

  private int startNo;
  private int endNo;
  private String[] sort;

  private String schType;
  private String schVal;

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

  public int getStartNo() {
    return this.startNo;
  }

  public void setStartNo(int startNo) {
    this.startNo = startNo;
  }

  public int getEndNo() {
    return this.endNo;
  }

  public void setEndNo(int endNo) {
    this.endNo = endNo;
  }

  public String[] getSort() {
    return sort;
  }

  public void setSort(String[] sort) {
    this.sort = sort;
  }

  public String getSchType() {
    return this.schType;
  }

  public void setSchType(String schType) {
    this.schType = schType;
  }

  public String getSchVal() {
    return this.schVal;
  }

  public void setSchVal(String schVal) {
    this.schVal = schVal;
  }

}
