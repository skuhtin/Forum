package forum.servlet;

import forum.dao.UsersDao;
import forum.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class LoginServlet extends HttpServlet{
  UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    resp.setContentType("text/html;charset=UTF-8");
    viewPage(out);
    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    String page = null;
    if (usersDao.userIsAbsent(login,password)) {
      page = "/ban";
    } else page = "/forum";
    Cookie cookieLog = new Cookie("login", login);
    cookieLog.setMaxAge(300);
    resp.addCookie(cookieLog);
    resp.sendRedirect(page);
  }

  private void viewPage(PrintWriter out) {
    out.print("<html><head>");
    out.print("<title>Authentication form</title>");
    out.print("</head>");
    out.print("<body>");
    out.print("<h1>Welcome to our forum!</h1>");
    out.print("<form action=\"/login\" method=\"POST\">");
    out.print("Login:<input type=\"text\" name=\"login\">");
    out.print("<p> Psswd: <input type=\"password\" name=\"password\"><br>");
    out.print("<p><input type=\"submit\" name=\"enter\" value=\"Enter\">");
    out.print("</form>");
    out.print("<h3>Registration form here: </h3>");
    out.print("<form action=\"/ban\" method=\"GET\">");
    out.print("<input type=\"submit\" name=\"registration\" value=\"Registration\"><br>");
    out.print("</form>");
    out.println("</body></html>");
  }
}
