package forum.servlet;

import forum.dao.MessageDao;
import forum.dao.TopicDao;
import forum.dao.UsersDao;
import forum.model.Topic;
import forum.model.User;

import java.io.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TopicsServlet extends HttpServlet {

  private TopicDao topicDao = new TopicDao();
  private MessageDao messageDao = new MessageDao();
  private UsersDao usersDao = new UsersDao();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = getUserName(req);
    String page;
    String forumPage = "/FORUM/forum";
    String loginPage = "/FORUM/login";
    if (userName == null){
      page = "/FORUM/login";
    } else if (usersDao.getUserbyLogin(userName).isBan()) {
      page = "/FORUM/ban";
    }else {
      page = "/WEB-INF/jsp/topic.jsp";
      User user = usersDao.getUserbyLogin(userName);
      boolean userIsAdmin = user.isAdmin();

      int countNewMessage = messageDao.getNewMessages(userName);
      Map<Integer, Topic> topics = topicDao.loadTopic();
      req.setAttribute("userName", userName);
      req.setAttribute("userIsAdmin", userIsAdmin);
      req.setAttribute("countNewMessage", countNewMessage);
      req.setAttribute("topics", topics);
      req.setAttribute("forumPage", forumPage);
      req.setAttribute("loginPage", loginPage);
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

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String newHeadOfTopic = req.getParameter("head");
    String newBodyOfTopic = req.getParameter("comment");
    String userName = getUserName(req);
    Topic topic = new Topic(newHeadOfTopic, newBodyOfTopic, userName);
    topicDao.insertTopic(topic);
    resp.sendRedirect("/FORUM/forum");
  }
}