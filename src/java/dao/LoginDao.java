package dao;

import initialize.ContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
  public boolean checkLogin(String username, String password) {
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Exception Creating Connection : " + e.getMessage());
      return false;
    } 
    PreparedStatement ps = null, ps1 = null, ps2 = null;
    ResultSet rs = null, rs1 = null, rs2 = null;
    try {
      ps = conn.prepareStatement("SELECT * FROM master_user WHERE USERNAME = ? and PASSWORD = PASSWORD(?) and IS_ACTIVE = \"1\"");
      ps.setString(1, username);
      ps.setString(2, password);
      rs = ps.executeQuery();
      if (rs.next())
        return true; 
    } catch (SQLException e) {
      System.out.println("Exception Reading User Master : " + e.getMessage());
    } finally {
      try {
        if (rs != null)
          rs.close(); 
      } catch (SQLException e) {
        System.out.println("Exception : " + e.getMessage());
      } 
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception : " + e.getMessage());
      } 
      try {
        if (conn != null)
          conn.close(); 
      } catch (SQLException e) {
        System.out.println("Exception : " + e.getMessage());
      } 
    } 
    return false;
  }
}
