package forum.servlet;

import forum.dao.MessageDao;
import forum.dao.TopicDao;
import forum.model.Topic;

import java.io.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TopicsServlet extends HttpServlet {

  private TopicDao topicDao = new TopicDao();
  private MessageDao messageDao = new MessageDao();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    String userName = getUserName(req);
    String page;
    String forumPage = "/forum";
    String loginPage = "/login";
    if (userName == null) {
      page = "/login";
    } else {
      page = "/WEB-INF/jsp/topic.jsp";
      int countNewMessage = messageDao.getNewMessages(userName);
      Map<Integer, Topic> topics = topicDao.loadTopic();
      req.setAttribute("userName", userName);
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
    String newHeadOfTopic = req.getParameter("head");
    String newBodyOfTopic = req.getParameter("comment");
    String userName = getUserName(req);
    Topic topic = new Topic(newHeadOfTopic, newBodyOfTopic, userName);
    topicDao.insertTopic(topic);
    resp.sendRedirect("/forum");
  }
}