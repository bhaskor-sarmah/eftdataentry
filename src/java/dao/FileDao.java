package dao;

import initialize.ContextListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.CustomUtils;

public class FileDao {
  public String readFile(String path) {
    String result = "";
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      return "<div class\"alert alert-danger\">Error Reading Files ! Contact Support Team.</div>";
    } 
    PreparedStatement ps = null, ps1 = null, ps2 = null;
    ResultSet rs = null, rs1 = null, rs2 = null;
    ArrayList<File> fileList = new ArrayList<>();
    fileList = CustomUtils.listf(path, fileList);
    int cnt = 0;
    for (File f : fileList) {
      cnt++;
      if (cnt % 100 == 0) {
        try {
          if (conn != null)
            conn.close(); 
        } catch (SQLException e) {
          System.out.println("Error Closing Connection");
        } 
        try {
          conn = ContextListener.ds.getConnection();
        } catch (SQLException e) {
          return "<div class\"alert alert-danger\">Error Reading Files ! Contact Support Team.</div>";
        } 
      } 
      System.out.println(f.getAbsolutePath());
      path = f.getAbsolutePath();
      int i = 0;
      try {
        ps = conn.prepareStatement("SELECT * FROM imagemaster WHERE path = ?");
        ps.setString(++i, f.getAbsolutePath());
        rs = ps.executeQuery();
        if (rs.next()) {
          System.out.println("File Already Present in Db");
        } else {
          String file_name = "", date = "", sp_case_no = "", folder = "", district = "", ft_no = "";
          String[] arr = path.split("/");
          try {
            file_name = arr[arr.length - 1];
            sp_case_no = arr[arr.length - 2];
            date = arr[arr.length - 3];
            ft_no = arr[arr.length - 4];
            district = arr[arr.length - 5];
            folder = path.replaceAll(file_name, "");
          } catch (Exception e) {
            System.out.println("Exception File Parameter Spliting for file - " + path);
          } 
          i = 0;
          ps1 = conn.prepareStatement("INSERT INTO imagemaster VALUES(NULL,?,?,?,?,?,?,?,NULL,NULL,NULL,NULL,NULL)");
          ps1.setString(++i, f.getAbsolutePath());
          ps1.setString(++i, file_name);
          ps1.setString(++i, date);
          ps1.setString(++i, sp_case_no);
          ps1.setString(++i, folder);
          ps1.setString(++i, district);
          ps1.setString(++i, ft_no);
          int j = ps1.executeUpdate();
        } 
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
    } 
    try {
      if (conn != null)
        conn.close(); 
    } catch (SQLException e) {
      System.out.println("Error Closing Connection");
    } 
    return result;
  }
}
