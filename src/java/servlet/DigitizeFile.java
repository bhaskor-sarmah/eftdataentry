package servlet;

import dao.UtilDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ImageList;
import model.ModelForDropDown;

@WebServlet(name = "DigitizeFile", urlPatterns = {"/DigitizeFile"})
public class DigitizeFile extends HttpServlet {
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    if (session.getAttribute("username") != null && session.getAttribute("userlogged").equals("1")) {
      UtilDao dao = new UtilDao();
      ArrayList<ImageList> imgList = dao.getPendingImageFile(session.getAttribute("username").toString());
      if (imgList.isEmpty()) {
        String imagePathRead = getServletContext().getInitParameter("imgPathRead");
        request.setAttribute("imagePathRead", imagePathRead);
        request.setAttribute("msg", "<div class=\"alert alert-warning\">No data pending for digitization.</div>");
        request.getRequestDispatcher("./readFile.jsp").forward((ServletRequest)request, (ServletResponse)response);
        return;
      } 
      ArrayList<ModelForDropDown> distList = dao.getDistrictList();
      request.setAttribute("imgList", imgList);
      request.setAttribute("distList", distList);
      request.getRequestDispatcher("./index.jsp").forward((ServletRequest)request, (ServletResponse)response);
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
