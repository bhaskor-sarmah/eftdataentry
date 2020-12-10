package dao;

import initialize.ContextListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ImageList;
import model.ModelForDropDown;

public class UtilDao {
  public ArrayList<ImageList> getPendingImageFile(String username) {
    ArrayList<ImageList> imgList = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    PreparedStatement ps = null, ps1 = null;
    ResultSet rs = null, rs1 = null;
    try {
      ps = conn.prepareStatement("SELECT FOLDER FROM imagemaster WHERE FLAG1 = ? AND FLAG IS NULL LIMIT 1");
      ps.setString(1, username);
      rs = ps.executeQuery();
      if (rs.next()) {
        String folder = rs.getString("FOLDER");
        try {
          ps1 = conn.prepareStatement("SELECT ID,PATH FROM imagemaster WHERE FLAG IS NULL AND FOLDER = ?");
          ps1.setString(1, folder);
          rs1 = ps1.executeQuery();
          while (rs1.next()) {
            ImageList img = new ImageList();
            img.setId(rs1.getInt("ID"));
            img.setFolder(folder.replaceAll("\\\\", "/"));
            img.setPath(rs1.getString("PATH").replaceAll("\\\\", "/"));
            imgList.add(img);
          } 
        } catch (SQLException e) {
          System.out.println("Exception Reading imagemaster - Image Dao - " + e.getMessage());
        } finally {
          try {
            if (rs1 != null)
              rs1.close(); 
          } catch (SQLException e) {
            System.out.println("Exception RS1 Closing - Image Dao - " + e.getMessage());
          } 
          try {
            if (ps1 != null)
              ps1.close(); 
          } catch (SQLException e) {
            System.out.println("Exception RS1 Closing - Image Dao - " + e.getMessage());
          } 
        } 
      } 
    } catch (SQLException e) {
      System.out.println("Exception Reading Db - Image Dao - " + e.getMessage());
    } finally {
      try {
        if (rs != null)
          rs.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
    } 
    return imgList;
  }
  
  public ArrayList<ModelForDropDown> getDistrictList() {
    ArrayList<ModelForDropDown> mdList = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    PreparedStatement ps = null, ps1 = null;
    ResultSet rs = null, rs1 = null;
    try {
      ps = conn.prepareStatement("SELECT * FROM master_district ORDER BY district_name");
      rs = ps.executeQuery();
      while (rs.next()) {
        if (!rs.getString("district_code").equals("000")) {
          ModelForDropDown md = new ModelForDropDown();
          md.setCode(rs.getString("district_code"));
          md.setName(rs.getString("district_name"));
          mdList.add(md);
        } 
      } 
    } catch (SQLException e) {
      System.out.println("Exception Reading Db - Image Dao - " + e.getMessage());
    } finally {
      try {
        if (rs != null)
          rs.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
    } 
    return mdList;
  }
  
  public ArrayList<ModelForDropDown> getThanaList(String distCode) {
    ArrayList<ModelForDropDown> mdList = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    PreparedStatement ps = null, ps1 = null;
    ResultSet rs = null, rs1 = null;
    try {
      ps = conn.prepareStatement("SELECT * FROM master_thana where fk_district_code = ?  ORDER BY thana_name");
      ps.setString(1, distCode);
      rs = ps.executeQuery();
      while (rs.next()) {
        ModelForDropDown md = new ModelForDropDown();
        md.setCode(rs.getString("thana_code"));
        md.setName(rs.getString("thana_name"));
        mdList.add(md);
      } 
    } catch (SQLException e) {
      System.out.println("Exception Reading Db - Image Dao - " + e.getMessage());
    } finally {
      try {
        if (rs != null)
          rs.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
    } 
    return mdList;
  }
  
  public ArrayList<ModelForDropDown> getVillList(String thanaCode) {
    ArrayList<ModelForDropDown> mdList = new ArrayList<>();
    Connection conn = null;
    try {
      conn = ContextListener.ds.getConnection();
    } catch (SQLException e) {
      System.out.println("Connection Exception : Image Dao - " + e.getMessage());
    } 
    PreparedStatement ps = null, ps1 = null;
    ResultSet rs = null, rs1 = null;
    try {
      ps = conn.prepareStatement("SELECT * FROM master_village  where fk_thana_code = ?  ORDER BY village_name");
      ps.setString(1, thanaCode);
      rs = ps.executeQuery();
      while (rs.next()) {
        ModelForDropDown md = new ModelForDropDown();
        md.setCode(rs.getString("village_code"));
        md.setName(rs.getString("village_name"));
        mdList.add(md);
      } 
    } catch (SQLException e) {
      System.out.println("Exception Reading Db - Image Dao - " + e.getMessage());
    } finally {
      try {
        if (rs != null)
          rs.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
      try {
        if (ps != null)
          ps.close(); 
      } catch (SQLException e) {
        System.out.println("Exception RS Closing - Image Dao - " + e.getMessage());
      } 
    } 
    return mdList;
  }
}
