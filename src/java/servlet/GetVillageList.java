package servlet;

import dao.UtilDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ModelForDropDown;

@WebServlet(name = "GetVillageList", urlPatterns = {"/GetVillageList"})
public class GetVillageList extends HttpServlet {
  protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String thanaCode = request.getParameter("thana");
    UtilDao dao = new UtilDao();
    try (PrintWriter out = response.getWriter()) {
      ArrayList<ModelForDropDown> villList = dao.getVillList(thanaCode);
      villList.forEach(vill -> out.println("<option value=\"" + vill.getCode() + "\">" + vill.getName() + "</option>"));
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
