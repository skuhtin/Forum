package forum.servlet;

import forum.dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class LoginServlet extends HttpServlet{
  UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req,resp);
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
    resp.addCookie(cookieLog);
    resp.sendRedirect(page);
  }

}
