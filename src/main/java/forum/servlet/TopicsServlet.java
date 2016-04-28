package forum.servlet;

import forum.dao.TopicDao;
import forum.model.Topic;

import java.io.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TopicsServlet extends HttpServlet{

  private TopicDao topicDao = new TopicDao();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    Map<Integer, Topic> topics = topicDao.loadTopic();
    viewTopicPage(resp, topics);
  }

  private void viewTopicPage(HttpServletResponse resp, Map<Integer, Topic> topics) throws IOException{

    PrintWriter out = resp.getWriter();

    out.print("<html><body>");
    out.print("<h1>List of topics</h1>");
    out.print("<ul>");
    for (Map.Entry<Integer, Topic> print : topics.entrySet()) {
      Topic topic = print.getValue();
      int id = print.getKey();
      out.print("<li>");
      out.print("<a href='/forum/" + id + "'>" + topic.getHead() + "</a>");
      out.print("</li>");
    }
    out.print("</ul>");

    out.println("<form action=\"/forum\" method=\"POST\">");
    out.println("<p><h3>Add new topic</h3>");
    out.print("<b>Head of topic:</b><br>");
    out.println("<textarea name=\"head\" cols=\"40\" rows=\"1\"></textarea><br>");
    out.print("<b>Text of topic:</b><br>");
    out.println("<textarea name=\"comment\" cols=\"40\" rows=\"3\"></textarea>");
    out.println("<p><input type=\"submit\" value=\"Send\">");
    out.println("<input type=\"reset\" value=\"Cancel\"></p>");
    out.println("</form>");
    out.println("</body></html>");

    out.close();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String newHeadOfTopic = req.getParameter("head");
    String newBodyOfTopic = req.getParameter("comment");

    topicDao.insertTopic(new Topic(newHeadOfTopic, newBodyOfTopic));
    Map<Integer, Topic> topics = topicDao.loadTopic();

    viewTopicPage(resp, topics);
    //resp.sendRedirect("http://127.0.0.1:8080/forum");
  }
}
