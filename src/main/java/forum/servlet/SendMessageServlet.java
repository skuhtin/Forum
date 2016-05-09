package forum.servlet;

import forum.dao.MessageDao;
import forum.model.Message;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SendMessageServlet extends HttpServlet{
  MessageDao messageDao = new MessageDao();


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    viewPage(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    MessageDao messageDao = new MessageDao();
    String text = req.getParameter("message");
    String userName = getUserName(req,resp);
    Message message = new Message(text);
    message.setFromUser(userName);
    message.setToUser(getUserForSend(req));
    messageDao.insertMessage(message);
    PrintWriter out = resp.getWriter();
    out.print("<html><body>");
    out.print("<h2>Message has been sent successfully</h2>");
    out.print("<form action=\"/forum/" + getTopicId(req) + "\" method=\"GET\">");
    out.print("<p><input type=\"submit\" value=\"Back\">");
    out.print("</form>");
    out.close();

  }

  private String getTopicId(HttpServletRequest req) {
    String[] parameters = req.getPathInfo().substring(1).split("/");
    return parameters[0];
  }

  private String getUserForSend(HttpServletRequest req) {
    String[] parameters = req.getPathInfo().substring(1).split("/");
    return parameters[1];
  }

  private void viewPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
      String userName = getUserName(req, resp);
      PrintWriter out = resp.getWriter();
      out.print("<html><body>");
      out.print("<a href='/user/" + userName + "'>Hi, " + userName + "</a>");
      out.print(" (" + messageDao.getNewMessages(userName) + " new messages)");
      out.print(" " + "<a href='/login'>LogOut</a>");
      out.print("<h3>Send private message to " + getUserForSend(req) + "</h3>");
      out.print("<form action=\"/message/" + getTopicId(req) + "/" + getUserForSend(req) + "\" method=\"POST\">");
      out.print("<textarea name=\"message\" cols=\"40\" rows=\"3\"></textarea>");
      out.print("<p><input type=\"submit\" value=\"Send\">");
      out.print("<input type=\"reset\" value=\"Cancel\"></p>");
      out.print("</form></body></html>");
      out.close();
  }

  private String getUserName(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String userName = null;
    try {
      Cookie[] cookies = req.getCookies();
      userName = cookies[0].getValue();
    } catch (NullPointerException e) {
      resp.sendRedirect("/login");
    }
    return userName;  }


}
