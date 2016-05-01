package forum.servlet;


import forum.dao.UsersDao;
import forum.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NonAuthPageServlet extends HttpServlet {
  UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    viewPage(out);
    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    if (usersDao.sameLogin(login)) {
      PrintWriter out = resp.getWriter();
      out.print("<html><body>");
      out.print("<h3>Somebody has already used this login. Please, try again: </h3>");
      registrationForm(out);
      out.close();
    } else registerUser(resp, login, password);
  }

  private void registerUser(HttpServletResponse resp, String login, String password) throws IOException {
    usersDao.insertUser(new User(login, password));
    PrintWriter out = resp.getWriter();
    out.print("<html><body>");
    out.print("<h3>Thank you. Waiting in our forum!</h3>");
    out.print("<form action=\"/login\" method=\"GET\">");
    out.print("<p><input type=\"submit\" value=\"Login now!\">");
    out.print("</body></html>");
    out.close();
  }

  private void viewPage(PrintWriter out) {
    out.print("<html><body>");
    out.print("<h1>You are not authorized or were banned.</h1>");
    registrationForm(out);
    out.print("<h3>Or try again, please:</h3>");
    out.print("<form action=\"/login\" method=\"GET\">");
    out.print("<input type=\"submit\" value=\"Try again!\">");
    out.print("</form>");
    out.print("</body></html>");
  }

  private void registrationForm(PrintWriter out) {
    out.print("<h3>Registration form:</h3>");
    out.print("<form action=\"/ban\" method=\"POST\">");
    out.print("<p>Enter your login: <input type=\"text\" name=\"login\">");
    out.print("<p>Enter your pswd: <input type=\"password\" name=\"password\">");
    out.print("<p><input type=\"submit\" name=\"send\" value=\"Send\">");
    out.print("</form>");
  }
}
