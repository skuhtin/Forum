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


public class SendMessageServlet extends HttpServlet{
  MessageDao messageDao = new MessageDao();
  UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = getUserName(req);
    String page;
    if (userName == null){
      page = "/FORUM/login";
    } else if (usersDao.getUserbyLogin(userName).isBan()) {
      page = "/FORUM/ban";
    } else {
      page = "/WEB-INF/jsp/sendMsg.jsp";
      String loginPage = "/FORUM/login";
      String actionUser = getUserForSend(req);
      int newMsg = messageDao.getNewMessages(userName);
      String link = "/FORUM/message/" + getUserForSend(req);
      String returnLink = "/FORUM/forum";
      req.setAttribute("userName", userName);
      req.setAttribute("countNewMessage", newMsg);
      req.setAttribute("actionUser", actionUser);
      req.setAttribute("link", link);
      req.setAttribute("returnLink", returnLink);
      req.setAttribute("loginPage",loginPage);
    }
    req.getRequestDispatcher(page).forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String text = req.getParameter("message");
    String fromUser = getUserName(req);
    int newMsg = messageDao.getNewMessages(fromUser);
    String toUser = getUserForSend(req);
    Message message = new Message(fromUser,toUser,text);
    messageDao.insertMessage(message);
    String loginPage = "/FORUM/login";
    String returnLink = "/FORUM/forum";
    req.setAttribute("returnLink", returnLink);
    req.setAttribute("loginPage",loginPage);
    req.setAttribute("userName", fromUser);
    req.setAttribute("countNewMessage", newMsg);
    req.getRequestDispatcher("/WEB-INF/jsp/responseMsg.jsp").forward(req,resp);

  }
  private String getUserForSend(HttpServletRequest req) {
    String parameters = req.getPathInfo().substring(1);
    return parameters;
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
