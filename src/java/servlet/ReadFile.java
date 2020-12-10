package servlet;

import dao.FileDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ReadFile", urlPatterns = {"/ReadFile"})
public class ReadFile extends HttpServlet {
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("username") != null && session.getAttribute("userlogged").equals("1")) {
      String submit = request.getParameter("Submit");
      if (submit.equals("Read File")) {
        FileDao fileDao = new FileDao();
        String path = request.getParameter("path");
        String result = fileDao.readFile(path);
        if (result.equals("")) {
          String imagePathRead = getServletContext().getInitParameter("imgPathRead");
          request.setAttribute("imagePathRead", imagePathRead);
          request.setAttribute("msg", "<div class=\"alert alert-success\">Files reading completed ! Click Digitize File to start digitizing</div>");
          request.getRequestDispatcher("./readFile.jsp").forward((ServletRequest)request, (ServletResponse)response);
        } else {
          request.setAttribute("msg", result);
          request.getRequestDispatcher("./index.jsp").forward((ServletRequest)request, (ServletResponse)response);
        } 
      } else if (submit.equals("Digitize File")) {
        request.getRequestDispatcher("./DigitizeFile").forward((ServletRequest)request, (ServletResponse)response);
      } else {
        System.out.println("Invalid submit button value");
        request.setAttribute("msg", "<div class=\"alert alert-danger\">Invalid Request Received</div>");
        request.getRequestDispatcher("./login.jsp").forward((ServletRequest)request, (ServletResponse)response);
      } 
    } else {
      request.setAttribute("msg", "<div class=\"alert alert-danger\">Unauthorised ! Please try to log in.</div>");
      request.getRequestDispatcher("./login.jsp").forward((ServletRequest)request, (ServletResponse)response);
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
