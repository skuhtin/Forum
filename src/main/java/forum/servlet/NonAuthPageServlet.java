package forum.servlet;


import forum.dao.UsersDao;
import forum.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class NonAuthPageServlet extends HttpServlet {
  UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("/WEB-INF/jsp/registrationPage.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String login = req.getParameter("login");
    String password = req.getParameter("password");
    String page;
    if (usersDao.sameLogin(login)) {
      page = "/WEB-INF/jsp/nonSuccRegPage.jsp";
    } else {
      usersDao.insertUser(new User(login, password));
      page = "/WEB-INF/jsp/succRegPage.jsp";
    }
    req.getRequestDispatcher(page).forward(req, resp);
  }
}
