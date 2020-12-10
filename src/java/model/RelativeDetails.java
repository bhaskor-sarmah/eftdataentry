package model;

import util.DateUtil;

public class RelativeDetails {
  String name;
  
  String relation;
  
  String memberAge;
  
  String dateOfBirth;
  
  String placeOfBirth;
  
  public String getName() {
    return this.name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String getRelation() {
    return this.relation;
  }
  
  public void setRelation(String relation) {
    this.relation = relation;
  }
  
  public String getMemberAge() {
    return this.memberAge;
  }
  
  public void setMemberAge(String memberAge) {
    this.memberAge = memberAge;
  }
  
  public String getDateOfBirth() {
    return this.dateOfBirth;
  }
  
  public void setDateOfBirth(String dateOfBirth) {
    if (dateOfBirth.trim().equals("")) {
      this.dateOfBirth = null;
    } else {
      this.dateOfBirth = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", dateOfBirth));
    } 
  }
  
  public String getPlaceOfBirth() {
    return this.placeOfBirth;
  }
  
  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }
}
