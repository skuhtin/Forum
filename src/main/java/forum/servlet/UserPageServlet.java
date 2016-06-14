package forum.servlet;

import forum.dao.MessageDao;
import forum.dao.UsersDao;
import forum.model.Message;
import forum.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UserPageServlet extends HttpServlet {
  UsersDao usersDao = new UsersDao();
  MessageDao messageDao = new MessageDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = getUserName(req);
    String page = null;
    if (userName == null) {
      page = "/FORUM/login";
    } else if (usersDao.getUserbyLogin(userName).isBan()) {
      page = "/FORUM/ban";
    } else {
      page = "/WEB-INF/jsp/userPage.jsp";
      messageDao.setReadMessage(userName);
      int countNewMessage = messageDao.getNewMessages(userName);
      String loginPage = "/FORUM/login";
      String forumPage = "/FORUM/forum";
      List<Message> messages = messageDao.loadMessage(userName);
      req.setAttribute("loginPage", loginPage);
      req.setAttribute("forumPage", forumPage);
      req.setAttribute("messages", messages);
      req.setAttribute("userName", userName);
      req.setAttribute("countNewMessage", countNewMessage);
    }
    req.getRequestDispatcher(page).forward(req, resp);
  }

  private String getUserName(HttpServletRequest req) throws IOException, ServletException {
    String userName = null;
    try {
      Cookie[] cookies = req.getCookies();
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("login")) {
          userName = cookie.getValue();
        }
      }
    } catch (NullPointerException e) {
      return null;
    }
    return userName;
  }
}
