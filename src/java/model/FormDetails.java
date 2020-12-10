package model;

import util.DateUtil;

public class FormDetails {
  String suspectName;
  
  String fatherName;
  
  String motherName;
  
  String maritalStatus;
  
  String spouseName;
  
  String age;
  
  String dob;
  
  String gender;
  
  String mobileNo;
  
  String pob;
  
  String profession;
  
  String height;
  
  String hairColor;
  
  String eyeColor;
  
  String complexion;
  
  String identificationMark;
  
  String spCaseNo;
  
  String imdtNo;
  
  String ftCaseNo;
  
  public String getSuspectName() {
    return this.suspectName;
  }
  
  public void setSuspectName(String suspectName) {
    this.suspectName = suspectName;
  }
  
  public String getFatherName() {
    return this.fatherName;
  }
  
  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }
  
  public String getMotherName() {
    return this.motherName;
  }
  
  public String getImdtNo() {
    return this.imdtNo;
  }
  
  public void setImdtNo(String imdtNo) {
    this.imdtNo = imdtNo;
  }
  
  public void setMotherName(String motherName) {
    this.motherName = motherName;
  }
  
  public String getMaritalStatus() {
    return this.maritalStatus;
  }
  
  public void setMaritalStatus(String maritalStatus) {
    this.maritalStatus = maritalStatus;
  }
  
  public String getSpouseName() {
    return this.spouseName;
  }
  
  public void setSpouseName(String spouseName) {
    this.spouseName = spouseName;
  }
  
  public String getAge() {
    return this.age;
  }
  
  public void setAge(String age) {
    this.age = age;
  }
  
  public String getDob() {
    return this.dob;
  }
  
  public void setDob(String dob) {
    if (dob.trim().equals("")) {
      this.dob = null;
    } else {
      this.dob = DateUtil.formatDate("yyyy-MM-dd", DateUtil.parseStringToDate("dd/MM/yyyy", dob));
    } 
  }
  
  public String getGender() {
    return this.gender;
  }
  
  public void setGender(String gender) {
    this.gender = gender;
  }
  
  public String getMobileNo() {
    return this.mobileNo;
  }
  
  public void setMobileNo(String mobileNo) {
    this.mobileNo = mobileNo;
  }
  
  public String getPob() {
    return this.pob;
  }
  
  public void setPob(String pob) {
    this.pob = pob;
  }
  
  public String getProfession() {
    return this.profession;
  }
  
  public void setProfession(String profession) {
    this.profession = profession;
  }
  
  public String getHeight() {
    return this.height;
  }
  
  public void setHeight(String height) {
    this.height = height;
  }
  
  public String getHairColor() {
    return this.hairColor;
  }
  
  public void setHairColor(String hairColor) {
    this.hairColor = hairColor;
  }
  
  public String getEyeColor() {
    return this.eyeColor;
  }
  
  public void setEyeColor(String eyeColor) {
    this.eyeColor = eyeColor;
  }
  
  public String getComplexion() {
    return this.complexion;
  }
  
  public void setComplexion(String complexion) {
    this.complexion = complexion;
  }
  
  public String getIdentificationMark() {
    return this.identificationMark;
  }
  
  public void setIdentificationMark(String identificationMark) {
    this.identificationMark = identificationMark;
  }
  
  public String getSpCaseNo() {
    return this.spCaseNo;
  }
  
  public void setSpCaseNo(String spCaseNo) {
    this.spCaseNo = spCaseNo;
  }
  
  public String getFtCaseNo() {
    return this.ftCaseNo;
  }
  
  public void setFtCaseNo(String ftCaseNo) {
    this.ftCaseNo = ftCaseNo;
  }
}
