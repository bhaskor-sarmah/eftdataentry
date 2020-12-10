package model;

public class User {
  private String userId;
  
  private String userCount = "0";
  
  public String getUserId() {
    return this.userId;
  }
  
  public void setUserId(String userId) {
    this.userId = userId;
  }
  
  public String getUserCount() {
    return this.userCount;
  }
  
  public void setUserCount(String userCount) {
    this.userCount = userCount;
  }
}
