package servlet;

import dao.LoginDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
  protected void processRequestGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    request.getRequestDispatcher("./login.jsp").forward((ServletRequest)request, (ServletResponse)response);
  }
  
  protected void processRequestPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    LoginDao lgn = new LoginDao();
    if (session != null)
      session.invalidate(); 
    session = request.getSession(true);
    if (lgn.checkLogin(username, password)) {
      session.setAttribute("username", username);
      session.setAttribute("userlogged", "1");
      String imagePathRead = getServletContext().getInitParameter("imgPathRead");
      request.setAttribute("imagePathRead", imagePathRead);
      request.getRequestDispatcher("./readFile.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } else {
      request.setAttribute("msg", "<div class=\"alert alert-danger\">Invalid Login !</div>");
      request.getRequestDispatcher("./login.jsp").forward((ServletRequest)request, (ServletResponse)response);
    } 
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequestGet(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    processRequestPost(request, response);
  }
  
  public String getServletInfo() {
    return "Short description";
  }
}
