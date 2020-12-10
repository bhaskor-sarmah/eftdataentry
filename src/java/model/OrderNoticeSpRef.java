package model;

import util.DateUtil;

public class OrderNoticeSpRef {
  String firstOrderDate;
  
  String lastOrderDate;
  
  String finalOrderDate;
  
  String noticeIssueDate;
  
  String hearingDate;
  
  String ftCaseNo;
  
  String spRefNo;
  
  String noticeThana;
  
  String spVillageName;
  
  String spPsName;
  
  String spDistName;
  
  String ft_id;
  
  String opinionType;
  
  String noticeType;
  
  public String getFirstOrderDate() {
    return this.firstOrderDate;
  }
  
  public void setFirstOrderDate(String firstOrderDate) {
    if (firstOrderDate.trim().equals("")) {
      this.firstOrderDate = null;
    } else {
      this.firstOrderDate = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", firstOrderDate));
    } 
  }
  
  public String getLastOrderDate() {
    return this.lastOrderDate;
  }
  
  public void setLastOrderDate(String lastOrderDate) {
    if (lastOrderDate.trim().equals("")) {
      this.lastOrderDate = null;
    } else {
      this.lastOrderDate = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", lastOrderDate));
    } 
  }
  
  public String getNoticeIssueDate() {
    return this.noticeIssueDate;
  }
  
  public void setNoticeIssueDate(String noticeIssueDate) {
    if (noticeIssueDate.trim().equals("")) {
      this.noticeIssueDate = null;
    } else {
      this.noticeIssueDate = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", noticeIssueDate));
    } 
  }
  
  public String getHearingDate() {
    return this.hearingDate;
  }
  
  public void setHearingDate(String hearingDate) {
    if (hearingDate.trim().equals("")) {
      this.hearingDate = null;
    } else {
      this.hearingDate = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", hearingDate));
    } 
  }
  
  public String getFinalOrderDate() {
    return this.finalOrderDate;
  }
  
  public void setFinalOrderDate(String finalOrderDate) {
    if (finalOrderDate.trim().equals("")) {
      this.finalOrderDate = null;
    } else {
      this.finalOrderDate = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", finalOrderDate));
    } 
  }
  
  public String getFtCaseNo() {
    return this.ftCaseNo;
  }
  
  public void setFtCaseNo(String ftCaseNo) {
    this.ftCaseNo = ftCaseNo;
  }
  
  public String getSpRefNo() {
    return this.spRefNo;
  }
  
  public void setSpRefNo(String spRefNo) {
    this.spRefNo = spRefNo;
  }
  
  public String getNoticeThana() {
    return this.noticeThana;
  }
  
  public void setNoticeThana(String noticeThana) {
    this.noticeThana = noticeThana;
  }
  
  public String getFt_id() {
    return this.ft_id;
  }
  
  public void setFt_id(String ft_id) {
    this.ft_id = ft_id;
  }
  
  public String getSpVillageName() {
    return this.spVillageName;
  }
  
  public void setSpVillageName(String spVillageName) {
    this.spVillageName = spVillageName;
  }
  
  public String getSpPsName() {
    return this.spPsName;
  }
  
  public void setSpPsName(String spPsName) {
    this.spPsName = spPsName;
  }
  
  public String getSpDistName() {
    return this.spDistName;
  }
  
  public void setSpDistName(String spDistName) {
    this.spDistName = spDistName;
  }
  
  public String getOpinionType() {
    return this.opinionType;
  }
  
  public void setOpinionType(String opinionType) {
    this.opinionType = opinionType;
  }
  
  public String getNoticeType() {
    return this.noticeType;
  }
  
  public void setNoticeType(String noticeType) {
    this.noticeType = noticeType;
  }
}
