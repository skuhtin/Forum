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
import java.io.PrintWriter;
import java.util.List;

public class UserPageServlet extends HttpServlet {
  UsersDao usersDao = new UsersDao();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String userName = getUserName(req);
    String page = null;
    if (userName == null) {
      page = "/login";
    } else if (usersDao.getUserbyLogin(userName).isBan()) {
      page = "/ban";
    } else {
      page = "/WEB-INF/jsp/userPage.jsp";
      MessageDao messageDao = new MessageDao();
      messageDao.setReadMessage(userName);
      String loginPage = "/login";
      String forumPage = "/forum";
      List<Message> messages = messageDao.loadMessage(userName);
      req.setAttribute("loginPage", loginPage);
      req.setAttribute("forumPage", forumPage);
      req.setAttribute("messages", messages);
      req.setAttribute("userName", userName);
    }
    req.getRequestDispatcher(page).forward(req, resp);
  }

  private void viewPage(HttpServletResponse resp, String userName) throws IOException {
    //MessageDao messageDao = new MessageDao();
    // PrintWriter out = resp.getWriter();
    //messageDao.setReadMessage(userName);
    //List<Message> messages = messageDao.loadMessage(userName);
    // out.println("<html><body>");
    // out.print("<h1>" + userName + ", your private messages: </h1>");
    // out.print("<ul>");
    // for (Message message : messages) {
    //  out.print("<li>");
    // out.print("<b>" + message.getFromUser() + " wrote: </b>");
    //  out.print("<p>" + message.getText() + "<br>");
    // out.print("</li>");
    // }
    // out.print("</ul>");
    // out.println("<form action=\"/forum\" method=\"GET\">");
    // out.println("<p><input type=\"submit\" value=\"Back to main page\">");
    // out.println("</form>");
    // out.print("</body></html>");
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
