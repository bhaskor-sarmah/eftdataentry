package model;

import java.util.List;

public class CountModel {
  private String date;
  
  private List<User> userList;
  
  public String getDate() {
    return this.date;
  }
  
  public void setDate(String date) {
    this.date = date;
  }
  
  public List<User> getUserList() {
    return this.userList;
  }
  
  public void setUserList(List<User> userList) {
    this.userList = userList;
  }
}
