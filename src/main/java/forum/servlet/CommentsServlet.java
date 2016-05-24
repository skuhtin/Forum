package forum.servlet;

import forum.dao.CommentsDao;
import forum.dao.MessageDao;
import forum.dao.TopicDao;
import forum.dao.UsersDao;
import forum.model.Comment;
import forum.model.Topic;
import forum.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CommentsServlet extends HttpServlet {

  private TopicDao topicDao = new TopicDao();
  private CommentsDao commentsDao = new CommentsDao();
  private MessageDao messageDao = new MessageDao();
  private UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    String userName = getUserName(req);
    String page;
    int topicId = getId(req);
    if (userName == null){
      page = "/login";
    } else if (usersDao.getUserbyLogin(userName).isBan()) {
      page = "/ban";
    } else {
      page = "/WEB-INF/jsp/comments.jsp";
      String loginPage = "/login";
      String forumPage = "/forum";

      int countNewMessage = messageDao.getNewMessages(userName);
      Map<Integer, Topic> topics = topicDao.loadTopic();
      List<Comment> comments = commentsDao.loadComment(topicId);
      String link = "/message/";
      Topic topic = topics.get(topicId);
      //req.setAttribute("topicId", topicId);
      req.setAttribute("loginPage", loginPage);
      req.setAttribute("userName", userName);
      req.setAttribute("countNewMessage", countNewMessage);
      req.setAttribute("topic", topic);
      req.setAttribute("forumPage", forumPage);
      req.setAttribute("comments", comments);
      req.setAttribute("link", link);
    }
    req.getRequestDispatcher(page).forward(req,resp);
  }

  private int getId(HttpServletRequest req) {
    String topicId = req.getPathInfo();
    return Integer.parseInt(topicId.substring(1));
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String text = req.getParameter("comment");
    int topicId = getId(req);
    String userName = getUserName(req);
    commentsDao.insertComment(topicId, new Comment(text, userName));
    resp.sendRedirect("/forum/" + topicId);
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