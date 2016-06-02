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
import java.util.List;


public class AdminPageServlet extends HttpServlet {
  UsersDao usersDao = new UsersDao();
  MessageDao messageDao = new MessageDao();
  String actionBun = "Bun";
  String actionUnBun = "UnBun";
  String actionKick = "Kick";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = getUserName(req);
    String actionUser = req.getParameter("actionUser");
    if (actionUser == null) {
      actionUser = req.getPathInfo().substring(1);
    }
    String page;
    if (userName == null) {
      page = "/login";
    } else if (usersDao.getUserbyLogin(userName).isBan()) {
      page = "/ban";
    } else if (usersDao.getUserbyLogin(actionUser) == null) {
      sidebarAttributes(req);
      page = "/WEB-INF/jsp/errorUser.jsp";
    }else {
      page = "/WEB-INF/jsp/admin.jsp";
      sidebarAttributes(req);
      int newMsg = messageDao.getNewMessages(userName);
      req.setAttribute("countNewMessage", newMsg);
      req.setAttribute("actionUser", actionUser);
      req.setAttribute("userName", userName);
      req.setAttribute("actionBun", actionBun);
      req.setAttribute("actionUnBun", actionUnBun);
      req.setAttribute("actionKick", actionKick);
    }
    req.getRequestDispatcher(page).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String userName = getUserName(req);
    String action = req.getParameter("action");
    String actionUser = req.getPathInfo().substring(1);
    String test = "false";
    if (action.equals(actionBun)) {
      usersDao.banProperty(actionUser, true);
    } else if (action.equals(actionUnBun)) {
      usersDao.banProperty(actionUser, false);
      test = actionUnBun;
    } else if (action.equals(actionKick)) {
      usersDao.kickUser(actionUser);
      test = actionKick;
    }
    String page = "/WEB-INF/jsp/actionResponse.jsp";
    sidebarAttributes(req);
    int newMsg = messageDao.getNewMessages(userName);
    req.setAttribute("countNewMessage", newMsg);
    req.setAttribute("actionUser", actionUser);
    req.setAttribute("action", action);
    req.setAttribute("userName", userName);
    req.setAttribute("test", test);
    req.getRequestDispatcher(page).forward(req, resp);
  }

  private void sidebarAttributes(HttpServletRequest req) {
    List<User> users = usersDao.getBannedUser();
    String topicPage = "/forum";
    String loginPage = "/login";
    req.setAttribute("topicPage", topicPage);
    req.setAttribute("loginPage", loginPage);
    req.setAttribute("users", users);

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
