package servlet;

import dao.CountDao;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CountListObject;

@WebServlet(name = "GetCount", urlPatterns = {"/GetCount"})
public class GetCount extends HttpServlet {
  CountDao dao = new CountDao();
  
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<CountListObject> allList = this.dao.getCountList();
    if (((CountListObject)allList.get(0)).getCountList() != null && ((CountListObject)allList.get(0)).getUserList() != null) {
      request.setAttribute("countList", ((CountListObject)allList.get(0)).getCountList());
      request.setAttribute("userList", ((CountListObject)allList.get(0)).getUserList());
    } else {
      request.setAttribute("msg", "<div class=\"alert alert-danger\">Some Error Has Ocurred</div>");
    } 
    request.getRequestDispatcher("./count.jsp").forward((ServletRequest)request, (ServletResponse)response);
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
