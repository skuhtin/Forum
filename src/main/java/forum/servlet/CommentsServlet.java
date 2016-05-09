package forum.servlet;

import forum.dao.CommentsDao;
import forum.dao.MessageDao;
import forum.dao.TopicDao;
import forum.model.Comment;
import forum.model.Topic;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class CommentsServlet extends HttpServlet {

  private TopicDao topicDao = new TopicDao();
  private CommentsDao commentsDao = new CommentsDao();
  private MessageDao messageDao = new MessageDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    int id = getId(req);
    viewCommentsPage(req, resp, id);
  }

  private int getId(HttpServletRequest req) {
    String topicId = req.getPathInfo();
    return Integer.parseInt(topicId.substring(1));
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String text = req.getParameter("comment");
    int id = getId(req);
    String userName = getUserName(req, resp);
    commentsDao.insertComment(id, new Comment(text, userName));
    viewCommentsPage(req, resp, id);
  }

  private void viewCommentsPage(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
    String userName = getUserName(req, resp);
    Map<Integer, Topic> topics = topicDao.loadTopic();
    List<Comment> comments = commentsDao.loadComment(id);
    Topic topic = topics.get(id);
    PrintWriter out = resp.getWriter();
    out.println("<html><body>");
    out.print("<a href='/user/" + userName + "'>Hi, " + userName + "</a>");
    out.print(" (" + messageDao.getNewMessages(userName) + " new messages)");
    out.print(" " + "<a href='/login'>LogOut</a>");
    out.print("<h1>Read and discuss topic</h1>");
    out.print("<h3>" + topic.getHead() + "</h3>");
    out.print(topic.getText() + "<br>");
    out.println("<form action=\"/forum\" method=\"GET\">");
    out.println("<p><input type=\"submit\" value=\"Back\">");
    out.print("</form>");
    out.print("<p><b>Comments:</b><br>");
    for (Comment comment : comments) {
      out.print("<p><b>" + comment.getUserHandler() + " said:</b>");
      out.print("(" + "<a href='/message/" + id + "/" + comment.getUserHandler() + "'>Send msg</a>" + ")<br>");
      out.print(comment.getText());
    }
    sendComment(id, out);
    out.print("</body></html>");
    out.close();

  }

  private void sendComment(int id, PrintWriter out) {
    out.print("<form action=\"/forum/" + id + "\" method=\"post\">");
    out.print("<p><b>Add your comment</b><br>");
    out.print("<textarea name=\"comment\" cols=\"40\" rows=\"3\"></textarea></p>");
    out.print("<p><input type=\"submit\" value=\"Send\">");
    out.print("<input type=\"reset\" value=\"Cancel\"></p>");
    out.print("</form>");
  }

  private String getUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String userName = null;
    try {
      Cookie[] cookies = req.getCookies();
      userName = cookies[0].getValue();
    } catch (NullPointerException e) {
      resp.sendRedirect("/login");
    }
    return userName;
  }

}