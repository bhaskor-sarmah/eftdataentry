package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetImage", urlPatterns = {"/GetImage"})
public class GetImage extends HttpServlet {
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    ServletOutputStream servletOutputStream = null;
    FileInputStream in = null;
    OutputStream out = null;
    try {
      response.setContentType("image/jpeg");
      String str = request.getParameter("img");
      System.out.println(str);
      File file = new File(str);
      response.setContentLength((int)file.length());
      in = new FileInputStream(file);
      servletOutputStream = response.getOutputStream();
      byte[] buf = new byte[1024];
      int count = 0;
      while ((count = in.read(buf)) >= 0)
        servletOutputStream.write(buf, 0, count); 
    } catch (IOException e) {
      System.out.println("IOException :" + e.getMessage());
    } finally {
      try {
        if (in != null)
          in.close(); 
      } catch (Exception exception) {}
      try {
        if (servletOutputStream != null)
          servletOutputStream.close(); 
      } catch (Exception exception) {}
    } 
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequest(request, response);
  }
  
  public String getServletInfo() {
    return "Short description";
  }
}
