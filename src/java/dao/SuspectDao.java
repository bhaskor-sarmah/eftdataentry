package dao;

import initialize.ContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.AddressDetails;
import model.FormDetails;
import model.OrderNoticeSpRef;
import model.RelativeDetails;

public class SuspectDao {
  public boolean doSaveData(FormDetails formDetails, ArrayList<RelativeDetails> relDetailsList, AddressDetails addDetails, OrderNoticeSpRef orderNoticeSpRef, String user, String ft_no, String district, String folder) {
    long suspectId = doSavePersonal(orderNoticeSpRef, formDetails, user, ft_no, district);
    boolean res = true;
    if (suspectId == 0L) {
      System.out.println("Error saving personal details : ");
      res = false;
    } 
    if (!doSaveRelative(relDetailsList, suspectId)) {
      System.out.println("Error saving Realtive details : ");
      res = false;
    } 
    if (!doSaveAddress(orderNoticeSpRef, addDetails, suspectId)) {
      System.out.println("Error saving Address details : ");
      res = false;
    } 
    if (!doSaveOrder(orderNoticeSpRef, suspectId)) {
      System.out.println("Error saving Order details : ");
      res = false;
    } 
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    if (res) {
      PreparedStatement ps = null;
      try {
        ps = conn.prepareStatement("UPDATE imagemaster SET SUSPECT_ID = ?, FLAG = \"1\" WHERE FOLDER = ? AND FLAG1=?");
        ps.setString(1, String.valueOf(suspectId));
        ps.setString(2, folder + "/");
        ps.setString(3, user);
        System.out.println(ps);
        ps.executeUpdate();
      } catch (SQLException e) {
        System.out.println("Exception : " + e.getMessage());
      } finally {
        try {
          if (ps != null)
            ps.close(); 
        } catch (SQLException e) {
          System.out.println("Error Closing PS");
        } 
      } 
    } 
    try {
      if (conn != null)
        conn.close(); 
    } catch (SQLException e) {
      System.out.println("Error Closing Conneciton during Suspect Save");
    } 
    return res;
  }
  
