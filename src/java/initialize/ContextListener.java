package initialize;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class ContextListener implements ServletContextListener {
  public static DataSource ds = null;
  
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Enter init");
    try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context)initCtx.lookup("java:comp/env");
      ds = (DataSource)envCtx.lookup("jdbc/eft_digitize_db");
    } catch (NamingException ex) {
      Logger.getLogger(ContextListener.class.getName()).log(Level.SEVERE, (String)null, ex);
      System.out.println("Connection Exception :" + ex.getMessage());
    } 
    System.out.println("Init Completed.........");
  }
  
  public void contextDestroyed(ServletContextEvent sce) {}
}
