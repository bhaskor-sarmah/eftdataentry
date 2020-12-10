package model;

import java.util.List;

public class CountListObject {
  private List<CountModel> countList;
  
  private List<String> userList;
  
  public List<CountModel> getCountList() {
    return this.countList;
  }
  
  public void setCountList(List<CountModel> countList) {
    this.countList = countList;
  }
  
  public List<String> getUserList() {
    return this.userList;
  }
  
  public void setUserList(List<String> userList) {
    this.userList = userList;
  }
}
