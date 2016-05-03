package forum.servlet;

import forum.dao.MessageDao;
import forum.dao.TopicDao;
import forum.model.Message;
import forum.model.Topic;
import forum.model.User;

import java.io.*;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class TopicsServlet extends HttpServlet{

  private TopicDao topicDao = new TopicDao();
  private MessageDao messageDao = new MessageDao();

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    //!!!!!resp.sendRedirect("login");
    Map<Integer, Topic> topics = topicDao.loadTopic();
    viewTopicPage(req, resp, topics);
  }

  private void viewTopicPage(HttpServletRequest req, HttpServletResponse resp, Map<Integer, Topic> topics) throws IOException{

    Cookie[] cookies = req.getCookies();
    try {
      String userName = cookies[1].getValue();
      PrintWriter out = resp.getWriter();
      out.print("<html><body>");
      //out.print("Hi, " + userName);
      out.print("<a href='/user/" + userName + "'>Hi, " + userName + "</a>");
      out.print(" (" + messageDao.getNewMessages(userName) + " new messages)");
      out.print("<h1>List of topics</h1>");
      out.print("<ul>");
      for (Map.Entry<Integer, Topic> print : topics.entrySet()) {
        Topic topic = print.getValue();
        int id = print.getKey();
        out.print("<li>");
        out.print("<b><a href='/forum/" + id + "'>" + topic.getHead() + "</a></b>" + " added by " + topic.getHandleUser());
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
    } catch (ArrayIndexOutOfBoundsException e) {
      resp.sendRedirect("/login");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String newHeadOfTopic = req.getParameter("head");
    String newBodyOfTopic = req.getParameter("comment");
    Cookie[] cookies = req.getCookies();
    String userName = cookies[1].getValue();
    Topic topic = new Topic(newHeadOfTopic, newBodyOfTopic, userName);
    topicDao.insertTopic(topic);
    Map<Integer, Topic> topics = topicDao.loadTopic();

    viewTopicPage(req, resp, topics);
  }
}