  public long doSavePersonal(OrderNoticeSpRef orderNoticeSpRef, FormDetails formDetails, String user, String ft_no, String district) {
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    PreparedStatement ps = null, ps1 = null;
    try {
      int index = 1;
      ps = conn.prepareStatement("INSERT INTO trans_suspect(`suspect_id`, `record_type`,`suspect_name`, `mobile_no`, `age`, `date_of_birth`, `fk_gender_code`, `father_name`, `mother_name`, `fk_marital_status_code`, `spouse_name`, `place_of_birth`, `fk_occupation_code`, `other_occupation`, `height`, `hair`, `eye`, `identification_mark`, `complexion`,`ft_case_no`, `police_case_no`, `fk_suspect_district_code`, `fk_suspect_ft_code`, `fk_suspect_state_code`, `fk_suspect_thana_code`,`created_at`, `created_by`,`district_ft_code`,`imdt_no`) VALUES(NULL,\"LEGACY\",?,?,?,?,?,?,?,?,?,?,\"999\",?,?,?,?,?,?,?,?,?,?,?,?,NOW(),?,?,?)", 1);
      ps.setString(index++, formDetails.getSuspectName());
      ps.setString(index++, formDetails.getMobileNo());
      ps.setString(index++, formDetails.getAge());
      ps.setString(index++, formDetails.getDob());
      ps.setString(index++, formDetails.getGender());
      ps.setString(index++, formDetails.getFatherName());
      ps.setString(index++, formDetails.getMotherName());
      ps.setString(index++, formDetails.getMaritalStatus());
      ps.setString(index++, formDetails.getSpouseName());
      ps.setString(index++, formDetails.getPob());
      ps.setString(index++, formDetails.getProfession());
      ps.setString(index++, formDetails.getHeight());
      ps.setString(index++, formDetails.getHairColor());
      ps.setString(index++, formDetails.getEyeColor());
      ps.setString(index++, formDetails.getIdentificationMark());
      ps.setString(index++, formDetails.getComplexion());
      ps.setString(index++, formDetails.getFtCaseNo());
      ps.setString(index++, formDetails.getSpCaseNo());
      ps.setString(index++, orderNoticeSpRef.getSpDistName());
      ps.setString(index++, orderNoticeSpRef.getFt_id());
      ps.setString(index++, "18");
      ps.setString(index++, orderNoticeSpRef.getSpPsName());
      ps.setString(index++, user);
      ps.setString(index++, district + "/" + ft_no);
      ps.setString(index++, formDetails.getImdtNo());
      int x = ps.executeUpdate();
      ResultSet rs = ps.getGeneratedKeys();
      long generatedKey = 0L;
      if (rs.next())
        generatedKey = rs.getLong(1); 
      return generatedKey;
    } catch (SQLException e) {
      System.out.println("Exception saving Suspect Details: " + e.getMessage());
      return 0L;
    } finally {
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (ps1 != null)
          ps1.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (conn != null)
          conn.close(); 
      } catch (SQLException e) {
        System.out.println("Exception Closing Connection : " + e.getMessage());
      } 
    } 
  }
  
  public boolean doSaveRelative(ArrayList<RelativeDetails> relDetailsList, long suspectId) {
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    boolean res = true;
    PreparedStatement ps = null;
    try {
      for (RelativeDetails relativeDetails : relDetailsList) {
        int index = 1;
        ps = conn.prepareStatement("INSERT INTO trans_suspect_family(`member_id`, `fk_suspect_id`, `fk_relation_code`, `member_name`,      `age`, `date_of_birth`, `place_of_birth`) VALUES (NULL,?,?,?,?,?,?)");
        ps.setString(index++, String.valueOf(suspectId));
        ps.setString(index++, relativeDetails.getRelation());
        ps.setString(index++, relativeDetails.getName());
        ps.setString(index++, relativeDetails.getMemberAge());
        ps.setString(index++, relativeDetails.getDateOfBirth());
        ps.setString(index++, relativeDetails.getPlaceOfBirth());
        int x = ps.executeUpdate();
        if (x == 0)
          res = false; 
      } 
    } catch (SQLException e) {
      res = false;
      System.out.println("Exception saving Suspect Details: " + e.getMessage());
    } finally {
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (conn != null)
          conn.close(); 
      } catch (SQLException e) {
        System.out.println("Exception Closing Connection " + e.getMessage());
      } 
    } 
    return res;
  }
  
  public boolean doSaveAddress(OrderNoticeSpRef orderNoticeSpRef, AddressDetails addDetails, long suspectId) {
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    boolean res = true;
    PreparedStatement ps = null, ps1 = null, ps2 = null;
    try {
      int index = 1;
      ps = conn.prepareStatement("INSERT INTO trans_suspect_address(`address_id`, `fk_suspect_id`, `fk_address_type_code`, `fk_suspect_district_code`, `fk_village_code`, `fk_suspect_thana_code`) VALUES(NULL, ?, \"1\", ?, ?, ?)");
      ps.setString(index++, String.valueOf(suspectId));
      ps.setString(index++, orderNoticeSpRef.getSpDistName());
      ps.setString(index++, orderNoticeSpRef.getSpVillageName());
      ps.setString(index++, orderNoticeSpRef.getSpPsName());
      int x = ps.executeUpdate();
      if (x == 0)
        res = false; 
    } catch (SQLException e) {
      res = false;
      System.out.println("Exception saving Suspect Details: " + e.getMessage());
    } finally {
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
    } 
    try {
      int index = 1;
      ps1 = conn.prepareStatement("INSERT INTO trans_suspect_address(`address_id`, `fk_suspect_id`, `fk_address_type_code`, `fk_village_code`, `other_village`,`post_office`, `revenue_village_name`,  `fk_thana_code`, `other_thana`, `fk_district_code`, `other_district`, `fk_state_code`, `other_state`,`pin_code`, `latitude`, `longitude`, `village_head_name`, `village_head_phone_no`, `fk_country_code`, `other_country`,`created_at`, `created_by`) VALUES(NULL, ?, \"2\", \"999999\",?,?,?,\"9999999\",?,\"999\",?,\"99\",?,?,?,?,?,?,\"91\",\"INDIA\", NOW(),\"bhaskor\")");
      ps1.setString(index++, String.valueOf(suspectId));
      ps1.setString(index++, addDetails.getVill());
      ps1.setString(index++, addDetails.getPs());
      ps1.setString(index++, addDetails.getRevVill());
      ps1.setString(index++, addDetails.getPoliceStation());
      ps1.setString(index++, addDetails.getDistrict());
      ps1.setString(index++, addDetails.getState());
      ps1.setString(index++, addDetails.getPinCode());
      ps1.setString(index++, addDetails.getLatitute());
      ps1.setString(index++, addDetails.getLongitude());
      ps1.setString(index++, addDetails.getVillageHead());
      ps1.setString(index++, addDetails.getVillHeadPh());
      int x = ps1.executeUpdate();
      if (x == 0)
        res = false; 
    } catch (SQLException e) {
      res = false;
      System.out.println("Exception saving Suspect Details: " + e.getMessage());
    } finally {
      try {
        if (ps1 != null)
          ps1.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
    } 
    try {
      int index = 1;
      ps2 = conn.prepareStatement("INSERT INTO trans_suspect_address( `address_id`, `fk_suspect_id`, `fk_address_type_code`, `fk_country_code`, `other_country`, `fk_state_code`, `other_state`, `fk_district_code`, `other_district`, `fk_thana_code`, `other_thana`, `fk_village_code`, `other_village` ) VALUES(NULL, ?, \"3\", \"00\",?,\"99\",?,\"999\",?,\"9999999\",?,\"999999\",?)");
      ps2.setString(index++, String.valueOf(suspectId));
      ps2.setString(index++, addDetails.getOriginCountry());
      ps2.setString(index++, addDetails.getOriginState());
      ps2.setString(index++, addDetails.getOriginDistrict());
      ps2.setString(index++, addDetails.getOriginPs());
      ps2.setString(index++, addDetails.getOriginVillage());
      int x = ps2.executeUpdate();
      if (x == 0)
        res = false; 
    } catch (SQLException e) {
      res = false;
      System.out.println("Exception saving Suspect Details: " + e.getMessage());
    } finally {
      try {
        if (ps2 != null)
          ps2.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
    } 
    try {
      if (conn != null)
        conn.close(); 
    } catch (SQLException e) {
      System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
    } 
    return res;
  }
  
  public boolean doSaveOrder(OrderNoticeSpRef orderNoticeSpRef, long suspectId) {
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    boolean res = true;
    PreparedStatement ps = null, ps1 = null, ps2 = null, ps3 = null, ps4 = null;
    try {
      int index = 1;
      ps = conn.prepareStatement("INSERT INTO trans_ft_case_register( `ft_case_id`,  `ft_case_no`,  `police_case_no`,  `fk_suspect_id`,`created_at`,`created_by` ) VALUES(NULL,?,?,?,NOW(),'bhaskor')", 1);
      ps.setString(1, orderNoticeSpRef.getFtCaseNo());
      ps.setString(2, orderNoticeSpRef.getSpRefNo());
      ps.setString(3, String.valueOf(suspectId));
      int x = ps.executeUpdate();
      if (x == 0)
        res = false; 
      ResultSet rs = ps.getGeneratedKeys();
      long ft_case_id = 0L;
      if (rs.next())
        ft_case_id = rs.getLong(1); 
      index = 1;
      ps1 = conn.prepareStatement("INSERT INTO trans_ft_notice_register( `ft_notice_id`,  `fk_case_id`,  `type_of_notice`,  `date_of_hearing`,  `date_of_issue`,  `thana_name` ) VALUES(NULL,?,?,?,?,?)");
      ps1.setString(index++, String.valueOf(ft_case_id));
      ps1.setString(index++, orderNoticeSpRef.getNoticeType());
      ps1.setString(index++, orderNoticeSpRef.getHearingDate());
      ps1.setString(index++, orderNoticeSpRef.getNoticeIssueDate());
      ps1.setString(index++, orderNoticeSpRef.getNoticeThana());
      int x1 = ps1.executeUpdate();
      if (x1 == 0)
        res = false; 
      index = 1;
      ps2 = conn.prepareStatement("INSERT INTO trans_ft_judgement_register( `ft_judgement_id`,  `fk_case_id`,  `order_date` ) VALUES(NULL,?,?)");
      ps2.setString(index++, String.valueOf(ft_case_id));
      ps2.setString(index++, orderNoticeSpRef.getFirstOrderDate());
      int x2 = ps2.executeUpdate();
      if (x2 == 0)
        res = false; 
      if (!orderNoticeSpRef.getLastOrderDate().trim().equals("")) {
        index = 1;
        ps3 = conn.prepareStatement("INSERT INTO trans_ft_judgement_register( `ft_judgement_id`,  `fk_case_id`,  `order_date` ) VALUES(NULL,?,?)");
        ps3.setString(index++, String.valueOf(ft_case_id));
        ps3.setString(index++, orderNoticeSpRef.getLastOrderDate());
        int x3 = ps3.executeUpdate();
        if (x3 == 0)
          res = false; 
      } 
      index = 1;
      ps4 = conn.prepareStatement("INSERT INTO trans_ft_final_opinion( `ft_opinion_id`,  `fk_case_id`,  `order_date`,  `final_opinion` ) VALUES(NULL,?,?,?)");
      ps4.setString(index++, String.valueOf(ft_case_id));
      ps4.setString(index++, orderNoticeSpRef.getFinalOrderDate());
      ps4.setString(index++, orderNoticeSpRef.getOpinionType());
      int x4 = ps4.executeUpdate();
      if (x4 == 0)
        res = false; 
    } catch (SQLException e) {
      res = false;
      System.out.println("Exception saving Suspect Details: " + e.getMessage());
    } finally {
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (ps1 != null)
          ps1.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (ps2 != null)
          ps2.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (ps3 != null)
          ps3.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
      try {
        if (ps4 != null)
          ps4.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Closing : SuspectDao - " + e.getMessage());
      } 
    } 
    try {
      if (conn != null)
        conn.close(); 
    } catch (SQLException e) {
      System.out.println("Exception Connection Closing : - " + e.getMessage());
    } 
    return res;
  }
}
