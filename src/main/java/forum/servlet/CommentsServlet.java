package forum.servlet;

import forum.dao.CommentsDao;
import forum.dao.TopicDao;
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

    commentsDao.insertComment(id, text);
    viewCommentsPage(req, resp, id);
  }

  private void viewCommentsPage(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
    Cookie[] cookies = req.getCookies();
    try {
      String userName = cookies[1].getValue();
      Map<Integer, Topic> topics = topicDao.loadTopic();
      List<String> comments = commentsDao.loadComment(id);
      Topic topic = topics.get(id);
      PrintWriter out = resp.getWriter();
      out.println("<html><body>");
      out.print("Hi, " + userName);
      out.print("<h1>Read and discuss topic</h1>");
      out.print("<h3>" + topic.getHead() + "</h3>");
      out.print(topic.getText() + "<br>");
      out.println("<form action=\"/forum\" method=\"GET\">");
      out.println("<p><input type=\"submit\" value=\"Back\">");
      out.print("</form>");
      out.print("<p><b>Comments:</b><br>");
      for (String outComment : comments) {
        out.print("<p>" + outComment);
      }
      out.print("<form action=\"/forum/" + id + "\" method=\"post\">");
      out.print("<p><b>Add your comment</b><br>");
      out.print("<textarea name=\"comment\" cols=\"40\" rows=\"3\"></textarea></p>");
      out.print("<p><input type=\"submit\" value=\"Send\">");
      out.print("<input type=\"reset\" value=\"Cancel\"></p>");
      out.print("</form></body></html>");

      out.close();
    }catch(ArrayIndexOutOfBoundsException e) {
      resp.sendRedirect("/login");
    }
  }
}
