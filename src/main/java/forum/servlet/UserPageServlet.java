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
import java.util.List;

public class UserPageServlet extends HttpServlet{

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      String userName = getUserName(req, resp);
      MessageDao messageDao = new MessageDao();
      PrintWriter out = resp.getWriter();
      messageDao.setReadMessage(userName);
      List<Message> messages = messageDao.loadMessage(userName);
      out.println("<html><body>");
      out.print("<h1>" + userName + ", your private messages: </h1>");
      out.print("<ul>");
      for (Message message : messages) {
        out.print("<li>");
        out.print("<b>" + message.getFromUser() + " wrote: </b>");
        out.print("<p>" + message.getText() + "<br>");
        out.print("</li>");
      }
      out.print("</ul>");
      out.println("<form action=\"/forum\" method=\"GET\">");
      out.println("<p><input type=\"submit\" value=\"Back to main page\">");
      out.println("</form>");
      out.print("</body></html>");

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
