package dao;

import initialize.ContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CountListObject;
import model.CountModel;
import model.User;

public class CountDao {
  public List<CountListObject> getCountList() {
    List<CountModel> countList = new ArrayList<>();
    List<String> userList = new ArrayList<>();
    List<CountListObject> allList = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Exception : " + e.getMessage());
      return null;
    } 
    PreparedStatement ps = null, ps1 = null, ps2 = null;
    ResultSet rs = null, rs1 = null, rs2 = null;
    try {
      ps = conn.prepareStatement("SELECT USERNAME FROM master_user WHERE IS_ACTIVE = \"1\"");
      rs = ps.executeQuery();
      while (rs.next())
        userList.add(rs.getString(1).toUpperCase()); 
    } catch (SQLException ex) {
      System.out.println("Exception : " + ex.getMessage());
    } finally {
      try {
        if (rs != null)
          rs.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Close in FileDao");
      } 
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Close in FileDao");
      } 
    } 
    try {
      ps1 = conn.prepareStatement("SELECT DISTINCT(SUBSTRING_INDEX(created_at,\" \",1)) FROM suspect_image order by created_at");
      rs1 = ps1.executeQuery();
      while (rs1.next()) {
        List<User> userCountList = new ArrayList<>();
        ps2 = conn.prepareStatement("SELECT FLAG1,COUNT(DISTINCT FOLDER) FROM suspect_image WHERE FLAG IS NOT NULL AND SUBSTRING_INDEX(created_at,\" \",1) = ? GROUP BY FLAG1");
        ps2.setString(1, rs1.getString(1));
        rs2 = ps2.executeQuery();
        while (rs2.next()) {
          for (String user : userList) {
            if (user.equals(rs2.getString(1).toUpperCase())) {
              User usr = new User();
              usr.setUserId(user);
              usr.setUserCount(rs2.getString(2));
              userCountList.add(usr);
            } 
          } 
        } 
        CountModel cnt = new CountModel();
        cnt.setDate(rs1.getString(1));
        List<String> removeListOne = new ArrayList<>();
        List<String> removeListTwo = new ArrayList<>(userList);
        for (User user : userCountList)
          removeListOne.add(user.getUserId()); 
        removeListTwo.removeAll(removeListOne);
        for (String s : removeListTwo) {
          User usr = new User();
          usr.setUserId(s);
          usr.setUserCount("0");
          userCountList.add(usr);
        } 
        cnt.setUserList(userCountList);
        countList.add(cnt);
      } 
    } catch (SQLException ex) {
      System.out.println("Exception : " + ex.getMessage());
    } finally {
      try {
        if (rs1 != null)
          rs1.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Close in FileDao");
      } 
      try {
        if (ps1 != null)
          ps1.close(); 
      } catch (SQLException e) {
        System.out.println("Exception PS Close in FileDao");
      } 
    } 
    CountListObject clt = new CountListObject();
    clt.setCountList(countList);
    clt.setUserList(userList);
    allList.add(clt);
    return allList;
  }
}

